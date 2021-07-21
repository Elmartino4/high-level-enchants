import csv

lst = []
with open('Enchants.csv', newline='') as csvfile:
    spamreader = csv.reader(csvfile, delimiter=',', quotechar='|')
    for row in spamreader:
        print(row)
        val = row[0]

        print(val)
        lst += val
        lvl = row[1]

        input_file = open("ExampleMixin.java", "r")
        output_file = open(val + "Mixin.java", "w")

        for line in input_file:
            output_file.write((line.replace("___", val)).replace("...", lvl))
