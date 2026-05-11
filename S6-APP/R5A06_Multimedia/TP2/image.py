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


def convolution(image: Image.Image, kernel: list[list[int]]) -> Image.Image:
	assert(len(kernel) == len(kernel[0]))
	assert(len(kernel) % 2 != 0)
	
	conv_len = len(kernel)
	im_map = image.load()
	h, w = image.size

	print(left, right)

	for j in range(h):
		for i in range(w):
			acc = [0, 0, 0] # R, G, B

			for x in range(conv_len):
				for y in range(conv_len):
					idx_h = i + x - conv_len // 2
					idx_w = j + y - conv_len // 2

					if idx_h < 0 or idx_h >= h:
						# TODO
						continue
					
					if idx_w < 0 or idx_w >= w:
						# TODO
						continue
					
					factor = kernel[x][y]
					pixel = im_map[idx_h, idx_w]

					for color_channel in range(3):
						acc[color_channel] += factor * pixel[color_channel]
			
			im_map[i, j] = tuple(acc)
