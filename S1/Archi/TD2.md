# R103 · Archi
## [TD2](https://cours.iut-orsay.fr/pluginfile.php/80413/mod_resource/content/4/TD2-M%C3%A9moires-Etudiants.pdf) · 06/12/23
#### Ash Merienne, TP 1B

### Exercice 1
1. Un mot mémoire est de taille 4 bits (1 mot = 1 case), ie  0.5 octets.
<br>
2. Capacité totale:
    - 4 bits d'@
    - 2<sup>4</sup> @ différents
    - 2<sup>4</sup> cases
    - 2<sup>4</sup> mots
    - 16 mots = 64 bits = 8 octets.
<br>
3. WE, D<sub>3</sub>, D<sub>2</sub>, D<sub>1</sub> et D<sub>0</sub> seraient supprimées.
<br>
4. On regarde les valeurs de WE et CS.
   - Impulsion a: Attente 
   - Impulsion b: Ecriture
   - Impulsion c: Lecture
<br>
5. On regarde les valeurs de A<sub>3</sub>A<sub>2</sub>A<sub>1</sub>A<sub>0</sub> et D<sub>3</sub>D<sub>2</sub>D<sub>1</sub>D<sub>0</sub>.
	- Impulsion b:
    	- (0000)<sub>2</sub> = (0)<sub>10</sub>
    	- (1011)<sub>2</sub> = (11)<sub>10</sub>
  	- Impulsion c:
		- (1111)<sub>2</sub> = (15)<sub>10</sub>
		- (0111)<sub>2</sub> = (7)<sub>10</sub>
  	- Impulsion d:
    	- (0000)<sub>2</sub> = (0)<sub>10</sub>
    	- (0111)<sub>2</sub> = (7)<sub>10</sub>
  	- Impulsion e:
    	- (1101)<sub>2</sub> = (13)<sub>10</sub>
    	- (1001)<sub>2</sub> = (9)<sub>10</sub>
<br>
6. Un inverseur est un composant électrique qui permet d'inverser un bit.

