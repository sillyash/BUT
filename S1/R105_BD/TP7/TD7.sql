// Q1
SELECT C.NumCamping, A.NomActivite
FROM td_camping C
INNER JOIN td_compocamping CO ON CO.NumCamping = C.NumCamping
INNER JOIN td_typechalet TC ON TC.NumTypeChalet = CO.NumTypeChalet
INNER JOIN td_acticamping AC ON AC.NumCamping = C.NumCamping
INNER JOIN td_activite A ON A.NumActivite = AC.NumActivite
WHERE TC.NomTypeChalet = 'Eden'
ORDER BY C.NumCamping;

// Q2
SELECT DISTINCT BL.NomBaseL
FROM td_baseloisirs BL
INNER JOIN td_actibase AB ON AB.NumBaseL = BL.NumBaseL
INNER JOIN td_activite A1 ON A1.NumActivite = AB.NumActivite
INNER JOIN td_camping C ON C.BaseLoisirs = BL.NumBaseL
INNER JOIN td_acticamping AC ON AC.NumCamping = C.NumCamping
INNER JOIN td_activite A2 ON A2.NumActivite = AC.NumActivite
WHERE(A1.NomActivite = 'VTT') AND (A2.NomActivite = 'Canoë-Kayak');




// Q5
SELECT DISTINCT C.NomCamping, TC.NomTypeChalet, TC.Capacite
FROM td_camping C
INNER JOIN td_acticamping AC ON AC.NumCamping = C.NumCamping
INNER JOIN td_compocamping CO ON CO.NumCamping = C.NumCamping
INNER JOIN td_typechalet TC ON TC.NumTypeChalet = CO.NumTypeChalet
WHERE AC.PrixActivite = 0;

