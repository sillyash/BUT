"""Programme de test de complexité de recherche dans une liste."""
import time
import random


def search_in_list(elem: any, li: list[any]) -> int|bool:
	"""
  Searches for an item in a given list.

	Args:
			elem (any): The item to search for
			li (list[any]): The list to search in

	Returns:
			int|bool: The index of the item if found or False
	"""
	index = 0
	for item in li:
	  if (item == elem):
	    return index
	return False


def get_execution_time(calls: int, li: list[int], elem: int) -> int:
	"""
	Returns the execution time of the search function for a single list.

	Args:
			calls (int): The number of calls to do
			li (list[int]): The list to pass to the function
			elem (int): The item to search for

	Returns:
			int: The execution time in nanoseconds
	"""
	start = time.process_time_ns()
	for i in range(calls): search_in_list(elem, li)
	end = time.process_time_ns()
	return end - start


def generate_worst_case_list(size: int, intrange: tuple[int], item: int) -> list[int]:
	"""
	Generates the worst case scenario int list for the given item
	and size regarding the `search_in_list` function.

	Args:
			size (int): The wanted size for the list
			intrange (tuple[int]): The range of integers to generate

	Returns:
			list[int]: The generated list
	"""
	min, max = intrange
	li = []

	for i in range(size-1):
		x = random.randint(min, max)
		li.append(x)
  
	li.append(item)
	return li


if __name__ == '__main__':
  elem = 7
  li = generate_worst_case_list(10, (5,20), elem)
  
  print("List of 10 elements [5-20]:")
  print(li)
  
  et = get_execution_time(1, li, elem)
  print("Execution time (once):", et)
  
  et = get_execution_time(1000, li, elem)
  print("Execution time (1000x):", et, "(average", et/1000, ")")
  