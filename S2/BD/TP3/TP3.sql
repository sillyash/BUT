// ---------- EX1 ----------

// a)
CREATE TABLE typeChalet (
    NumTypeChalet   NUMBER(8) NOT NULL,
    NomTypeChalet   VARCHAR2(50) NOT NULL UNIQUE,
    Capacite        NUMBER(8) NOT NULL,
    Decription      VARCHAR2(500) DEFAULT NULL,
    
    CONSTRAINT typeChaletPK PRIMARY KEY (NumTypeChalet),
    CONSTRAINT capacite_CHK CHECK (Capacite > 0)
);


CREATE TABLE baseLoisirs (
    NumBaseL        NUMBER(8) NOT NULL,
    NomBaseL        VARCHAR2(50),
    AdrBaseL        VARCHAR2(100),
    CPBaseL         VARCHAR2(5),
    VilleBaseL      VARCHAR2(100),
    
    CONSTRAINT baseLoisirsPK PRIMARY KEY (NumBaseL)
);


CREATE TABLE Camping (
    NumCamping      NUMBER(8) NOT NULL,
    NomCamping      VARCHAR2(50) NOT NULL,
    AdrCamping      VARCHAR2(100),
    CPCamping       VARCHAR2(5),
    VilleCamping    VARCHAR2(100),
    TelCamping      VARCHAR2(20),
    BaseLoisirs     NUMBER(8),
    DateOuv         DATE,
    DateFerm        DATE,
    NbEtoiles       NUMBER(1),
    QualiteFrance   VARCHAR2(3) DEFAULT 'Non',
    
    
    
    CONSTRAINT CampingPK PRIMARY KEY (NumCamping),
    CONSTRAINT DateCHK CHECK (DateOuv < DateFerm),
    CONSTRAINT NbEtoilesCHK CHECK (NbEtoiles >= 1 AND NbEtoiles <= 4),
    CONSTRAINT QualiteFranceCHK CHECK (QualiteFrance = 'Oui' OR QualiteFrance = 'Non')
);


CREATE TABLE compoCamping (
    NumCamping      NUMBER(8) NOT NULL,
    NumTypeChalet   NUMBER(8) NOT NULL,
    NbreChalet      NUMBER(8) NOT NULL,
    
    CONSTRAINT compoCampingPK PRIMARY KEY (NumCamping, NumTypeChalet),
    CONSTRAINT CampingCIR FOREIGN KEY (NumCamping) REFERENCES Camping,
    CONSTRAINT TypeChaletCIR FOREIGN KEY (NumTypeChalet) REFERENCES typeChalet,
    CONSTRAINT NbreChaletCHK CHECK (NbreChalet > 0)
    
);


// b)
/*
DROP TABLE compoCamping;
DROP TABLE baseLoisirs;
DROP TABLE typeChalet;
DROP TABLE Camping;
*/

// ---------- EX2 ----------

// a)
INSERT INTO TYPECHALET SELECT * FROM FFIOREN.TYPECHALET;
INSERT INTO BASELOISIRS SELECT * FROM FFIOREN.BASELOISIRS;
INSERT INTO CAMPING SELECT * FROM FFIOREN.CAMPING;
INSERT INTO COMPOCAMPING SELECT * FROM FFIOREN.COMPOCAMPING;


// b)
UPDATE CAMPING
SET DateFerm = ADD_MONTHS (DateFerm, 12),
    DateOuv = ADD_MONTHS (DateOuv, 12);


// c)
INSERT INTO CAMPING VALUES (20, 'Les Flots Bleus', NULL, NULL, NULL, NULL, NULL, '01-JAN-2024', '31-DEC-2024', NULL, 'Oui');

INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 1, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 2, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 3, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 4, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 5, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 6, 1);


// d)
INSERT INTO CAMPING VALUES ((SELECT MAX(NumCamping)+1 FROM CAMPING), 'Les Dents de la Mer', NULL, NULL, NULL, NULL, NULL, '05-JUL-2024', '31-AUG-2024', NULL, 'Non');

INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 1, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 2, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 3, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 4, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 5, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 6, 1);

// e)
CREATE TABLE PossedeChalet (NumCamping INT);
INSERT INTO PossedeChalet(
    SELECT NumCamping
    FROM COMPOCAMPING C
    WHERE NumTypeChalet = 6
);

CREATE TABLE AffilieBaseL (NumCamping INT);
INSERT INTO AffilieBaseL (
    SELECT NumCamping
    FROM CAMPING C
    INNER JOIN BASELOISIRS BL ON C.BaseLoisirs = BL.NumBaseL
    WHERE BL.NomBaseL = 'La Maladrerie'
);

UPDATE COMPOCAMPING
SET COMPOCAMPING.nbrechalet = COMPOCAMPING.nbrechalet + 1
WHERE (NumCamping IN (select * from PossedeChalet)
    AND NumCamping IN (select * from AffilieBaseL));


INSERT INTO COMPOCAMPING 
SELECT NumCamping, 6, 1
FROM AffilieBaseL
WHERE NumCamping NOT IN (select * from PossedeChalet);

DROP TABLE AffilieBaseL;
DROP TABLE PossedeChalet;


// f)
DROP TABLE CompoCamping;

CREATE TABLE compoCamping (
    NumCamping      NUMBER(8) NOT NULL,
    NumTypeChalet   NUMBER(8) NOT NULL,
    NbreChalet      NUMBER(8) NOT NULL,
    
    CONSTRAINT compoCampingPK PRIMARY KEY (NumCamping, NumTypeChalet),
    CONSTRAINT CampingCIR FOREIGN KEY (NumCamping) REFERENCES Camping ON DELETE CASCADE,
    CONSTRAINT TypeChaletCIR FOREIGN KEY (NumTypeChalet) REFERENCES typeChalet,
    CONSTRAINT NbreChaletCHK CHECK (NbreChalet > 0)
);

INSERT INTO COMPOCAMPING SELECT * FROM FFIOREN.COMPOCAMPING;

INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 1, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 2, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 3, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 4, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 5, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES (20, 6, 1);

INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 1, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 2, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 3, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 4, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 5, 1);
INSERT INTO COMPOCAMPING (NumCamping, NumTypeChalet, NbreChalet) VALUES ((SELECT MAX(NumCamping) FROM CAMPING), 6, 1);

delete from camping where baseloisirs is null;
rollback;


// g)
UPDATE CompoCamping CC
SET CC.NbreChalet = CC.NbreChalet + (select nbrechalet from compocamping
                                    where numtypechalet = 5 and numcamping = CC.numcamping)
WHERE NumCamping IN (select numcamping from camping
                    where numtypechalet = 5);


// h)
DROP TABLE compoCamping;
DROP TABLE baseLoisirs;
DROP TABLE typeChalet;
DROP TABLE Camping;






