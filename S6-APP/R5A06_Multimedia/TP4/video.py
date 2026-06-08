import cv2

# Conserver en binaire (noir ou blanc) que les différences
# de pixels suffisamment intense, entre 200 et 255.
THRESH = (200, 255)
FPS = 24


def draw_rect(contours: list):
  """Récupère les coordonnées depuis l'objet contour et dessine un rectangle"""
  (x, y, w, h) = cv2.boundingRect(contours)

  # dessine un rectangle à ces coordonnées sur la frame
  cv2.rectangle(frame, (x, y), (x+w, y+h), (0, 255, 0), 2)

  # Affichages pour vérifier le masque
  cv2.imshow("Frame de la vidéo", frame)
  cv2.imshow("Le masque binaire", mask)


def background_substract(cap: cv2.VideoCapture) -> None:
  subbg = cv2.createBackgroundSubtractorMOG2()

  while True:
    ret, frame = cap.read()
    if not ret: break

    mask = subbg.apply(frame)

    # supprime certains pixels blanc de contours, en particulier les pixels isolés
    mask = cv2.erode(mask, None, iterations=2) 

    # reconstruit les zones larges de pixels blancs abimées par l'érosion précédente
    mask = cv2.dilate(mask, None, iterations=2)

    mask = cv2.threshold(mask, THRESH[0], THRESH[1], cv2.THRESH_BINARY)
    
    # à appeler sur le masque qui a subi l'érosion et la dilatation
    listecontours, _ = cv2.findContours(mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)

    time.sleep(1 / FPS)