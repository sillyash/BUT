/*********************************************
 * OPL 22.1.1.0 Model
 * Author: amerie1
 * Creation Date: 16 mars 2026 at 15:30:09
 *********************************************/

// nombre d'étudiant.e.s
int E = 6 ; 

// nombre de sujet
int S = 3 ;
 
// tableau décrivant préférences de chaque étudiant.e pour chaque sujet
int P[1..E][1..S] = [[  3,	2,	1],
				      [ 3,	1,	2], 
		              [ 2,	3,	1],
				      [ 1,	3,	2],
				      [ 2,	3,	1], 
		              [ 1,	3,	2]]; 

dvar boolean affect[1..E][1..S] ;

maximize sum(e in 1..E, s in 1..S) P[e][s] * affect[e][s] ;

subject to {
	forall(s in 1..S) sum(e in 1..E) affect[e][s] == 2 ; // 2 étudiant.e.s par projet 
	forall(e in 1..E) sum(s in 1..S) affect[e][s] == 1 ; // 1 projet par étudiant.e.s
}