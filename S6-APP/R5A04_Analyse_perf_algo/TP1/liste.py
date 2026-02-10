"""Programme de test de complexité de recherche dans une liste."""
import time
import random
import matplotlib.pyplot as plt

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
	for i in range(len(li)):
		if (li[i] == elem):
			return i
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
	for i in range(calls): search_in_list(elem=elem, li=li)
	end = time.process_time_ns()
	return (end - start)/calls


def generate_list(size: int, intrange: tuple[int]) -> list[int]:
  min, max = intrange
  return [random.randint(min, max) for i in range(size)]


def test_search_worst(nb_exec: int, intrange: tuple[int], sizerange: tuple[int]):
  exec_times = []
  size_values = []
  elem = -5 # Doesn't exist
  
  n_min, n_max = sizerange
  n_step = (n_max - n_min) // 100
  
  for i in range(n_min, n_max, n_step):
    li = generate_list(size=i, intrange=intrange)
    et = get_execution_time(calls=nb_exec, li=li, elem=elem)
    
    exec_times.append(et)
    size_values.append(i)
  
  plt.plot(size_values, exec_times, 'ro')
  plt.xlabel('List size')
  plt.ylabel('Execution time (ns)')
  plt.title('Linear search worst case')
  plt.show()


def test_search_regul(nb_exec: int, intrange: tuple[int], sizerange: tuple[int]):
  exec_times = []
  size_values = []
  elem = (intrange[1] - intrange[0]) // 2 # Avg number
  
  n_min, n_max = sizerange
  n_step = (n_max - n_min) // 100
  
  for i in range(n_min, n_max, n_step):
    li = generate_list(size=i, intrange=intrange)
    et = get_execution_time(calls=nb_exec, li=li, elem=elem)
    
    exec_times.append(et)
    size_values.append(i)
  
  plt.plot(size_values, exec_times, 'ro')
  plt.xlabel('List size')
  plt.ylabel('Execution time (ns)')
  plt.title('Linear search regular')
  plt.show()


if __name__ == '__main__':
  test_search_worst(nb_exec=1000, intrange=(0, 1000), sizerange=(1, 10000))
  test_search_regul(nb_exec=1000, intrange=(0, 1000), sizerange=(1, 10000))
