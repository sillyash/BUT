/*********************************************
 * OPL 22.1.0.0 Model
 * Author: Celine
 * Creation Date: 12 sept. 2023 at 15:12:50
 *********************************************/
 
 // variables de decision
 // float+ = indique que les variables doivent prendre des valeurs reelles positives. 
dvar float+ x1 ;
dvar float+ x2 ; 

// fonction objectif
minimize 400*x1+600*x2 ;

// contraintes
subject to {
110*x1+220*x2 >=4400 ;
100*x1+100*x2 >=2600 ;
400*x1 <=9600 ;
600*x2<=9600  ;
}
