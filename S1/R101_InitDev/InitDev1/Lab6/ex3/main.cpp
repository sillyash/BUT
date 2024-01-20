#include <iostream>
#include <vector>
#include "menu.h"
#include "computations.h"

using namespace std;

int main() {
    int n, choice;
    vector<int> divisors;

    /// TESTS
    // sumInt
    cout << "Test of sumInt" << endl << "General case (n>1)" << endl;
    if (sumInt(5) == 15) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: sumInt(5) = " << sumInt(5) << " (expected: 15)" << endl << endl;}

    cout << "Test of sumInt" << endl << "Particular case (n=1)" << endl;
    if (sumInt(1) == 1) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: sumInt(1) = " << sumInt(1) << " (expected: 1)" << endl << endl;}

    // factorial
    cout << "Test of factorial" << endl << "General case (n>1)" << endl;
    if (factorial(5) == 120) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: factorial(5) = " << factorial(5) << " (expected: 120)" << endl << endl;}

    cout << "Test of factorial" << endl << "Particular case (n=1)" << endl;
    if (factorial(1) == 1) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: factorial(1) = " << factorial(1) << " (expected: 1)" << endl << endl;}
    /// END OF TESTS


    // User input loop
    do {
        cout << "Enter a positive integer: ";
        cin >> n;
    }
    // Check input using isPositive()
    while(not isPositive(n));


    // User input using menu()
    do {
        choice = menu();

        // Execute a subprogram depending on the answer, or quit.
        switch(choice) {
            // Compute and display the factorial using factorial()
            case FACT:
                cout << endl << "The factorial of n is "
                << factorial(n) << endl << endl;
                break;
            // Compute and display the sum using sumInt()
            case SUM:
                cout << endl << "The sum of consecutive integers from 1 to n is "
                << sumInt(n) << endl << endl;
                break;
            // Compute and display the list of divisors using listDiv()
            case DIV:
                cout << endl << "The list of divisors of n is: ";
                // listDiv prints the output itself: it's a procedure
                listDiv(n);
                cout << endl << endl;
                break;
            // Quits the program
            case QUIT:
                return 0;
        }
    }
    // Infinite loop to allow user to reiterate the
    // process, as "case QUIT" allows him to quit.
    while(1);
}

/*
MANUAL TESTS

-----FIRST INPUT (n)-----
error case: 0, result: loops and ask a second time

error case: -2, result: loops and ask a second time

general case: 5, result: validates the input


-----MENU-----
general case: 1, result: displays the factorial of n

general case: 2, result: displays the sum of consecutive integers from 1 to n

general case: 3, result: displays the list of dividers of n

general case: 4, result: immediately exits the program

error case: 0, result: loops and ask a second time

error case: 5, result: loops and ask a second time

error case: -2, result: loops and ask a second time


-----INPUT + MENU-----
general case: 1 1 2 3 4, result: n=1, factorial=1, sum=1, list of dividers=1, then quits

general case: 5 1 2 3 4, result: n=5, factorial=120, sum=15, list of dividers=[1,  5], then quits

general case: 3 4, result: n=3, quits


-----listDiv(n)-----
general case: 12, result: [1,  2,  3,  4,  6,  12]

particular case: 1, result: [1]

*/
