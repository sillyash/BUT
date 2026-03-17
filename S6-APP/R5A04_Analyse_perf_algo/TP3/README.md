# TP3

L'objectif de ce TP est de choisir entre plusieurs
solutions algorithmiques pour des problèmes simples
et moins simples - sous l'angle de la complexité.

On commence par une question de complexité :

## Q0

Vous avez une liste d'un million d'identifiants (`int`)
dans laquelle vous devez effectuer $5000$ recherches.

Avez-vous interêt à trier la liste auparavant ? Et
pour $5$ recherches ? Quelle est la valeur limite ?

> Il est rentable de trier la liste aupravant pour
> un million d'identifiants : cela nous permet ensuite
> d'utiliser la recherche dichotomique en coût total
> de $n*log(n)$ puis $log(n)$, donc $O(n*log(n))$.
>
> Pour $5$ recherches cependant, il est plus coûteux de
> trier la liste que de simplement la parcourir, pour un
> coût total de $O(n)$.
>
> Pour cacluler le point où l'un devient préférable on peut
> calculer:
> 
> $$ log_{2}(1000000) \simeq 20 $$
>
> Donc à partir de $20$ recherches, il devient préférable
> d'utiliser le tri.

> [!IMPORTANT]
> Pour les questions suivantes, essayez de trouver au
> moins deux algorithmes différents à implémenter et
> décider si l'un est beaucoup plus rapide que l'autre.

## Q1

Trouver deux entiers dans une liste dont la somme vaut
une certaine cible (donnée en entrée).

> __Alogirhtme naïf :__ \
> On prend tous les couples possibles, donc $n^2 + n$ couples,
> et on calcule la somme. On est donc sur un coût en $O(n^2)$.
>
> __Algorithme optimisé :__ \
> On évite de calculer deux fois les couples puisque l'addition
> est transitive.
> 
> TODO : algo

## Q2

Trouver la $7^{e}$ plus grande valeur d'une liste. (et si
on remplace $7$ par une valeur donnée en entrée dont on
pense qu'elle n'est pas trop grande ?)

> __Alogirhtme naïf :__ \
> On initialise une liste vide des valeurs les plus grandes
> de taille fixe $7$ ou par une valeur en entrée. On y ajoute
> au début les $7$ premières valeurs de la liste, de manière
> triée.
>
> On parcours la liste, on compare chaque élément de la liste
> à chaque élément de notre liste des valeurs les plus grandes.
>
> Si on trouve plus grand, on insère au bon endroit (on doit
> parcourir toute la liste à chaque fois).
>
> On a donc un coût de: $O(n*M)$ où $n$ est la taille de la
> liste et $M = 7$ ou est un paramètre.
> 
> __Algorithme optimisé :__ \
> a

## Q3

Dire si deux listes données en entrée ont au moins un
élément en commun.

Et si on cherche tous les éléments en commun ?

> __Alogirhtme naïf :__ \
> a
>
> __Algorithme optimisé :__ \
> a

## Q4

Étant données une liste et une cible, trouver deux
entiers dans la liste dont la somme vaut la cible.

> __Alogirhtme naïf :__ \
> a
>
> __Algorithme optimisé :__ \
> a

## Q5 (difficile)

Dans un tableau d'entiers tab, trouvez le sous-tableau
`tab[a:b]` avec la somme maximale.

> __Algorithme naïf :__ \
> Pour chaque tranche ($n*(n+1)$ tranches), on calcule la
> somme ($n$ opérations) : on a donc $O(n^3)$.
>
> __Algorithme optimisé :__ \
> Prenons une approche *"diviser pour régner"* : si l'on
> part du principe que la tranche avec la somme maximale
> est la somme des tranches adjaçentes les plus
> grandes, on peut diviser la tableau initialement en
> tranches de taille 1.
>
> Mettons ces valeurs dans un dictionnaire: *e.g.*
> ```json
> {
>   "1:1" : 2,
>   "2:2" : -1,
>   ...
> }
> ```
>
> A chaque itération, prenons chaque agrandissement,
> (gauche et droite) et calculons la somme intelligemment
> à l'aide des valeurs déjà connues.
> 
> ```json
> {
>   "1:1" : 2,
>   ...
>   "3:7" : 9
> }
> ```
> On doit donc calculer pour $n*n$ couples des coordonnées,
> ainsi on descend à un coût en $O(n^2)$.
