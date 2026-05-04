import heapq

class HuffNode:
  def __init__(self, freq: int, char: str):
    self.char = char
    self.freq = freq
    self.left = None
    self.right = None
  
  def __repr__(root, prefix="", is_left=True):
    if root is not None:
        print_tree(root.right, prefix + ("│   " if is_left else "    "), False)
        print(prefix + ("└── " if is_left else "┌── ") + str(root.value))
        print_tree(root.left, prefix + ("    " if is_left else "│   "), True)
  
  def __str__(self):
    pass


def build_huffman_tree(occ_dict: dict):
  assert(occ_dict is not None)
  
  # Zip used for iteration
  # Converts the dict to iterator of tuples
  priority_queue = [HuffNode(char, freq) for char, freq in zip(occ_dict.items())]
  
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
    node = HuffNode(freq=freq)
    
    node.left = left_child
    node.right = right_child
    
    # Push
    heapq.heappush(priority_queue, node)
    
  return priority_queue[0] # Parent element (root)

