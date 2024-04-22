SET SERVEROUTPUT ON;

// Exercice 1
// a)
CREATE OR REPLACE FUNCTION nbFilms
    (acteur NUMBER)
RETURN NUMBER IS
    nbFilmsAct NUMBER(5);
BEGIN
    SELECT COUNT(*) INTO nbFilmsAct
    FROM bdfilm.Film F
    INNER JOIN bdfilm.RoleFilm RF ON RF.idFilm = F.idFilm
    WHERE RF.idPersonne = acteur;
    RETURN nbFilmsAct;
END nbFilms;
/

EXECUTE DBMS_OUTPUT.PUT_LINE(nbFilms(880)||' films.');

// b)

CREATE OR REPLACE PROCEDURE Afficher
    (acteur NUMBER)
BEGIN
    SELECT *
    FROM bdfilm.Film F
END Afficher;
/
