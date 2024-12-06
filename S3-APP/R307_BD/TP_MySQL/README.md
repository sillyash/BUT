# TD MySQL

## Ash MERIENNE | S3 APP1

### 1. On reprend la base de données des vins de l’an dernier, mais on ne reprend que les tables vin et viticulteur. Créez ces deux tables en mySql, et insérez les valeurs suivantes :

```sql
INSERT INTO Viticulteur Values (NULL,'Tarroux', 'Philippe', 'Carcassonne');
INSERT INTO Viticulteur Values (NULL, 'Maillard', 'Catherine', 'Bordeaux');
INSERT INTO Viticulteur Values (NULL, 'Rallègue', 'Didier', 'Vauvert');
INSERT INTO Viticulteur Values (NULL, 'Bollinger', 'Charles', 'Corrèze');
INSERT INTO Viticulteur Values (NULL, 'Bréchard', 'Maurice', 'Dijon');
INSERT INTO Viticulteur Values (NULL, 'Macaze', 'Philippe', 'Auxerre');
INSERT INTO Viticulteur Values (NULL, 'Cliquot', 'Julie', 'Epernay');

INSERT INTO Vin values(NULL, 'Côtes du Jura',2004, 'Jura', 3);
INSERT INTO Vin values(NULL, 'Château Maillard',2002, 'Bordeaux', 2);
INSERT INTO Vin values(NULL, 'Château Lafourche ',2005, 'Médoc', 2);
INSERT INTO Vin values(NULL, 'Vin Jaune',2003, 'Jura', 3);
INSERT INTO Vin values(NULL, 'Minervois',2008, 'Languedoc', 1);
INSERT INTO Vin values(NULL, 'Limoux',2007, 'Languedoc', 1);
INSERT INTO Vin values(NULL, 'Veuve Cliquot',2013, 'Champagne', 7);
INSERT INTO Vin values(NULL, 'Dom Perignon',2008, 'Champagne', 7);
INSERT INTO Vin values(NULL, 'Châblis',2002, 'Bourgogne', 5);
```

[Creation des tables](./creation_base.sql)

### 2. Afficher la structure, puis le contenu de la table Vin ; idem pour la table Viticulteur

```sql
DESC Vin;
SELECT * FROM Vin;
```

```sql
DESC Viticulteur;
SELECT * FROM Viticulteur;
```

### 3. Ecrire une procédure `leViti` qui prend en paramètre d’entrée un *numViti* et en paramètre de sortie un nom, puis l’exécuter avec **4** comme paramètre d’entrée, et afficher le paramètre calculé

```sql

```

### 4. Écrire une fonction `reg` qui prend en paramètre un *numVin* et une *région* et retourne oui ou non, selon que le vin vient ou non de cette région (pas très intéressant mais pas grave!) 

```sql

```

### 5. Écrire une procédure qui retourne les régions des vins produits par un viticulteur dont on donne le nom.

```sql

```
