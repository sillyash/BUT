SET SERVEROUTPUT ON;
ALTER SESSION SET NLS_DATE_FORMAT='DD-MON-YYYY';

DROP TABLE Client;

CREATE TABLE Client (
    login           VARCHAR2(30)    PRIMARY KEY NOT NULL,
    nomClient       VARCHAR2(100)   NOT NULL,
    prenomClient    VARCHAR2(50)    NOT NULL,
    motDePasse      VARCHAR2(70)    NOT NULL,
    adresse         VARCHAR2(180)
);

INSERT INTO Client VALUES ('xXjohnXx', 'Doe', 'John', 'password123', '123 Fake Street');
INSERT INTO Client VALUES ('smithereen', 'Smith', 'Jane', 'securePass456', '456 Imaginary Road');
INSERT INTO Client VALUES ('charlie_brownie', 'Brown', 'Charlie', 'charliePass789', '789 Nowhere Lane');
INSERT INTO Client VALUES ('aliceinwonderland', 'White', 'Alice', 'aliceSecret321', '321 Hidden Path');
INSERT INTO Client VALUES ('bobbypatootie', 'Black', 'Bob', 'bobPass654', NULL);

SELECT * FROM Client;

UPDATE client SET adresse = 'b'
WHERE login = 'xXjohnXx';

SELECT * FROM Client;

COMMIT;
SELECT * FROM Client;
