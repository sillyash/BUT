/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 17 mars 2026 at 14:14:43
 *********************************************/

int T = 12 ; // Nombre de jours

// Variables de decision
dvar boolean four[1..T] ; // Allumage du four au jour J
dvar int+ s[1..T] ; // Stock le jour j
dvar int+ p[1..T] ; // Production le jour j

// Données
int f = 1500 ; 
int h = 5 ;
int d[1..T] = [250 , 200 , 170 , 470 , 180 , 450 , 490 , 200 , 170 , 160 , 480 , 100];
int s0 = 50 ;
int cap = 600 ;

// Objectif
minimize sum(j in 1..T) (four[j]*f + s[j]*h) ;

// Contraintes
subject to {
	forall(j in 1..T) p[j] <= cap ;
	forall(j in 1..T) p[j] <= four[j] * cap ; // Pas de four -> pas de production
	
	// Stock
	forall(j in 2..T) s[j] == (p[j] + s[j-1]) - d[j] ;
	s[1] == s0 + p[1] - d[1] ;
}
