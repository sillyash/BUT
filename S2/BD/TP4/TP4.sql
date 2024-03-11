// Exercice 1
// a)
SELECT DISTINCT F.idFilm, F.titreOriginal
FROM bdfilm.Film F
INNER JOIN bdfilm.Sortie S ON S.idFilm = F.idFilm
INNER JOIN bdfilm.Pays P ON P.codePays = S.codePays
WHERE (P.nomPays != 'France')
ORDER BY idFilm;

// b)
SELECT P.prenomPersonne, P.nomPersonne
FROM bdfilm.Personne P
WHERE P.idPersonne NOT IN (select idpersonne from bdfilm.EquipeFilm EF where EF.job = 'Director')
AND P.idPersonne NOT IN (select idpersonne from bdfilm.RoleFilm);

// c)
SELECT P.prenomPersonne, P.nomPersonne
FROM bdfilm.Personne P
WHERE (select count(idpersonne) from bdfilm.EquipeFilm EF where EF.job = 'Director' and idpersonne = P.idpersonne)
        > (select count(idpersonne) from bdfilm.RoleFilm where idpersonne = P.idpersonne);
        
// d)
SELECT DISTINCT P.prenomPersonne, P.nomPersonne
FROM bdfilm.Personne P
INNER JOIN bdfilm.EquipeFilm EF ON EF.idPersonne = P.idPersonne
INNER JOIN bdfilm.Film F ON EF.idFilm = F.idFilm
WHERE P.idPersonne IN (select idPersonne from bdfilm.RoleFilm where idFilm = F.idFilm);


// Exercice 2
// a)
DROP VIEW vFilm;

CREATE VIEW vFilm AS
SELECT idFilm, (select count (*) from bdfilm.sortie where idFilm = F.idFilm) AS NbSorties
FROM bdfilm.Film F
ORDER BY idFilm;

SELECT * FROM vFilm;


