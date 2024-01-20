#include <iostream>
#include <cstring>

using namespace std;

int main()
{
    const int MAX = 17;
    char tab[MAX];

    cout << "Entrez une valeur en binaire sur 16 bits:  >>> ";
    cin.getline(tab,MAX);
    cout << endl;

    unsigned short int masque = 0x8000;
    masque = masque >> (16 - strlen(tab));
    short int val = 0;
    int i=0;

    while (tab[i] != '\0')
    {
        if (tab[i] == '1') val = val | masque;
        masque = masque >> 1;
        i++;
    }

    cout << "La valeur binaire " << tab << " est egale a "
        << val << " en decimal." << endl;

	return 0;
}


