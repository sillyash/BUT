# TP1

Les objectifs de ce TP1 sont de mesurer le temps de calcul des fonctions de recherche et de les comparer à la théorie.

On travaillera avec des listes d'entiers __Python__ `[1, 2, 3]`.

Puisqu'on mesure du temps de calcul, n'utilisez pas des exécuteurs en ligne ou sur des serveurs externes : il faut que le code soit exécuté localement.

## Notation $O(.)$

### 1. Proposer une comparaison asymptotique avec la notation $O(.)$ pour chacune des fonctions ci-dessous:

- $f(n) = 4n+7$
> $O(n)$

- $g(n) = 0,01n + log(3n)$
> $O(n)$

- $h(n) = 5log(n^2)$
> $O(log(n))$

- $j(n) = 5n^2 + 150n$
> $O(n^2)$

- $k(n) = \sum_{i=0}^n (3i+7)$
> $O(n)$

- $l(n) = $4n(2n + 1)(n^2 + 2)$ 
> $O(n^4)$

### 2. Ouvrir **Geogebra** et tracer les courbes de vos fonctions divisées par votre majoration asymptotique (la fonction dans le $O(.)$).

Dire pour chaque courbe obtenue si asymptotiquement (=quand n tend vers $\infty$) elle diverge (tend vers $\infty$), tend vers $0$ ou alors semble tendre vers une constante.

## Recherche classique


