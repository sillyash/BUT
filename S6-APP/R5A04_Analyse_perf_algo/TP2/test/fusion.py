import time
from .. import tools
from .. import algos


def get_execution_time(calls: int, li: list[int]):
	start = time.process_time_ns()
	for i in range(calls): algos.tri_fusion(li=li)
	end = time.process_time_ns()
	return (end - start)/calls


def execute_worst(size: int, nb_exec: int, intrange: tuple[int]) -> int:
	li = tools.generate_ordered_list(size=size, intrange=intrange)
	tools.generate_worst_for_merge(li, 0, len(li)-1)
	et = get_execution_time(calls=nb_exec, li=li)
	return et


def execute_average(size: int, nb_exec: int, intrange: tuple[int]) -> int:
	li = tools.generate_random_list(size=size, intrange=intrange) # Average
	et = get_execution_time(calls=nb_exec, li=li)
	return et


def metrics_worst(nb_exec: int, sizerange: tuple[int], intrange: tuple[int], steps: int=100):
  exec_times = []
  size_values = []
  
  n_min, n_max = sizerange
  n_step = (n_max - n_min) // steps
  if n_step == 0: n_step = 1
  
  for i in range(n_min, n_max, n_step):
    et = execute_worst(size=i, nb_exec=nb_exec, intrange=intrange)
    exec_times.append(et)
    size_values.append(i)
  
  return exec_times, size_values


def metrics_average(nb_exec: int, sizerange: tuple[int], intrange: tuple[int], steps: int=100):
  exec_times = []
  size_values = []
  
  n_min, n_max = sizerange
  n_step = (n_max - n_min) // steps
  if n_step == 0: n_step = 1
  
  for i in range(n_min, n_max, n_step):
    et = execute_average(size=i, nb_exec=nb_exec, intrange=intrange)
    exec_times.append(et)
    size_values.append(i)
  
  return exec_times, size_values


def worst_case(nb_exec: int, sizerange: tuple[int], intrange: tuple[int], steps: int=100) -> None:
  exec_times, size_values = metrics_worst(
		nb_exec=nb_exec,
		sizerange=sizerange,
		intrange=intrange,
		steps=steps
	) 
  
  tools.plot_results(
		size_values=size_values,
		exec_times=exec_times,
		graph_title='Merge sort (worst case)',
		x_label='List size (n)',
		y_label='Execution time (ns)'
	)


def average(nb_exec: int, sizerange: tuple[int], intrange: tuple[int], steps: int=100):
  exec_times, size_values = metrics_average(
		nb_exec=nb_exec,
		sizerange=sizerange,
		intrange=intrange,
		steps=steps
	)
  
  tools.plot_results(
		size_values=size_values,
		exec_times=exec_times,
		graph_title='Merge sort (random)',
		x_label='List size (n)',
		y_label='Execution time (ns)'
	)
