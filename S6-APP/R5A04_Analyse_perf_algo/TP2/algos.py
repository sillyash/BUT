def tri_insertion(li: list[int]) -> list[int]:
	for i in range(len(li)):
		x = li[i]
		j = i

		while (j > 0) and (li[j-1] > x):
			li[j] = li[j-1]
			j = j-1

		li[j] = x

	return li


def tri_bulles(li: list[int]) -> list[int]:
	for i in range(len(li)-1, 1, -1):
		for j in range(i-1):
			if li[j+1] < li[j]:
				(li[j+1], li[j]) = (li[j], li[j+1])

	return li


def tri_rapide(li: list[int]):
	if not li:
		return []
	else:
		pivot = li[-1]
		plus_petit = [x for x in li if x < pivot]
		plus_grand = [x for x in li[:-1] if x >= pivot]
		return tri_rapide(plus_petit) + [pivot] + tri_rapide(plus_grand)


def fusion(gauche: list[int], droite: list[int]):
	if not len(gauche) or not len(droite):
			return gauche or droite

	result = []
	i, j = 0, 0
	while len(result) < len(gauche) + len(droite):
			if gauche[i] < droite[j]:
					result.append(gauche[i])
					i += 1
			else:
					result.append(droite[j])
					j += 1
			if i == len(gauche) or j == len(droite):
					result.extend(gauche[i:] or droite[j:])
					break

	return result


def tri_fusion(li: list[int]) -> list[int]:
  if len(li) < 2: return li
  
  milieu = int(len(li) / 2)
  gauche = tri_fusion(li[:milieu])
  droite = tri_fusion(li[milieu:])
  
  return fusion(gauche, droite)

