import random
from line_profiler import profile

@profile
def naiveSearch(tab, target):
    found = False
    for elt in tab:
        if elt == target:
            found = True
    return found

@profile
def directSearch(tab, target):
    for elt in tab:
        if elt == target:
            return True
    return False
            
@profile
def random_tab(value, length):
        print("bla")
        return [random.randint(1, value) for _ in range(length)]

tab = random_tab(100, 10000)
print(naiveSearch(tab,3))
print(directSearch(tab,3))