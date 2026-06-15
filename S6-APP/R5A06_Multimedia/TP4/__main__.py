import argparse
import tools
import video

from __init__ import AVAILABLE_EXTS


def main(path: str) -> int:
  cap = None  # Video capture

  if path == "" or path is None:
    print("No file provided, using default camera.")
    cap = tools.camera()
  else:
    cap = tools.video(path=path)
  
  video.background_substract(cap=cap)
  return 0


if __name__ == "__main__":
  parser = argparse.ArgumentParser()
  parser.add_argument(
      "file",
      nargs="?",
      default="",
      help=f"Path to the video file ({', '.join(tools.AVAILABLE_EXTS)}).\nIf not provided, will use default camera."
  )
  
  args = parser.parse_args()
  main(args.file)
