#!/usr/bin/python
"""
    This code is originally build by Ridho Muhammad
    To use it, first thing you should do is put all your image into one folder.
"""
import os
import sys, getopt
import cv2
import shutil

def main(argv):

   input_folder_path = ''
   num_of_compressing = 1
   try:
      opts, args = getopt.getopt(argv,"hi:n:",["ifile=","num="])
   except getopt.GetoptError:
      print ('reducing_size.py -i <input_folder_path>  -n <num_of_compressing(integer)>')
      sys.exit(2)
   for opt, arg in opts:
      if opt == '-h':
         print ('reducing_size.py -i <input_folder_path>  -n <num_of_compressing(integer)>')
         sys.exit()
      elif opt in ("-i", "--ifile"):
         input_folder_path = arg
      elif opt in ("-n", "--num"):
         try:
             num_of_compressing = int(arg)
         except ValueError:
             print('please enter an integer')
             sys.exit()

   try:
       os.mkdir(os.path.join(input_folder_path, 'new'))
   except Exception as e:
       print(e)
   print(f'new path on {os.path.join(input_folder_path,"new")}')
   files = os.listdir(input_folder_path)

   for file in files:
       if os.path.isdir(os.path.join(input_folder_path, file)) == True:
           pass
       else:
           file_path = os.path.join(input_folder_path, file)
           img = cv2.imread(file_path)
           x = int(img.shape[1] / num_of_compressing)
           y = int(img.shape[0] / num_of_compressing)
           dim = (x, y)
           img_new = cv2.resize(img, dim, interpolation = cv2.INTER_AREA)
           cv2.imwrite(os.path.join(input_folder_path, 'new', file), img_new)
           print(f'{file} is done converted')
           os.remove(os.path.join(input_folder_path, file))
           shutil.move(os.path.join(input_folder_path, "new", file), os.path.join(input_folder_path, file))

   os.removedirs(os.path.join(input_folder_path, "new"))


if __name__ == "__main__":
   main(sys.argv[1:])
