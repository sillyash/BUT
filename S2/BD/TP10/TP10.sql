SET SERVEROUTPUT ON;

// Exercice 1

CREATE OR REPLACE PACKAGE statistics AS
    FUNCTION nbHorsFrance RETURN NUMBER;
    PROCEDURE basseRes;
END statistics;
/

CREATE OR REPLACE PACKAGE BODY statistics AS
    FUNCTION nbHorsFrance RETURN NUMBER AS
        nbFilms NUMBER;
    BEGIN
        SELECT COUNT(*) INTO nbFilms
        FROM bdfilm.Film F
        WHERE F.idFilm NOT IN (
            SELECT F.idFilm
            FROM bdfilm.Film F
            INNER JOIN bdfilm.Sortie S ON S.idFilm = F.idFilm
            INNER JOIN bdfilm.Pays P ON P.codePays = S.codePays
            WHERE P.nomPays = 'France');
        RETURN nbFilms;
    END nbHorsFrance;
    
    PROCEDURE basseRes IS
        nbFilmsHiRes NUMBER;
        nbFilmsTT NUMBER;
    BEGIN
        SELECT COUNT (DISTINCT F.idFilm) INTO nbFilmsHiRes
        FROM bdfilm.Film F
        INNER JOIN bdfilm.Version V ON V.idFilm = F.idFilm
        WHERE V.resolution = '4K' OR V.resolution = '8K';
        
        SELECT COUNT (*) INTO nbFilmsTT
        FROM bdfilm.Film F;
        
        DBMS_OUTPUT.PUT_LINE((nbFilmsHiRes/nbFilmsTT)*100||
            '% des films de la base ne sont pas disponibles à haute résolution (4K/8K).');
    END basseRes;
END statistics;
/


EXECUTE DBMS_OUTPUT.PUT_LINE('Il y a '||statistics.nbHorsFrance||' films de la base qui ne sont jamais sortis en France.');
EXECUTE statistics.basseRes;


// Exercice 2


CREATE OR REPLACE PACKAGE famille AS
END famille;
/

CREATE OR REPLACE PACKAGE BODY famille AS
END famille;
/
