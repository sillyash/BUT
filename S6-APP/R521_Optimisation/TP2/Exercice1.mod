/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 16 mars 2026 at 14:18:04
 *********************************************/

// Variables de decision
dvar boolean entrepot_a ;
dvar boolean entrepot_r ;
dvar boolean entrepot_t ;

dvar boolean usine_a ;
dvar boolean usine_r ;
dvar boolean usine_t ;

// Données
int ret_usine_r = 35 ;
int ret_usine_t = 30 ;
int ret_usine_a = 28 ;

int cout_usine_r = 8 ;
int cout_usine_t = 5 ;
int cout_usine_a = 5 ;

int ret_entrepot_r = 10 ;
int ret_entrepot_t = 12 ;
int ret_entrepot_a = 6 ;

int cout_entrepot_r = 2 ;
int cout_entrepot_t = 3 ;
int cout_entrepot_a = 2 ;

// Objectif
maximize ret_entrepot_a * entrepot_a
		+ ret_entrepot_r * entrepot_r
		+ ret_entrepot_t * entrepot_t
		+ ret_usine_a * usine_a
		+ ret_usine_r * usine_r
		+ ret_usine_t * usine_t ;

// Contraintes
subject to {
	cout_entrepot_a * entrepot_a
	+ cout_entrepot_r * entrepot_r
	+ cout_entrepot_t * entrepot_t
	+ cout_usine_a * usine_a
	+ cout_usine_r * usine_r
	+ cout_usine_t * usine_t <= 15 ;
	
	entrepot_a <= usine_a ;
	usine_a + usine_r + usine_t == 1 ;
	entrepot_a + entrepot_r + entrepot_t <= 2 ;
}
