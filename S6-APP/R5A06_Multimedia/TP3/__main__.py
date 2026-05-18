import os
import sys
import argparse
import tkinter as tk 		# system dependency

from app import PitchApp

def main() -> int:
	root = tk.Tk()
	app = PitchApp(root)
	root.mainloop()

	return 0


if __name__ == "__main__":
	main()
