#include <iostream>
using namespace std;

const int MINI = 0, MAXI = 65535;
const int MAX = 17;

// EX1
void toBin(const int& val, char valBin[MAX])
{
    unsigned short int masque = 0x8000;

    for (int i=0; i<MAX; i++)
    {
        if (val & masque) valBin[i] = '1';
        else valBin[i] = '0';

        masque = masque >> 1;
    }
    valBin[MAX-1] = '\0';
}

// DISPLAY
void arrayCout(const char tab[MAX])
{
	for (int i=0; i<MAX; i++)
	{
	    if (i%4 == 0) cout << " ";
		cout << tab[i];
	}
}

// EX2
int main()
{
    int val;

	// input utilisateur
	do
    {
       	cout << "Saisissez un valeur comprise entre " << MINI << " et " << MAXI << " >>> ";
        cin >> val; cout << endl;
    }
    while (val > 65535 || val < 0);


	// calcul du nombre en binaire sur 16 bits
	char valBin[MAX];
	toBin(val, valBin);
	cout << "La valeur initiale est\t\t: "; arrayCout(valBin); cout << endl;

	//operations sur les bits
	cout << "Mise a 0 des bits 0 a 3\t\t: ";
    val = val & 0xFFF0;     // 1111 1111 1111 0000
    toBin(val, valBin);
    arrayCout(valBin); cout << endl;

    cout << "Inversion des bits 8 a 11\t: ";
    val = val ^ 0x0F00;     // 0000 1111 0000 0000
    toBin(val, valBin);
    arrayCout(valBin); cout << endl;

    cout << "Mise a 1 des bits 12 a 15\t: ";
    val = val | 0xF000;     // 1111 0000 0000 0000
    toBin(val, valBin);
    arrayCout(valBin); cout << endl;

	return 0;
}


