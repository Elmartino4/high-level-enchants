import csv

lst = []
with open('Enchants.csv', newline='') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
    for row in spamreader:
        val = row[0]
        print("\"" + val + "Mixin\",")
