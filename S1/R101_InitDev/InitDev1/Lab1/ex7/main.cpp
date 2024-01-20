#include <iostream>
using namespace std;

int main() {
    int temp;
    float avg;
    // Enter variables
    cout << "Enter 3 temperatures you recorded during the day." << endl;
    cin >> temp;
    avg += temp;
    cin >> temp;
    avg += temp;
    cin >> temp;
    avg += temp;

    // Calculate and display average temp
    avg /= 3;
    cout << "The average temperature is " << avg << "." << endl;
    return 0;
}

/*
TESTS

20
20
20
The average temperature is 20.

26
20
23
The average temperature is 23.

20
0
0
The average temperature is 6.66667.

0
0
0
The average temperature is 0.

-20
0
10
The average temperature is -3.33333.
*/
