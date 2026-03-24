/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 17 mars 2026 at 13:23:27
 *********************************************/

// variables de décision
dvar float+ x[1..5];

// fonction objectif
minimize 2*x[1] + 2*x[2] + 3*x[3] + 3*x[4] + 2*x[5] ;

// contraintes
subject to {
  x[1] + 1.2*x[2] - 2*x[3] + 1.5*x[4] + 2*x[5] >= 30 ;
  -x[2] + 2*x[3] + x[5] >= 25 ;
  2*x[1] + 1.5*x[2] + 3*x[3] + 4*x[4] >= 12.5 ;

  /**
   * ajouter ici les contraintes nécessaires à la construction
   * de l'arbre de recherche de l'algorithme du branch and bound
   */
  
  x[4] == 0 ;
  x[3] == 3 ;
}
