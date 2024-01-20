/// HBM, JL, CTB sept. 2021

#ifndef MENU_H_
#define MENU_H_

const int FACT = 1;
const int SUM = 2;
const int DIV = 3;
const int QUIT = 4;

/// function menu() returns int
/// paramaters : none
/// displays menu, inputs choice, checks it, and returns it
int menu();

/// function validChoice(choice) returns boolean
/// parameters : (G) choice : int  {G for given, =(D) in French}
/// returns true if choice is valid, else false
bool validChoice(int choix);

#endif
