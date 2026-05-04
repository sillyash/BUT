import heapq
from typing import Optional

class HuffNode:
  def __init__(self, freq: int, char: Optional[str] = None, left=None, right=None):
    self.char = char
    self.freq = freq
    self.left = left
    self.right = right

  def __lt__(self, other):
    # Tie-break on char so heap operations stay deterministic.
    return (self.freq, self.char or "") < (other.freq, other.char or "")
  
  def __repr__(self):
    return self.__str__()

  def _label(self) -> str:
    if self.left is None and self.right is None:
      return f"{self.char!r}:{self.freq}"
    return f"*:{self.freq}"

  def pretty(self) -> str:
    lines = []

    def walk(node, prefix: str, is_last: bool):
      if node is None:
        return

      branch = "`-- " if is_last else "|-- "
      lines.append(prefix + branch + node._label())

      children = [child for child in (node.left, node.right) if child is not None]
      next_prefix = prefix + ("    " if is_last else "|   ")
      for index, child in enumerate(children):
        walk(child, next_prefix, index == len(children) - 1)

    walk(self, "", True)
    return "\n".join(lines)
  
  def __str__(self):
    return self.pretty()


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
