#include <iostream>
using namespace std;



int main() {
    // Create and assign values to the variables
    double num1;
    double num2;
    cout << "Enter 2 real numbers." << endl;
    cin >> num1;
    cin >> num2;
    // Compare variables and output
    if (num1 <= num2) {
        cout << "Increasing order." << endl;
    }else {
        cout << "Decreasing order." << endl;
    }
    return 0;
}


/*
TESTS

1, 5 >>>Increasing order.
5, 1 >>> Decreasing order.
5, 5 >>>Increasing order.
-2.21, -1.1254 >>>Increasing order.
-1.12, -5.145 >>>Decreasing order.
*/
