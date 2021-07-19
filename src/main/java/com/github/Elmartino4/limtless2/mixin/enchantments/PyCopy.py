bull = True
lst = []
while bull:
    val = input("Enter enchant: ")
    if val == "exit":
        bull = False
        print(*lst, sep = ", ")

    print(val)
    if val != "exit":
        lst += val
        lvl = input("Enter level: ")

        input_file = open("ExampleMixin.java", "r")
        output_file = open(val + "EnchantmentMixin.java", "w")

        for line in input_file:
            output_file.write((line.replace("___", val)).replace("...", lvl))
