# -*- coding: utf-8 -*-
"""Content_based_Filtering_Model_Final_Retraining.ipynb

Automatically generated by Colab.

# Content-based Filtering Model Final
"""

import csv
import random
import tensorflow as tf

"""Create a list of brand choice for one hot encoding."""

user_dict = {}
brand_choice_list = ['Oppo', 'Asus', 'Infinix', 'Samsung', 'Vivo', 'Huawei', 'Apple', 'Realme', 'Xiaomi', 'Poco', 'lainnya/tidak ada']

"""Create user_list"""

user_row = 1507
user_column = 7
user_list = [[0 for _ in range(user_column)] for _ in range(user_row)]

"""Access user dataset from csv file."""

with open('user_surveys.csv', 'r') as file:
  user_dict['user'] = []
  user_dict['user_fav'] = []
  user_index = []
  csvFile = csv.reader(file)
  next(csvFile)
  for line in csvFile:
    user_index.append(int(line[0])-1)
    user_dict[f'user'].append([int(line[1]), int(line[2]), int(line[3]), int(line[4]), int(line[5]), int(line[6])])
    user_dict[f'user_fav'].append(int(line[8]))

"""Convert user features to tensors and one hot encode one of user features, and then concatenate them."""

user_feature1_6 = tf.cast(tf.convert_to_tensor(user_dict['user']), tf.float32)
user_feature7 = tf.one_hot(user_dict['user_fav'], len(brand_choice_list))
user = tf.concat([user_feature1_6, user_feature7], 1)

"""User features are performance, camera, battery, software, RAM, storage, and favorite brand."""

"""Create a brand list and os list for one hot encoding."""

phone_dict = {}
brand_list = ['Oppo', 'Asus', 'Infinix', 'Samsung', 'Vivo', 'Huawei', 'Apple', 'Realme', 'Xiaomi', 'Poco']
os_list = ['iOS', 'EMUI', 'Android']
chipset_list = ['unisoc', 'helio','dimensity_6', 'dimensity_7', 'dimensity_8', 'dimensity_9', 'snapdragon_6', 'snapdragon_7', 'snapdragon_8', 'exynos_1', 'exynos_2', 'kirin', 'apple']
price_list = ['_2', '2_3', '3_4', '4_6', '6_8', '8_10', '10_']

"""Access phone dataset from csv file.<br>
Preprocess the data,  one hot encode some of the features, normalize some of the features.<br>
Then concatenate all the phone data.
"""

with open('phone_dataset.csv', 'r') as file:
  phone_dict = {'phone_brand': [], 'phone_name': [], 'phone_weight': [], 'phone_os': [], 'phone_chipset': [], 'phone_memory': [], 'phone_ram': [], 'phone_camera': [], 'phone_audio_jack': [], 'phone_battery': [], 'phone_charging': [], 'phone_nfc': [], 'phone_price': []}
  csvFile = csv.reader(file)
  next(csvFile)
  for i,line in enumerate(csvFile):
    phone_dict['phone_brand'].append(brand_list.index(line[1]))
    phone_dict['phone_name'].append(line[2])
    phone_dict['phone_weight'].append(float(line[6]))
    phone_dict['phone_os'].append(os_list.index('iOS' if line[7].startswith('iOS') else 'EMUI' if line[7].startswith('EMUI') else 'Android'))
    phone_dict['phone_chipset'].append(chipset_list.index('unisoc' if 'unisoc' in line[8].lower()
                                                          else 'helio' if 'helio' in line[8].lower()
                                                          else 'dimensity_6' if 'dimensity 6' in line[8].lower()
                                                          else 'dimensity_7' if 'dimensity 7' in line[8].lower()
                                                          else 'dimensity_8' if 'dimensity 8' in line[8].lower()
                                                          else 'dimensity_9' if 'dimensity 9' in line[8].lower()
                                                          else 'snapdragon_6' if 'snapdragon 6' in line[8].lower()
                                                          else 'snapdragon_7' if 'snapdragon 7' in line[8].lower()
                                                          else 'snapdragon_8' if 'snapdragon 8' in line[8].lower()
                                                          else 'exynos_1' if 'exynos 1' in line[8].lower()
                                                          else 'exynos_2' if 'exynos 2' in line[8].lower()
                                                          else 'kirin' if 'kirin' in line[8].lower()
                                                          else 'apple' if 'apple' in line[8].lower() else ''))
    phone_dict['phone_memory'].append(float(line[9]))
    phone_dict['phone_ram'].append(float(line[10]))
    phone_dict['phone_camera'].append([float(line[11]), float(line[12]), float(line[13]), float(line[14])])
    phone_dict['phone_audio_jack'].append(1.0 if line[15] == 'TRUE' else 0.0)
    phone_dict['phone_battery'].append(float(line[16]))
    phone_dict['phone_charging'].append(float(line[17]))
    phone_dict['phone_nfc'].append(1.0 if line[19] == 'TRUE' else 0.0)
    phone_dict['phone_price'].append(price_list.index('_2' if int(line[20]) < 2000000
                                                      else '2_3' if int(line[20]) < 3000000
                                                      else '3_4' if int(line[20]) < 4000000
                                                      else '4_6' if int(line[20]) < 6000000
                                                      else '6_8' if int(line[20]) < 8000000
                                                      else '8_10' if int(line[20]) < 10000000
                                                      else '10_'))

