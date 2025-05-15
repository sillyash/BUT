# TP2

### Q1

#### Utiliser le tableau des règles RDFS pour déduire les triplets qu’un raisonneur RDFS peut inférer du graphe suivant

```turtle
iut:s rdf:type iut:o .
iut:o rdfs:subClassOf iut:q .
iut:q rdfs:subClassOf iut:r .
```

```turtle
iut:s rdfs:subClassOf iut:q .
iut:s rdfs:subClassOf iut:r .
```

### Q2

#### Utiliser le tableau des règles RDFS pour déduire les triplets qu’un raisonneur RDFS peut inférer du graphe suivant :

```turtle
iut:s iut:p iut:o .
iut:p rdfs:domain iut:C .
rdfs:domain rdfs:range rdfs:Class .
iut:p rdfs:subPropertyOf iut:q .
```

