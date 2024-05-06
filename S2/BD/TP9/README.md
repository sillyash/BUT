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

### e. Dire ce qui se passe dans la table Trace si on exécute la requête qui insère un nouveau visionnage pour le client n. 882213 :

	INSERT INTO Visionnage VALUES (882213, SYSDATE-3/24, 792307, 1, NULL, SYSDATE);
 
 (Le client n. 882213 a commencé à voir le film n. 792307 il y a 3 heures et finit de le voir maintenant.)

>






