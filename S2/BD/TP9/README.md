# R206 - BD

## TP9 - Déclencheurs

### Ash MERIENNE - 2C

<br>

#### Remarque : Avant toute chose, on active l'output de serveur.

	SET SERVEROUTPUT ON;

## Exercice 1. Nous allons tester l’ordre de déclanchement des triggers. Cet exercice est complétement guidé, il faut juste suivre attentivement toutes les étapes.

### a. Rependre le script d’insertion dans les tables Visionnage et Client vu au TP précédents. Il faut avoir 7 lignes dans Visionnage et au moins 3 dans Client.
	
	DROP TABLE Visionnage;
	DROP TABLE Client;
	
	CREATE TABLE Client (
	    idClient        NUMBER (15),
	    prenomClient    VARCHAR (50) NOT NULL,
	    nomClient       VARCHAR (50) NOT NULL,
	    planClient      VARCHAR (24) NOT NULL,
	    
	    CONSTRAINT ClientPK PRIMARY KEY (idClient),
	    CONSTRAINT planClientCHK CHECK (planClient IN ('Standard', 'Essentiel avec publicité', 'Essentiel', 'Supérieur', 'Premium'))
	);
	
	CREATE TABLE Visionnage (
	    idClient        NUMBER (15),
	    horoDatageDebut TIMESTAMP,
	    idFilm          NUMBER (10) NOT NULL,
	    numVersion      NUMBER (1) NOT NULL,
	    minuteStop      NUMBER (10),
	    horoDatageFin   TIMESTAMP,
	    
	    CONSTRAINT VisionnagePK PRIMARY KEY (idClient, horoDatageDebut),
	    CONSTRAINT idFilmFK FOREIGN KEY (idFilm, numVersion) REFERENCES bdfilm.Version
	);
	
	INSERT INTO Client VALUES (882213, 'William', 'Blake', 'Essentiel avec publicité');
	INSERT INTO Client VALUES (114455, 'Helena', 'Lovett', 'Supérieur');
	INSERT INTO Client VALUES (446672, 'Jeanne', 'Moreau', 'Premium');
	INSERT INTO CLIENT VALUES (1, 'Ashley', 'Merienne', 'Standard');
	INSERT INTO CLIENT VALUES (2, 'Emma', 'Escoffier', 'Standard');
	
	INSERT INTO Visionnage VALUES (882213, '08-02-2024 00:06:00', 792307, 1, 31, '08-02-2024 00:41:00');
	INSERT INTO Visionnage VALUES (882213, '08-02-2024 20:41:00', 792307, 1, NULL, '08-02-2024 23:49:00');
	INSERT INTO Visionnage VALUES (114455, '14-02-2024 22:23:00', 398818, 4, NULL, '15-02-2024 00:34:00');
	INSERT INTO Visionnage VALUES (114455, '15-02-2024 19:16:00', 398818, 2, 86, '15-02-2024 19:32:00');
	INSERT INTO Visionnage VALUES (114455, SYSDATE-1, 7345, 3, 120, SYSDATE-1-(2/24));
	INSERT INTO Visionnage VALUES (114455, SYSDATE, 7345, 3, NULL, NULL);
	INSERT INTO Visionnage VALUES (446672, SYSDATE, 496243, 5, NULL, NULL);

### b. Crée une table Trace (numLigne, message), où numLigne est un NUMBER et message est une chaîne de caractères.
	
	DROP TABLE Trace;
	
	CREATE TABLE Trace (
	    NumLigne    NUMBER(8),
	    Message     VARCHAR2(100)
	);

### c. Copier et compiler la fonction nbLignes ci-dessous qui compte le nombre de lignes de la table Trace :
	
	CREATE OR REPLACE FUNCTION nbLignes RETURN NUMBER AS
		nb NUMBER;
	BEGIN
		SELECT COUNT(*) INTO nb FROM Trace;
		RETURN nb;
	END nbLignes;
	/

### d. Copier et compiler les 4 triggers ci-dessous :

	CREATE OR REPLACE TRIGGER Trigger1
	AFTER INSERT OR UPDATE OF horoDatageFin
	ON Visionnage FOR EACH ROW
	BEGIN
	    IF INSERTING THEN INSERT INTO Trace VALUES
	        (nbLignes+1, 'Trigger 1 (AFTER/FOR EACH ROW) : Nouveau visionnage pour le client '||:NEW.idClient);
	    END IF;
	    IF UPDATING THEN INSERT INTO Trace VALUES
	        (nbLignes+1, 'Trigger 1 (AFTER/FOR EACH ROW) : Mise à jour de fin de visionnage le pour le client '||
	            :OLD.idClient);
	    END IF;
	END trigger1;
	/
	
	CREATE OR REPLACE TRIGGER Trigger2
	AFTER INSERT OR UPDATE OF horoDatageFin
	ON Visionnage
	BEGIN
	    INSERT INTO Trace VALUES (nbLignes+1, 'Trigger 2 (AFTER) : Modification dans Visionnage');
	END trigger2;
	/
	
	CREATE OR REPLACE TRIGGER Trigger3
	BEFORE INSERT OR UPDATE OF horoDatageFin
	ON Visionnage FOR EACH ROW
	BEGIN
	    IF INSERTING THEN INSERT INTO Trace VALUES
	        (nbLignes+1, 'Trigger 3 (BEFORE/FOR EACH ROW) : Nouveau visionnage pour le client '||:NEW.idClient);
	    END IF;
	    IF UPDATING THEN INSERT INTO Trace VALUES
	        (nbLignes+1, 'Trigger 3 (BEFORE/FOR EACH ROW) : Mise à jour de fin de visionnage le pour le client '||
	            :OLD.idClient);
	    END IF;
	END trigger3;
	/
	
	CREATE OR REPLACE TRIGGER Trigger4
	BEFORE INSERT OR UPDATE OF horoDatageFin
	ON Visionnage
	BEGIN
	    INSERT INTO Trace VALUES (nbLignes+1, 'Trigger 4 (BEFORE) : Modification dans Visionnage');
	END trigger4;
	/

