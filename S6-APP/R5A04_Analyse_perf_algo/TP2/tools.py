import random
import matplotlib.pyplot as plt

def generate_random_list(size: int, intrange: tuple[int]) -> list[int]:
  min, max = intrange
  return [random.randint(min, max) for i in range(size)]


def generate_ordered_list(size: int, intrange: tuple[int]) -> list[int]:
	val_min, val_max = intrange
	step = (val_max - val_min) // size

	if size > (val_max - val_min):
		raise ValueError("Cannot generate ordered list: list is bigger than available range.")
		return

	li = [i for i in range(val_min, val_max, step)]
	return li


def plot_results(size_values: list[int], exec_times: list, graph_title: str, x_label: str, y_label: str):
  plt.plot(size_values, exec_times, 'ro')
  plt.title(graph_title)
  plt.xlabel(x_label)
  plt.ylabel(y_label)
  plt.show()
  return

