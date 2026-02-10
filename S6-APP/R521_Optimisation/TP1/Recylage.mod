/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 10 f�vr. 2026 at 14:34:32
 *********************************************/

// Variables de decision
dvar float+ floc_i ;
dvar float+ floc_c ;
dvar float+ resine_i ;
dvar float+ resine_c ;

// Objectif
maximize 150*floc_i + 80*floc_c + 300*(resine_i + resine_c) ;

// Variables initiales
float floc_i_init = 100 ;
float floc_c_init = 60 ;

// Contraintes
subject to {
  resine_i + resine_c <= 45 ;
  resine_i <= (floc_i_init)*0.9 ;
  resine_c <= (floc_c_init)*0.9 ;
  floc_i <= floc_i_init - resine_i/0.9 ;
  floc_c <= floc_c_init - resine_c/0.9 ;
}
