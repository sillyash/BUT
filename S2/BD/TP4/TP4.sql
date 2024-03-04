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
WHERE P.idPersonne NOT IN (select * from bdfilm.EquipeFilm EF where EF.job = 'Director')
AND P.idPersonne NOT IN bdfilm.Rolefilm;

select * from bdfilm.equipefilm;