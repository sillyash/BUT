# TP5

L'objectif de ce TP est d'apprendre à utiliser un
profileur simple pour ne pas avoir à faire des
mesures de temps à la main.

Il existe beaucoup d'alternatives ; celle que je
propose est open-source et simple à utiliser, mais
il est probable que vous en utilisiez d'autres
dans votre carrière. Vous aurez besoin d'accéder à
un terminal (commandes données en `bash`).

1. Installez `line_profiler` avec

```bash
python3 -m venv env
source env/bin/activate
pip install line_profiler
```

2. Récupérez le code [`profile.py`](./profile.py)
qui contient les fonctions de recherches vues au
TP1. Lisez le code pour comprendre la manière
d'indiquer les fonctions à profiler.

3. Dans votre terminal, mettez la variable
`LINE_PROFILE` à $1$ pour tous les processus issus
du terminal en utilisant la commande

```bash
export LINE_PROFILE=1.
```

4. Exécutez le programme et regardez les fichiers
produits. Vous devez:
  - comprendre ce que chaque colonne signifie et
	pourquoi les valeurs obtenues sont logiques ;
  - savoir dire à quelle colonne correspond les
	annotations que nous faisons sur les programmes ;
  - être capables de prédire comment chaque valeur
	changerait pour une entrée $10$ fois plus grande
	(et vérifier).

> Cf. [`profile.txt`](./profile.txt)

> Au début du fichier:
>   - `Timer unit: 1e-09 s` : unité de temps (ici microsecondes)
>
> Les colonnes:
>   - `Line #` : le n° de ligne
>   - `Hits` : le nombre d'exécutions ce cette ligne
>   - `Time` : temps total d'exécution pour cette ligne
>   - `Per Hit` : temps d'exécution moyen pour cette ligne
>   - `% Time` : proportion du temps d'exécution de cette ligne par
> 	rapport au total de la fonction
>   - `Line Contents` : contenu de la ligne (code)

5. Récupérez le code [`profile_insertSort.py`](./profile_insertSort.py)
et profilez toutes les fonctions sur une taille de
liste raisonnable. Comparez la version naïve en temps
$O(n^2)$ et plus rapide en temps $O(n \cdot \log n)$
(en utilisant `insertInOrder_rec`).

> Cf. [`profile_insertSort.txt`](./profile_insertSort.txt)

6. Récupérez le code [`profile_dicho.py`](./profile_dicho.py)
et profilez la fonction main. On va revisiter une
question posée au TP3 : si on fait $k$ recherches
dans un tableau de taille $1000000$, à partir de
quelle valeur de $k$ est-ce que cela devient
intéressant de trier le tableau auparavant ? 

7. Ecrivez les deux versions et trouvez la valeur
de $k$ où l'algorithme le plus rapide change.
Vérifiez que cela correspond à l'analyse qu'on a
fait au TP3.

8. Au passage, regardez le nombre d'appels à la
fonction dicho. Est-ce que cela correspond à vos
attentes ?

9. Récupérez le code [`profile_web.py`](./profile_web.py).
Vous arrivez dans une entreprise dont le métier est
de récupérer les informations de l'IUT afin de les
vendre à des écoles privées, mais le code écrit par
le stagiaire avant vous est trop lent pour le
management. Une personne pense que l'outil utilisé
(BeautifulSoup) est trop lent, une autre personne
pense que c'est l'algorithme écrit par le stagiaire,
une troisième pense que le problème est la connexion
Internet.

10. Essayez de comprendre le code, profilez-le, et
dites au management quelle est la partie sur laquelle
il y a du temps à gagner et sur quelles partie ce n'est
pas la peine de chercher à optimiser.
