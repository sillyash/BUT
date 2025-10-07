# TP2

Introduction à Wikidata

---

## Requêtes

### 1. Compter 

Listez le nombre d’humains présents dans Wikidata.

```js
SELECT (COUNT(*) AS ?count)
WHERE {
  ?item wdt:P31 wd:Q5 .
}
```

### 2. Comparer

Classez les pays par leur surface.

Visualisez le résultat avec la vue 'Bubble Chart'.

```js
SELECT ?countryLabel ?area
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "fr,en". }
  ?country wdt:P31 wd:Q6256 .
  ?country wdt:P2046 ?area .
}
ORDER BY DESC(?area)
LIMIT 10
```

![areas](./areas.svg)
[See SVG file](./areas.svg)

| countryLabel                  | area                      |
|:------------------------------|--------------------------:|
| Russie                        | 17 075 400 km<sup>2</sup> |
| Canada                        | 9 984 670 km<sup>2</sup>  |
| États-Unis                    | 9 826 675 km<sup>2</sup>  |
| République populaire de Chine | 9 596 961 km<sup>2</sup>  |
| Brésil                        | 8 515 767 km<sup>2</sup>  |
| Australie                     | 7 692 024 km<sup>2</sup>  |
| Inde                          | 3 287 263 km<sup>2</sup>  |
| Argentine                     | 2 780 400 km<sup>2</sup>  |
| Kazakhstan                    | 2 724 900 km<sup>2</sup>  |
| Algérie                       | 2 381 741 km<sup>2</sup>  |


### 3. Explorer

Affichez toutes les capitales européennes. Essayez la vue 'Map'.

```js
SELECT ?countryLabel ?capitalLabel ?geoPoint
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "fr,en". }
  ?country wdt:P31 wd:Q6256;
           wdt:P36 ?capital .
  
  ?capital wdt:P625 ?geoPoint
}
```

[See GeoJSON file](./capitals.geojson)

### 4. Analyser

Quel continent compte le plus de lauréats du prix Nobel ?

Essayez la vue 'Area Chart'.

```js
SELECT ?continentLabel (COUNT(*) AS ?nobelPrizes)
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?person wdt:P31 wd:Q5 ;
          wdt:P166 ?nobel ;
          wdt:P27 ?country .
  
  ?nobel wdt:P279 wd:Q7191 .
  
  ?country wdt:P30 ?continent .
}
GROUP BY ?continentLabel
ORDER BY DESC(?nobelPrizes)
```

![nobels](./nobels.svg)
[See SVG file](./nobels.svg)

| continentLabel       | nobelPrizes |
|:---------------------|------------:|
| Europe               | 682         |
| North America        | 405         |
| Asia                 | 123         |
| Africa               | 36          |
| Eurasia              | 22          |
| Oceania              | 15          |
| South America        | 11          |
| Insular Oceania      | 5           |
| Australian continent | 3           |

### 5. Séries TV

Affichez les noms de tous les épisodes de chaque saison de la
série Breaking Bad.

Essayez la vue 'TreeMap'.

```js
SELECT ?seasonsLabel ?episodesLabel
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?BreakingBad wdt:P31 wd:Q5398426 ;
               wdt:P1476 "Breaking Bad"@en ;
               wdt:P527 ?seasons .
  
  ?seasons wdt:P527 ?episodes .
}
```

### 6. Art

Affichez les tableaux exposés à Paris produits par Claude Monet.

Essayez la vue 'Image Grid'.

```js
SELECT ?paintingLabel ?arrLabel ?image
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?ClaudeMonet wdt:P31 wd:Q5 ;
               wdt:P1559 "Claude Monet"@fr .
  
  ?painting wdt:P31 wd:Q3305213 ;
            wdt:P276 ?location ;
            wdt:P170 ?ClaudeMonet ;
            wdt:P18 ?image .
  
  ?location wdt:P131 ?arr .
  ?arr wdt:P131 wd:Q90 .
}
```

https://w.wiki/FRi4

### 7. Professions

Parmi les professions, lesquelles comptent le plus grand nombre
d’astronautes ?

Essayez la vue 'Bar Chart'.

```js
SELECT ?otherOccupationLabel (COUNT(*) AS ?nb)
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?astronaut wdt:P31 wd:Q5 ;
             wdt:P106 wd:Q11631 .
  
  ?astronaut wdt:P106 ?otherOccupation .
}
GROUP BY ?otherOccupationLabel
ORDER BY DESC(?nb)
OFFSET 1
```

