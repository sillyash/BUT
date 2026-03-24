
def mergeSort(tab):
    if (len(tab) <= 1):
        return tab
    mid = len(tab) // 2
    tab1 = mergeSort(tab[:mid])
    tab2 = mergeSort(tab[mid:])
    return merge(tab1, tab2)
    
def merge(tab1, tab2):
    tab = []
    while (tab1 != [] and tab2 != []):
        if (tab1[0] < tab2[0]):
            tab.append(tab1.pop())
        else:
            tab.append(tab2.pop())
    return tab
        
def random_tab(value, length):
        return [random.randint(1, value) for _ in range(length)]

def main():
    tab = random_tab(10000, ____)
    print(mergeSort(tab))