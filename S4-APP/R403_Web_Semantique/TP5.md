# TP5

## Q1

### Modélisation des données en RDF avec GeoSPARQL

Utilisez le vocabulaire GeoSPARQL pour modéliser les entités géographiques
décrites ci-dessus dans un fichier Turtle nommé « paris.ttl ». N’oubliez pas
d’ajouter un label en français pour chaque entité et de vérifier la syntaxe de
votre graphe sur : http://ttl.summerofcode.be

> Check out [paris.ttl](./paris.ttl)

## Q2

### Interrogation des données avec GeoSPARQL

Créer un nouveau dépôt de données dans GraphDB nommé « Paris » et importer
votre fichier « paris.ttl » (vérifier que le bon dépôt de données est sélectionné
avant d’importer vos données).

#### En utilisant l'interface SPARQL dans GraphDB, activer les fonctionnalités de GeoSPARQL en exécutant la requête suivante :

```sparql
PREFIX geosparql: <http://www.ontotext.com/plugins/geosparql#>
INSERT DATA {
  [] geosparql:enabled "true" .
}
```

#### Écrivez une requête SPARQL qui permet d’afficher la distance en mètre (arrondi) entre la Tour Eiffel et la Tour Montparnasse.

```sparql
PREFIX geof: <http://www.opengis.net/def/function/geosparql/>
PREFIX geo: <http://www.opengis.net/ont/geosparql#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX iut: <https://cours.iut-orsay.fr/qar/>
PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/>

select (ROUND(geof:distance(?eiffelWKT, ?montparnasseWKT, uom:metre)) as ?distance)
where {
    ?eiffel a geo:Feature ;
    	rdfs:label "Tour Eiffel"@fr .
    
    ?eiffel geo:hasGeometry ?geo1 .
    ?geo1 geo:asWKT ?eiffelWKT .
    
    ?montparnasse a geo:Feature ;
    	rdfs:label "Tour Montparnasse"@fr .
    
    ?montparnasse geo:hasGeometry ?geo2 .
    ?geo2 geo:asWKT ?montparnasseWKT .
}
```

#### Écrivez une requête SPARQL qui permet d’afficher le musée le plus proche à chaque monument.

```sparql
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>
PREFIX geof: <http://www.opengis.net/def/function/geosparql/>
PREFIX geo: <http://www.opengis.net/ont/geosparql#>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX iut: <https://cours.iut-orsay.fr/qar/>
PREFIX uom: <http://www.opengis.net/def/uom/OGC/1.0/>

SELECT ?monument (ROUND(MIN(geof:distance(?WKTMonument, ?WKTMusee, uom:metre))) as ?distance)
WHERE {
    ?monument a iut:Monument ;
    	geo:hasGeometry ?geoMonument .
    
    ?musee a iut:Musée ;
    	geo:hasGeometry ?geoMusee .
    
    ?geoMonument geo:asWKT ?WKTMonument .
    ?geoMusee geo:asWKT ?WKTMusee .
}
GROUP BY ?monument
```

#### Écrivez une requête SPARQL qui permet de vérifier avec GeoSPARQL si la seine traverse Paris.

```sparql
PREFIX xsd: 	<http://www.w3.org/2001/XMLSchema#>
PREFIX geof: 	<http://www.opengis.net/def/function/geosparql/>
PREFIX geo: 	<http://www.opengis.net/ont/geosparql#>
PREFIX rdfs: 	<http://www.w3.org/2000/01/rdf-schema#>
PREFIX iut: 	<https://cours.iut-orsay.fr/qar/>
PREFIX uom: 	<http://www.opengis.net/def/uom/OGC/1.0/>

SELECT ?seine ?paris (geof:sfIntersects(?parisWKT, ?seineWKT) as ?intersection)
WHERE {
    ?seine a iut:Fleuve ;
    	rdfs:label "La Seine"@fr ;
    	geo:hasGeometry/geo:asWKT ?seineWKT .
    
    ?paris a iut:Commune ;
    	rdfs:label "Paris"@fr ;
    	geo:hasGeometry/geo:asWKT ?parisWKT .
}
```
