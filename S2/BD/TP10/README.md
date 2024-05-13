# R206 - BD

## TP8 - Curseurs, fonctions et procédures stockées

### Ash MERIENNE - 2C

<br>

#### Remarque : Avant toute chose, on active l'output de serveur.

	SET SERVEROUTPUT ON;

## Exercice 1. On souhaite regrouper une série de données statistiques relatives à la base des films.

### Créer un paquetage ```statistics``` qui rassemble :

	CREATE OR REPLACE PACKAGE statistics AS
	    FUNCTION nbHorsFrance RETURN NUMBER;
	    PROCEDURE basseRes;
	END statistics;
	/

- a. Une fonction ```nbHorsFrance``` donnant le nombre de films jamais sortis en France
- b. Une procédure ```basseRes``` affichant le pourcentage de films pour lesquels il n’y a pas de version à haute résolution (4K ou 8K).
<!-- end of the list -->

	Code

### Exécuter cette fonction et cette procédure.

<br>

## Exercice 2. On veut réunir dans un paquetage toutes les fonctions et procédures concernant une famille de personnes portant le même nom

- a. Écrire le paquetage famille avec les spécifications des fonctions et procédures suivantes :
  - Une fonction donnant le nombre de personnes portant un nom donné en paramètre
  - Une procédure écrivant les prénoms et noms des personnes portant un nom donné en paramètre
  - Une fonction donnant le nombre de films réalisés (globalement) par les personnes portant un nom donné en paramètre
  - Une fonction donnant le nombre de films dans lesquels ont joué (globalement) les personnes portant un nom donné en paramètre
<!-- end of the list -->

	Code

- b. Ecrire un bloc PL/SQL qui appelle toutes ces fonctions et procédures avec pour chacune le nom « Jackson » passé en paramètre
<!-- end of the list -->

	Code

- c. Rendre le bloc PL/SQL du point ```b)``` ci-dessus une procédure supplémentaire du paquetage famille et le tester.
<!-- end of the list -->

	Code

- d. Faire en sorte que la procédure ci-dessus affiche, en plus, la liste des films dans lesquels chaque personne a joué.
<!-- end of the list -->

  Code



