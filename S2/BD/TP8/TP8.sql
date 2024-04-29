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

EXECUTE DBMS_OUTPUT.PUT_LINE('L"acteur n°880 a joué dans '||nbFilms(880)||' film(s).');

// b)

CREATE OR REPLACE PROCEDURE Afficher
    (idActeur bdfilm.personne.idPersonne%type)
IS
nomActeur bdfilm.personne.nomPersonne%type;
prenomActeur bdfilm.personne.prenomPersonne%type;
CURSOR curseurActGenre IS
    SELECT G.idGenre, G.nomGenre, COUNT (*) AS NbFilmsGenre
    FROM bdfilm.RoleFilm RF
    INNER JOIN bdfilm.Film F ON F.idFilm = RF.idFilm
    INNER JOIN bdfilm.GenreFilm GF ON GF.idFilm = F.idFilm
    INNER JOIN bdfilm.Genre G ON G.idGenre = GF.idGenre
    WHERE idPersonne = idActeur
    GROUP BY G.idGenre, G.nomGenre;
BEGIN
    SELECT P.PrenomPersonne, P.NomPersonne INTO prenomActeur, nomActeur
    FROM bdfilm.Personne P
    WHERE idPersonne = idActeur;
    DBMS_OUTPUT.PUT_LINE(prenomActeur||' '||nomActeur||' a joué dans dans ces genres :');
    FOR ligneC IN curseurActGenre
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'idGenre : '||ligneC.idGenre||CHR(9)||
            'Nom du genre : '||ligneC.nomGenre||CHR(9)||
            'Nombre de films : '||ligneC.NbFilmsGenre);
    END LOOP;
END Afficher;
/

EXECUTE Afficher(880);


// c)

CREATE OR REPLACE PROCEDURE AfficherNom
    (nomActeur bdfilm.Personne.NomPersonne%type)
IS
CURSOR acteurNom IS
    SELECT idPersonne
    FROM bdfilm.Personne P
    WHERE nomPersonne = nomActeur;
BEGIN
    FOR ligneC IN acteurNom
    LOOP
        Afficher(ligneC.idPErsonne);
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
END AfficherNom;
/

EXECUTE AfficherNom('Affleck');


// d)

CREATE OR REPLACE PROCEDURE AfficherNom
    (nomActeur bdfilm.Personne.NomPersonne%type)
IS
nbreFilms NUMBER;
CURSOR acteurNom IS
    SELECT idPersonne
    FROM bdfilm.Personne P
    WHERE nomPersonne = nomActeur;
BEGIN
    FOR ligneC IN acteurNom
    LOOP
        nbreFilms := nbFilms(ligneC.idPersonne);
        Afficher(ligneC.idPErsonne);
        DBMS_OUTPUT.PUT_LINE('Nombre total de films joués : '||nbreFilms);
        DBMS_OUTPUT.PUT_LINE('');
    END LOOP;
END AfficherNom;
/

EXECUTE AfficherNom('Affleck');


// Exercice 2
// a)

CREATE OR REPLACE PROCEDURE ActeursDe
    (film bdfilm.Film.idFilm%type)
IS
nbreFilms NUMBER;
titreFilm bdfilm.film.titreOriginal%type;
CURSOR acteursFilm IS
    SELECT nomPersonne, prenomPersonne
    FROM bdfilm.Film F
    INNER JOIN bdfilm.RoleFilm RF ON RF.idFilm = F.idFilm
    INNER JOIN bdfilm.Personne P ON P.idPersonne = RF.idPErsonne
    WHERE F.idFilm = film;
BEGIN
    SELECT titreOriginal INTO titreFilm
    FROM bdfilm.Film F
    WHERE F.idFilm = film;
    DBMS_OUTPUT.PUT_LINE('Acteurs qui jouent dans "'||titreFilm||'" :');
    DBMS_OUTPUT.PUT_LINE('');
    FOR ligne IN acteursFilm
    LOOP
        DBMS_OUTPUT.PUT_LINE(
            'Nom : '||ligne.nomPersonne||CHR(9)||
            'Prénom : '||ligne.prenomPersonne);
    END LOOP;
END ActeursDe;
/

EXECUTE ActeursDe(489);


// b)

CREATE OR REPLACE PROCEDURE ActeursDuFilm
    (titreFilm bdfilm.Film.titre%type)
IS
nbreFilms NUMBER;
numFilm bdfilm.film.idFilm%type;
BEGIN
    SELECT idFilm INTO numFilm
    FROM bdfilm.Film F
    WHERE F.titre = titreFilm;
    DBMS_OUTPUT.PUT_LINE('Le film "'||titreFilm||'" est le film n°'||numFilm||'.');
    DBMS_OUTPUT.PUT_LINE('');
    ActeursDe(numFilm);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Il n''y a pas de film avec ce titre.');
    WHEN TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('Plusieurs films avec ce titre !');
END ActeursDuFilm;
/

EXECUTE ActeursDuFilm('Will Hunting');
EXECUTE ActeursDuFilm('Arizona Dream');
EXECUTE ActeursDuFilm('La vie est belle');


// c)

CREATE OR REPLACE PROCEDURE ActeursDuFilm
    (titreFilm bdfilm.Film.titre%type)
IS
nbreFilms NUMBER;
numFilm bdfilm.film.idFilm%type;
BEGIN
    SELECT idFilm INTO numFilm
    FROM bdfilm.Film F
    WHERE F.titre LIKE '%titreFilm%';
    DBMS_OUTPUT.PUT_LINE('Le film "'||titreFilm||'" est le film n°'||numFilm||'.');
    DBMS_OUTPUT.PUT_LINE('');
    ActeursDe(numFilm);
EXCEPTION
    WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Il n''y a pas de film avec ce titre.');
    WHEN TOO_MANY_ROWS THEN
    DBMS_OUTPUT.PUT_LINE('Plusieurs films avec ce titre !');
END ActeursDuFilm;
/

EXECUTE ActeursDuFilm('Will');
EXECUTE ActeursDuFilm('Arizona');
EXECUTE ActeursDuFilm('Dream');

