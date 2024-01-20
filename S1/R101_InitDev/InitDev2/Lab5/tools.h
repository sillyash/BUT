/// Lab5 Sorting and complexity
/// 2022-2023
/// tools.h file

#ifndef TOOLS_H_INCLUDED
#define TOOLS_H_INCLUDED

#include <vector>

using namespace std;

/// the four possible sorting algorithms
/// an Algorithm is an int that has only 4 possible values
enum Algorithm {SELECTION, BUBBLE, BUBBLE_OPT, QUICK};

/// the three possible values for the order of the values
/// an Order is an int which has only 3 possible values
enum Order {RANDOM, ASCENDING, DECREASING};

/// return a positive integer entered by the user.
int inputInteger();

/// return the initial order of the vector chosen by the user
Order inputOrder();

/// return the sorting algorithm chosen by the user
Algorithm inputAlgorithm();

/// Input the various parameters needed to sort :
/// number of values, maximum value, number of executions, initial order, sorting algorithm.
void sortParameters(int & nbElts, int & maxVal, int & nbExec, Order & myOrder, Algorithm & myAlgorithm);


/// Create a vector containing nbVal random integers ranging between theMin and theMax
void randomVector(vector<int> &v, int nbVal, int theMin, int theMax);

/// Ask the user if she wants to start over
bool again();

#endif // TOOLS_H_INCLUDED
