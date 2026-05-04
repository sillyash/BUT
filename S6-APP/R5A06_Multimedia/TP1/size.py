"""Calculate compression statistics for Huffman encoding."""


def calculate_sizes(occ_dict: dict, huffman_table: dict) -> tuple:
  # Original size: each character = 8 bits
  total_chars = sum(occ_dict.values())
  original_size_bits = total_chars * 8
  
  # Encoded size: sum of (frequency * code_length) for each character
  encoded_size_bits = 0
  for char, freq in occ_dict.items():
    if char in huffman_table:
      code_length = len(huffman_table[char])
      encoded_size_bits += freq * code_length
  
  return original_size_bits, encoded_size_bits


def print_compression_report(original_bits: int, encoded_bits: int):
  original_bytes = original_bits / 8
  encoded_bytes = encoded_bits / 8
  
  if original_bits > 0:
    compression_ratio = encoded_bits / original_bits
    saved_percent = (1 - compression_ratio) * 100
  else:
    compression_ratio = 1.0
    saved_percent = 0.0
  
  print("\n" + "="*60)
  print("COMPRESSION REPORT")
  print("="*60)
  print(f"Original file size: {original_bytes:,.1f} bytes ({original_bits:,} bits)")
  print(f"Encoded file size:  {encoded_bytes:,.1f} bytes ({encoded_bits:,} bits)")
  print(f"Compression ratio:  {compression_ratio:.2%}")
  print(f"Space saved:        {saved_percent:.1f}%")
  print("="*60)
