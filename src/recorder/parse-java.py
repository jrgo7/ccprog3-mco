# List all classes and methods in this folder.
import os
import re

# All classes
# os.system("javap -private src/controller/gui/*.class src/model/*.class src/view/gui/component/*.class src/view/gui/delegate/*.class src/view/gui/panel/*.class src/view/gui/subpanel/*.class src/view/gui/*.class > listing.txt")

# Model only
os.system("javap -private src/model/*.class > listing.txt")

with open("listing.txt", "r") as file:
    lines = file.readlines()
    for line in lines:
        if "class" in line: # print class
            print(f"\nClass: {line.split()[-2]}")
            method_count = 1
        elif ");" in line: # print method
            access = line.split()[0]
            linesplit = re.split(r'(?<!\,)\s+', line)
            name = linesplit[-2]
            name = name.replace("java.lang.", "").replace("java.util.", "").replace("src.model.", "")
            if access == "public":
                print("+ ", end="")
            elif access == "private":
                print("- ", end="")
            print(name[:-1], end="")
            if len(linesplit) > 4:
                print(": ", end="")
                return_type = linesplit[2]
                return_type = return_type.replace("java.lang.", "").replace("java.util.", "").replace("src.model.", "")
                if return_type == "static":
                    return_type = line.split()[2].replace("java.lang.", "").replace("java.util.", "").replace("src.model.", "")
                    # God - lowest
                print(return_type)
            else:
                print()
            method_count = method_count + 1
# Yes I messed up the code because of the UML
# - wafl