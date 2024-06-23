# List all classes and methods in this folder.
import os

os.system("javap *.class > listing.txt")
with open("listing.txt", "r") as file:
    lines = file.readlines()
    for line in lines:
        if "class" in line: # print class
            print(f"Class: {line.split()[2]}")
            method_count = 1
        elif ");" in line: # print method
            print(line.split("(")[0].split()[-1])
            method_count = method_count + 1