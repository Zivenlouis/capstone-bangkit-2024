import pandas as pd
import numpy as np
import tensorflow as tf
from tensorflow.keras.models import model_from_json

#Init
df_phone_dataset = pd.read_csv('phone_dataset.csv')
df_user_clicks = pd.read_csv('user_clicks_1_brand.csv')
df_user_ratings = pd.read_csv('user_ratings_1_brand.csv')
PHONE_COUNT = len(df_phone_dataset)
MIN_USER_ID = min(df_user_clicks['user_id'].min(), df_user_ratings['user_id'].min())
MAX_USER_ID = max(df_user_clicks['user_id'].max(), df_user_ratings['user_id'].max())
TOTAL_USER = MAX_USER_ID - MIN_USER_ID + 1
TAKE_RATING_PER_USER = 20
TAKE_CLICK_PER_USER = 20



#Preprocess user_click
df_user_clicks = df_user_clicks.sort_values(by='visit_time', ascending=False).drop_duplicates(subset=['user_id', 'phone_id'], keep='first').groupby('user_id').head(TAKE_CLICK_PER_USER).reset_index(drop=True)
missing_user_ids = set(range(MIN_USER_ID, MAX_USER_ID + 1)) - set(df_user_clicks['user_id'])
missing_data = pd.DataFrame({'user_id': list(missing_user_ids), 'phone_id': -1, 'visit_time': '2024-01-01 00:00:00'})
df_user_clicks_complete = pd.concat([df_user_clicks, missing_data], ignore_index=True).sort_values(by='user_id').reset_index(drop=True)
train_clicks = df_user_clicks_complete[['user_id', 'phone_id']].pivot_table(index='phone_id', columns='user_id', aggfunc=lambda x: 1 if len(x) > 0 else 0, fill_value=0)
if len(missing_user_ids) > 0:
    train_clicks = train_clicks[1:]

#Preprocess user_rating    
df_user_ratings = df_user_ratings.sort_values(by='rate_time', ascending=False).drop_duplicates(subset=['user_id', 'phone_id'], keep='first').groupby('user_id').head(TAKE_RATING_PER_USER).reset_index(drop=True)
missing_user_ids = set(range(MIN_USER_ID, MAX_USER_ID + 1)) - set(df_user_ratings['user_id'])
missing_data = pd.DataFrame({'user_id': list(missing_user_ids), 'phone_id': -1, 'rating': 0})
df_user_ratings_complete = pd.concat([df_user_ratings, missing_data], ignore_index=True).sort_values(by='user_id').reset_index(drop=True)
train_ratings =  df_user_ratings_complete.pivot(index='phone_id', columns='user_id', values='rating').fillna(0)
if len(missing_user_ids) > 0:
    train_ratings = train_ratings[1:]
    
def combine_collaborative_model(num_features):
    click_input = tf.keras.Input(shape=(PHONE_COUNT,), dtype=tf.int32, name='click_input')
    rating_input = tf.keras.Input(shape=(PHONE_COUNT,), dtype=tf.int32, name='rating_input')
    X = tf.keras.layers.Concatenate(name='concatenated_inputs')([rating_input, click_input])
    X = tf.keras.layers.Dense(units=num_features, activation=None, kernel_initializer='random_normal', name='X')(X)
    # output_rating = tf.keras.layers.Dense(PHONE_COUNT, activation='softmax', name='output_rating')(X)
    output_click = tf.keras.layers.Dense(PHONE_COUNT, activation='sigmoid', name='output')(X)
    model = tf.keras.Model(inputs=[rating_input, click_input], outputs=output_click, name='collaborative_model')
    return model

model = combine_collaborative_model(128)
model.compile(optimizer='adam', loss=tf.keras.losses.MeanSquaredError())
model_json = model.to_json()
with open("model.json", "w") as json_file:
    json_file.write(model_json)
click_input = train_clicks.to_numpy().T
train_ratings = train_ratings/5
rating_input = train_ratings.to_numpy().T
model.fit([rating_input, click_input], [rating_input, click_input], epochs=30, batch_size=32)
model.save('collaborative_filtering.h5')
