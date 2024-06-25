# List all classes and methods in this folder.
import os

os.system("javap -private *.class > listing.txt")
with open("listing.txt", "r") as file:
    lines = file.readlines()
    for line in lines:
        if "class" in line: # print class
            print(f"Class: {line.split()[2]}")
            method_count = 1
        elif ");" in line: # print method
            access = line.split()[0]
            name = line.split("(")[0].split()[-1]
            if access == "public":
                print("+ ", end="")
            elif access == "private":
                print("- ", end="")
            print(name + "(): ", end="")
            if len(line.split()) > 1:
                return_type = line.split()[1]
                if return_type == "static":
                    return_type = line.split()[2]
                print(return_type)
            else:
                print()
            method_count = method_count + 1
# Yes I messed up the code because of the UML
# - wafl