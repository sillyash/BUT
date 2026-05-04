import os
import sys
import argparse

from pprint import pprint
from occurence import build_occurence_dict


def main(path: str) -> int:
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)
  
  file = open(path, mode='r')
  occ_dict = build_occurence_dict(file=file)
  print("Occurence dict:")
  pprint(occ_dict)


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("file", help="Path to the file")
  
  args = parser.parse_args()
  main(args.file)
