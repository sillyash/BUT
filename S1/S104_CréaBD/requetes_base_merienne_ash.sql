/* 	R1
	Donne l'IBAN et le BIC de la société Oyoba.
	Peut servir pour retrouver l'IBAN d'un Client. */
SELECT CL.RaisonSociale, CL.IBAN, CL.BIC
FROM CLIENT CL
WHERE CL.RaisonSociale = 'Oyoba';


/* 	R2
	Donne le nombre de clients situés en Alsace.
	Peut servir pour des statistiques ou de la promotion. */
SELECT COUNT(*) AS NbClientsAlsace
FROM CLIENT CL
INNER JOIN DEPARTEMENT DP ON DP.CodeDepartement = CL.CodeDepartement
WHERE DP.NomDepartement = 'Alsace';


/*	R3
	Liste toutes les commandes du 20/12/2022.
	Peut servir à des fins de logistique. */
SELECT CM.idCommande, CM.idClient, CM.idSite, CM.TauxTVA
FROM COMMANDE CM
WHERE CM.DateCommande = '2022-12-20';


/*	R4
	Pour la commande n°34, liste les éléments de la commande.
	Peut servir à des fins logistiques. */
SELECT CM.idCommande, CM.DateCommande, LC.idProduit, LC.NbProduit
FROM COMMANDE CM
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
WHERE CM.idCommande = 34;


/*	R5
	Pour la commande n°34, liste les éléments de la commande,
	cette fois avec le nom et le prix individuel des produits.
	Peut servir à des fins financières. */
SELECT CM.idCommande, CM.DateCommande, LC.idProduit, LC.NbProduit, P.Description, P.Prix AS PrixUnitaire
FROM COMMANDE CM
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
INNER JOIN PRODUIT P ON P.idProduit = LC.idProduit
WHERE CM.idCommande = 34;


/*	R6
	Pour la commande n°34, donne le prix total hors taxe, et avec taxes.
	Peut servir à des fins financières. 
	
	Remarque: on multiplie TauxTVA par 1.0 pour avoir un résultat décimal*/
SELECT	CM.idCommande, CM.DateCommande,
		SUM(P.Prix*LC.NbProduit) AS PrixHT, CM.TauxTVA, 
		SUM(P.Prix*LC.NbProduit)-(SUM(P.Prix*LC.NbProduit)*(CM.TauxTVA*1.0/100)) AS PrixTTC
FROM COMMANDE CM
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
INNER JOIN PRODUIT P ON P.idProduit = LC.idProduit
WHERE CM.idCommande = 34;


/*	R7
	On veut savoir si l'entrepôt situé au 5658 Glacier Hill Place
	dispose d'un quai de déchargement.
	Peut servir à des fins logistiques. */
SELECT E.idEntrepot, E.QuaiDeDéchargement
FROM ENTREPOT E
WHERE E.AddresseEntrepot = '5658 Glacier Hill Place';


/* 	R8
	Pour l'entrpot situé au 252 Eastwood Center, on veut connaître
	le stock de 'Dragline' et leur placement dans l'entrepôt.
	Peut servir à des fins logistiques. */
SELECT E.idEntrepot, P.idProduit, P.Description, S.QuantiteStock, S.NumBaie, S.NumRangée
FROM ENTREPOT E
INNER JOIN STOCK S ON S.idEntrepot = E.idEntrepot
INNER JOIN PRODUIT P ON P.idProduit = S.idProduit
WHERE (E.AddresseEntrepot = '252 Eastwood Center') AND (P.Description = 'Dragline');


/* 	R9
	Pour l'entrpot situé au 4463 Crownhardt Terrace, on veut connaître
	la valeur totale HT des produits stockés dans l'entrepôt.
	Peut servir à des fins économiques. */
SELECT E.idEntrepot, (P.Prix*S.QuantiteStock) AS ValeurHT
FROM ENTREPOT E
INNER JOIN STOCK S ON S.idEntrepot = E.idEntrepot
INNER JOIN PRODUIT P ON P.idProduit = S.idProduit
WHERE (E.AddresseEntrepot = '4463 Crownhardt Terrace')
GROUP BY E.idEntrepot;


