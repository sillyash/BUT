import os
import sys
import cv2

from __init__ import AVAILABLE_EXTS


def check_video_file(path: str) -> None:
  """Checks the given file path and extension."""
  if not os.path.exists(path):
    print("File not found: ", path, file=sys.stderr)
    exit(2)

  ext = os.path.splitext(path)[-1]

  if ext not in AVAILABLE_EXTS:
    print(
      f"File is not a video file: {path}.\nAvailable filetypes are: {', '.join(AVAILABLE_EXTS)}",
      file=sys.stderr
    )
    exit(2)


def get_fps(cap: cv2.VideoCapture) -> int:
  """Returns FPS value for a given video file."""
  return cap.get(cv2.CAP_PROP_FPS)


def get_resolution(cap: cv2.VideoCapture) -> (int, int):
  h = cap.get(cv2.CAP_PROP_FRAME_HEIGHT)
  w = cap.get(cv2.CAP_PROP_FRAME_WIDTH)
  return (h,w)


def camera() -> cv2.VideoCapture:
  """Returns video capture for default camera."""
  return cv2.VideoCapture(0)


def video(path: str)-> cv2.VideoCapture:
  """Returns video capture for given video file."""
  check_video_file(path)
  return cv2.VideoCapture(path)
