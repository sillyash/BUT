// Q1
SELECT V.NumVoyage, V.NomVoyage, E.NumOrdre, VI.NomVille, E.duree
FROM Voyage V
INNER JOIN Etape E ON E.NumVoyage = V.NumVoyage
INNER JOIN Ville VI ON VI.NumVille = e.numville
WHERE V.typepension = 'DP'
ORDER BY V.numvoyage, E.numordre;

// Q2
SELECT L.codelieu, L.libellelieu
FROM Lieu L
INNER JOIN Tournage T ON T.codelieu = L.codelieu
INNER JOIN Film F ON F.codefilm = T.codefilm
INNER JOIN Pays P ON L.numpays = P.numpays
WHERE (P.nompays = 'Italie') AND (F.titrefilm = 'Le Parrain')
ORDER BY L.libellelieu;

// Q3
SELECT V.numville, V.nomville
FROM Ville V
INNER JOIN Lieu L ON L.numville = V.numville
INNER JOIN Tournage T ON T.codelieu = L.codelieu
INNER JOIN Film F ON F.codefilm = T.codefilm
WHERE (F.titrefilm = 'No Country For Old Men');

// Q4
SELECT DISTINCT L.codelieu, L.libellelieu
FROM Lieu L
INNER JOIN Ville V ON V.numville = L.numville
INNER JOIN Etape E ON E.numville = V.numville
ORDER BY l.libellelieu;

// Q5
SELECT DISTINCT VG.numvoyage, VG.nomvoyage
FROM Voyage VG
INNER JOIN Etape E ON E.numvoyage = VG.numvoyage
INNER JOIN Etape E2 ON ((E2.numvoyage = VG.numvoyage) AND (E2.numville < E.numville))
WHERE (E.duree = E2.duree);

// Q6
SELECT DISTINCT L.codelieu, L.libellelieu
FROM Lieu L
INNER JOIN Tournage T ON T.codelieu = L.codelieu
INNER JOIN Film F ON F.codefilm = T.codefilm
INNER JOIN Film F2 ON F2.codefilm = T.codefilm
WHERE (F.anneefilm = F2.anneefilm);

