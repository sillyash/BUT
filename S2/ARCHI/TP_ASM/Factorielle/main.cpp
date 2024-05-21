#include <iostream>
using namespace std;

extern "C" long long int factorielle(long long int n);

int main()
{
	long long int n;

	cout << endl << "Ce programme sert a calculer la factorielle d'un entier." << endl << endl;

	cout << "Entrez une valeur entiere pour n : "; cin >> n;

	cout << endl << "Pour n = " << n << " : "<< "factorielle(n) = " << factorielle(n) << endl;

	return 0;
}