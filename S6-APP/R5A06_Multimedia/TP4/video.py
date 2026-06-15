import cv2
import time

THRESH = (200, 255)
FPS = 24
MIN_AREA = 500  # ignore les contours trop petits (bruit)


def draw_rect(contour, frame, mask):
  x, y, w, h = cv2.boundingRect(contour)
  cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)


def background_substract(cap: cv2.VideoCapture) -> None:
  subbg = cv2.createBackgroundSubtractorMOG2()

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

    for contour in listecontours:
      if cv2.contourArea(contour) < MIN_AREA:
        continue
      draw_rect(contour=contour, frame=frame, mask=mask)

    cv2.imshow("Le masque binaire", mask)

    if cv2.waitKey(1) & 0xFF == ord('q'):
      break

    time.sleep(1 / FPS)

  cv2.destroyAllWindows()