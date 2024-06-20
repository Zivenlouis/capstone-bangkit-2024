# -*- coding: utf-8 -*-
"""Content-based Filtering Model Final Inference.ipynb

Automatically generated by Colab.

# Content-based Filtering Model Inference
"""

import csv
import pandas as pd
import tensorflow as tf

brand_choice_list = ['Oppo', 'Asus', 'Infinix', 'Samsung', 'Vivo', 'Huawei', 'Apple', 'Realme', 'Xiaomi', 'Poco', 'lainnya/tidak ada']

# user_model = tf.keras.models.load_model('user_model.keras')
user_model = tf.keras.models.load_model('user_model.h5')

"""Preprocess data into input compatible with the model."""

def preprocess_user(x):
  one_hot = x[-1]
  one_hot = tf.one_hot(brand_choice_list.index(one_hot), len(brand_choice_list))
  x = tf.cast(tf.convert_to_tensor(x[:-1]), tf.float32)
  return tf.expand_dims(tf.concat([x, one_hot], 0), 0)

a = [5,5,5,5,5,5,'Apple'] # performance, camera, battery, software, RAM, storage, favorite brand
preprocessed_output = preprocess_user(a)

u_vector = user_model.predict(preprocessed_output)

p_vector = pd.read_csv('phone_vector.csv', header=None)

p_vector = tf.convert_to_tensor(p_vector)
p_vector = tf.cast(p_vector, tf.float32)

"""Predict phone ratings for a user."""

tf.tensordot(u_vector, p_vector, axes=(1,1)) * 5

rating_pred_list = []
for p in p_vector:
  rating_pred_list.append(tf.tensordot(u_vector, tf.expand_dims(p, 0), axes=(1,1)) * 5)

"""Get phone name."""

phone_pred_list = pd.read_csv('phone_dataset.csv')

phone_pred_list = phone_pred_list['name'].tolist()

"""Sort ratings to get top 10 recommended phones for a user."""

top_10_phone = list(zip(rating_pred_list, phone_pred_list))
top_10_phone.sort(reverse=True)
rating_pred_list, phone_pred_list = zip(*top_10_phone)
rating_pred_list, phone_pred_list = list(rating_pred_list), list(phone_pred_list)

top_10_list = []
for i in range(10):
  top_10_list.append(phone_pred_list[i])
  print(i+1, phone_pred_list[i], rating_pred_list[i].numpy())