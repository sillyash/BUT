#include <iostream>
using namespace std;

extern "C" int pgcd(int a, int b);

int main()
{
	int a, b;

	cout << "Ce programme calcule le PGCD de deux entiers." << endl;
	cout << endl << "Entrez un entier a : "; cin >> a;
	cout << endl << "Entrez un entier b : "; cin >> b;

	cout << endl << "Le PGCD de a et b est : " << pgcd(a, b) << endl;

	return 0;
}