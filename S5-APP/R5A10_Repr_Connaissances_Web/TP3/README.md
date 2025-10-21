# TP3

Enrichissement de données avec Wikidata

---

## A

### 1 - Relier les départements et récupérer population / surface

Dans cette première partie, vous allez écrire une requête `CONSTRUCT` qui :
- Relie chaque département de votre graphe local à l’élément Wikidata correspondant,
- Crée un lien `owl:sameAs` entre les deux,
- Copie dans votre graphe la population et la surface (area) récupérées depuis
Wikidata.

```tsx
#------------------------------- SELECT -------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

select ?department ?wikidataDepartment ?departmentLabel ?population ?area
where {
    ?department a rcw:Département ;
      rdfs:label ?departmentLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikidataDepartment wdt:P31 wd:Q6465 ;
          wdt:P2046 ?area ;
          wdt:P1082 ?population ;
          rdfs:label ?departmentLabel .
  
      filter(lang(?departmentLabel) = "fr")
    }
}

#------------------------------- CONSTRUCT -------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

construct {
    ?department owl:sameAs ?wikidataDepartment ;
      rcw:surface ?area ;
      rcw:population ?population .
}
where {
    ?department a rcw:Département ;
      rdfs:label ?departmentLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikidataDepartment wdt:P31 wd:Q6465 ;
          wdt:P2046 ?area ;
          wdt:P1082 ?population ;
          rdfs:label ?departmentLabel .
  
      filter(lang(?departmentLabel) = "fr")
    }
}

#------------------------------- INSERT -------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

insert {
    ?department owl:sameAs ?wikidataDepartment ;
      rcw:surface ?area ;
      rcw:population ?population .
}
where {
    ?department a rcw:Département ;
      rdfs:label ?departmentLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikidataDepartment wdt:P31 wd:Q6465 ;
          wdt:P2046 ?area ;
          wdt:P1082 ?population ;
          rdfs:label ?departmentLabel .
  
      filter(lang(?departmentLabel) = "fr")
    }
}
```

Consignes :
- Identifiez les départements sur Wikidata à partir de leur nom en français pour
faire la correspondance.
- Dans le graphe local, enregistrez les valeurs extraites avec les
propriétés rcw:population et rcw:surface.
- Vérifiez après exécution que tous les départements ont bien un lien `owl:sameAs`.
- Vous pouvez ensuite transformer la requête `CONSTRUCT` en `INSERT` pour
sauvegarder les données dans votre graphe.

### 2 - Comparer la densité des départements

Écrivez une requête `SELECT` qui calcule la densité de population de chaque département
(population / surface) et affiche les 10 départements ayant la plus haute densité.

```tsx
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>

select ?departmentLabel ((?population / ?surface) as ?densite)
where {
    ?department a rcw:Département ;
      rdfs:label ?departmentLabel ;
      rcw:surface ?surface ;
      rcw:population ?population .
} order by desc(?densite) limit 10
```

## B

### 1 - Relier les régions

Dans cette partie, vous allez écrire une autre requête `CONSTRUCT` qui relie vos
régions locales aux entités Wikidata correspondantes.

Consignes :
- Identifiez les régions à partir de leur nom en français pour faire la correspondance.
- Ajoutez le lien `owl:sameAs` entre la région locale et l’entité Wikidata.

```tsx
#--------------------------------- SELECT -----------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

select ?region ?regionLabel
where {
    ?region a rcw:Région ;
      rdfs:label ?regionLabel
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          rdfs:label ?regionLabel .
  
        minus {
          ?wikiDataRegion wdt:P1366 ?x .
        }
      filter(lang(?regionLabel) = "fr")
    }
}

#----------------------------- CONSTRUCT ---------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

construct {
    ?region owl:sameAs ?wikiDataRegion .
}
where {
    ?region a rcw:Région ;
      rdfs:label ?regionLabel
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          rdfs:label ?regionLabel .
  
        minus {
          ?wikiDataRegion wdt:P1366 ?x .
        }
      filter(lang(?regionLabel) = "fr")
    }
}

#----------------------------- INSERT ---------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

insert {
    ?region owl:sameAs ?wikiDataRegion .
}
where {
    ?region a rcw:Région ;
      rdfs:label ?regionLabel
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          rdfs:label ?regionLabel .
  
        minus {
          ?wikiDataRegion wdt:P1366 ?x .
        }
      filter(lang(?regionLabel) = "fr")
    }
}
```