/*	R10
	Pour le produit n°80, on liste les addresses et les
	villes des entrepôts où il est stocké et le stock restant.
	Peut servir à des fins logistiques. */
SELECT P.idProduit, S.idEntrepot, S.QuantiteStock, E.AddresseEntrepot, V.NomVille
FROM PRODUIT P
INNER JOIN STOCK S ON S.idProduit = P.idProduit
INNER JOIN ENTREPOT E ON E.idEntrepot = S.idEntrepot
INNER JOIN VILLE V ON (V.CodeVille, V.CodeDepartement) = (E.CodeVille, E.CodeDepartement)
WHERE P.idProduit = 80;


/*	R11
	Lister les interlocuteurs du client Twitterworks.
	Peut servir à des fins commerciales. */
SELECT CL.RaisonSociale, INTER.idInterloc, INTER.RoleInterloc, INTER.PrenomInterloc, 
		INTER.NomInterlocuteur, INTER.EmailInterloc, INTER.TelInterloc
FROM CLIENT CL
INNER JOIN INTERLOCUTEUR INTER ON INTER.idClient = CL.idClient
WHERE CL.RaisonSociale = 'Twitterworks';


/*	R12
	Donner l'URL de la fiche technique du produit
	commandé par la société Lazzy le 2015-11-11.
	Peut servir à des fins de SAV. */
SELECT CL.idClient, CL.NomJuridique, CM.DateCommande, LC.idProduit, P.Description, P.FicheTechnique
FROM CLIENT CL
INNER JOIN COMMANDE CM ON CM.idClient = CL.idClient
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
INNER JOIN PRODUIT P ON P.idProduit = LC.idProduit
WHERE CL.RaisonSociale = 'Lazzy' AND CM.DateCommande = '2015-11-11';


/*	R13
	Donner l'addresse, le départment et la ville
	du site qui doit recevoir la commande
	passée le 2012-09-23 par la société Buzzshare.
	Peut servir à des fins logistiques.
	
	Remarque: on utilise || pour concaténer des éléments. */
SELECT 	CL.RaisonSociale, CM.DateCommande, 
		SI.idSite, SI.NomSite, 
		V.NomVille, DEP.NomDepartement,
		SI.CodeDepartement||SI.CodeVille AS CodePostal
FROM CLIENT CL
INNER JOIN COMMANDE CM ON CM.idClient = CL.idClient
INNER JOIN SITE SI ON SI.idSite = CM.idSite
INNER JOIN DEPARTEMENT DEP ON DEP.CodeDepartement = SI.CodeDepartement
INNER JOIN VILLE V ON (V.CodeDepartement, V.CodeVille) = (DEP.CodeDepartement, SI.CodeVille)
WHERE CL.RaisonSociale = 'Buzzshare' AND CM.DateCommande = '2012-09-23';
	
	
/*	R14
	Lister les sites appartenant à la société Brainverse,
	ainsi que le département dans lesquels ils sont situés.
	Peut servir à des fins logistiques et commerciales. */
SELECT CL.RaisonSociale, SI.idSite, SI.NomSite, DEP.NomDepartement
FROM CLIENT CL
INNER JOIN SITE SI ON SI.idClient = CL.idClient
INNER JOIN DEPARTEMENT DEP ON DEP.CodeDepartement = SI.CodeDepartement
WHERE CL.RaisonSociale = 'Brainverse';	

	
/*	R15
	On veut connaître l'emplacement du "Backhoe"
	commandé le 2016-02-12 par la société Yakijo,
	dans un ou plusieurs entrepôt du même département.
	Peut servir à des fins logistiques. */
SELECT CL.RaisonSociale, CM.DateCommande,
		P.idProduit, P.Description,
		E.idEntrepot, DEP.NomDepartement,
		ST.NumBaie, ST.NumRangée
