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
    CONSTRAINT planClientCHK CHECK (planClient = 'Standard' OR planClient = 'Essentiel avec publicité' OR planClient = 'Essentiel' OR planClient = 'Supérieur' OR planClient = 'Premium')
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


// b)
INSERT INTO Client VALUES (882213, 'William', 'Blake', 'Essentiel avec publicité');
INSERT INTO Client VALUES (114455, 'Helena', 'Lovett', 'Supérieur');
INSERT INTO Client VALUES (446672, 'Jeanne', 'Moreau', 'Premium');


// c)
INSERT INTO Visionnage VALUES (882213, '08-02-2024 00:06:00', 792307, 1, 31, '08-02-2024 00:41:00');
INSERT INTO Visionnage VALUES (882213, '08-02-2024 20:41:00', 792307, 1, NULL, '08-02-2024 23:49:00');
INSERT INTO Visionnage VALUES (114455, '14-02-2024 22:23:00', 398818, 4, NULL, '15-02-2024 00:34:00');
INSERT INTO Visionnage VALUES (114455, '15-02-2024 19:16:00', 398818, 2, 86, '15-02-2024 19:32:00');
INSERT INTO Visionnage VALUES (114455, SYSDATE-1, 7345, 3, 120, SYSDATE-1-(2/24));
INSERT INTO Visionnage VALUES (114455, SYSDATE, 7345, 3, NULL, NULL);
INSERT INTO Visionnage VALUES (446672, SYSDATE, 496243, 5, NULL, NULL);


// d)
COMMIT;


// e)
UPDATE Visionnage
SET horodatagefin = SYSDATE+(1/24)
WHERE idclient = (select idclient from Client where prenomclient = 'Helena' AND nomclient = 'Lovett') AND idfilm = 7345;


// f)
DELETE FROM Visionnage
WHERE idclient = (select idclient from Client where prenomclient = 'Helena' AND nomclient = 'Lovett');

DELETE FROM Client
WHERE prenomclient = 'Helena' AND nomclient = 'Lovett';


