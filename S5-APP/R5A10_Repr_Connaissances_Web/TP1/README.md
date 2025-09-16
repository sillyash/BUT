# TP1

De données tabulaires (CSV) vers un graphe RDF
avec Ontorefine

---

## Partie A

### Q2. Proposez un modèle RDF qui relie ces informations

#### Quelles seront vos ressources (entités) ?

>Exemple : Région, Département, etc.

- Région
- Département
- Circonscription
- Fonction / PCS (Professions et Catégories Socioprofessionnelles)
- Député / élu

#### Quelles seront vos propriétés (relations) ?

>Exemple : `rcw:comprend`, `rcw:nom`, `rcw:prénom`, etc.

- Région
  - `rdfs:label `
  - `rcw:comprend` Département
- Département
  - `rdfs:label `
  - `rcw:region` Région
- Circonscription
  - `rdfs:label `
  - `rcw:departement` Département
- PCS
  - `rdfs:label `
- Élu
  - `rcw:nom`
  - `rcw:prénom`
  - `rcw:genre`
  - `rcw:dateNaissance`
  - `rcw:debutMandat`

#### Quelles informations seront représentées comme littéraux et lesquellescomme IRIs ?

Littéraux:

- `rcw:prénom`
  - `xsd:string`
- `rcw:nom`
  - `xsd:string`
- `rdfs:label `
  - `xsd:string`
- `rcw:dateNaissance`
  - `xsd:date`
- `rcw:debutMandat`
  - `xsd:date`

IRIs:

- Département
- Région
- Circonscription
- PCS
- Élu

### Q3. Pour rendre votre modélisation concrète, prenez un exemple de député et décrivez-le dans votre modèle

> Vous pouvez choisir le député de la 5e circonscription de l’Essonne (où se trouve Orsay), ou bien le député de votre propre circonscription.
>
> Montrez comment vous reliez ce député à sa circonscription, à son département (Essonne, code 91) et à sa région (Île-de-France).

```GraphQL
PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> .
PREFIX rcw: <https://cours.iut-orsay.fr/rcw/> .
PREFIX dep: <https://cours.iut-orsay.fr/rcw/departement/> .
PREFIX reg: <https://cours.iut-orsay.fr/rcw/region/> .
PREFIX pcs: <https://cours.iut-orsay.fr/rcw/PCS/> .
PREFIX circ: <https://cours.iut-orsay.fr/rcw/circonscription/> .
PREFIX depu: <https://cours.iut-orsay.fr/rcw/depute/> .

reg:11 a rcw:Region ;
  rdfs:label    "Île de France"@fr ;
  rcw:comprend  dep:91 .

dep:91 a rcw:Departement ;
  rdfs:label    "Essonne"@fr ;
  rcw:region    reg:11 .

circ:9105 rcw:Circonscription ;
  rdfs:label      "5ème Circonscription"@fr ;
  rcw:Departement dep:91 .

pcs:85 a rcw:PCS ;
  rdfs:label  "Personne diverse sans activité professionnelle de moins de 60 ans (sauf retraité)" ;

depu:488 a rcw:Elu ;
  rcw:nom             "Midy"@fr ;
  rcw:prénom          "Paul"@fr ;
  rcw:dateNaissance   "1983-01-25"^^xsd:date ;
  rcw:debutMandat     "2024-07-08"^^xsd:date ;
  rcw:circonscription circ:9105 ;
  rcw:genre           "M"@fr ;
  rcw:pcs             pcs:85 .
```

## Partie B

### Importation des données dans OntoRefine

1. Importer le fichier `.csv` dans OntoRefine
2. Editer le squelette RDF:
  i. Ajouter les préfixes
  ii. Créer les ressources et les propriétés
3. Exporter les données au format RDF (Turtle)

### Méthode

1. Commencer par le fichier [`regions.csv`](./regions.csv) pour créer les ressources `Région` (préfixe `reg:`)
2. Ensuite, importer le fichier [`departements.csv`](./departements.csv) pour créer les ressources `Département` (préfixe `dep:`) et les relier aux régions
3. Puis, importer le fichier [`elus-deputes.csv`](./elus-deputes.csv) pour créer les ressources `Circonscription` (préfixe `circ:`), `Depute` (préfixe `depu:`), et `PCS` (préfixe `pcs:`), et les relier aux départements.

On obtienra ainsi les fichiers suivants:
- [`regions.ttl`](./regions.ttl)
- [`departements.ttl`](./departements.ttl)
- [`deputes_pcs_circonscriptions.ttl`](./deputes_pcs_circonscriptions.ttl)

## Partie C

