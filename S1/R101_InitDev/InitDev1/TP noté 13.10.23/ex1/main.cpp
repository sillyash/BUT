#include <iostream>
using namespace std;

const int SUP = 10;
const int MAXTRIES = 4;

int main() {
    int nb, mult, nbTries = 0;
    bool isMult, triesOK = true;

    // User inputs the number he wants to train on
    cout << "Give me a positive number smaller than 10:  ";
    cin >> nb;
    cout << endl;

    // Input loop if nb isn't a positive number between 1 and 10
    while ((nb >= SUP) or (nb <= 0)) {
        cout << "Error! I want a positive number smaller than 10:  ";
        cin >> nb;
        cout << endl;
    }

    // Input for a multiple of nb
    cout << "Good. Now give me a multiple of " << nb << ":  ";
    cin >> mult;
    cout << endl;

    // Add a try to nbTries
    nbTries += 1;

    // Check if mult is a multiple of nb
    isMult = (mult % nb == 0) and (nb != 0);

    // Input loop if mult is not a multiple of nb
    while ((not isMult) and (triesOK)) {
        cout << mult << " is not a multiple of " << nb << ". Try again:  ";
        cin >> mult;
        cout << endl;
        isMult = (mult % nb == 0) and (nb != 0);

        // Add a try each time
        nbTries += 1;

        // Check the number of tries
        triesOK = nbTries <= MAXTRIES;
    }

    // If we exit the loop because the child found a multiple
    if (triesOK) {
        cout << "Great! You've found a multiple of " << nb << " in "
        << nbTries << " tries." << endl;
    }
    // If we exit the loop because the child used all of their tries
    else {
        cout << "Too bad, " << mult << " is still not a multiple of " << nb
        << ". Come back when you want to play again!" << endl;
    }

}

/*
TESTS

----FIRST INPUT-----
>>> 0
expected result: Error! I want a positive number smaller than 10:
result: same as expected

>>> 10
expected result: Error! I want a positive number smaller than 10:
result: same as expected

>>> -1
expected result: Error! I want a positive number smaller than 10:
result: same as expected


----SECOND INPUT----
>>> 9 0
expected result: 0 is not a multiple of 9. Try again:
result: 0 is a multiple of 9
-> HAS BEEN FIXED

>>> 1 2
expected result: Great! You've found a multiple of 1 in 1 tries.
result: same as expected


----ENTIRE PROGRAM-----
>>> 9 12 10 5 18
expected result: Great! You've found a multiple of 9 in 4 tries.
result: same as expected

>>> 9 12 10 8 7 5
expected result: Too bad, 5 is still not a multiple of 9.
                 Come back when you want to play again!
result: same as expected
*/
