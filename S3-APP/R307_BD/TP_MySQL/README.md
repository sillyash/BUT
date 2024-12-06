# TD MySQL

## Ash MERIENNE | S3 APP1

### 1. On reprend la base de données des vins de l’an dernier, mais on ne reprend que les tables vin et viticulteur. Créez ces deux tables en mySq, et insérez les valeurs suivantes :

- [Creation des tables](./creation_base.sql)
- [Insertions](./insertions.sql)

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
