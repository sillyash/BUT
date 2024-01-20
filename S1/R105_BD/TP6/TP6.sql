SELECT VOYAGE.NumVoyage, VOYAGE.NomVoyage, TARIF.Prix
FROM VOYAGE
INNER JOIN ETAPE
ON ETAPE.NumVoyage = VOYAGE.NumVoyage
INNER JOIN VILLE
ON VILLE.NumVille = ETAPE.NumVille
INNER JOIN TARIF
ON VOYAGE.NumVoyage = Tarif.NumVoyage
WHERE (VILLE.Numville = 4);