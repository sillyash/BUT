# R206 - BD

## TP8 - Curseurs, fonctions et procédures stockées

### Ash MERIENNE - 2C

<br>

#### Remarque : Avant toute chose, on active l'output de serveur.

	SET SERVEROUTPUT ON;

## Exercice 1. On s’intéresse aux films dans lesquels un acteur joue

### a. Ecrire une fonction ```nbFilms``` qui, étant donné un acteur (son ```idPersonne```), retourne le nombre de films dans lesquels il a joué. Se servir de la fonction ```nbFilms``` pour afficher le nombre de films où joue l’acteur n. ```880```.

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

### b. On va maintenant afficher les genres des films dans lesquels un acteur joue. Écrire une procédure ```Afficher``` qui, étant donné un acteur (son ```idPersonne```), affiche les genres et le nombre de films par genre dans lesquels il a joué.  Exécuter la procédure ```Afficher``` pour les genres des films où joue l’acteur n. ```880```. (Rappel : Un film peut avoir plusieurs genres.)


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

### c. Écrire une procédure ```AfficherNom``` qui à partir du nom d’un acteur, cherche toutes les personnes qui portent ce nom et, pour chacune d’entre elles, affiche son prénom, son nom et appelle la procédure ```Afficher``` précédente. Exécuter la procédure ```AfficherNom``` pour les personnes dont le nom est « ```Affleck``` ».

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

### d. On veut améliorer le résultat précèdent en indiquant pour chacun des acteurs le nombre total de films dans lesquels il a joué, ceci avant le détail sur les genres de ses films. Exécuter la nouvelle procédure ```AfficherNom``` pour les personnes dont le nom est « ```Affleck``` ».

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

## Exercice 2. On veut trouver des renseignements sur un film à partir de son titre.

### a. Écrire d’abord une procédure ```ActeursDe``` qui à partir d’un ```idFilm``` donne la liste des acteurs (prénom et nom) qu’y jouent dedans. Exécuter la procédure ```ActeursDe``` pour le film n. ```489```.

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

### b. Écrire une procédure ```ActeursDuFilm``` qui, à partir du titre d’un film, fait ensuite appel à la procédure précédente pour donner la liste des acteurs qu’y jouent dedans. Traiter par des exceptions les cas où aucun film ne porte un tel titre, ou si au contraire il y en a plusieurs (sans faire appel à un curseur). Exécuter la procédure ```ActeursDuFilm``` pour les films « ```Will Hunting``` », « ```Arizona Dream``` » et « ```La vie est belle``` ».

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

### C. Prévoir maintenant que le titre peut être partiel, et prévoir un curseur pour gérer les cas où il y a plusieurs films avec ce titre partiel. Donner également l’année de sortie du film. Exécuter la nouvelle procédure ```ActeursDuFilm``` pour les titres « ```Will``` », « ```Arizona``` » et « ```Dream``` »
	
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