FROM CLIENT CL
INNER JOIN COMMANDE CM ON CM.idClient = CL.idClient
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
INNER JOIN PRODUIT P ON P.idProduit = LC.idProduit
INNER JOIN STOCK ST ON ST.idProduit = P.idProduit
INNER JOIN ENTREPOT E ON E.idEntrepot = ST.idEntrepot
INNER JOIN DEPARTEMENT DEP ON DEP.CodeDepartement = E.CodeDepartement
WHERE CL.RaisonSociale = 'Yakijo' 
		AND CM.DateCommande = '2016-02-12'
		AND DEP.CodeDepartement = CL.CodeDepartement;
	
	
/*	R16
	Donne le nombre de stocks restants pour le produit
	n°35 dans chaque département.
	Peut servir à des fins logistiques. */
SELECT DEP.CodeDepartement, DEP.NomDepartement, SUM(ST.QuantiteStock) AS StockDepartement
FROM PRODUIT P
INNER JOIN STOCK ST ON ST.idProduit = P.idProduit
INNER JOIN ENTREPOT E ON E.idEntrepot = ST.idEntrepot
INNER JOIN DEPARTEMENT DEP ON DEP.CodeDepartement = E.CodeDepartement
WHERE P.idProduit = 35
GROUP BY DEP.CodeDepartement, DEP.NomDepartement;


/*	R17
	Donne le nombre de commande passée par chaque
	client en 2022.
	Peut servir à des fins statistiques. */
SELECT CL.idClient, CL.RaisonSociale, COUNT(*) AS NbCommandes
FROM COMMANDE CM
INNER JOIN CLIENT CL ON CL.idClient = CM.idClient
WHERE CM.DateCommande >= '2022-01-01'
		AND CM.DateCommande < '2023-01-01'
GROUP BY CL.idClient, CL.RaisonSociale;


/*	R18
	Pour chaque département, donne le nombre de commandes passées,
	(on estime que le département est celui dans lequel est situé
	le siège de l'entreprise cliente)
	ainsi que le pourcentage sur toutes les commandes.
	Peut servir à des fins statistiques.
	
	Remarque: On utilise une sous-requête pour le nombre total de
	commandes pour compter "en dehors" du GROUP BY.
	On multiplie par 1.0 pour transformer en nombre flottant.*/
SELECT DEP.CodeDepartement, DEP.NomDepartement,
		COUNT(*) AS CommandesDepartement,
		COUNT(*)*1.0/(select count(*) from commande)*100 AS PartCommandes
FROM COMMANDE CM
INNER JOIN CLIENT CL ON CM.idClient = CL.idClient
INNER JOIN DEPARTEMENT DEP ON DEP.CodeDepartement = CL.CodeDepartement
GROUP BY DEP.CodeDepartement, DEP.NomDepartement;
	
	
/*	R19
	Donne le stock par entrepôt du produit n°20
	et le pourcentage sur le stock de tous les entrpôts.
	Peut servir à des fins statistiques.
	
	Remarque: On utilise une sous-requête pour le nombre total de
	stock pour compter "en dehors" du GROUP BY. */
SELECT P.idProduit, P.Description,
		E.idEntrepot, ST.QuantiteStock,
		(select sum(quantitestock) from stock where idproduit = 73) AS StockTT,
		ST.QuantiteStock*1.0/(select sum(quantitestock) from stock where idproduit = 73)*100 
		AS PourcentageStock
FROM PRODUIT P
INNER JOIN STOCK ST ON ST.idProduit = P.idProduit
INNER JOIN ENTREPOT E ON E.idEntrepot = ST.idEntrepot
WHERE (P.idProduit = 73);


/*	R20
	Donne le nombre de commandes passées chaque jour,
	par date croissante, et le nombre de produits commandés.
	Peut servir à des fins statistiques. */
SELECT CM.DateCommande, COUNT(*) AS NbCommandes,
		SUM(LC.NbProduit) AS NbProduitsCommandes
FROM COMMANDE CM
INNER JOIN LIGNECOMMANDE LC ON LC.idCommande = CM.idCommande
GROUP BY CM.DateCommande
ORDER BY CM.DateCommande ASC;
		