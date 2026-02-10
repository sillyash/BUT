/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 10 f�vr. 2026 at 15:10:32
 *********************************************/

// Paramètres d'entrée
float eta = 60 ;
float C = 10 ;
int D = 28 ; 
int H = 4 ;

/* On stocke les informations sur la quantité d'electricite fournie
 * par les éoliennes dans un tableau à 4 entrées.
 *
 * Utiliser E[1], ... E[h] pour accéder à la quantité pour
 * l'heure 1,... pour l'heure h.
 */
int E[1..H] = [400, 200, 0, 800] ;

/* On stocke les informations sur le prix d'achat de
 * l'électricité auprès du réseau national dans un
 * tableau à 4 entrées.
 */
float P[1..H] = [0.1, 0.15, 0.2, 0.3] ;

// Variables de décisions
dvar float+ Q[1..H] ;
dvar float+ A[1..H] ;

// Fonction objectif
minimize sum(h in 1..H) P[h] * A[h];

// Contraintes
subject to {
  sum(h in 1..H) Q[h] == D ;
  forall(h in 1..H) Q[h] <= C ;
  forall(h in 1..H) A[h] >= eta * Q[h] - E[h] ;
}
 