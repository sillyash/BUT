// a)
SELECT DISTINCT numvoyage, nomvoyage
FROM Voyage
NATURAL JOIN Etape
NATURAL JOIN Ville
NATURAL JOIN Pays
WHERE nompays = 'France'
ORDER BY numvoyage;


// b)
SELECT DISTINCT numvoyage, nomvoyage
FROM Voyage
NATURAL JOIN Etape
NATURAL JOIN Ville
NATURAL JOIN Pays
WHERE numvoyage NOT IN (SELECT numvoyage
                        FROM Etape
                        NATURAL JOIN Ville
                        NATURAL JOIN Pays
                        WHERE nompays != 'France')
ORDER BY numvoyage;


// c)
SELECT DISTINCT numvoyage, nomvoyage
FROM Voyage
NATURAL JOIN Etape
NATURAL JOIN Ville
NATURAL JOIN Pays
WHERE numvoyage IN (SELECT numvoyage
                    FROM Etape
                    NATURAL JOIN Ville
                    NATURAL JOIN Pays
                    WHERE nompays != 'France')
ORDER BY numvoyage;


// d)
SELECT DISTINCT numvoyage, nomvoyage
FROM Voyage
NATURAL JOIN Etape
NATURAL JOIN Ville
NATURAL JOIN Pays
WHERE nompays != 'France'
ORDER BY numvoyage;


// e)
SELECT DISTINCT VG.nomvoyage, V.nomville, E.duree
FROM Voyage VG
INNER JOIN Etape E ON E.numvoyage = VG.numvoyage
INNER JOIN Ville V ON V.numville = E.numville
WHERE E.duree = (SELECT MAX(duree)
                FROM Etape E
                WHERE VG.numvoyage = E.numvoyage);


// f)
SELECT VG.nomvoyage
FROM Voyage VG
WHERE (SELECT COUNT (*)
        FROM Etape E
        INNER JOIN Ville V ON E.numville = V.numville
        WHERE etat = 'California' AND E.numvoyage = VG.numvoyage)
        > (SELECT COUNT (*)
           FROM Etape E
           INNER JOIN Ville V ON E.numville = V.numville
           WHERE etat = 'Arizona' AND E.numvoyage = VG.numvoyage);


// g)
SELECT VG.nomvoyage
FROM Voyage VG
WHERE (SELECT COUNT (*)
        FROM Etape E
        INNER JOIN Ville V ON E.numville = V.numville
        WHERE etat = 'California' AND E.numvoyage = VG.numvoyage)
        = (SELECT COUNT (*)
           FROM Etape E
           INNER JOIN Ville V ON E.numville = V.numville
           WHERE etat = 'Arizona' AND E.numvoyage = VG.numvoyage);


// h)
SELECT VG.nomvoyage, TF.prix, TF.datedep
FROM Voyage VG
INNER JOIN Tarif TF ON TF.numvoyage = VG.numvoyage
WHERE TF.prix = (SELECT MIN (prix)
                FROM Tarif
                WHERE tarif.numvoyage = VG.numvoyage)
ORDER BY TF.prix;


// i)
SELECT VG.nomvoyage
FROM Voyage VG
WHERE (SELECT COUNT (*)
        FROM Tarif TF
        WHERE TF.numvoyage = VG.numvoyage
        AND TF.prix < 1000)
        > (SELECT COUNT (*)
        FROM Tarif TF
        WHERE TF.numvoyage = VG.numvoyage)/2
ORDER BY VG.nomvoyage;


// j)
SELECT F.titrefilm
FROM Film F
WHERE (F.codefilm IN (SELECT codefilm
                    FROM Tournage T
                    INNER JOIN Lieu L ON L.codelieu = T.codelieu
                    INNER JOIN Ville V ON L.numville = V.numville
                    INNER JOIN Pays P ON ((P.numpays = L.numpays) OR (P.numpays = V.numpays))
                    WHERE P.nompays = 'Allemagne')
AND F.codefilm IN (SELECT codefilm
                    FROM Tournage T
                    INNER JOIN Lieu L ON L.codelieu = T.codelieu
                    INNER JOIN Ville V ON L.numville = V.numville
                    INNER JOIN Pays P ON ((P.numpays = L.numpays) OR (P.numpays = V.numpays))
                    WHERE P.nompays = 'Irlande'))           
ORDER BY F.titrefilm;


// k)
SELECT VG.numvoyage, VG.nomvoyage
FROM Voyage VG
WHERE (SELECT COUNT (*)
        FROM Tarif TF
        WHERE TF.numvoyage = VG.numvoyage
        AND TF.prix < 1000) >= 2
AND VG.numvoyage IN (SELECT E1.numvoyage
    FROM Etape E1
    INNER JOIN Etape E2 ON E2.numvoyage = E1.numvoyage AND E2.numordre <> E1.numordre
    WHERE E1.numvoyage = VG.numvoyage
    AND (E1.numville = E2.numville OR E1.duree >= 2 OR E2.duree >= 2));
    

// l))
SELECT DISTINCT F.titrefilm
FROM Film F
INNER JOIN Tournage T ON T.codefilm = F.codefilm
INNER JOIN Lieu L ON L.codelieu = T.codelieu
INNER JOIN Ville V ON V.numville = L.numville
WHERE V.numville IN (SELECT V.numville
                    FROM Tournage T
                    INNER JOIN Lieu L ON L.codelieu = T.codelieu
                    INNER JOIN Ville V ON V.numville = L.numville
                    WHERE T.codefilm = F.codefilm
                    GROUP BY F.titrefilm, V.numville
                    HAVING COUNT (*) >= 2)
ORDER BY F.titrefilm;

