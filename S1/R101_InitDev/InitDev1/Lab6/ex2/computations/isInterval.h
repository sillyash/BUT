/// HBM, JL, CTB sept. 2021

#ifndef ISINTERVAL_H_
#define ISINTERVAL_H_

const int POS = 5;
const int ZERO = 0;
const int NEG = -5;

/// function tests() displays the intervals of POS, NEG and ZERO
/// parameters : None
/// returns nothing
void tests();

/// function interval(int) returns an interval
/// parameters : (G) n : int
/// returns 'positive' if n is positive, 'negative' if n is negative, else returns 'null'
std::string interval(int n);

#endif

