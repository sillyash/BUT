# R103 · Archi
## [TD3](https://cours.iut-orsay.fr/pluginfile.php/80605/mod_resource/content/1/TD-TP-3_Pointeurs.pdf) · 13/12/23
#### Ash Merienne, TP 1B

<br>


### **Exercice 1**

1.		int *p; int x=34; *p=x;
	Erreur: l'addresse de p n'a pas été initialisée.

<br>

2.		int x=17, *p; &x=p;
	Erreur: on ne peut pas attribuer un pointeur à une addresse (&x = p).

<br>

3.		int x=17, *p; p=&x;
	Instructions correctes.

<br>

4.		int x=17, *p; p=&x; **p=4;
	Erreur: *p renvoie vers x, qui n'est pas un pointeur, donc **p (ie. *x) n'est pas une instruction correcte.

<br>

5.		char mot[10];
		char car=’A’;
		char *pc = &car;
		mot = pc;
	Erreur: mot est une addresse de tableau non modifiable.

<br>

6.		int main ()
		{
			char *chaine;
			cin>>chaine;
			cout <<chaine<<endl;
			return(0);
		}
	Erreur: puisque l'addresse de chaine n'est pas initialisée, cin >> chaine ne fonctionne pas.

<br>

7.		int * exemple3()
		{
			const int MAX=10;
			int t1[MAX]={0,1,2,3,4,5,6,7,8,9};
			int t2[MAX];
			for (int i=0; i<MAX; i++) t2[i]=t1[i]*t1[i];
			return t2;
		}
	La fonction renvoie un tableau d'entiers au lieu d'un pointeur d'entier, mais cela fonctionne (la plupart du temps). Cependant, puisque les variables locales de la fonction sont détruites à sa sortie, on va juste récupérer une addresse d'un espace libéré, ce qui peut causer des problèmes.

	=> Il ne faut **JAMAIS** renvoyer l'addresse d'une variable locale.

<br>


### **Exercice 2**

	#include <iostream>
	using namespace std;

	int main()
	{
		const int MAX=11;
		char c, *p, *q;
		char tab[MAX]="ONE+TWELVE"; // 11e char = '\o'			O N E + T W E L V E
		p=tab;						// addresse du 1e elem		O N E + T W E L V E
		q=p+MAX-2;					// addresse du 9e elem		O N E + T W E L V E
		c=*p;						// c = 'O'					O N E + T W E L V E
		*p=*q;						// *p = *q = 'E'			E N E + T W E L V E
		*q=c;						// *q = c = 'O'				E N E + T W E L V O
		q=q-3;						// addresse du 7e elem		E N E + T W E L V O
		p++;						// addresse du 2e elem		E N E + T W E L V O
		c=*p;						// c = 'N'					E N E + T W E L V O
		p[0]=*(q+1);				// p[0] = 8e char = 'L'		E L E + T W E L V O
		*q=*(p+2);					// *q = 4e char = '+'		E L E + T W + L V O
		p+=2;						// addresse du 4e elem
		q+=2;						// addresse du 9e elem
		*p=*q;						// *p = *q = 'V'			E L E V T W + L V O
		q[-1]=*(p+1);				// 8e char = 5e char = 'T'	E L E V T W + T V O
		p[1]=p[-1];					// 5e char = 3e char = 'E'	E L E V E W + T V O
		*q=p[2];					// *q = 6e char = 'W'		E L E V E W + T W O
		*(q-3)=c;					// 6e char = c = 'N'		E L E V E N + T W O
		
		cout<<tab;
		
		return 0;
	}

L'affichage produit par le programme ci-dessus est "ELEVEN+TWO"

<br>


### **Exercice 3**

cf. [fichier c++](./TD3EX3.cpp)

<br>


### **Exercice 4**

cf. [fichier c++](./TD3EX4.cpp)

