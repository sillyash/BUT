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


def generate_worst_for_merge(arr: list[int], l: int, r: int) -> list[int]:
  def join(arr, left, right, l, m, r):
    i = 0;
    for i in range(m-l+1):
      arr[i] = left[i];
      i+=1;

    for j in range(r-m):
      arr[i + j] = right[j];

  def split(arr, left, right, l, m, r):
    for i in range(m-l+1):
      left[i] = arr[i * 2];

    for i in range(r-m):
      right[i] = arr[i * 2 + 1];
    if (l < r):
      m = l + (r - l) // 2;

  if (l < r):
    m = l + (r - l) // 2;

    # create two auxiliary arrays
    left = [0 for i in range(m - l + 1)];
    right = [0 for i in range(r-m)];

    # Store alternate array elements in left
    # and right subarray
    split(arr, left, right, l, m, r);

    # Recurse first and second halves
    generate_worst_for_merge(left, l, m);
    generate_worst_for_merge(right, m + 1, r);

    # join left and right subarray
    join(arr, left, right, l, m, r);


def plot_results(size_values: list[int], exec_times: list, graph_title: str, x_label: str, y_label: str):
  plt.plot(size_values, exec_times, 'ro')
  plt.title(graph_title)
  plt.xlabel(x_label)
  plt.ylabel(y_label)
  plt.show()
  return

