import heapq
from typing import Optional

class HuffNode:
  def __init__(self, freq: int, char: Optional[str] = None, left=None, right=None):
    self.char = char
    self.freq = freq
    self.left = left
    self.right = right
  
  def __str__(self):
    pass


def build_huffman_tree(occ_dict: dict):
  assert(occ_dict is not None)
  if not occ_dict:
    return None
  
  # Convert frequency dictionary to heap nodes.
  priority_queue = [HuffNode(freq=freq, char=char) for char, freq in occ_dict.items()]
  
  # Heap queue used for fast access
  # Converts the list of tuples into a sorted heap
  heapq.heapify(priority_queue)
  
  # Build tree
  while len(priority_queue) > 1:
    # Get children
    left_child = heapq.heappop(priority_queue)
    right_child = heapq.heappop(priority_queue)
    
    # Merge
    freq = left_child.freq + right_child.freq
    node = HuffNode(freq=freq, left=left_child, right=right_child)
    
    # Push
    heapq.heappush(priority_queue, node)
    
  return priority_queue[0] # Parent element (root)
