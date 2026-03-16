/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 16 mars 2026 at 14:59:20
 *********************************************/
 
 dvar float+ x1 ;
 dvar float+ x2 ;
 dvar float+ x3 ;
 dvar float+ x4 ;
 
 maximize 18*x1 + 10*x2 + 12*x3 + 8*x4 ;
 
 subject to {
   6*x1 + 3*x2 + 5*x3 + 2*x4 <= 10 ; 
   x3 + x4 <= 1 ;
   -x1 + x3 <= 0 ;
   -x2 + x4 <= 0 ; 
   x1 <= 1 ; 
   x2 <= 1 ; 
   x3 <= 1 ; 
   x4 <= 1 ; 
    
   //*** ajouter ici les contraintes nécessaires à la construction
   //    de l'arbre de recherche de l'algorithme du branch and bound ***/
  
   x1 == 1 ;
   x2 == 1 ;
   x4 == 0 ;
   x3 == 0 ;
}
