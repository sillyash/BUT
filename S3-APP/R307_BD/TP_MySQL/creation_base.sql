DROP TABLE IF EXISTS Livraison;
DROP TABLE IF EXISTS Commande;
DROP TABLE IF EXISTS Vin;
DROP TABLE IF EXISTS Viticulteur;
DROP TABLE IF EXISTS Buveur;


CREATE TABLE Viticulteur (
    numViti INT AUTO_INCREMENT,
    nom		VARCHAR(100) NOT NULL,
    prenom	VARCHAR(100) NOT NULL,
    ville	VARCHAR(70) NOT NULL,
    
    PRIMARY KEY (numViti)
);


CREATE TABLE Buveur (
	numBuv INT AUTO_INCREMENT,
    nom		VARCHAR(100) NOT NULL,
    prenom	VARCHAR(100) NOT NULL,
    ville	VARCHAR(70) NOT NULL,
    
    PRIMARY KEY (numBuv)
);


CREATE TABLE Vin (
	numVin INT AUTO_INCREMENT,
    cru VARCHAR(200) NOT NULL,
    millesime YEAR NOT NULL,
    region VARCHAR(50) NOT NULL,
    numViti INT NOT NULL,
    
    PRIMARY KEY (numVin),
    FOREIGN KEY (numViti) REFERENCES Viticulteur (numViti)
);


CREATE TABLE Commande (
	numComm INT AUTO_INCREMENT,
    numBuv INT NOT NULL,
    numVin INT NOT NULL,
    dateComm DATE NOT NULL,
    quantite INT NOT NULL,
    
    PRIMARY KEY (numComm),
    FOREIGN KEY (numBuv) REFERENCES Buveur (numBuv),
    FOREIGN KEY (numVin) REFERENCES Vin (numVin)
);


CREATE TABLE Livraison (
	numComm	INT,
    dateLiv DATE NOT NULL,
    quantiteLiv INT NOT NULL,
    
    PRIMARY KEY (numComm, dateLiv),
    FOREIGN KEY (numComm) REFERENCES Commande (numComm)
);
