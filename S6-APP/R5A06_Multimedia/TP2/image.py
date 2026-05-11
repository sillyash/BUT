import sys
import json
import numpy as np
from PIL import Image

BG_COLOR = (0, 0, 0)
MARIO_RED = (177, 52, 37)
LUIGI_GREEN = (30, 180, 45)

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


def create_new_image(size: tuple[int, int]) -> Image.Image:
	img = Image.new(mode='RGB', size=size, color=BG_COLOR)
	return img


def upscale_image(pixels: list[list[int]], ratio: int) -> Image.Image:
	h, w = len(pixels[0]), len(pixels)
	new_h, new_w = (h * ratio, w * ratio)

	img = create_new_image(size=(new_h, new_w))
	im_map = img.load()
	
	for j in range(new_h):
		for i in range(new_w):
			pixel = tuple(pixels[i//ratio][j//ratio])

			# swap mario for the big'a broth'a Luigi mama mia !!
			if pixel == MARIO_RED: pixel = LUIGI_GREEN

			im_map[j,i] = pixel

	return img

