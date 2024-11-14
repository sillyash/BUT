SET SERVEROUTPUT ON;

-- 1)
-- a)

CREATE OR REPLACE FUNCTION nbFilmsRealisateur(nomReal ens2004.individu.numIndividu%type)
RETURN NUMBER IS nbFilmsRealises NUMBER(8);
BEGIN
    SELECT COUNT(*) INTO nbFilmsRealises
    FROM ens2004.Film F
    WHERE F.realisateur = nomReal;

    RETURN nbFilmsRealises;
END nbFilmsRealisateur;
/

EXECUTE DBMS_OUTPUT.PUT_LINE('Quentin Tarantino a réalisé ' || NBFILMSREALISATEUR(1292) || ' films.');


-- b)

CREATE OR REPLACE FUNCTION nbFilmsRealisateur(nomReal ens2004.individu.nomIndividu%type)
RETURN NUMBER IS nbFilmsRealises NUMBER(8);
BEGIN
    SELECT COUNT(*) INTO nbFilmsRealises
    FROM ens2004.Film F
    INNER JOIN ens2004.INDIVIDU I ON I.NUMINDIVIDU = F.REALISATEUR
    WHERE I.NOMINDIVIDU = UPPER(nomReal);

    RETURN nbFilmsRealises;
END nbFilmsRealisateur;
/

EXECUTE DBMS_OUTPUT.PUT_LINE('Tarantino a réalisé ' || NBFILMSREALISATEUR('Tarantino') || ' films.');


-- c)

CREATE OR REPLACE PROCEDURE filmsRealisateur(nomReal ens2004.individu.nomIndividu%type) IS
CURSOR c IS
    SELECT *
    FROM ens2004.FILM F
    INNER JOIN ens2004.INDIVIDU I ON I.NUMINDIVIDU = F.REALISATEUR
    WHERE I.NOMINDIVIDU = UPPER(nomReal);
BEGIN
    FOR linec in c
    LOOP
        DBMS_OUTPUT.PUT_LINE(linec.titre);
    END LOOP;
END filmsRealisateur;
/

EXECUTE FILMSREALISATEUR('Tarantino');


-- 2)

CREATE OR REPLACE FUNCTION EXEMPLAIRESRESTANTS(numeroFilm ENS2004.FILM.NUMFILM%TYPE)
RETURN NUMBER IS NBEXEMPLAIRES NUMBER(8);
BEGIN
    SELECT COUNT(*) INTO NBEXEMPLAIRES
    FROM ENS2004.FILM F
    INNER JOIN ENS2004.EXEMPLAIRE E ON E.NUMEXEMPLAIRE = F.NUMFILM
    INNER JOIN LOCATION L ON L.NUMEXEMPLAIRE = E.NUMEXEMPLAIRE
    WHERE (F.NUMFILM = numeroFilm) AND (L.DATERETOUR IS NOT NULL);

    RETURN NBEXEMPLAIRES;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RAISE_APPLICATION_ERROR(-20001, 'Aucun film n"existe avec cet identifiant.');
END EXEMPLAIRESRESTANTS;
/

EXECUTE DBMS_OUTPUT.PUT_LINE('Il reste ' || EXEMPLAIRESRESTANTS(11846) || ' exemplaires de Pulp Fiction.');


-- 3)

-- a)

CREATE OR REPLACE FUNCTION nbNonRendus(idClient CLIENT.NUMCLIENT%TYPE)
RETURN NUMBER IS
    nbNonRendu NUMBER;
BEGIN
    SELECT COUNT(*) INTO nbNonRendu
    FROM LOCATION L
    WHERE (L.NumClient = idClient)
    AND (L.DateRetour IS NULL)
    AND ((SYSDATE - L.DateEnvoi) > 30);
    
    RETURN nbNonRendu;
END;
/

CREATE OR REPLACE TRIGGER clientNonRenduAlerte
BEFORE INSERT
ON LOCATION
FOR EACH ROW
BEGIN
    IF nbNonRendus(:NEW.numclient) > 0 THEN
        DBMS_OUTPUT.PUT_LINE('Attention : ce client a une ou plusieurs location(s) non rendues datant d"un mois ou plus');
    END IF;
END;
/

-- b)

CREATE OR REPLACE TRIGGER clientNonRenduBlocage
BEFORE INSERT
ON LOCATION
FOR EACH ROW
BEGIN
    IF nbNonRendus(:NEW.numclient) > 0 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Client a un ou plusieurs exemplaire(s) non rendu de 1 mois ou plus.');
    END IF;
END;
/

-- c)

INSERT INTO CLIENT (
    NomClient,
    PrenomClient,
    MotPasse,
    AdrClient,
    CPClient
)
VALUES (
    'Merienne',
    'Ashley',
    'motDePasse',
    '5 allée de la bannière de Maupertuis',
    '91190'
);

SELECT * FROM CLIENT;

SELECT * FROM LOCATION;

SELECT * FROM ENS2004.FILM F
WHERE UPPER(F.TITRE) = 'PULP FICTION';

SELECT * FROM ENS2004.EXEMPLAIRE E
WHERE E.NumFilm = 11846;

INSERT INTO LOCATION (
    NumExemplaire,
    DateLocation,
    NumClient,
    DateEnvoi
)
VALUES (
    20096,
    SYSDATE,
    1,
    SYSDATE
); -- should work

INSERT INTO LOCATION (
    NumExemplaire,
    DateLocation,
    NumClient,
    DateEnvoi
)
VALUES (
    20096,
    '10-01-2020',
    1,
    '06-02-2020'
); -- should get caught by trigger

SELECT * FROM LOCATION;

-- 4)

-- b)

CREATE OR REPLACE TRIGGER clientNonRenduBlocage3
BEFORE INSERT
ON LOCATION
FOR EACH ROW
BEGIN
    IF nbNonRendus(:NEW.numclient) > 3 THEN
        RAISE_APPLICATION_ERROR(-20003, 'Client a un ou plusieurs exemplaire(s) non rendu de 1 mois ou plus.');
    END IF;
END;
/


-- DROP TABLE LOCATION;
-- DROP TABLE CLIENT;
