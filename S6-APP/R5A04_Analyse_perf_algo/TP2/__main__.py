from .test import insertion, bulles, rapide, fusion

if __name__ == '__main__':
	intrange = (0, 10000)

	# insertion.worst_case(nb_exec=100, sizerange=(10, 1000), intrange=intrange)
	# insertion.average(nb_exec=100, sizerange=(10, 1000), intrange=intrange)

	# bulles.worst_case(nb_exec=50, sizerange=(10, 500), intrange=intrange)
	# bulles.average(nb_exec=50, sizerange=(10, 500), intrange=intrange)

	# rapide.worst_case(nb_exec=100, sizerange=(10, 500), intrange=intrange)
	# rapide.average(nb_exec=100, sizerange=(10, 500), intrange=intrange)

	fusion.worst_case(nb_exec=50, sizerange=(10, 500), intrange=intrange)
	fusion.average(nb_exec=50, sizerange=(10, 500), intrange=intrange)
