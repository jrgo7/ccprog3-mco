"""
Get all class names.
For each class, add a page:
    Seven columns-wide "Class: <Name>"
    Next row
    Column A: Method
    Column B: #
    Column C: Test Description
    Column D: Sample Input Data
    Column E: Expected Output
    Column F: Actual Output
    Column G: P/F
    Next row: do the ff. pattern for each method name
        Method name 1
                    2
                    3
"""
import os
import regex
# from openpyxl import Workbook

""" Retrieved from https://stackoverflow.com/a/77307448"""
JAVA_METHOD_REGEX: regex.Pattern  = regex.compile(r"""\b(public|private|protected)\s*(<[^>]*>)?\s*(abstract|static|final)?\s*(abstract|static|final)?\s*(abstract|static|final)?\s+[^*](\w+(\.\w+)*)*(\s*<[^>]*>)?(\s*\[[^\]]*\])*(\s+\w+\s*\([^)]*\)(\s+throws\s+\w+(\s*,\s*\w+)*)?)+""")

# def deal_with(file_path: str, wb: Workbook) -> int:
def deal_with(file_path: str):
    folder_path, class_name = os.path.split(file_path)
    print(class_name)
    print("Method\t#\tTest Description\tSample Input Data\tExpected Output\tActual Output\tP/F")
    os.system("javap %s > \"%s\"" % (file_path, os.path.join(folder_path, "charlotte.txt")))
    with open(os.path.join(folder_path, "charlotte.txt"), "r") as file:
        listing = file.read()
    methods_matches = regex.findall(JAVA_METHOD_REGEX, listing)
    methods = [method[9].strip() for method in methods_matches]
    for i, method in enumerate(methods, start=1):
        print(f"{method}\t1\t\t\t\t\tP")
        for i in range (2, 4):
            print(f"\t{i}\t\t\t\t\tP")
            
    os.remove(os.path.join(folder_path, "charlotte.txt"))
    
def main():
    # wb = Workbook()
    folder_path: str = "./src"
    method_count = 0
    out = ""
    for path, folders, files in os.walk(folder_path):
        for folder in folders:
            for file in os.listdir(os.path.join(path, folder)):
                if file.endswith(".class"):
                    deal_with(os.path.join(path, folder, file))
    # wb.save("test_script_blank.csv")
    return

if __name__ == '__main__':
    main()