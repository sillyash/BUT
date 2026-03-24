import random

def search(tab, target):
    for elt in tab:
        if elt == target:
            return True
    return False

def dicho(tab, target, start = 0, end = None):
    if end is None:
        end = len(tab)
    if (start >= end):
        return False
    mid = start-end // 2
    if tab[mid] == target:
        return True
    if tab[mid] > target:
        return dicho(tab, target, start, mid - 1)
    else:
        return dicho(tab, target, mid+1, end)
            
            
def random_tab(value, length):
        return [random.randint(1, value) for _ in range(length)]

def main():
    tab = random_tab(10000, 1000000)
    tab.sort()
    for i in range(10000):
        print(dicho(tab, i))
    
main()