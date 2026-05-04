import os
import sys
import argparse

from pprint import pprint
from huffman import build_huffman_tree, build_huffman_table
from occurence import build_occurence_dict


def main(path: str) -> int:
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)
  
  file = open(path, mode='r')
  occ_dict = build_occurence_dict(file=file)
  file.close()
  
  print("="*60)
  print("OCCURENCE TABLE")
  print("="*60)
  pprint(occ_dict)
  
  huff_tree = build_huffman_tree(occ_dict=occ_dict)
  
  print("\n" + "="*60)
  print("HUFFMAN TREE")
  print("="*60)
  print(huff_tree)
  
  huffman_table = build_huffman_table(huff_tree)
  
  print("\n" + "="*60)
  print("HUFFMAN ENCODING TABLE")
  print("="*60)
  for char in sorted(huffman_table.keys(), key=lambda c: huffman_table[c]):
    display_char = repr(char)[1:-1]  # Remove quotes from repr
    print(f"{display_char:>15} -> {huffman_table[char]}")


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument("file", help="Path to the file")
  
  args = parser.parse_args()
  main(args.file)