### 2 - Extraire et insérer les relations de voisinage de régions

Une fois les liens `owl:sameAs` enregistrés, écrivez une deuxième requête qui utilise
ces correspondances pour importer les relations de voisinage.

Cette requête interrogera Wikidata et récupérera toutes les relations `wdt:P47 `
entre les régions françaises.

Il faudra directement faire correspondre les régions Wikidata aux régions locales grâce aux
liens `owl:sameAs` déjà présents dans votre graphe et créer dans votre graphe local des
triplets de la forme `reg:11` `rcw:sharesBorderWith` `reg:13`.

```tsx

#--------------------------------- SELECT ---------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

select ?region ?limitrophe ?regionLabel ?limitropheLabel
where {
    ?region a rcw:Région ;
    owl:sameAs ?wikiDataRegion ;
      rdfs:label ?regionLabel .

  ?limitrophe a rcw:Région ;
    owl:sameAs ?wikiDataLimitrophe ;
      rdfs:label ?limitropheLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          wdt:P47 ?wikiDataLimitrophe .
    }
}

#--------------------------------- CONSTRUCT ---------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

construct {
    ?region rcw:shareBorderWith ?limitrophe .
}
where {
    ?region a rcw:Région ;
    owl:sameAs ?wikiDataRegion ;
      rdfs:label ?regionLabel .

  ?limitrophe a rcw:Région ;
    owl:sameAs ?wikiDataLimitrophe ;
      rdfs:label ?limitropheLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          wdt:P47 ?wikiDataLimitrophe .
    }
}

#--------------------------------- INSERT ---------------------------------

PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
PREFIX owl: <http://www.w3.org/2002/07/owl#>
PREFIX wd: <http://www.wikidata.org/entity/>
PREFIX wdt: <http://www.wikidata.org/prop/direct/>
PREFIX wikibase: <http://wikiba.se/ontology#>
PREFIX p: <http://www.wikidata.org/prop/>
PREFIX ps: <http://www.wikidata.org/prop/statement/>
PREFIX pq: <http://www.wikidata.org/prop/qualifier/>
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX bd: <http://www.bigdata.com/rdf#>

insert {
    ?region rcw:shareBorderWith ?limitrophe .
}
where {
    ?region a rcw:Région ;
    owl:sameAs ?wikiDataRegion ;
      rdfs:label ?regionLabel .

  ?limitrophe a rcw:Région ;
    owl:sameAs ?wikiDataLimitrophe ;
      rdfs:label ?limitropheLabel .
    
    service <https://query.wikidata.org/sparql> {
        ?wikiDataRegion wdt:P31 wd:Q36784 ;
          wdt:P47 ?wikiDataLimitrophe .
    }
}
```

### 3 - Extraire et insérer les relations de voisinage de régions

Une fois les triples importés, vous pouvez interroger votre graphe avec
des requêtes simples et parlantes :
1. Lister les régions voisines de la région Ile-de-France
2. Quels sont les 5 régions ayant le plus de frontières avec d’autres régions
françaises ?

```tsx
# 1)

PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>

select ?limitropheLabel 
where {
    ?idf a rcw:Région ;
      rdfs:label "Île-de-France"@fr ;
      rcw:shareBorderWith ?limitrophe .
    
    ?limitrophe rdfs:label ?limitropheLabel .
}

#2)

PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
PREFIX rcw: <https://cours.iut-orsay.fr/rcw/>
select ?regionLabel (count(?limitrophe) as ?limitropheCount)
where {
    ?region a rcw:Région ;
      rcw:shareBorderWith ?limitrophe ;
      rdfs:label ?regionLabel .
}
group by ?regionLabel
order by desc(?limitropheCount)
limit 5
```
