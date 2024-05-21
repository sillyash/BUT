#include <iostream>
using namespace std;

extern "C" short int ppcm(int a, int b);

int main()
{
	short int a, b;

	cout << "Ce programme calcule le PPCM de deux entiers." << endl;
	cout << endl << "Entrez un entier a : "; cin >> a;
	cout << endl << "Entrez un entier b : "; cin >> b;

	cout << endl << "Le PPCM de a et b est : " << ppcm(a, b) << endl;

	return 0;
}