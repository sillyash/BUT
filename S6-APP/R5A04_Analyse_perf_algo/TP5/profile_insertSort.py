import random

def insertSort(tab):
    result = []
    for elt in tab:
        result = insertInOrder(elt, result)
    return result

def insertInOrder(elt, tab):
    i = 0
    while (i < len(tab)):
        if (tab[i] >= elt):
            break
        i += 1
    tab.insert(i, elt)
    return tab

def random_tab(value, length):
        return [random.randint(1, value) for _ in range(length)]


def insertInOrder_rec(elt, tab):
    if (tab == []):
        return [elt]
    mid = len(tab)//2
    if (tab[mid] < elt):
        return tab[:mid+1] + insertInOrder_rec(elt, tab[mid+1:])
    else:
        return insertInOrder_rec(elt, tab[:mid]) + tab[mid:]

def main():
    tab = random_tab(10000, ____)
    print(insertSort(tab))

main()