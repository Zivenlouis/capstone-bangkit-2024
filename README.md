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
https://www.google.com

## Tech Used
Auxilium app is developed with:
1. Express.js
2. Tensorflow
3. Android Studio
4. FlaskAPI
5. Google Cloud Platform (MySQL, Storage Bucket, Compute Engine)

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
