from line_profiler import profile
import requests
from bs4 import BeautifulSoup
import sys

# Inspiration du cours 'Missing semester' du MIT
# https://missing.csail.mit.edu/2020/debugging-profiling/

with open('out.html', 'w') as outfile:
    @profile
    def get_info():
        response = requests.get('https://info.iut-orsay.fr')
        s = BeautifulSoup(response.content, 'html.parser')
        classes = []
        for classe in s.find_all(attrs={"class": "nom"}):
            for elt in classe.contents:
                print(elt, file=outfile)
        
    get_info()
