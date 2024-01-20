// Q1
SELECT DISTINCT VG.numvoyage, VG.nomvoyage
FROM Voyage VG
INNER JOIN ETAPE E ON E.numvoyage = VG.numvoyage
INNER JOIN VILLE V ON V.numville = E.numville
INNER JOIN PAYS P ON P.numpays = V.numpays
INNER JOIN FILM F ON F.paysfilm = P.numpays
WHERE (F.scorefilm > 9);

// Q2
SELECT VG.numvoyage, VG.nomvoyage, TF.datedep
FROM VOYAGE VG
INNER JOIN TARIF TF ON TF.numvoyage = VG.numvoyage
WHERE ((TF.datedep >= '01/11/21') AND (TF.datedep <= '30/11/21'));

// Q3
SELECT VG.numvoyage, VG.nomvoyage, TF.datedep, (VG.nbmin*TF.prix) AS "Chiffre d'affaire min"
FROM TARIF TF
INNER JOIN VOYAGE VG ON VG.numvoyage = TF.numvoyage
ORDER BY "Chiffre d'affaire min" DESC;

// Q4
SELECT COUNT(*) AS NbDep, (SUM(TF.prix)/COUNT(*)) AS PrixMoyDep, COUNT(DISTINCT(TF.numvoyage)) AS NbVoyages
FROM TARIF TF
WHERE ((TF.datedep >= '01/08/21') AND (TF.datedep <= '31/08/21'));

// Q5
SELECT (COUNT(DISTINCT(V.numpays))) AS NbPaysVisites
FROM VOYAGE VG
INNER JOIN ETAPE E ON E.numvoyage = VG.numvoyage
INNER JOIN VILLE V ON V.numville = E.numville
WHERE (VG.nomvoyage = 'La Dernière Croisade');

// Q6
SELECT COUNT(DISTINCT(L.codelieu)) AS NbLieuVisite
FROM ETAPE E
INNER JOIN VILLE V ON V.numville = E.numville
INNER JOIN LIEU L ON L.numville = V.numville;

// Q7
SELECT P.NumPays, P.NomPays, COUNT(*) AS NbVillesConnues
FROM VILLE V
INNER JOIN PAYS P ON P.numpays = V.numpays
GROUP BY (P.NumPays, P.NomPays)
ORDER BY (P.NomPays);

