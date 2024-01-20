/// Lab6, menu.cpp
/// HBM, JL, CTB sept. 2023

#include <fstream>
#include <iostream>
#include "menu.h"

using namespace std;


/// returns true if choice is valid, else false
bool validChoice(int choice)
{
    return (choice >= FACT && choice <= QUIT);
}

/// displays menu, inputs choice, checks it, and returns it
int menu()
{
    int	choice;

    cout << "\nEnter " << endl;
    cout << FACT << " to compute the factorial " << endl;
    cout << SUM << " to compute the sum  " << endl;
    cout << DIV << " to display divisors " << endl;
    cout << QUIT << " to quit the program" << endl;
    cout << ">>> ";
    cin >> choice;

    while (!validChoice(choice))
    {
        cout << endl << choice <<
         " is not a valid option, please enter again:"
         << endl << ">>> ";
        cin >> choice;
    }
    return choice;
}

