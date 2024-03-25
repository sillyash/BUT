set serveroutput on;

// ----- Exercice 1 -----

CREATE OR REPLACE FUNCTION nbComedie
RETURN NUMBER IS
    nbFilmsComedie NUMBER(5);
BEGIN
    SELECT COUNT(DISTINCT(GF.idFilm)) INTO nbFilmsComedie
    FROM bdfilm.GenreFilm GF
    INNER JOIN bdfilm.Genre G ON G.idGenre = GF.idGenre
    WHERE (G.nomGenre = 'Comédie');
    RETURN nbFilmsComedie;
END nbComedie;
/

CREATE OR REPLACE FUNCTION nbComedieAction
RETURN NUMBER IS
    nbFilmsComedieAction NUMBER(5);
BEGIN
    SELECT COUNT(*) INTO nbFilmsComedieAction
    FROM bdfilm.GenreFilm GF1
    INNER JOIN bdfilm.GenreFilm GF2 ON GF2.idFilm = GF1.idFilm
    INNER JOIN bdfilm.Genre G1 ON G1.idGenre = GF1.idGenre
    INNER JOIN bdfilm.Genre G2 ON G2.idGenre = GF2.idGenre
    WHERE (G1.nomGenre = 'Comédie') AND (G2.nomGenre = 'Action');
    RETURN nbFilmsComedieAction;
END nbComedieAction;
/

CREATE OR REPLACE PROCEDURE exercice1 IS
    nbFilm NUMBER(5);
BEGIN
    nbFilm := nbComedie;
    IF nbFilm > 0 THEN DBMS_OUTPUT.PUT_LINE('Il y a '||nbFilm||' films de comédie.');
    ELSE DBMS_OUTPUT.PUT_LINE('Il y a '||nbFilm||' film de comédie.');
    END IF;
    nbFilm := nbComedieAction;
    IF nbFilm > 0 THEN DBMS_OUTPUT.PUT_LINE('Il y a '||nbFilm||' films d''action et de comédie.');
    ELSE DBMS_OUTPUT.PUT_LINE('Il y a '||nbFilm||' film d''action et de comédie.');
    END IF;
END exercice1;
/

EXECUTE exercice1;

// ----- Exercice 2 -----

CREATE OR REPLACE PROCEDURE exercice2 IS
    titreFilm       bdfilm.Film.titreOriginal%TYPE;
    prenomReal      bdfilm.Personne.prenomPersonne%TYPE;
    nomReal         bdfilm.Personne.nomPersonne%TYPE;
    idReal          bdfilm.Personne.idPErsonne%TYPE;
    titreVieuxFilm  bdfilm.Film.titreOriginal%TYPE;
BEGIN
    SELECT F.titreOriginal, P.prenompersonne, P.nompersonne, P.idPersonne INTO titreFilm, prenomReal, nomReal, idReal
    FROM bdfilm.Film F
    INNER JOIN bdfilm.EquipeFilm EF ON EF.idFilm = F.idFilm
    INNER JOIN bdfilm.Personne P ON P.idPersonne = EF.idPersonne
    WHERE (F.idFilm = 599) AND (EF.job = 'Director');
    
    SELECT F.titreOriginal INTO titreVieuxFilm
    FROM bdfilm.Film F
    INNER JOIN bdfilm.EquipeFilm EF ON EF.idFilm = F.idFilm
    WHERE F.DateSortie = ( select min(F.dateSortie)
                        from bdfilm.Film F
                        inner join bdfilm.EquipeFilm EF ON EF.idFilm = F.idFilm
                        where EF.idPersonne = idReal)
    AND EF.idPersonne = idReal;
    
    DBMS_OUTPUT.PUT_LINE('Le film n°599 est '||titreFilm||'.');
    DBMS_OUTPUT.PUT_LINE('Son réalisateur est '||prenomReal||' '||nomReal||'.');
    DBMS_OUTPUT.PUT_LINE('Son film le plus vieux est '||titreVieuxFilm||' .');
END exercice2; 
/

EXECUTE exercice2;
