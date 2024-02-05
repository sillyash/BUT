// a)
SELECT DISTINCT C.numcamping, C.nomcamping
FROM Camping C
INNER JOIN Acticamping AC ON AC.numcamping = C.numcamping
INNER JOIN Activite A ON A.numactivite = AC.numactivite
WHERE AC.prixactivite < 3;


// b)
SELECT MAX(AC.prixactivite) AS MaxPrixActivite
FROM acticamping AC
INNER JOIN camping C ON C.numcamping = AC.numcamping
WHERE C.nomcamping = 'La Forêt';


// c)
SELECT DISTINCT C.numcamping, C.nomcamping
FROM Camping C
INNER JOIN Acticamping AC ON AC.numcamping = C.numcamping
WHERE AC.prixactivite > (SELECT MAX(AC.prixactivite)
                        FROM acticamping AC
                        INNER JOIN camping C ON C.numcamping = AC.numcamping
                        WHERE C.nomcamping = 'La Forêt')
ORDER BY C.numcamping;


// d)
SELECT C.numcamping, C.nomcamping, COUNT(AC.numactivite)
FROM Camping C
INNER JOIN Acticamping AC ON AC.numcamping = C.numcamping
GROUP BY C.numcamping, C.nomcamping
HAVING COUNT(AC.numactivite) > (SELECT COUNT(*)
                                FROM Acticamping AC
                                INNER JOIN Camping C ON C.numcamping = AC.numcamping
                                WHERE C.nomcamping = 'Aqua Viva')
ORDER BY C.numcamping;


// e)
SELECT DISTINCT A.numactivite, A.nomactivite
FROM Camping C
INNER JOIN Acticamping AC ON AC.numcamping = C.numcamping
INNER JOIN Activite A ON A.numactivite = AC.numactivite
WHERE C.nbetoiles = 3
ORDER BY A.numactivite;


// f)
SELECT A.numactivite, A.nomactivite, COUNT(*) AS NbCampings3Etoiles
FROM Camping C
INNER JOIN Acticamping AC ON AC.numcamping = C.numcamping
INNER JOIN Activite A ON A.numactivite = AC.numactivite
WHERE C.nbetoiles = 3
GROUP BY A.numactivite, A.nomactivite
HAVING COUNT(*) = (SELECT COUNT(*)
                    FROM Camping
                    WHERE nbetoiles = 3)
ORDER BY A.numactivite;
