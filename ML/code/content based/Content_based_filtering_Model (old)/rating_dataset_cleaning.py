import csv

user_list = []
phone_list = []
rating_list = []
with open('rating_dataset.csv', 'r') as file:
    csvFile = csv.reader(file)
    next(csvFile)
    for line in csvFile:
        user_list.append(int(line[0]))
        phone_list.append(int(line[1]))
        rating_list.append(int(line[2]))

cleaned = [[0 for _ in range(96)] for _ in range(1500)]

for i in range(len(user_list)):
    cleaned[user_list[i]-1][phone_list[i]-1] = rating_list[i]

with open('rating_cleaned.csv', 'w') as file:
    csvwriter = csv.writer(file)
    csvwriter.writerows(cleaned)