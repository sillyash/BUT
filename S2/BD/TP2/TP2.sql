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

/* SELECT VG.nomvoyage
FROM Voyage VG
INNER JOIN Etape E ON E.numvoyage = VG.numvoyage
INNER JOIN Ville V ON V.numville = E.numville
GROUP BY VG.nomvoyage
HAVING COUNT(); */


// g)
SELECT VG.nomvoyage
FROM Voyage VG
WHERE (SELECT SUM(duree)
        FROM Etape E
        INNER JOIN Ville V ON E.numville = V.numville
        WHERE etat = 'California' AND E.numvoyage = VG.numvoyage)
        > (SELECT SUM(duree)
           FROM Etape E
           INNER JOIN Ville V ON E.numville = V.numville
           WHERE etat = 'Arizona' AND E.numvoyage = VG.numvoyage);
