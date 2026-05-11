import sys
import json
import numpy as np
from PIL import Image


def load_pixels_from_json(path: str) -> list[list[int]]:
	js = json.load(path)
	try:
		pixels = js['pixels']
		return pixels
	except Exception as e:
		print("No 'pixels' entry found in provided JSON.", file=sys.stderr)
		exit(2)


def json_to_image(pixels: list[list[int]]) -> Image.Image:
	arr = np.array(pixels, dtype=np.uint8)
	img = Image.fromarray(arr)

	return img
