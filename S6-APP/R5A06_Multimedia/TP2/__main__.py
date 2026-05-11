import os
import sys
import argparse


def main(path: str) -> int:
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)
  
  file = open(path, mode='r')
  # whatever
  file.close()
  
  return 0


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("file", help="Path to the file")
  
  args = parser.parse_args()
  main(args.file)
