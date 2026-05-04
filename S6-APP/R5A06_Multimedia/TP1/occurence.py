import io
BUFFER_SIZE = 1024


def build_occurence_dict(file: io.TextIOWrapper) -> dict:
	occ_dict = {}
	buffer = file.read(BUFFER_SIZE)

	while buffer:
		process_buffer(occ_dict=occ_dict, buffer=buffer)
		buffer = file.read(BUFFER_SIZE)

	return occ_dict


def process_buffer(occ_dict: dict, buffer: str):
	for char in buffer:
		add_to_occurence_dict(occ_dict, char)
	return


def add_to_occurence_dict(occ_dict: dict, char: str):
	assert(len(char) == 1)

	if char in occ_dict.keys():
		occ_dict[char] += 1
		return

	occ_dict[char] = 1
	return
