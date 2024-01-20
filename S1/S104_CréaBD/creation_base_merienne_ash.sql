DROP TABLE STOCK;
DROP TABLE LIGNECOMMANDE;
DROP TABLE ENTREPOT;
DROP TABLE COMMANDE;
DROP TABLE SITE;
DROP TABLE INTERLOCUTEUR;
DROP TABLE CLIENT;
DROP TABLE VILLE;
DROP TABLE DEPARTEMENT;
DROP TABLE PRODUIT;

CREATE TABLE PRODUIT(
   idProduit INT,
   Prix DECIMAL(15,2) NOT NULL CHECK (Prix > 0),
   Description TEXT,
   Spécifications TEXT,
   FicheTechnique BLOB, /* url pointant vers un fichier PDF */
   PRIMARY KEY(idProduit)
);

CREATE TABLE DEPARTEMENT(
   CodeDepartement INT,
   NomDepartement VARCHAR(50) NOT NULL UNIQUE,
   PRIMARY KEY(CodeDepartement)
);

CREATE TABLE VILLE(
   CodeDepartement INT,
   CodeVille INT,
   NomVille VARCHAR(50) NOT NULL,
   PRIMARY KEY(CodeDepartement, CodeVille),
   FOREIGN KEY(CodeDepartement) REFERENCES DEPARTEMENT(CodeDepartement)
);

CREATE TABLE CLIENT(
   idClient INT,
   RaisonSociale VARCHAR(50) NOT NULL,
   StatutJuridique VARCHAR(50) NOT NULL,
   NomJuridique VARCHAR(50) NOT NULL,
   AdresseSiege TEXT NOT NULL,
   TelClient INT NOT NULL,
   EmailClient VARCHAR(50) NOT NULL,
   Siret INT NOT NULL UNIQUE,
   IBAN VARCHAR(50) NOT NULL UNIQUE,
   BIC VARCHAR(50) NOT NULL,
   CodeDepartement INT NOT NULL,
   CodeVille INT NOT NULL,
   PRIMARY KEY(idClient),
   FOREIGN KEY(CodeDepartement, CodeVille) REFERENCES VILLE(CodeDepartement, CodeVille)
);

CREATE TABLE INTERLOCUTEUR(
   idInterloc INT,
   PrenomInterloc TEXT NOT NULL,
   NomInterlocuteur VARCHAR(50) NOT NULL,
   EmailInterloc VARCHAR(50) NOT NULL,
   RoleInterloc VARCHAR(50) NOT NULL,
   TelInterloc INT NOT NULL,
   idClient INT NOT NULL,
   PRIMARY KEY(idInterloc),
   FOREIGN KEY(idClient) REFERENCES CLIENT(idClient)
);

CREATE TABLE SITE(
   idSite INT,
   NomSite VARCHAR(50) NOT NULL,
   Adresse VARCHAR(50) NOT NULL,
   CodeDepartement INT NOT NULL,
   CodeVille INT NOT NULL,
   idClient INT NOT NULL,
   idInterloc INT NOT NULL,
   PRIMARY KEY(idSite),
   FOREIGN KEY(CodeDepartement, CodeVille) REFERENCES VILLE(CodeDepartement, CodeVille),
   FOREIGN KEY(idClient) REFERENCES CLIENT(idClient),
   FOREIGN KEY(idInterloc) REFERENCES INTERLOCUTEUR(idInterloc)
);

CREATE TABLE COMMANDE(
   idCommande INT,
   DateCommande DATE NOT NULL,
   TauxTVA INT CHECK(TauxTVA >= 0),
   idSite INT NOT NULL,
   idClient INT NOT NULL,
   PRIMARY KEY(idCommande),
   FOREIGN KEY(idSite) REFERENCES SITE(idSite),
   FOREIGN KEY(idClient) REFERENCES CLIENT(idClient)
);

CREATE TABLE ENTREPOT(
   idEntrepot INT,
   AddresseEntrepot TEXT NOT NULL,
   QuaiDeDéchargement LOGICAL NOT NULL,
   CodeDepartement INT NOT NULL,
   CodeVille INT NOT NULL,
   PRIMARY KEY(idEntrepot),
   FOREIGN KEY(CodeDepartement, CodeVille) REFERENCES VILLE(CodeDepartement, CodeVille)
);

CREATE TABLE LIGNECOMMANDE(
   idCommande INT,
   idProduit INT,
   NbProduit INT NOT NULL CHECK(NbProduit > 0),
   PRIMARY KEY(idCommande, idProduit),
   FOREIGN KEY(idCommande) REFERENCES COMMANDE(idCommande),
   FOREIGN KEY(idProduit) REFERENCES PRODUIT(idProduit)
);

CREATE TABLE STOCK(
   idProduit INT,
   idEntrepot INT,
   QuantiteStock INT,
   Référence VARCHAR(50) NOT NULL,
   NumBaie INT NOT NULL CHECK(NumBaie > 0),
   NumRangée INT NOT NULL CHECK(NumRangée > 0),
   PRIMARY KEY(idProduit, idEntrepot),
   FOREIGN KEY(idProduit) REFERENCES PRODUIT(idProduit),
   FOREIGN KEY(idEntrepot) REFERENCES ENTREPOT(idEntrepot)
);
