// Q1
SELECT F.titrefilm, COUNT(T.CodeLieu) AS NbLieuxTournage
FROM FILM F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
WHERE F.anneefilm = 1994
GROUP BY (F.titrefilm);

// Q2
SELECT F.titrefilm, COUNT(L.NumVille) AS NbLieuxTournageVille
FROM FILM F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
INNER JOIN Lieu L ON T.CodeLieu = L.CodeLieu
WHERE F.anneefilm = 1994
GROUP BY (F.titrefilm);

// Q3a
SELECT F.titrefilm, COUNT(P.NumPays) AS NbLieuxTournagePays
FROM FILM F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
INNER JOIN Lieu L ON T.CodeLieu = L.CodeLieu
INNER JOIN Pays P ON P.NumPays = L.NumPays
WHERE F.anneefilm = 1994
GROUP BY (F.titrefilm);

// Q3b
SELECT F.titrefilm, COUNT(L.NumPays) AS NbLieuxTournagePays
FROM FILM F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
INNER JOIN Lieu L ON T.CodeLieu = L.CodeLieu
WHERE F.anneefilm = 1994
GROUP BY (F.titrefilm);

// Q4
SELECT F.CodeFilm, F.TitreFilm
FROM Film F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
GROUP BY (F.CodeFilm, F.TitreFilm)
HAVING COUNT(T.CodeLieu) >= 35;

// Q5
SELECT F.CodeFilm, F.TitreFilm
FROM Film F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
WHERE (F.anneefilm >= 2000)
GROUP BY (F.CodeFilm, F.TitreFilm)
HAVING COUNT(T.CodeLieu) >= 35;

// Q6a
SELECT L.CodeLieu, L.LibelleLieu
FROM Film F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
INNER JOIN Lieu L ON L.CodeLieu = T.CodeLieu
WHERE (F.scorefilm >= 8)
GROUP BY (L.CodeLieu, L.LibelleLieu)
HAVING (COUNT(F.CodeFilm) >= 2);

// Q6b
SELECT L.CodeLieu, L.LibelleLieu
FROM Film F
INNER JOIN Tournage T ON T.CodeFilm = F.CodeFilm
INNER JOIN Lieu L ON L.CodeLieu = T.CodeLieu
GROUP BY (L.CodeLieu, L.LibelleLieu)
HAVING (AVG(F.scorefilm) >= 8) AND (COUNT(F.CodeFilm) >= 2);

// Q7
SELECT VG.NomVoyage, E.numville, SUM(E.duree) AS DureeVilleTT
FROM Voyage VG
INNER JOIN Etape E ON E.NumVoyage = VG.NumVoyage
GROUP BY (VG.NomVoyage, E.numville)
HAVING(SUM(E.duree) >= 3);
