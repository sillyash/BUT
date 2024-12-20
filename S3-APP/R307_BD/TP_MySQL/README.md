# TD MySQL

#### Ash MERIENNE | S3 APP1

## Exercices

### 1. On reprend la base de données des vins de l’an dernier, mais on ne reprend que les tables vin et viticulteur. Créez ces deux tables en mySql, et insérez les valeurs suivantes :

- [Insertions](./insertions.sql)
- [Creation des tables](./creation_base.sql)

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
DELIMITER $$

CREATE PROCEDURE leViti (
    IN viti INT,
    OUT nom VARCHAR(100)
)
BEGIN
    SELECT nom INTO nom
    FROM Viticulteur
    WHERE numViti = viti;
END $$

DELIMITER ;
```

### 4. Écrire une fonction `reg` qui prend en paramètre un *numVin* et une *région* et retourne oui ou non, selon que le vin vient ou non de cette région (pas très intéressant mais pas grave!) 

```sql
DELIMITER $$

CREATE PROCEDURE reg (
    IN viti INT,
    OUT region VARCHAR(100)
)
BEGIN
    SELECT region INTO region
    FROM Vin
    WHERE numViti = viti;
END $$

DELIMITER ;
```

### 5. Écrire une procédure qui retourne les régions des vins produits par un viticulteur dont on donne le nom.

```sql
DELIMITER $$

CREATE OR REPLACE PROCEDURE reg (
    IN viti VARCHAR(100),
    OUT regions VARCHAR(1000)
)
BEGIN
    SET regions = '';

    SELECT GROUP_CONCAT(region SEPARATOR ', ') INTO regions
    FROM Viticulteur VT
    INNER JOIN Vin VI ON VI.numViti = VT.numViti
    WHERE VT.nom = viti;
END $$

DELIMITER ;
```

### Regarder comment faire du JDBC vers mySQL

Mes classes Java :
- [JDBCTools](./JDBCTools.java)
  - [JDBCmySql](./JDBCmySql.java)
  - [JDBCoracle](./JDBCoracle.java)
