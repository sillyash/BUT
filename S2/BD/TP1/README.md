# R206 · TP1

## Exercice 1

**Evaluer sur le jeu d’essai puis traduire en bon français les requêtes suivantes**

#### a)

    SELECT MIN (prixSem) AS PrixMin
    FROM Tarif T
    INNER JOIN Periode P ON T.codePeriode = P.codePeriode
    WHERE nomPeriode = 'Pleine saison'
    AND numTypeChalet = 2 ;

Donne le plus petit prix disponible disponible en pleine saison pour le chalet dont le numéro de type est 2.

#### b)

    SELECT T.numCamping, nomCamping
    FROM Tarif T
    INNER JOIN Periode P ON T.codePeriode = P.codePeriode
    INNER JOIN Camping C ON T.numCamping = C.numCamping
    WHERE nomPeriode = 'Pleine saison'
    AND numTypeChalet = 2
    AND prixSem = (SELECT MIN(prixSem)
                  FROM Tarif T
                  INNER JOIN Periode P ON T.codePeriode = P.codePeriode
                  WHERE nomPeriode = 'Pleine saison'
                  AND numtypeChalet = 2) ;

Donne les campings qui proposent le prix minimal pour le chalet de type numéro 2 en pleine saison.

#### c)

    SELECT C.numCamping, nomCamping
    FROM CompoCamping CC
    INNER JOIN TypeChalet TC ON CC.numTypeChalet = TC.numTypeChalet
    INNER JOIN Camping C ON CC.numCamping = C.numCamping
    WHERE capacite = 6
    GROUP BY C.numCamping, nomCamping
    HAVING COUNT(*) = (SELECT COUNT(*)
                      FROM TypeChalet
                      WHERE capacite = 6) ;

Donne les campings qui disposent de tous les types chalets de capacité 6.

<br>

## Exercice 2

**Écrire en SQL les requêtes suivantes**

[Voir fichier](./TP1.sql)



