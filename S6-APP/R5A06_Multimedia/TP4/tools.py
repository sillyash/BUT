import os
import sys
import cv2

def check_video_file(path: str) -> None:
  """Checks the given file path and extension."""
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)

  if ext not in AVAILABLE_EXTS:
    print(
      f"File is not a video file: {path}.\nAvailable filetypes are: {', '.join(AVAILABLE_EXTS)}",
      file=sys.stderr
    )
    exit(2)


def camera() -> cv2.VideoCapture:
  """Returns video capture for default camera."""
  return cv2.VideoCapture(0)


def video(path: str)-> cv2.VideoCapture:
  """Returns video capture for given video file."""
  check_video_file(path)
  return cv2.VideoCapture(path)
