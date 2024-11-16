from PIL import Image
import pytesseract
import numpy as np
import re
import os

class Tes:
    def __init__(self):
        pytesseract.pytesseract.tesseract_cmd = r'C:/Program Files/Tesseract-OCR/tesseract.exe'

    def greyscale(self, image_path):
        # Check if file exists
        if not os.path.exists(image_path):
            raise FileNotFoundError(f"File '{image_path}' does not exist.")
        
        # Open the image
        img = Image.open(image_path)  # Use the file path string directly here
        # Convert to grayscale
        grayscale_image = img.convert("L")
        # Save the grayscale image
        filename = "grayscale_image.png"
        grayscale_image.save(filename)
        return filename


    def only_numerics(self, seq):
        nums = re.findall(r'\d+', seq)
        return nums

    def insert_free_space(self, arr):
        new_arr = []
        for i in range(len(arr) + 1):
            if i < 12:
                new_arr.append(arr[i])
            elif i == 12:
                new_arr.append(0)  # Insert a free space
            else:
                new_arr.append(arr[i - 1])
        return new_arr

    def shape_board(self, arr):
        arr = np.array(arr)
        if arr.size != 25:
            raise ValueError("Input array must contain exactly 25 elements after processing.")
        return arr.reshape(5, 5)
    
    def call(self, image_num):    
        image_path="BingoCard.png"
        filename = self.greyscale(image_path)
        img1 = np.array(Image.open(filename))
        text = pytesseract.image_to_string(img1)
        
        nums = self.only_numerics(text)
        if not nums:
            raise ValueError("No numeric values were detected in the image.")
        
        nums = list(map(int, nums))  # Convert strings to integers
        processed_nums = self.insert_free_space(nums)
        
        # Ensure we have the right number of elements
        if len(processed_nums) < 25:
            processed_nums.extend([0] * (25 - len(processed_nums)))  # Pad with zeroes if needed
        
        shaped_board = self.shape_board(processed_nums)
        return shaped_board

if __name__ == "__main__":
    t = Tes()
    try:
        result = t.call("BingoCard.png")
        print(result)
    except Exception as e:
        print(f"Error: {e}")
