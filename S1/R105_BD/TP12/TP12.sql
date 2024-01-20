// Q1
SELECT ROUND(AVG(BudgetFilm),2) AS BudgetMoyen
FROM Film;

// Q2
SELECT SUM(F.RevenuFilm) AS TTRevenuFilm
FROM Film F
INNER JOIN Pays P ON P.NumPays = F.PaysFilm
WHERE P.NomPays = 'Nouvelle-Zélande';

// Q3
SELECT  COUNT (*) AS NbTournages,
        COUNT (L.NumVille) AS NbTournagesVille,
        COUNT (L.NumPays) AS NbTournagesNotVille
FROM Lieu L;

// Q4
SELECT VG.NumVoyage, VG.NomVoyage, MAX(E.duree) AS "Durée de sejour de l'étape la plus longue"
FROM Voyage VG
INNER JOIN Etape E ON E.NumVoyage = VG.NumVoyage
GROUP BY (VG.NumVoyage, VG.NomVoyage);

// Q5
SELECT  P.NumPays, P.NomPays,
        COUNT (DISTINCT L.CodeLieu) AS NbLieuTournageTT,
        COUNT (DISTINCT L.NumVille) AS NbLieuTournageVille
FROM Pays P, Ville V, Lieu L
INNER JOIN Tournage T ON T.CodeLieu = L.CodeLieu
WHERE   (((L.NumVille = V.NumVille) OR (L.NumPays IS NOT NULL)) AND
        ((P.NumPays = L.NumPays) OR (P.NumPays = V.NumPays)))
GROUP BY P.NumPays, P.NomPays
ORDER BY P.NumPays;