phone_brand = tf.one_hot(phone_dict['phone_brand'], len(brand_list))
phone_weight = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_weight']) / max(phone_dict['phone_weight']), 1)
phone_os = tf.one_hot(phone_dict['phone_os'], len(os_list))
phone_chipset = tf.one_hot(phone_dict['phone_chipset'], len(chipset_list))
phone_memory = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_memory']), 1)  / max(phone_dict['phone_memory'])
phone_ram = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_ram']), 1) / max(phone_dict['phone_ram'])
phone_camera = tf.convert_to_tensor(phone_dict['phone_camera'])
phone_audio_jack = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_audio_jack']), 1)
phone_battery = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_battery']), 1) / max(phone_dict['phone_battery'])
phone_charging = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_charging']), 1)
phone_nfc = tf.expand_dims(tf.convert_to_tensor(phone_dict['phone_nfc']), 1)
phone_price = tf.one_hot(phone_dict['phone_price'], len(price_list))

phone = tf.concat([phone_brand, phone_weight, phone_os, phone_chipset, phone_memory, phone_ram, phone_camera, phone_audio_jack, phone_battery, phone_charging, phone_nfc, phone_price], 1)

"""Phone features are brand, weight, os, chipset, memory, RAM, camera 1, camera 2, camera 3, selfie camera, audio jack, battery, charging, nfc, and price."""

"""Create a rating dictionary and a rating list."""

rating_dict = {}

rating = [[0.0 for _ in range(1507)] for _ in range(96)]

"""Access rating dataset from csv file and only save the ratings from the users with information."""

with open('user_ratings.csv', 'r') as file:
  csvFile = csv.reader(file)
  next(csvFile)
  for line in csvFile:
    if (int(line[0])-1) in user_index and line[2]:
      rating[int(line[1])-1][int(line[0])-1] = float(line[2])

phone_data = []
user_data = []
rating_data = []

"""Save the phone data and user data for training only for the rated phone."""

for i,p in enumerate(phone):
  for j,u in zip(user_index, user):
    if rating[i][j] > 0:
      phone_data.append(phone[i])
      user_data.append(user[user_index.index(j)])
      rating_data.append(rating[i][j])

"""Convert phone data, user data, and rating data to tensors and shuffle them."""

combined_dataset = list(zip(phone_data, user_data, rating_data))
random.shuffle(combined_dataset)
phone_dataset, user_dataset, rating_dataset = zip(*combined_dataset)
phone_dataset, user_dataset, rating_dataset = list(phone_dataset), list(user_dataset), list(rating_dataset)

