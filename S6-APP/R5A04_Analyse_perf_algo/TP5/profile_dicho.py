import random
from line_profiler import profile

@profile
def search(tab, target):
    for elt in tab:
        if elt == target:
            return True
    return False

@profile
def dicho(tab, target, start = 0, end = None):
    if end is None: end = len(tab) - 1
    if (start >= end): return False
    
    mid = start + (end - start) // 2
    
    if tab[mid] == target: return True
    if tab[mid] > target: return dicho(tab, target, start, mid - 1)
    
    else: return dicho(tab, target, mid+1, end)
            
            
def random_tab(value, length):
        return [random.randint(1, value) for _ in range(length)]

def main():
    tab = random_tab(10000, 1000000)
    tab.sort()
    
    for i in range(0, 10000, 100):
        dicho(tab, i)
        search(tab, i)
    
main()