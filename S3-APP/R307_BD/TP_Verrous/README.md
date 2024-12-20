# TP Verrous

#### Ashley MERIENNE

## Travail préliminaire

### 1. (Re)créez la table Client

```sql
client(login, nomClient, prenomClient, motDePasse, adresse)
```

### 2. Insérez cinq clients

```sql
...
```

### 3. Tapez *commit* pour enregistrer les changements

```sql
COMMIT;
```

### 4. Ouvrez une deuxième connexion SQL Developer

menu *File* -> New -> Database Connection, sous le même usager.

> [!WARNING]
> Connection Type : TNS
> Network Alias : ETUDOM

> [!NOTE] 
> Si vous n’arrivez pas à selection un alias de réseau, creear une connexion avec les paramètres par défauts

### 5. S’assurer d’être en *Autocommit Off*.

Pour ce faire sous SQL Developer :
- choisir le menu Tools puis Preferences
- ouvrir le petit + à gauche de base de données
- cliquer sur Avancé
- décocher Autocommit in SQL ou Validation automatique
- décocher Autocommit in SQL Worksheet

## Accès simultané aux données


### 1. Dans la fenêtre 2, peut-on faire un SELECT sur la table concernée par la mise à jour effectuée dans la fenêtre 1 ?


### 2. Dans la fenêtre 2, peut-on faire un UPDATE sur la ligne concernée par la mise à jour effectuée dans la fenêtre 1 ?


### 3. Dans la fenêtre 2, peut-on faire un UPDATE sur une ligne de la table non concernée par la mise à jour effectuée dans la fenêtre 1 ?


### 4. Le comportement au niveau des verrous est-il le même si la requête UPDATE effectuée dans la 2ème fenêtre est remplacée par une suppression dans la table ?


