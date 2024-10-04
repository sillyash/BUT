DROP TABLE LOCATION;
DROP TABLE CLIENT;

CREATE TABLE CLIENT (
    NUMCLIENT       NUMBER GENERATED BY DEFAULT AS IDENTITY,
    NOMCLIENT       VARCHAR2(50),
    PRENOMCLIENT    VARCHAR2(50),
    MOTPASSE        VARCHAR2(50),
    ADRCLIENT       VARCHAR2(100),
    CPCLIENT        VARCHAR2(10),

    CONSTRAINT PK_CLIENT PRIMARY KEY(NUMCLIENT)
);


CREATE TABLE LOCATION (
    NUMEXEMPLAIRE   NUMBER,
    DATELOCATION    DATE,
    NUMCLIENT       NUMBER,
    DATEENVOI        DATE,
    DATERETOUR      DATE,

    CONSTRAINT PK_LOCATION PRIMARY KEY(NUMEXEMPLAIRE, DATELOCATION),
    
    CONSTRAINT FK_EXEMPLAIRE FOREIGN KEY(NUMEXEMPLAIRE)
    REFERENCES ENS2004.EXEMPLAIRE(NUMEXEMPLAIRE),
    
    CONSTRAINT FK_CLIENT FOREIGN KEY(NUMCLIENT)
    REFERENCES CLIENT(NUMCLIENT)
);
