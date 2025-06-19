# TP4

## Q4

### Lister toutes les régions avec leurs noms

```sparql
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX iut: <http://iut.orsay.fr/iws/>

SELECT ?lab ?reg

WHERE {
    ?reg a iut:Region .
    ?reg rdfs:label ?lab .
}

ORDER BY ?lab
LIMIT 20
OFFSET 0
```

### Afficher l’année de création de l’ « Université Paris-Saclay »@fr

```sparql
```

## Q5

### Afficher les noms des départements situés dans la région « Île-de-France ».

```sparql
```

## Q6

### Afficher les noms des universités fondées entre 1800 et 1900 (inclus), en classant les résultats de la plus récente à la plus ancienne.

```sparql
```

## Q7

### Afficher les noms et les années de création des universités dont le nom commence par « Institut ».

```sparql
```

## Q8

### Afficher toutes les informations disponibles sur l’université dont le nom court est « HEC Paris ».

```sparql
```

## Q9

### Afficher les noms des universités situées dans la région « Île-de-France ». Si un nom court est disponible pour une université, l’afficher à côté de son nom (condition non obligatoire d’avoir un nom court pour apparaitre dans les résultats).

```sparql
```

## Q10

### Afficher les noms des communes situées dans le même département que la commune d’« Orsay ».

```sparql
```

## Q11

### Afficher le nombre total de communes dans le département de Paris.

```sparql
```

## Q12

### Afficher le nombre de communes pour chaque département, en classant les résultats par ordre décroissant (du département avec le plus de communes à celui avec le moins)

```sparql
```

## Q13

### Afficher les noms des 5 régions qui comptent le plus grand nombre d’universités en France.

```sparql
```

## Q14

### Pour toutes les universités ayant un nom court, afficher dans une seule colonne le nom de l’université suivi de son nom court entre parenthèses. Utiliser la fonction `CONCAT` pour combiner les chaînes de caractères et le mot-clé `BIND` pour créer une nouvelle variable.

```sparql
```

## Q15

### Afficher les régions classées par le nombre maximal d'universités présentes dans un seul de leurs départements. Pour chaque région, on souhaite obtenir son nom et le nombre maximal d'universités dans un département de cette région (en affichant aussi le nom du département concerné).

```sparql
```
