import os
import sys
import argparse
import tkinter as tk 		# system dependency

from sound import PitchApp

def main() -> int:
	root = tk.Tk()
	app = PitchApp(root)
	root.mainloop()

	return 0


if __name__ == "__main__":
	parser = argparse.ArgumentParser()
	parser.add_help(
		"This program opens your microphone / master " +
		"channel and tries to detect the main frequency " +
		"and give you it's note."
	)
	main()
