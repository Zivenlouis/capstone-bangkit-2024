# Auxillium - Your Personal Mobile Phone Recommendation App
 ## Bangkit Capstone Project 2024 Batch 1
 Bangkit Capstone Team ID : C241 - PS060
 ### Our Team
| No | Name | Bangkit-ID | Learning Path |
|:--:|:------------------|:---------------|:----------------------|
|1   | Filbert Wijaya    | M319D4KY3095   | Machine Learning      |
|2   | Jason Tjoa        | M293D4KY2440   | Machine Learning      |
|3   | Ziven Louis       | M293D4KY2441   | Machine Learning      |
|4   | Nur Aisyah Aswari | C528D4KX0454   | Cloud Computing       |
|5   | Abednegeo Sirait  | C528D4KY0534   | Cloud Computing       |
|6   | Harry Sion Tarigan| A319D4KY4102   | Mobile Development    |
|7   | Pieter Tanoto     | A319D4KY4242   | Mobile Development    |

## Table of Contents
- [Introduction](#introduction)
- [Download APK Link](#download-apk-link)
- [Tech Used](#tech-used)
- [Steps to Replicate](#steps-to-replicate)
- [Dependencies](#dependencies)


## Introduction
Auxilium is a mobile phone recommendation application that offers tailored suggestions based on specifications and ratings. Users complete a preference survey regarding general specifications, enabling the application to generate personalized recommendations. Additionally, Auxillium employs collaborative filtering methods to suggest new phones, allowing users to explore diverse options. The app also features a community page where users can seek advice, share experiences, and read authentic reviews, fostering a supportive environment for informed decision-making. This comprehensive approach ensures users receive suggestions tailored to their preferences, enhancing satisfaction with their purchases.

## Download APK Link
[Download Auxilium APK Link](https://github.com/Zivenlouis/capstone-bangkit-2024/releases/download/beta-version/auxilium.apk)

## Tech Used
Auxilium app is developed with:
1. Express.js
2. Tensorflow
3. Android Studio
4. FlaskAPI
5. Google Cloud Platform (MySQL, Storage Bucket, Compute Engine)
6. Postman

## Steps to Replicate
To replicate the Auxillium Mobile App, we can follow this steps:
### Clone This Project
``` 
git clone https://github.com/Zivenlouis/capstone-bangkit-2024.git
```
1. Re-Train the model with new dataset by running collaborative_filtering_train.py and content_based_filtering_model_final_training.py script.(optional)
2. Move the .h5 model data to CC folder that contain flaskAPI
3. Set Up Flask API
4. Configure Backend with SQL Server on GCP
    - IP       : 34.101.58.1xx
    - User     : root
    - Password : xxxxxx
5. Endpoint Integration



## Dependencies
| No | Dependency | Purpose |
|:--:|:------------------------|:------------------------------------------------------------|
| 1  | pandas                  | Data manipulation and analysis.                             |
| 2  | numpy                   | Numerical computing with arrays and matrices.               |
| 3  | tensorflow              | Machine learning framework for building models.             |
| 4  | express                 | Node.js framework is for building web applications and APIs.|
| 5  | google-cloud/storage    | Clients to access Google Cloud Storage from the Node.js app.|
| 6  | bcrypt                  | Library for secure password hashing.                        |
| 7  | cors                    | Middleware to configure Cross-Origin Resource Sharing.      |
| 8  | dotenv                  | Library to load environmental variables from .env files.    |
| 9  | helmet                  | Middleware to improve web application security.             |
| 10 | jsonwebtoken            | Library to create and verify JSON Web Tokens (JWT).         |
| 11 | morgan                  | Middleware to log HTTP request logs in Express.             |
| 12 | multer                  | Middleware to handle file uploads in Express applications.  |
| 13 | mysql2                  | Library for connecting Node.js with MySQL database.         |
| 14 | node-cron               | Library to schedule tasks (cron jobs).                      |
| 15 | sequelize               | ORM to manage SQL databases in Node.js application.         |
| 16 | `androidx.core:core-ktx:1.13.1`                           | Core Android dependencies                                |
| 17 | `androidx.appcompat:appcompat:1.7.0`                      | Provides backward-compatible versions of Android UI components |
| 18 | `androidx.annotation:annotation:1.8.0`                    | Support for annotations                                  |
| 19 | `androidx.constraintlayout:constraintlayout:2.1.4`        | Layout manager for creating complex layouts              |
| 20 | `com.google.android.material:material:1.12.0`             | Material design components for Android                   |
| 21 | `androidx.recyclerview:recyclerview:1.3.2`                | Support for RecyclerView widget                          |
| 22 | `com.facebook.shimmer:shimmer:0.5.0`                      | Shimmer effect for loading placeholders                  |
| 23 | `androidx.lifecycle:lifecycle-livedata-ktx:2.8.1`         | Lifecycle-aware LiveData components                      |
| 24 | `androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.1`        | Lifecycle-aware ViewModel components                     |
| 25 | `androidx.lifecycle:lifecycle-runtime-ktx:2.8.1`          | Lifecycle-aware runtime components                       |
| 26 | `androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.1` | ViewModel SavedState library for persistent state management |
| 27 | `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0`  | Support for Kotlin coroutines on Android                 |
| 28 | `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0`     | Core library for Kotlin coroutines                       |
| 29 | `com.squareup.retrofit2:retrofit:2.11.0`                  | Type-safe HTTP client for Android                        |
| 30 | `com.squareup.retrofit2:converter-gson:2.11.0`            | Gson converter for Retrofit                              |
| 31 | `com.squareup.okhttp3:okhttp:5.0.0-alpha.14`              | HTTP & HTTP/2 client for Android                         |
| 32 | `com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14` | Logging interceptor for OkHttp                           |
| 33 | `com.github.bumptech.glide:glide:4.16.0`                  | Image loading and caching library                        |
| 34 | `com.google.firebase:firebase-firestore-ktx:25.0.0`       | Firebase Firestore support for Android                   |
| 35 | `androidx.paging:paging-common-ktx:3.3.0`                 | Common paging library components                         |
| 36 | `androidx.paging:paging-runtime-ktx:3.3.0`                | Runtime paging library components                        |
| 37 | `androidx.room:room-paging:2.6.1`                         | Paging support for Room                                  |
| 38 | `androidx.datastore:datastore-preferences:1.1.1`          | DataStore preferences library                            |
| 39 | `androidx.work:work-runtime-ktx:2.9.0`                    | WorkManager library for Android                          |
| 40 | `androidx.exifinterface:exifinterface:1.3.7`              | EXIF interface for handling image metadata               |
| 41 | `de.hdodenhof:circleimageview:3.1.0`                      | CircleImageView library for circular images              |
| 42 | `androidx.navigation:navigation-fragment-ktx:2.7.7`       | Navigation component for fragments                       |
| 43 | `androidx.navigation:navigation-ui-ktx:2.7.7`             | Navigation component for UI                              |
