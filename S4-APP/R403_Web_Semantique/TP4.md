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
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?nomDept

WHERE {
    ?idf a iut:Région .
    ?idf iut:nom "Île-de-France"@fr .
    
    ?idf iut:comprend ?dpt .
    ?dpt iut:nom ?nomDept .
}
```

## Q6

### Afficher les noms des universités fondées entre 1800 et 1900 (inclus), en classant les résultats de la plus récente à la plus ancienne.

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?nom ?annee

WHERE {
    ?univ a iut:Université .
    ?univ iut:nom ?nom .
    ?univ iut:annéeDeCréation ?annee .
    
    FILTER (
        ?annee >= "1800"^^xsd:gYear &&
        ?annee <= "1900"^^xsd:gYear
    ) .
}
```

## Q7

### Afficher les noms et les années de création des universités dont le nom commence par « Institut ».

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?nom ?annee

WHERE {
    ?univ a iut:Université .
    ?univ iut:nom ?nom .
    ?univ iut:annéeDeCréation ?annee .
    
    FILTER (
        STRSTARTS(?nom, "Institut")
    ) .
}
```

## Q8

### Afficher toutes les informations disponibles sur l’université dont le nom court est « HEC Paris ».

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?a ?prop

WHERE {
	?hec a iut:Université .
    ?hec iut:nomCourt "HEC Paris"@fr .
    ?hec ?a ?prop .
}
```

## Q9

### Afficher les noms des universités situées dans la région « Île-de-France ». Si un nom court est disponible pour une université, l’afficher à côté de son nom (condition non obligatoire d’avoir un nom court pour apparaitre dans les résultats).

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?nom ?depName

WHERE { 
    ?uni a iut:Université ;
    	iut:nom ?nom .
    
    ?dep a iut:Département ;
    	iut:nom ?depName ;
    	iut:comprend ?uni .
    
	?idf a iut:Région ;
    	iut:comprend ?dep ;
    	iut:nom "Île-de-France"@fr .
}
```

## Q10

### Afficher les noms des communes situées dans le même département que la commune d’« Orsay ».

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?regName ?comName

WHERE {
    ?region a iut:Département ;
    	iut:nom ?regName ;
    	iut:comprend ?orsay ;
    	iut:comprend ?commune .
    
	?commune a iut:Commune ;
    	iut:nom ?comName .
    
    ?orsay a iut:Commune ;
    	iut:nom "Orsay"@fr .
}
```

## Q11

### Afficher le nombre total de communes dans le département de Paris.

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ( COUNT(?commune) as ?nbCommunes )

WHERE {
    ?region a iut:Département ;
    	iut:nom "Paris"@fr ;
    	iut:comprend ?commune .
    
	?commune a iut:Commune ;
}
```

## Q12

### Afficher le nombre de communes pour chaque département, en classant les résultats par ordre décroissant (du département avec le plus de communes à celui avec le moins)

```sparql
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?deptName ( COUNT(?commune) as ?nbCommunes )
WHERE {
    ?region a iut:Département ;
    	iut:nom ?deptName ;
    	iut:comprend ?commune .
    
	?commune a iut:Commune ;
}
GROUP BY ?deptName
ORDER BY DESC(?nbCommunes)
```

## Q13

### Afficher les noms des 5 régions qui comptent le plus grand nombre d’universités en France.

```sparql
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?regName ( COUNT(?uni) as ?nbUni )
WHERE {
    ?region a iut:Région ;
    	iut:nom ?regName ;
    	iut:comprend ?dept .
    
    ?uni a iut:Université .
    
	?dept a iut:Département ;
    	iut:comprend ?uni .
}
GROUP BY ?regName
ORDER BY DESC(?nbUni)
LIMIT 5
```

## Q14

### Pour toutes les universités ayant un nom court, afficher dans une seule colonne le nom de l’université suivi de son nom court entre parenthèses. Utiliser la fonction `CONCAT` pour combiner les chaînes de caractères et le mot-clé `BIND` pour créer une nouvelle variable.

```sparql
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?nom
WHERE {
    ?uni a iut:Université ;
    	iut:nom ?nomUni ;
		iut:nomCourt ?nomCourtUni .
    BIND ( CONCAT (?nomUni, " (", ?nomCourtUni, ")") as ?nom )
}
LIMIT 20
```

## Q15

### Afficher les régions classées par le nombre maximal d'universités présentes dans un seul de leurs départements. Pour chaque région, on souhaite obtenir son nom et le nombre maximal d'universités dans un département de cette région (en affichant aussi le nom du département concerné).

```sparql
PREFIX iut: <https://cours.iut-orsay.fr/iws/>

SELECT ?regName ?deptName ?nbUni
WHERE {
  {
    # calculates universities per department
    SELECT ?regName ?deptName (COUNT(?uni) as ?nbUni)
    WHERE {
      ?region a iut:Région ;
        iut:nom ?regName ;
        iut:comprend ?dept .
      
      ?dept a iut:Département ;
        iut:nom ?deptName ;
        iut:comprend ?uni .
        
      ?uni a iut:Université .
    }
    GROUP BY ?regName ?deptName
  }
  
  {
    # finds the max universities per region
    SELECT ?regName (MAX(?count) as ?maxCount)
    WHERE {
      ?region a iut:Région ;
        iut:nom ?regName ;
        iut:comprend ?dept .
      
      ?dept a iut:Département ;
        iut:comprend ?uni .
        
      ?uni a iut:Université .
      
      {
        SELECT ?dept (COUNT(?u) as ?count)
        WHERE {
          ?dept iut:comprend ?u .
          ?u a iut:Université .
        }
        GROUP BY ?dept
      }
    }
    GROUP BY ?regName
  }
  
  # link both requests
  FILTER(?nbUni = ?maxCount)
}
ORDER BY ?regName DESC(?nbUni)
```
