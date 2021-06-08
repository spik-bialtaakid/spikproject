"""
    This code is originally build by Ridho Muhammad
    To use it, consider your path root gonna be like This
    -root
    --A
    ---img1.jpg
    ---img2.jpg
    ...
    --B
    ---img1.jpg
    ---img1.jpg
    ...

"""
import os
import shutil
import numpy as np
def main():
    path = input("root_path : ")
    path = os.path.join(path)
    path_list = os.listdir(path)
    while True:
        print("--------------------")
        print("select task above :")
        print("1. rename images name based on alphabet dir")
        print("2. check the sum of images contain in each alphabet dir")
        print("3. equalize the sum of all images set ")
        print("4. delete alphabet folder an put all into root folder")
        while True:
            pil = input("your selection :")
            if pil == 1:
                rename_file(path_list, path)
                break
            else if pill == 2:
                check(path_list, path)
                break
            else if pill == 3:
                equalize(path_list, path)
                break
            else if pill == 4:
                last(path_list, path)
                break
            else:
                print("wrong answer")
        new = input("anything else? (Y/n)")
        if new.lower() == "y":
            pass
        else:
            break

def rename_file(p_list, p):
    for folder in p_list:
        i = 0
        for files in os.listdir(os.path.join(p, folder)):
            name, ext = os.path.splitext(file)
            i += 1
            os.rename(os.path.join(p, folder, files), os.path.join(p,folder, f"Project_{folder}_{str(i)}{ext}"))
            print(os.path.join(p, folder, files)+"-->"+ os.path.join(p,folder, f"Project_{folder}_{str(i)}{ext}"))

def check(p,p_list):
    for folder in p_list:
        print(folder, "contain about : ", os.listdir(os.path.join(p, folder)), "images")

def equalize(p, p_list):
    tmp = []
    for folder in p_list:
        tmp.append(len(os.listdir(os.path.join(p, folder))))

    num = min(tmp)

    for folder in p_list:
        files = os.listdir(os.path.join(p, folder))
        for item in range(len(files)-(num-1)):
            os.remove(os.path.join(p, folder, files[item]))
    print("all images at each alphabet have been equalized")
def last(p_list, p):
    for folder in p_list:
        for files in os.listdir(os.path.join(p, folder)):
            shutil.move(os.path.join(p, folder, files), os.path.join(p, files))

        os.removedirs(os.path.join(p, folder))
    print("every alphabet folder have been deleted")
if __name__ == '__main__':
    main()
