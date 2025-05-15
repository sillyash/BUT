# TP3 

## Modélisation de données réelles avec RDF et RDFS

En utilisant l’éditeur de texte de votre choix, écrire dans un fichier (.ttl)
la description en RDF de toutes les régions dans le fichier Regions.csv.
Utiliser la relation iut:nom pour les noms des régions et déclarer que le
nom est en français.

```turtle
iut:idf a iut:Region ;
		iut:pays iut:france ;
		iut:idf iut:code "11"^xsd:integer ;
		iut:idf rdfs:label "Île-de-France"@fr .
```

En utilisant l’éditeur de texte de votre choix, écrire dans un fichier (.ttl)
la description en RDF de la commune d’Orsay et de l’université Paris-
Saclay en se basant sur leurs descriptions dans les fichiers CSV.

> Il faudra inclure dans cette description les régions et les
départements qui les comprennent.

> Pour la modélisation, utiliser la relation iut:comprend pour déclarer
le lien entre les régions, départements, communes et les universités.

```turtle
iut:france a iut:Country ;
		rdfs:label "France"@fr .

iut:essonne a iut:Department ;
		iut:country iut:france ;
		rdfs:label "Essonne"@fr .

iut:orsay a iut:Commune ;
		iut:departement iut:essonne ;
		iut:country iut:france ;
		rdfs:label "Orsay"@fr .

iut:upsay a iut:Universite ;
		iut:Commune iut:orsay ;
		rdfs:label "Université Paris-Saclay"@fr .

iut:france iut:comprend iut:essonne .
iut:essonne iut:comprend iut:orsay .
iut:orsay iut:comprend iut:upsay.
```

On souhaite inclure les deux classes CollectivitéTerritoriale et
Établissement dans notre modélisation. Ajouter les triplets nécessaires afin
d’inférer que chaque région, département et commune est aussi une
CollectivitéTerritoriale et que chaque université est aussi un Établissement.