Après avoir modélisé vos données [Partie A](#partie-a) et généré vos graphes RDF avec
Ontorefine [Partie B](#partie-b), vous allez maintenant charger ces données dans une
base RDF afin de pouvoir les interroger avec le langage SPARQL.

### Q1. Quelles catégories socio-professionnelles sont les plus représentées à l’Assemblée nationale ?

```Sparql
SELECT ?pcsName (COUNT(?pcs) AS ?nbPcs)
WHERE {
    ?depute a rcw:Depute ;
    	rcw:pcs ?pcs .
    
    ?pcs rdfs:label ?pcsName .
    
}
GROUP BY ?pcs ?pcsName
ORDER BY DESC(?nbPcs)
```

Voici les 10 PCS les plus représentés à l'assemblée:

| Rang | PCS                                                                               | Nombre |
|:----:|:----------------------------------------------------------------------------------|:------:|
| 1    | Cadre de la fonction publique                                                     | 103    |
| 2    | Cadre administratif et commercial d'entreprise                                    | 77     |
| 3    | Profession libérale                                                               | 62     |
| 4    | Professeur, profession scientifique                                               | 57     |
| 5    | Ancien cadre                                                                      | 40     |
| 6    | Personne diverse sans activité professionnelle de moins de 60 ans (sauf retraité) | 32     |
| 7    | Chef d'entreprise de 10 salariés ou plus                                          | 19     |
| 8    | Ingénieur et cadre technique d'entreprise                                         | 18     |
| 9    | Profession intermédiaire de la santé et du travail social                         | 14     |
| 10   | Ancien artisan, commerçant,chef d'entreprise                                      | 12     |

### Q2. Quelles régions comptent le plus grand nombre de députés ?

```Sparql
SELECT ?regionName (COUNT(?depute) AS ?NbDepute)
WHERE {
    ?depute a rcw:Depute ;
    	rcw:departement ?departement .
    
    ?departement rcw:Region ?region .
    ?region rdfs:label ?regionName .
}
GROUP BY ?regionName
ORDER BY DESC(?NbDepute)
```

Voici les 10 régions avec le plus de députés:
| Rang | Région                     | Nombre |
|:----:|:---------------------------|:------:|
| 1    | Île-de-France              | 97     |
| 2    | Auvergne-Rhône-Alpes	      | 65     |
| 3    | Hauts-de-France            | 50     |
| 4    | Grand Est                  | 49     |
| 5    | Occitanie                  | 49     |
| 6    | Nouvelle-Aquitaine         | 49     |
| 7    | Provence-Alpes-Côte d'Azur	| 42     |
| 8    | Pays de la Loire           | 30     |
| 9    | Normandie                  | 28     |
| 10   | Bourgogne-Franche-Comté    | 27     |

### Q3. Quels départements ont, en moyenne, les députés les plus jeunes ?

```Sparql
SELECT ?departementNom (ROUND(SUM(YEAR(NOW())-YEAR(?dateNaissance))/COUNT(?depute)) AS ?ageMoy)
WHERE {
    ?depute a rcw:Depute ;
    	rcw:dateNaissance ?dateNaissance ;
    	rcw:departement ?departement .
    
    ?departement rdfs:label ?departementNom
    
    
}
GROUP BY ?departementNom
ORDER BY DESC(?ageMoy)
```

Voici les 10 régions avec le plus de députés:
| Rang | Département                | Age moyen |
|:----:|:---------------------------|:---------:|
| 1    | Haute-Corse                | 69        |
| 2    | Corrèze                    | 68        |
| 3    | Cantal                     | 67        |
| 4    | Alpes-de-Haute-Provence    | 65        |
| 5    | Côte-d'Or                  | 65        |
| 6    | Tarn-et-Garonne            | 65        |
| 7    | Guadeloupe                 | 64        |
| 8    | Indre                      | 62        |
| 9    | Hautes-Pyrénées            | 62        |
| 10   | Yonnen                     | 62        |

### Q4. Quelle est la répartition hommes/femmes parmi les députés de l’Assemblée nationale (en pourcentage) ?

```Sparql
SELECT ?genre (CONCAT(SUBSTR(STR(((COUNT(?depute)/?nbDepTT)*100)), 1, 5), "%") AS ?nbDep)
WHERE {
    ?depute a rcw:Depute ;
    	rcw:genre ?genre .
    
    {
        SELECT (COUNT(?d) AS ?nbDepTT)
    	WHERE {
    		?d a rcw:Depute .
        }
    }
}
GROUP BY ?genre ?nbDepTT
```

| Genre | Proportion |
|:-----:|:----------:|
| M     | 63.79%     |
| F     | 36.20%     |

### Q5. Quelles régions présentent la plus faible proportion de députées femmes ?

```Sparql
SELECT ?regName 
       (((COUNT(?deputeF) / ?nbDepTT) * 100) AS ?nbDep) 
WHERE {
  ?deputeF a rcw:Depute ;
          rcw:genre "F" ;
          rcw:departement ?dept .

  ?dept rcw:Region ?reg .
  ?reg rdfs:label ?regName .

  # Subquery to count total deputies in the same region
  {
    SELECT ?reg (COUNT(?d) AS ?nbDepTT)
    WHERE {
      ?d a rcw:Depute ;
         rcw:departement ?dept2 .

      ?dept2 rcw:Region ?reg .
    }
    GROUP BY ?reg
  }
}
GROUP BY ?regName ?nbDepTT
ORDER BY ASC(?nbDep)
```

Les 10 régions avec le moins de femmes députées:

| Région                     | Proportion |
|:---------------------------|-----------:|
| Centre-Val de Loire        | 13,636%    |
| Hauts-de-France            | 22%        |
| Martinique                 | 25%        |
| Grand Est                  | 26,531%    |
| La Réunion                 | 28,571%    |
| Normandie                  | 32,143%    |
| Provence-Alpes-Côte d'Azur | 35,714%    |
| Nouvelle-Aquitaine         | 36,735%    |
| Bourgogne-Franche-Comté    | 37,037%    |
| Occitanie                  | 38,776%    |

