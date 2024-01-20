#include <iostream>

using namespace std;


int main() {
    int nb;
    int nbL;
    char myChar;
    // User input
    cout << "Enter your character." << endl;
    cin >> myChar;

    // User input must be positive and odd
    cout << "Enter the number of stars. (positive and odd integer only)." << endl;
    cin >> nb;
    while (nb <= 0 || nb%2 == 0) {
        cout << "Error, your number must be strictly positive and odd. Try again:" << endl;
        cin >> nb;
    }
    // Print myChar as a triangle of base nb
    int i=1;
    int spaces;
    while (i<=nb) {
        spaces = nb-i/2;
        for (int x=0; x<spaces; x++) {
            cout << " ";
        }
        for (int x=0; x<i; x++) {
            cout << myChar;
        }
        cout << endl;
        i += 2;
    }
    return 0;
}

/*
TESTS
 a)
12
************

b)
0
Error, your number must be strictly positive. Try again:
12
************

c)
Enter the number of stars. (positive integer only).
12
Enter the number of lines. (positive integer only).
4
************
************
************
************

d)
Enter your character.
o
Enter the number of stars. (positive integer only).
10
Enter the number of lines. (positive integer only).
5
oooooooooo
oooooooooo
oooooooooo
oooooooooo
oooooooooo

e)
Enter your character.
o
Enter the number of stars. (positive and odd integer only).
5
     o
    ooo
   ooooo

*/