phone_dataset = tf.convert_to_tensor(phone_dataset)
user_dataset = tf.convert_to_tensor(user_dataset)
rating_dataset = tf.convert_to_tensor(rating_dataset)

"""Distribute data into training and validation set."""

train_phone = phone_dataset[int(len(phone_dataset)/4):]
train_user = user_dataset[int(len(user_dataset)/4):]
train_rating = rating_dataset[int(len(rating_dataset)/4):]

val_phone = phone_dataset[:int(len(phone_dataset)/4)]
val_user = user_dataset[:int(len(user_dataset)/4)]
val_rating = rating_dataset[:int(len(rating_dataset)/4)]

"""Create a model with two inputs for user data and phone data, the output is the dot product of the two vectors from user_NN and phone_NN which is the predicted rating."""

# model = tf.keras.models.load_model('model.keras', safe_mode=False)
model = tf.keras.models.load_model('model.h5', safe_mode=False)

model.summary()

user_model = tf.keras.models.Model(inputs=model.layers[0].input, outputs=model.layers[4].output)

user_model.summary()

phone_model = tf.keras.models.Model(inputs=model.layers[1].input, outputs=model.layers[5].output)

phone_model.summary()

model.compile(optimizer=tf.keras.optimizers.Adam(learning_rate=0.01), loss='mse')

"""Train the model."""

model.fit([train_user, train_phone], train_rating, epochs=30, validation_data=([val_user, val_phone], val_rating))

model.evaluate([val_user, val_phone], val_rating)

"""Save model in keras format."""

# model.save('model.keras')
#
# user_model.save('user_model.keras')
#
# phone_model.save('phone_model.keras')

"""Save model in h5 format."""

model.save('model.h5')

user_model.save('user_model.h5')

phone_model.save('phone_model.h5')

"""Predict rating."""

model.predict([tf.convert_to_tensor([user_data[0]]), tf.convert_to_tensor([phone_data[0]])])

"""Predict rating by dot product of user vector and phone vector."""

user_vector_pred = user_model.predict(tf.expand_dims(user_data[0], 0))
phone_vector_pred = phone_model.predict(tf.expand_dims(phone_data[0], 0))
tf.tensordot(user_vector_pred, phone_vector_pred, axes=(1,1)) * 5

"""Create phone vectors."""

p_vector = phone_model.predict(phone)

"""Save phone vectors into csv file."""

with open('phone_vector.csv', 'w') as file:
  csvwriter = csv.writer(file)
  csvwriter.writerows(p_vector)

"""Calculate square distance between phone vectors to get related phones."""

top_distance = []
top_distance_name = []
for i,x in enumerate(p_vector):
  all_distance = []
  phone_pred_list = phone_dict['phone_name']
  for j,y in enumerate(p_vector):
    if tf.math.reduce_all(tf.math.equal(x,y)):
      continue
    else:
      all_distance.append(tf.math.reduce_sum(tf.math.square(x-y)))
  phone_pred_list = phone_pred_list[:i] + phone_pred_list[i+1:]
  combined_dist = list(zip(all_distance, phone_pred_list))
  combined_dist.sort()
  all_distance, phone_pred_list = zip(*combined_dist)
  all_distance, phone_pred_list = list(all_distance), list(phone_pred_list)
  top_distance.append(all_distance[:10])
  top_distance_name.append(phone_pred_list[:10])

"""Save related phones data into csv file."""

with open('related_phone.csv', 'w') as file:
  csvwriter = csv.writer(file)
  csvwriter.writerow(['phone_name', 'related1', 'related2', 'related3', 'related4', 'related5', 'related6', 'related7', 'related8', 'related9', 'related10'])
  for i in range(96):
    csvwriter.writerow([phone_dict['phone_name'][i], top_distance_name[i][0], top_distance_name[i][1], top_distance_name[i][2], top_distance_name[i][3], top_distance_name[i][4], top_distance_name[i][5], top_distance_name[i][6], top_distance_name[i][7], top_distance_name[i][8], top_distance_name[i][9]])