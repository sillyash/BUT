DROP TABLE TARIF;
DROP TABLE COMPO_CAMP;
DROP TABLE PRIX;
DROP TABLE CAMPING;
DROP TABLE OFFRE;
DROP TABLE BASE_LOISIRS;
DROP TABLE PERIODE;
DROP TABLE TYPECHAL;
DROP TABLE ACTIVITE;


CREATE TABLE "ACTIVITE" (
	"idActivite"	INT,
	"NomActivite"	TEXT NOT NULL,
	"TypeActivite"	INT NOT NULL,
	PRIMARY KEY("idActivite")
);

CREATE TABLE "TYPECHAL" (
	"idTC" INT,
	PRIMARY KEY("idTC")
);

CREATE TABLE "PERIODE" (
	"CodePeriode"	INT,
	"NomPeriode"	TEXT NOT NULL,
	PRIMARY KEY("CodePeriode")
);

CREATE TABLE "BASE_LOISIRS" (
	"idBase"	INT,
	"NomBase"	TEXT NOT NULL,
	"VoieBL"	TEXT NOT NULL,
	"VilleBL"	TEXT NOT NULL,
	"CPBL"	INTEGER NOT NULL,
	PRIMARY KEY("idBase")
);

CREATE TABLE "OFFRE" (
	"idBase" INT,
	"idActivite" INT,
	PRIMARY KEY("idBase","idActivite"),
	FOREIGN KEY("idBase") REFERENCES BASE_LOISIRS("idBase"),
	FOREIGN KEY("idActivite") REFERENCES ACTIVITE("idActivite")
);

CREATE TABLE "CAMPING" (
	"idCamping"	INT,
	"NomCamping" TEXT NOT NULL,
	"DateOuv" TEXT NOT NULL,
	"DateFerm" TEXT NOT NULL,
	"Etoiles" INT NOT NULL,
	"LabelQF" INT,
	"Telephone" TEXT NOT NULL,
	"VoieCamping"	TEXT NOT NULL,
	"VilleCamping"	TEXT NOT NULL,
	"CPCamping"	INT NOT NULL,
	"idBase" INT,
	PRIMARY KEY("idCamping"),
	FOREIGN KEY("idBase") REFERENCES BASE_LOISIRS("idBase")
);

CREATE TABLE "PRIX" (
	"idCamping"	INT,
	"idActivite" INT,
	"Prix" INT,
	PRIMARY KEY("idCamping","idActivite"),
	FOREIGN KEY("idCamping") REFERENCES CAMPING("idCamping"),
	FOREIGN KEY("idActivite") REFERENCES ACTIVITE("idActivite")
);

CREATE TABLE "COMPO_CAMP" (
	"idCamping"	INT,
	"idTC" INT,
	"nbChal" INT,
	PRIMARY KEY("idCamping","idTC"),
	FOREIGN KEY("idCamping") REFERENCES CAMPING("idCamping"),
	FOREIGN KEY("idTC") REFERENCES TYPECHAL("idTC")
);

CREATE TABLE "TARIF" (
	"idCamping"	INT,
	"idTC" INT,
	"CodePeriode" INT NOT NULL,
	PRIMARY KEY("idCamping","idTC"),
	FOREIGN KEY("idCamping", "idTC") REFERENCES COMPO_CAMP("idCamping","idTC")
);

INSERT INTO BASE_LOISIRS VALUES(1, "Etang de Tamniès", "12 rue du Lac", "Roubaix", 59100);
INSERT INTO BASE_LOISIRS VALUES(2, "La Maladrerie", "12 rue du Lac", "Roubaix", 59100);

INSERT INTO CAMPING VALUES(1, '20230513', '20230930', 4, 0, "05 53 31 46 00", "Camping Aqua Viva", "Route de Souillac", "Sarlat La Canéda", 59100, 1);
INSERT INTO CAMPING VALUES(2, '20230101', '20231231', 3, 1, "05 65 46 89 85", "Camping Le Caussonel", "Lac de Pareloup", "Canet-de-Salars", 12290, 2);
INSERT INTO CAMPING VALUES(3, '20230603', '20230930', 2, 0, "02 51 40 24 07", "Camping La Rivière", "25 rue du Stade", "Sainte Cécile", 85110, NULL);


--Q1
SELECT CPCamping, VilleCamping
FROM CAMPING
WHERE(idBase IS NOT NULL);

--Q2 ??? DOES NOT WORK >:3
SELECT NomCamping
FROM CAMPING
WHERE((DateOuv < '20230601') AND (DateFerm <= '20230501'));
--https://stackoverflow.com/questions/1975737/sqlite-datetime-comparison

--Q3


