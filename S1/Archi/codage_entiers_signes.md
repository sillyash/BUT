# Archi

## Codage des entiers signés

### 06-11-2023

#### Vocabulaire

BN: Binaire naturel

C1: Complément à un

### Problème

Pas de signes, que des 0 ou 1.

#### Complément à un (restreint)

**valeur positive:** identique aux nombres naturels

**valeur négative:** ...

Temps d'exécution de 2 étapes

#### Complément à deux (vrai)

**valeur positive:** identique aux nombres naturels

**valeur négative:** addition de 1 au complément restreint

Temps d'exécution plus faible qu'avec le C1 (1 étape)

**C2 = C1 + 1**

#### Exemple

| Binaire | BN  | C1/CR | C2/CV |
|:-------:|:---:|:-----:|:-----:|
| 000     | 0   | + 0   | + 0   |
| 001     | 1   | + 1   | + 1   |
| 010     | 2   | + 2   | + 2   |
| 011     | 3   | + 3   | + 3   |
| 100     | 4   | - 3   | - 4   |
| 101     | 5   | - 2   | - 3   |
| 110     | 6   | - 1   | - 2   |
| 111     | 7   | - 0   | - 1   |

#### Codage par excès (ou par biais)

**Principe**

Soit x la valeur en décimal qu'on souhaite coder

Soit e l'excès (biais) à ajouter

Le code x par excès de e est égal au code de x+e en binaire naturel

**Remarque**

En général, l'excès e est égal à 2⁽n-1⁾ - 1, n étant le nombre de bits utilisés

Ceci permet d'avoir la moitié des codes pour les nombres négatifs et l'autre moitié pour les nombres positifs

**Avantages**

Opérations de comparaison rapides

Utilisé pour le codage de l'exposant des réels dans la norme IEEE754

**Exemple sur 8 bits**

- n = 8 bits -> e = 2⁷

#### Remarques

Sur un nombre signé, il faut toujours préciser le nombre de bits sur lequel est codé le nombre.
