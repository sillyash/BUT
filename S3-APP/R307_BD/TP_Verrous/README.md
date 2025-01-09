# TP Verrous

#### Ashley MERIENNE

- [fenetre1.sql](./fenetre1.sql)
- [fenetre2.sql](./fenetre2.sql)

## 1. Travail préliminaire

### a. (Re)créez la table Client

```sql
CREATE TABLE Client (
    login           VARCHAR2(30)    PRIMARY KEY NOT NULL,
    nomClient       VARCHAR2(100)   NOT NULL,
    prenomClient    VARCHAR2(50)    NOT NULL,
    motDePasse      VARCHAR2(70)    NOT NULL,
    adresse         VARCHAR2(180)
);
```

### b. Insérez cinq clients

```sql
INSERT INTO Client VALUES ('xXjohnXx', 'Doe', 'John', 'password123', '123 Fake Street');
INSERT INTO Client VALUES ('smithereen', 'Smith', 'Jane', 'securePass456', '456 Imaginary Road');
INSERT INTO Client VALUES ('charlie_brownie', 'Brown', 'Charlie', 'charliePass789', '789 Nowhere Lane');
INSERT INTO Client VALUES ('aliceinwonderland', 'White', 'Alice', 'aliceSecret321', '321 Hidden Path');
INSERT INTO Client VALUES ('bobbypatootie', 'Black', 'Bob', 'bobPass654', NULL);
```

### c. Tapez *commit* pour enregistrer les changements

```sql
COMMIT;
```

### d. Ouvrez une deuxième connexion SQL Developer

menu *File* -> New -> Database Connection, sous le même usager.

> [!WARNING]
> Connection Type : TNS
> Network Alias : ETUDOM

> [!NOTE] 
> Si vous n’arrivez pas à selection un alias de réseau, creear une connexion avec les paramètres par défauts

### e. S’assurer d’être en *Autocommit Off*.

Pour ce faire sous SQL Developer :
- choisir le menu Tools puis Preferences
- ouvrir le petit + à gauche de base de données
- cliquer sur Avancé
- décocher Autocommit in SQL ou Validation automatique
- décocher Autocommit in SQL Worksheet

## 2. Accès simultané aux données

### Tableau

#### Quel est le résultat dans les deux fenêtres ? Expliquez.

Le résultat dans la fenêtre 1 contient l'adresse mise à jour ('a') et dqns la fenêtre 2 l'adresse originale. \
Cela s'explique par le fait que l'instruction dans la fenêtre 1 n'a pas été commit.

#### Que se passe-t-il dans les deux fenêtres ?

Il ne se passe rien dans la fenêtre 1, cependant dans la fanêtre 2 l'instruction ne se termine pas : elle s'exécute indéfiniment.

#### Que se passe-t-il dans les deux fenêtres ?

Dans la fenêtre 1, le commit s'effectue. Dans la fenêtre 2, l'instruction s'achève et la ligne est mise à jour.

#### Quel est le résultat dans les deux fenêtres ?

Dans la fenêtre 1, l'adresse du client 1 est toujours 'a'. Dans la fenêtre 2, c'est 'b'.

#### Quel est le résultat dans les deux fenêtres maintenant ?

L'adresse du client 1 est 'b' dans les deux fenêtres.


### a. Dans la fenêtre 2, peut-on faire un SELECT sur la table concernée par la mise à jour effectuée dans la fenêtre 1 ?

Oui, cependant les changements ne sont pas visibles tant qu'ils ne sont pas commit.

### b. Dans la fenêtre 2, peut-on faire un UPDATE sur la ligne concernée par la mise à jour effectuée dans la fenêtre 1 ?

Oui et non : l'appel est fait mais ne se termine pas tant que les changements sont validés sur l'autre fenêtre.

### c. Dans la fenêtre 2, peut-on faire un UPDATE sur une ligne de la table non concernée par la mise à jour effectuée dans la fenêtre 1 ?

Oui.

### d. Le comportement au niveau des verrous est-il le même si la requête UPDATE effectuée dans la 2ème fenêtre est remplacée par une suppression dans la table ?

Oui, toute modification est susceptible au même verrouillage : seul l'affichage n'est pas concerné.


## 3. Blocage de ressources

### Tableau

#### Que se passe-t-il dans les deux fenêtres? Expliquez



#### Quelles valeurs ont été enregistrées ?



### a. Comment s’appelle le phénomène que vous avez observé ?



### b. Comment faire pour éviter que ce problème se produise ?

