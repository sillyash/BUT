import os
import sys
import argparse

from image import load_pixels_from_json, json_to_image

def main(path: str) -> int:
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)
  
  if not os.path.splitext(path)[-1] == '.json':
    print("File is not a JSON file: ", path, file=sys.stderr)
    exit(2)
  
  file = open(path, mode='r')
  
  pixels = load_pixels_from_json(path=file)
  img = json_to_image(pixels=pixels)
  img.show()

  file.close()
  
  return 0


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("file", help="Path to the JSON file")
  
  args = parser.parse_args()
  main(args.file)
