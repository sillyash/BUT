// Enoncé
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


INSERT INTO Visionnage VALUES (882213, '08-02-2024 00:06:00', 792307, 1, 31, '08-02-2024 00:41:00');
INSERT INTO Visionnage VALUES (882213, '08-02-2024 20:41:00', 792307, 1, NULL, '08-02-2024 23:49:00');
INSERT INTO Visionnage VALUES (114455, '14-02-2024 22:23:00', 398818, 4, NULL, '15-02-2024 00:34:00');
INSERT INTO Visionnage VALUES (114455, '15-02-2024 19:16:00', 398818, 2, 86, '15-02-2024 19:32:00');
INSERT INTO Visionnage VALUES (114455, SYSDATE-1, 7345, 3, 120, SYSDATE-1-(2/24));
INSERT INTO Visionnage VALUES (114455, SYSDATE, 7345, 3, NULL, NULL);
INSERT INTO Visionnage VALUES (446672, SYSDATE, 496243, 5, NULL, NULL);


// a)
DROP VIEW vvFilm;

CREATE VIEW vvFilm AS
SELECT F.idFilm, COUNT(*) AS NbVersions
FROM bdfilm.Film F
INNER JOIN bdfilm.Version V ON V.idFilm = F.idFilm
GROUP BY F.idFilm;

// non modifiable car clause GROUP BY

// b)
DROP VIEW vRealisateur;

CREATE VIEW vRealisateur AS
SELECT DISTINCT P.idPersonne, P.nomPersonne, P.prenomPersonne
FROM bdfilm.Personne P
INNER JOIN bdfilm.Equipefilm EF ON P.idPersonne = EF.idPersonne
WHERE EF.job = 'Director'
ORDER BY idPersonne;

// c)
DROP VIEW vvAuj;

CREATE VIEW vvAuj AS
SELECT VE.idFilm, VE.numversion, VE.resolution, C.idClient, C.nomclient,
        C.prenomclient, VI.horodatagedebut, VI.horodatagefin, VI.minutestop
FROM bdfilm.Version VE
INNER JOIN Visionnage VI ON (VI.idFilm = VE.idFilm) AND (VI.numVersion = VE.numVersion)
INNER JOIN Client C ON VI.idClient = C.idClient;

SELECT * FROM vvAuj;


// d)
UPDATE vvAuj
SET horodatagedebut = SYSDATE-1
WHERE idFilm = 496243;


// e)
DROP VIEW vClient;

CREATE VIEW vClient AS
SELECT C.nomclient, C.prenomclient, TO_CHAR(VI.horodatagedebut,'DD-MM-YYYY') AS horodatagedebut,
        TO_CHAR(VI.horodatagefin,'DD-MM-YYYY') AS horodatagedefin, VI.numversion
FROM Client C
INNER JOIN Visionnage VI ON VI.idClient = C.idClient;

SELECT * FROM vClient;


// f)
DROP VIEW vFilmAtt;

CREATE OR REPLACE VIEW vFilmAtt AS
SELECT C.prenomClient, C.nomClient, VI.numversion, VI.horodatagedebut, VI.horodatagefin, VI.minutestop
FROM Client C
INNER JOIN Visionnage VI ON VI.idClient = C.idClient
WHERE VI.minutestop is null
WITH CHECK OPTION;

SELECT * FROM vFilmAtt;

UPDATE vFilmAtt
SET minutestop = 36;
