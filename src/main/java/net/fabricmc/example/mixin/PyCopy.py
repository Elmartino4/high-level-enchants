while True:
    val = input("Enter enchant: ")
    print(val)

    input_file = open("_EnchantmentMixin.java", "r")
    output_file = open(val + "EnchantmentMixin.java", "w")
    br = 0
    ot = 0

    for line in input_file:
        output_file.write(line.replace("___", val))
