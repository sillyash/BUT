import argparse
import tools
import video


def main(path: str) -> int:
  cap = None # Video capture

  if path == "" or path is None:
    print("No file provided, using default camera.")
    cap = tools.camera()
  else:
    cap = tools.video(path)
  
  # TODO

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
