import random
from line_profiler import profile

@profile
def insertSort(tab):
    result = []
    for elt in tab:
        result = insertInOrder(elt, result)
    return result

@profile
def insertSort_rec(tab):
    result = []
    for elt in tab:
        result = insertInOrder_rec(elt, result)
    return result

@profile
def insertInOrder(elt, tab):
    i = 0
    while (i < len(tab)):
        if (tab[i] >= elt):
            break
        i += 1
    tab.insert(i, elt)
    return tab

@profile
def random_tab(value, length):
        return [random.randint(1, value) for _ in range(length)]

@profile
def insertInOrder_rec(elt, tab):
    if (tab == []):
        return [elt]
    mid = len(tab)//2
    if (tab[mid] < elt):
        return tab[:mid+1] + insertInOrder_rec(elt, tab[mid+1:])
    else:
        return insertInOrder_rec(elt, tab[:mid]) + tab[mid:]

def main():
    tab = random_tab(10000, 1000)
    insertSort(tab)
    insertSort_rec(tab)

main()