#### Remarque : Pour voir clairement les triggers pour chaque question, on utilise le ```rollback```

	// Instructions...
  	SELECT * FROM Trace;
   	ROLLBACK;  // On enlève les modifications et les insertions dans trace, pour mieux voir les triggers à la question suivante

### e. Dire ce qui se passe dans la table Trace si on exécute la requête qui insère un nouveau visionnage pour le client n. 882213 :

	INSERT INTO Visionnage VALUES (882213, SYSDATE-3/24, 792307, 1, NULL, SYSDATE);
 
 (Le client n. 882213 a commencé à voir le film n. 792307 il y a 3 heures et finit de le voir maintenant.)

>Le ```trigger4``` est déclenché avant l'insertion de données et insère une ligne dans ```Trace``` nous informant d'un début d'insertion/modification de données.
>
>Le ```trigger3``` est aussi déclenché avant l'insertion de cahque ligne (ici une seule) dans ```Visionnage```, et insère une ligne dans trace nous informant de l'insertion.
>
>Après l'insertion de chaque ligne, le ```trigger1``` est déclenché, nous informant de la fin de l'insertion de la ligne dans la table ```Visionnage```, en insérant une nouvelle ligne dans la table ```Trace```.
>
>Enfin, le ```trigger2``` est déclenché après l'insertion de données et insère une ligne dans ```Trace``` nous informant de la fin de l'insertion/modification de données.

### f. Dire ce qui se passe dans la table Trace si on exécute la requête qui met à jour la ligne qu’on vient d’insérer pour le client n. 882213 :

	UPDATE Visionnage
	SET horoDatageFin = NULL
	WHERE idClient = 882213
	AND TRUNC(horoDatageDebut) = TRUNC(SYSDATE);

(Le client n. 882213 n’a en effet pas encore fini de le voir le film n. 792307 commencé il y a 3
heures.)

>Le ```trigger4``` est déclenché avant l'insertion de données et insère une ligne dans ```Trace``` nous informant d'un début d'insertion/modification de données.
>
>Ensuite, le ```trigger2``` est déclenché après l'insertion de données et insère une ligne dans ```Trace``` nous informant de la fin de l'insertion/modification de données.

### g. Dire ce qui se passe dans la table Trace si on exécute la requête qui met à jour 2 lignes pour le client n. 114455 :

	UPDATE Visionnage
	SET horoDatageFin = horoDatageFin+1/(24*60)
	WHERE idClient = 114455
	AND minuteStop IS NOT NULL;

(Le client n. 114455 a toujours interrompu ses visionnages 1 minute plus tard de ce qui était
indiqué.)

>

### h. Que peut-on en déduire sur l’ordre d’activation des déclencheurs d’une même table, en fonction de leurs types ?

 >??

## Exercice 2. Dans la table ```Visionnage``` il existe trois types de lignes :

- Les visionnages en cours : ```minuteStop``` et ```horoDatageFin``` ne sont pas renseignés (leur valeur est ```NULL```). Dans ce cas le client est en train de visionner le film;
- Les visionnages terminés : ```minuteStop``` n’est pas renseigné mais ```horoDatageFin``` est renseigné. Dans ce cas le client a visionné le film en entier et a terminé au moment indiqué dans ```horoDatageFin```;
- Les visionnages interrompus : ```minuteStop``` et ```horoDatageFin``` sont renseignés. Dans ce cas le client a visionné le film jusqu’à minuteStop et a interrompu la vision du film au moment indiqué dans ```horoDatageFin```. Il pourra reprendre plus tard la vision.

Il ne devrait donc pas être possible d’avoir le cas où ```minuteStop``` est renseigné et ```horoDatageFin``` ne l’est pas, car ```minuteStop``` correspond toujours à une interruption. Toutefois, il est techniquement possible d’avoir une telle ligne dans la table ```Visionnage```.

### Ecrire un trigger ```verifStop``` qui assure le respect de cette contrainte.

	// TODO : Code

## Exercice 3. On veut empêcher un client de visionner plus d’un film à la fois. Ecrire un trigger ```verifVisio``` qui bloque l’insertion d’un visionnage en cours (voir ci-dessus) lorsque le client concerné a déjà un visionnage en cours dans la table ```Visionnage```.

## Exercice 4. On souhaite créer 3 déclencheurs qui surveillent la suppression d’une ligne de la table ```Client```. Afin de pouvoir tester sans contrainte, supprimez pour le moment la table ```Visionnage``` (le script pour la récréer et la peupler doit toujours être disponible).

- Le premier envoie une alerte à chaque suppression.
- Le deuxième envoie un message d’alerte quand la suppression est faite par quelqu’un d'autre que soi.
- Le troisième bloque la suppression quand ce n’est pas soi-même qui la fait.
- Donner à votre voisin/voisine les droits nécessaires sur votre table Client et testez !


