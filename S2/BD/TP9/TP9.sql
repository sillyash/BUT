SET SERVEROUTPUT ON;

// Exercice 1

// a)

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
    
    CONSTRAINT VisionnagePK PRIMARY KEY (idClient, horoDatageDebut)
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


//  b)
DROP TABLE Trace;

CREATE TABLE Trace (
    NumLigne    NUMBER(8),
    Message     VARCHAR2(100)
);

// c)

CREATE OR REPLACE FUNCTION nbLignes RETURN NUMBER AS
    nb NUMBER;
BEGIN
    SELECT COUNT(*) INTO nb FROM Trace;
    RETURN nb;
END nbLignes;
/

// d)

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

// e)

INSERT INTO Visionnage VALUES (882213, SYSDATE-3/24, 792307, 1, NULL, SYSDATE);

SELECT * FROM Trace;
ROLLBACK;

// f)


UPDATE Visionnage
SET horoDatageFin = NULL
WHERE idClient = 882213
AND TRUNC(horoDatageDebut) = TRUNC(SYSDATE);

SELECT * FROM Trace;
ROLLBACK;

// g)

UPDATE Visionnage
SET horoDatageFin = horoDatageFin+1/(24*60)
WHERE idClient = 114455
AND minuteStop IS NOT NULL;

SELECT * FROM Trace;
ROLLBACK;


// Exercice 2

CREATE OR REPLACE TRIGGER VerifStop
BEFORE INSERT OR UPDATE OF minuteStop
ON Visionnage FOR EACH ROW
BEGIN
    IF :NEW.horodatageFin IS NULL AND :NEW.minuteStop IS NOT NULL THEN
        RAISE_APPLICATION_ERROR(-20001, 'minuteStop renseigné alors que horodatageFin n"est pas renseigné.');
    END IF;
END VerifStop;
/

// Exercice 3

CREATE OR REPLACE TRIGGER VerifVisio
BEFORE INSERT OR UPDATE
ON Visionnage FOR EACH ROW
DECLARE nbFilms NUMBER;
BEGIN
    SELECT COUNT(*) INTO nbFilms
    FROM Visionnage V
    WHERE (idClient = :OLD.idClient);
    IF nbFilms >= 1 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Tentative d"insertion d"un visionnage alors que le Client en a déjà un.');
    END IF;
END VerifVisio;
/

// Exercice 4

DROP TABLE Visionnage;

// a)

CREATE OR REPLACE TRIGGER AlerteSuppr
BEFORE DELETE
ON Client FOR EACH ROW
BEGIN
    DBMS_OUTPUT.PUT_LINE('Alerte : suppression d"une ligne de la table Client.');
END AlerteSuppr;
/

// b)

CREATE OR REPLACE TRIGGER AlerteSupprTiers
BEFORE DELETE
ON Client FOR EACH ROW
BEGIN
    IF USER != 'AMERIE1' THEN
        DBMS_OUTPUT.PUT_LINE('Alerte : suppression sur la table Client par un tiers.');
    END IF;
END AlerteSupprTiers;
/

// c)

CREATE OR REPLACE TRIGGER BloquerSupprTiers
BEFORE DELETE
ON Client FOR EACH ROW
BEGIN
    IF USER != 'AMERIE1' THEN
        RAISE_APPLICATION_ERROR(-20003, 'Erreur : suppression sur la table Client par un tiers.');
    END IF;
END BloquerSupprTiers;
/

// d)

GRANT SELECT, UPDATE, INSERT
ON Client
TO AVIAU;

REVOKE ALL
ON Client
FROM AVIAU;
