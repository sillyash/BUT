# TP6

## Q2

### Afficher les 10 communes les plus proches à la commune d’Orsay avec leurs coordonnées géographiques.

```sparql
PREFIX iut: 	<https://cours.iut-orsay.fr/iws/>
PREFIX rdf: 	<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: 	<http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: 	<http://www.w3.org/2001/XMLSchema#>
PREFIX geo: 	<http://www.opengis.net/ont/geosparql#>
PREFIX geof: 	<http://www.opengis.net/def/function/geosparql/>
PREFIX uom: 	<http://www.opengis.net/def/uom/OGC/1.0/>

# Afficher les 10 communes les plus proches à la commune d’Orsay avec leurs coordonnées géographiques

SELECT ?communeName (ROUND(?distance) as ?dist)
WHERE {
    ?orsay a iut:Commune ;
    	iut:nom "Orsay"@fr ;
    	geo:hasGeometry/geo:asWKT ?orsayWKT .
    
    ?commune a iut:Commune ;
        iut:nom ?communeName ;
        geo:hasGeometry/geo:asWKT ?communeWKT .

    BIND(geof:distance(?communeWKT, ?orsayWKT, uom:metre) AS ?distance)
}
ORDER BY ?distance
LIMIT 10
OFFSET 1
```

## Q3

### Parmi les communes du département de l’Essonne, afficher les deux communes qui sont les plus éloignées l’une de l’autre, en affichant la distance entre ces deux communes et les coordonnées géographiques de ces communes.

```sparql
PREFIX iut: 	<https://cours.iut-orsay.fr/iws/>
PREFIX rdf: 	<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: 	<http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: 	<http://www.w3.org/2001/XMLSchema#>
PREFIX geo: 	<http://www.opengis.net/ont/geosparql#>
PREFIX geof: 	<http://www.opengis.net/def/function/geosparql/>
PREFIX uom: 	<http://www.opengis.net/def/uom/OGC/1.0/>

# Parmi les communes du département de l’Essonne, afficher les deux communes qui sont les plus éloignées l’une de l’autre, en affichant la distance entre ces deux communes et les coordonnées géographiques de ces communes

SELECT ?commune1Nom ?commune2Nom ?commune1WKT ?commune2WKT (ROUND(?distance) as ?dist)
WHERE {
    ?commune1 a iut:Commune ;
    	iut:nom ?commune1Nom ;
    	geo:hasGeometry/geo:asWKT ?commune1WKT .
    
    ?commune2 a iut:Commune ;
    	iut:nom ?commune2Nom ;
    	geo:hasGeometry/geo:asWKT ?commune2WKT .
    
    ?essonne a iut:Département ;
    	iut:nom "Essonne"@fr ;
    	iut:comprend ?commune1 ;
    	iut:comprend ?commune2 .

    BIND(geof:distance(?commune1WKT, ?commune2WKT, uom:metre) AS ?distance)
}
ORDER BY DESC(?distance)
LIMIT 1
```

## Q4

### Écrivez une requête CONSTRUCT pour créer un nouveau graphe RDF qui contient uniquement les communes situées dans un rayon de 30 km autour de la commune d’Orsay.

La requête doit inclure les propriétés suivantes pour chaque commune : son nom, ses coordonnées géographiques, et son département.

Vous pouvez télécharger le résultat de la requête sous forme de graphe Turtle.

```sparql
PREFIX iut: 	<https://cours.iut-orsay.fr/iws/>
PREFIX rdf: 	<http://www.w3.org/1999/02/22-rdf-syntax-ns#>
PREFIX rdfs: 	<http://www.w3.org/2000/01/rdf-schema#>
PREFIX xsd: 	<http://www.w3.org/2001/XMLSchema#>
PREFIX geo: 	<http://www.opengis.net/ont/geosparql#>
PREFIX geof: 	<http://www.opengis.net/def/function/geosparql/>
PREFIX uom: 	<http://www.opengis.net/def/uom/OGC/1.0/>

CONSTRUCT {
    ?c a iut:Commune ;
    	geo:hasGeometry/geo:asWKT ?cWKT ;
    	iut:Département ?cDep ;
    	iut:nom ?cNom .
}
WHERE {
    ?orsay a iut:Commune ;
    	iut:nom "Orsay"@fr ;
    	geo:hasGeometry/geo:asWKT ?orsayWKT .
    
    ?c a iut:Commune ;
    	iut:nom ?cNom ;
    	geo:hasGeometry/geo:asWKT ?cWKT .
   
    ?cDep a iut:Département ;
    	iut:comprend ?c .
    
    BIND(geof:distance(?cWKT, ?orsayWKT, uom:metre) AS ?dist)
    FILTER(?dist <= 30000)
}
```

## Q5

### Visualisation des données sur une Carte avec YASGUI


```sparql

```
