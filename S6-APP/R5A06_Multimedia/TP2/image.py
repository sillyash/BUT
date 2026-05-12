import sys
import json
import numpy as np
from PIL import Image

BG_COLOR = (0, 0, 0)
MARIO_RED = (177, 52, 37)
LUIGI_GREEN = (30, 180, 45)

def clamp(value : int|float, val_min, val_max) -> int|float:
	return max(val_min, min(value, val_max))


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
	w, h = image.size

	src_map = image.load()

	new_img = create_new_image(size=(w, h))
	new_map = new_img.load()

	for i in range(w):
		for j in range(h):
			acc = [0, 0, 0] # R, G, B

			for x in range(conv_len):
				for y in range(conv_len):
					idx_w = clamp(value=(i + x - conv_len // 2), val_min=0, val_max=w-1)
					idx_h = clamp(value=(j + y - conv_len // 2), val_min=0, val_max=h-1)

					factor = kernel[x][y]
					pixel = src_map[idx_w, idx_h]

					for color_channel in range(3):
						acc[color_channel] += factor * pixel[color_channel]
			
			new_map[i,j] = tuple(round(val) for val in acc)

	return new_img


def test_jpeg_compression(path : str, fac : int):
	test_image = Image.open(path).convert('RGB')
	
	test_image.save(f'assets/test_{fac}.jpg', quality=fac)