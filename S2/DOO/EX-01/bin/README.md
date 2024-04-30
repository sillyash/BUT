# EX-04 - Questions sur l’héritage

Répondre aux questions dans un document (par exemple googledoc) comme vous faites pour les autres exercices. \
Programmer les classes sous Eclipse et les tester.


## Exercice 1

### 1. Quelle est la classe mère de toutes les classes en Java ?

La classe ```Object``` est la classe mère de toutes les classes en Java.


### 2) Est-ce possible de définir une classe C1 qui hérite des attributs d’une classe C2 sans hériter de ses méthodes ? Si oui expliquer comment. 

Non, car en Java, une sous-classe hérite des attributs et des méthodes de la superclasse. Cependant, on peut Override ces méthodes, ou utiliser la composition plutôt que l'héritage.


### 3) En Java, une classe peut-elle hériter de plusieurs classes ? Si oui, comment ? 

Non, il n'y a pas d'héritage multiple en Java, mais on peut hériter hiérarchiquement, ou hériter de plusieurs interfaces.


### 4) Soit le programme suivant et son exécution ([voir fichier](./C.java)). Pour chaque ligne numérotée de a à h : expliquer ce qui se passe à l’exécution

	public class C {
		int i ; 
		public static void main(String[] args) {
	a		C o = new C1();
	b		((C1)o).i = 1 ; 
	c		((C)o).i = 2;
	d		System.out.println(o.i);
	e		System.out.println(((C)o).i);
	f		System.out.println(((C1)o).i);
		}
	}
	
	g class C1 extends C {
	h	int i ; 
	}


	EXECUTION: 
	2
	2
	1


***ligne a*** : Cette ligne crée un objet ```o``` de type ```C1```, mais il est référencé par une variable de type ```C```. Cela est permis car ```C1``` est une sous-classe de ```C```.

***ligne b*** : On accède à la variable ```i``` de ```C1``` en effectuant un cast de ```o``` vers ```C1``` et en lui attribuant la valeur ```1```.

***ligne c*** : On définit la variable ```i``` de la classe ```C``` (la superclasse) à ```2```. Comme ```C``` possède sa propre variable ```i``` et que ```o``` est toujours référencé en tant que ```C```, cette instruction définira la variable i de la superclasse à ```2```.

***ligne d*** : On affiche la valeur de ```i``` de l'objet ```o```. Comme ```o``` est de type ```C1``` et que ```i``` n'est pas redéfinie dans ```C1```, la valeur affichée est celle de la variable ```i``` de ```C```, qui a été définie à ```2```.

***ligne e*** : On affiche également la valeur de ```i``` de l'objet ```o```, mais en effectuant un **cast explicite** vers ```C```. Comme précédemment, cela affiche la valeur de ```i``` de la superclasse, qui est ```2```.

***ligne f*** : On affiche la valeur de ((C1)o).i, cela affiche 1, démontrant que la variable i spécifique à C1 conserve sa valeur

<br>

## Exercice 2


### 1. Que veut dire le mot clé final devant une déclaration de classe ? 

Cela signifie que l'on ne peut pas hériter de la classe.


### 2. Que veut dire le mot clé final devant une déclaration de méthode ? 

Cela signifie que l'on ne peut pas "Override", surcharger la méthode dans une sous-classe.


### 3. Définir une classe ```Véhicule``` qui comporte un attribut entier ```x``` et une procédure ```init()``` qui initialise l’attribut à ```-1```. Faire en sorte que cette méthode ne puisse pas être redéfinie dans les sous-classes. 

**[Voir fichier](Vehicule.java)**

On utilise "final" devant la déclaration de la méthode pour empêcher la redéfinition de la méthode dans des sous-classes.


### 4. Définir une classe ```Voiture``` qui étend la classe Véhicule. Vous ajouterez un attribut ```boolean volantDroite```. Faire en sorte que les développeurs ne puissent pas définir de sous-classe de la classe ```Voiture```. 


**[Voir fichier](Voiture.java)**

On utilise "final" devant le déclaration de la classe pour empêcher la définition de sous-classes.



