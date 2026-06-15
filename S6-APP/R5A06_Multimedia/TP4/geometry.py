def merge_rects(rect: (int, int, int, int), rect2: (int, int, int, int)):
  x1, y1, w1, h1 = rect
  x2, y2, w2, h2 = rect2

  x = min(x1, x2)
  y = min(y1, y2)
  w = max(x1 + w1, x2 + w2) - x
  h = max(y1 + h1, y2 + h2) - y

  return (x, y, w, h)


def intersect_rects(rect: (int, int, int, int), rect2: (int, int, int, int)):
  x1, y1, w1, h1 = rect
  x2, y2, w2, h2 = rect2

  return\
    intersect_pt([x2     , y2     ], rect) or\
    intersect_pt([x2 + w2, y2     ], rect) or\
    intersect_pt([x2     , y2 + h2], rect) or\
    intersect_pt([x2 + w2, y2 + h2], rect)


def intersect_pt(pt, rect):
  x, y = pt
  xr,yr,wr,hr = rect
  return x > xr and x < wr + xr and\
         y > yr and y < hr + yr
