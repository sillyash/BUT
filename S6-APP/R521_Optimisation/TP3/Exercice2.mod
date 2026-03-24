/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 17 mars 2026 at 13:50:22
 *********************************************/

// Nombre d'intervalles horaires (4h)
int I = 6 ;

// Variables de décision
dvar int+ P[1..I] ; // Affectation du personnel par intervalle horaire


// Objectif
minimize sum(i in 1..I) P[i] ;


// Données
int B[1..I] = [70, 80, 20, 60, 40, 30] ; // Besoin en effectifs

// Contraintes
subject to {
	// On répond au besoin en personnel
	P[I] + P[1] >= B[1] ;						// Premier & dernier intervalle
	forall(i in 2..I) P[i-1] + P[i] >= B[i] ; 	// Reste des intervalles
}
