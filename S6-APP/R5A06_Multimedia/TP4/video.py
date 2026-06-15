import cv2
import time
import tools

from __init__ import THRESH, MIN_AREA_RATIO, MERGE_RATIO, PERSIST_RATIO
from geometry import intersect_rects, merge_rects

def draw_rect(rect: (int, int, int, int), frame, mask):
  x, y, w, h = rect
  cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)
  return


def background_substract(cap: cv2.VideoCapture) -> None:
  fps = tools.get_fps(cap=cap)
  resolution = tools.get_resolution(cap=cap)

  area_thresh         = (resolution[0] * resolution[1]) / MIN_AREA_RATIO
  dist_thresh_merge   = (resolution[0] * resolution[1]) / MERGE_RATIO
  dist_thresh_persist = (resolution[0] * resolution[1]) / PERSIST_RATIO

  print(
    f"AREA THRESH    : {area_thresh}",
    f"MERGE THRESH   : {dist_thresh_merge}",
    f"PERSIST THRESH : {dist_thresh_persist}",
    sep='\n'
  )

  subbg = cv2.createBackgroundSubtractorMOG2()
  previousRectList = []

  while True:
    ret, frame = cap.read()
    if not ret:
      break

    mask = subbg.apply(frame)
    mask = cv2.erode(mask, None, iterations=2)
    mask = cv2.dilate(mask, None, iterations=2)

    _, mask = cv2.threshold(
      mask,
      THRESH[0],
      THRESH[1],
      cv2.THRESH_BINARY
    )

    listecontours, _ = cv2.findContours(
      mask,
      cv2.RETR_TREE,
      cv2.CHAIN_APPROX_SIMPLE
    )

    currentRectList = []

    for contour in listecontours:
      if cv2.contourArea(contour) < area_thresh:
        continue
      
      rect = cv2.boundingRect(contour)

      for rect2 in currentRectList:

        if intersect_rects(rect, rect2) or intersect_rects(rect2, rect):
          rect = merge_rects(rect, rect2)

      currentRectList.append(rect)
    
    for rect in currentRectList:
      draw_rect(rect=rect, frame=frame, mask=mask)

    previousRectList = currentRectList

    cv2.imshow("Frame", frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):
      break

    time.sleep(1 / fps)

  cv2.destroyAllWindows()
  return
