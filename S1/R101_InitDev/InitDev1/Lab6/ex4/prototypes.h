#ifndef PROTOTYPES_H_
#define PROTOTYPES_H_

/// Procedure afficheSpecial(int, int)
/// Displays x times the value of y}
/// Parameters (G) x, y : integers
void afficheSpecial(x, y);

/// Function estDiviseur(int,int) returns boolean
/// Return True if x is a divider of y, else False
/// Parameters (G) x, y: integers
bool estDiviseur(x,y);


/// Function encore() returns boolean
/// Return True if the user wants to reiterate the current process, else False
/// Parameters None
bool encore();

/// Function sommeDesDiviseurs(int, int) returns integer
/// Return the sum of all the dividers of nb
/// Parameters (G) nb: integer
///            (R) somme : integer
void sommeDesDiviseurs(nb, somme);

/// Function nbOccurences(vector<int>,int) returns int
/// Return the number of occurences of a given integer in a vector of integers
/// Parameters (G) tab: vector<int>
///            (G) val: integer
int nbOccurrences(tab, val);

/// Function doubler(vector<vector<int> >) returns vector<vector<int> >
/// Returns the 2D vector with all of it's values doubled
/// Parameters (G/R) tab2D: vector<vector<int> >
void doubler(tab2D);

/// Function tousEgaux(vector<vector<int> >) returns boolean
/// Return True if all values from the 2D vector are identical, else False. We suppose that the vector isn't empty.
/// Parameters (G) tab2D: vector<vector<int> > (not empty)
bool tousEgaux (tab2D);

#endif
