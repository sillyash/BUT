# R103 · Archi
## [TD4](https://cours.iut-orsay.fr/pluginfile.php/80998/mod_resource/content/2/TD-TP-4_Op%C3%A9rateurs%20Bool%C3%A9ens-Etudiants.pdf) · 13/12/23
#### Ash Merienne, TP 1B

<br>

## Sommaire

- [Exercice 1](#exercice-1)
- [Exercice 2](#exercice-2)
- [Exercice 3](#exercice-3)
- [Exercice 4](#exercice-4)

## Exercice 1

cf. [fichier](./TD4Ex1et2.cpp)

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

<br>

## Exercice 2

cf. [fichier](./cpp/TD4Ex1et2.cpp)

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

<br>

## Exercice 3

cf. [fichier](./cpp/TD4Ex3.cpp)

	int main()
	{
		const int MAX = 17;
		char tab[MAX];

		cout << "Entrez une valeur en binaire sur 16 bits:  >>> ";
		cin.getline(tab,MAX);
		cout << endl;

		unsigned short int masque = 0x8000;
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


<br>

## Exercice 4

cf. [fichier](./cpp/TD4Ex4.cpp)


