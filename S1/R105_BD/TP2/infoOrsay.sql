DROP TABLE ligneCommande;
DROP TABLE commande;
DROP TABLE produit;
DROP TABLE client;
DROP TABLE categorie;


CREATE TABLE categorie (
  codeCategorie    CHAR(1) CONSTRAINT categorie_PK PRIMARY KEY,
  libelleCategorie VARCHAR2(20) CONSTRAINT categorie_libelle_NN NOT NULL,
  remise           NUMBER(3)
);

CREATE TABLE client (
  numClient       NUMBER(6) CONSTRAINT client_PK PRIMARY KEY,
  nomClient       VARCHAR2(20) CONSTRAINT client_nomClient_NN NOT NULL,
  prenomClient    VARCHAR2(20), 
  adresseClient   VARCHAR2(50),
  telClient       CHAR(14),
  categorieClient CHAR(1)
                  CONSTRAINT client_categorie_FK REFERENCES categorie
                  CONSTRAINT client_categorie_NN NOT NULL
);

CREATE TABLE produit (
  numProduit  NUMBER(6) CONSTRAINT produit_PK PRIMARY KEY,
  designation VARCHAR2(30) CONSTRAINT produit_designation_NN NOT NULL,
  prix        NUMBER(8,2)
);
  
CREATE TABLE commande (
  numCommande   NUMBER(6) CONSTRAINT commande_PK PRIMARY KEY,
  dateCommande  DATE,
  numClient     NUMBER(6)
                CONSTRAINT commande_client_FK REFERENCES client
                CONSTRAINT commande_numClient_NN NOT NULL
);

CREATE TABLE ligneCommande (
  numCommande NUMBER(6) CONSTRAINT lignec_commande_FK REFERENCES commande,
  numProduit  NUMBER(6) CONSTRAINT lignec_produit_FK REFERENCES produit,
  quantite    NUMBER(6) CONSTRAINT lignec_quantite_NN NOT NULL,
  CONSTRAINT lignec_PK PRIMARY KEY (numCommande, numProduit)
);


INSERT INTO categorie
VALUES
    ('E', 'Entreprise', 10),
    ('F', 'Carte de fidelité', 5),
    ('P', 'Particulier', 0);

INSERT INTO client 
VALUES
    (1, 'ROSSI', 'Maria', '21 avenue de la Forêt, 91400 Orsay',
    '01 74 23 21 33', 'E');
    (2,	'DEGHAR', 'Ali', '33 avenue des Platanes, 91400 Orsay',	
    '01 46 57 28 32', 'P'),
    (3	'TURNER', 'J.-Baptiste','3 rue des Rosiers, 92160 Antony',	
    '01 34 56 43 22', 'E'),
    (4	'SCHMITT', 'Michel','41 impasse des Tilleuls, 91000 Evry',	
    '01 53 22 41 33', 'P'),
    (5	'DUVAL', 'Tatiana', '23 rue des Sapins, 91300 Massy',	
    '01 32 56 43 21', 'F'),
    (6	'DUVAL', 'Matthieu','3 rue des Acacias, 91300 Massy',	
    '06 22 33 41 27', 'F');

INSERT INTO commande
VALUES
    (1,	25/07/2023,	1),
    (2,	26/07/2023,	2),
    (3,	02/08/2023,	3),
    (4,	03/08/2023,	1),
    (5,	05/08/2023,	4),
    (6,	06/08/2023,	5),
    (7,	18/08/2023,	1),
    (8,	18/08/2023,	1),
    (9,	26/08/2023,	6),
    (10, 02/09/2023, 3),
    (11, 04/09/2023, 5);

INSERT INTO produit
VALUES
    (1233,	'Imprimante laser',	1222),
    (1569,	'PC Pentium 120Mhz',	1500),
    (342,	'Cartouche laser',	158),
    (651,	'Carte son 16 bits',	300),
    (890,	'Imprimante jet d''encre',	870),
    (2345,	'Scanner à main',	327),
    (670,	'Enceintes stéréo',	122);
    
INSERT INTO lignecommande
VALUES
    (1,	1233, 2),
    (1,	1569, 5),
    (1,	342, 4),
    (2,	342, 1),
    (2,	651, 1),
    (3,	1233, 1),
    (4,	890, 2),
    (4,	1233, 1),
    (4,	1569, 10),
    (4,	2345, 1),
    (5,	1569, 1),
    (6,	342, 1),
    (6,	651, 1),
    (6,	670, 1),
    (7,	342, 1),
    (7,	651, 1),
    (8,	342, 3),
    (8,	1569, 2),
    (9,	1569, 1),
    (9,	890, 1),
    (10, 1569, 4),
    (11, 342, 1),
    (11, 651, 1);

    