![professions](./astronaut_professions.svg)
[See SVG file](./astronaut_professions.svg)

| Profession               | Count |
|--------------------------|------:|
| aircraft pilot           | 249   |
| engineer                 | 223   |
| military officer         | 218   |
| test pilot               | 82    |
| military flight engineer | 76    |
| physicist                | 53    |
| politician               | 50    |
| physician                | 50    |
| fighter pilot            | 40    |
| military personnel       | 37    |
| university teacher       | 31    |
| writer                   | 26    |
| businessperson           | 25    |
| explorer                 | 22    |
| flight engineer          | 21    |
| air force officer        | 19    |
| chemist                  | 18    |
| naval officer            | 16    |
| entrepreneur             | 14    |
| airman                   | 14    |
| test cosmonaut           | 14    |
| Research Cosmonaut       | 13    |
| astronomer               | 12    |
| member of the State Duma | 12    |
| ...                      |       |

### 8. Histoire

Affichez les lieux (avec coordonnées) des guerres auxquelles la France, le
Royaume-Uni ou les États-Unis ont participé.

Essayez la vue 'Map'.

```js
SELECT ?countryLabel ?warLabel ?coordinates
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?war wdt:P31 wd:Q198 ;
       wdt:P710 ?country ;
       wdt:P276 ?location .
  
  ?location wdt:P625 ?coordinates .
  
  FILTER(?country IN (wd:Q142, wd:Q145, wd:Q30))
}
```

[View GeoJSON file](./wars.geojson)

### 9. Politique

Affichez les présidents français avec leur nom, photo, date de début de
mandat et leur âge à ce moment.

Essayez la vue 'Timeline'.

```js
SELECT ?president ?presidentLabel ?startDate (CONCAT(STR(?age), " ans") AS ?ageLabel) (SAMPLE(?photo) AS ?photo) 
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "en". }
  
  ?president p:P39 ?statement ;
             wdt:P569 ?birthDate ;
             wdt:P18 ?photo .
  
  ?statement ps:P39 wd:Q191954 ;
             pq:P580 ?startDate .
  
  BIND(YEAR(?startDate) - YEAR(?birthDate) AS ?age)
}
GROUP BY ?president ?presidentLabel ?startDate ?age
ORDER BY ?startDate
```

### 10.  Sport

Affichez les joueurs de football français ayant gagné le Ballon d’or, avec leurs
photos et l’année dans laquelle ils ont gagné.

```js
SELECT ?joueur ?joueurLabel ?date ?image 
WHERE {
  SERVICE wikibase:label { bd:serviceParam wikibase:language "fr,en". }

  ?joueur wdt:P106 wd:Q937857 ;
          wdt:P27 wd:Q142 ;
          wdt:P18 ?image ;
          p:P166 ?statement .
  
  ?statement ps:P166 wd:Q166177 ;
             pq:P585 ?date .
} ORDER BY ?date
```

### 11.  Question du cours

Trouvez tous les pays finalistes de la Coupe du Monde FIFA.

Parmi ces pays, identifiez uniquement ceux qui ont connu un changement de
gouvernement la même année que leur arrivé en finale.

Pour ces pays, affichez:
- l’année de la finale
- le nom du nouveau Premier ministre
- le drapeau du pays

```js
SELECT ?final ?finalLabel ?finalDate ?country ?countryLabel ?headOfGov ?headOfGovLabel ?startGov ?flag
WHERE {
  ?final wdt:P31 wd:Q12708896 ;
          wdt:P1923 ?team ;
          wdt:P580 ?finalDate .
  ?team wdt:P17 ?country .
  ?country wdt:P41 ?flag ; 
            p:P6 ?head_of_gov_statement .
  ?head_of_gov_statement pq:P580 ?startGov ; 
                          ps:P6 ?headOfGov .
  ?headOfGov p:P39 ?position_statement .
  ?position_statement ps:P39 ?premierMinistre .
  
  {?premierMinistre wdt:P31 wd:Q14212} UNION {?premierMinistre wdt:P279 wd:Q14212} 
    
  FILTER ((YEAR(?startGov) = YEAR(?finalDate)))
  SERVICE wikibase:label { bd:serviceParam wikibase:language "[AUTO_LANGUAGE],en" . }
}
ORDER BY DESC(?finalDate)
```

### 12.  Recherche personnelle

Posez une question de votre choix à Wikidata, en utilisant
une visualisation autre qu’un simple tableau.

La question doit être intéressante et difficile à résoudre
avec un moteur de recherche classique

```SparQL

```
