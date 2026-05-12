import os
import sys
import argparse

from image import load_pixels_from_json, json_to_image, upscale_image, convolution, test_jpeg_compression


SHARPEN_MATRIX = [[ 0, -1,  0],
                  [-1,  5, -1],
                  [ 0, -1,  0]]

BLUR_MATRIX =  [[1/9, 1/9, 1/9],
                [1/9, 1/9, 1/9],
                [1/9, 1/9, 1/9]]

LAPLACIAN_MATRIX = [[-1, -1, -1],
                    [-1,  8, -1],
                    [-1, -1, -1]]

DIRECTIONAL_BLUR_MATRIX =  [[  0,   0,   0],
                            [1/3, 1/3, 1/3],
                            [  0,   0,   0]]


def main(path: str) -> int:
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)
  
  if not os.path.splitext(path)[-1] == '.json':
    print("File is not a JSON file: ", path, file=sys.stderr)
    exit(2)
  
  file = open(path, mode='r')
  
  # LOAD JSON
  pixels = load_pixels_from_json(path=file)
  img = json_to_image(pixels=pixels)
  img.show()

  file.close()

  # UPSCALE
  img = upscale_image(pixels=pixels, ratio=40) # 480 x 640
  img.show()

  # CONVOLUTION
  img = convolution(image=img, kernel=BLUR_MATRIX)
  img.show()

  # JPEG COMPRESSION
  factors = [95, 50, 25]
  for fac in factors:
    test_jpeg_compression("assets/cradlefive-art2.png", fac)
  
  return 0


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("file", help="Path to the JSON file")
  
  args = parser.parse_args()
  main(args.file)
