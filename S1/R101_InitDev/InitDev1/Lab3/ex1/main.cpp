#include <iostream>

using namespace std;


int main() {
    int n;
    int numdiv;
    // User inputs
    cout << "Enter a positive integer." << endl;
    cin >> n;

    // For i between 1 and n-1
    for (int i=2; i<n; i++) {
        // Check if it's a divisor
        if (n%i == 0) {
            numdiv++;
            cout << i << endl;
        }
    }
    // Display number of divisors
    cout << n << " has " << numdiv << " divisors." << endl;
    return 0;
}

/*
TESTS

>>12
2
3
4
6
12 has 4 divisors.

>>456
2
3
4
6
8
12
19
24
38
57
76
114
152
228
456 has 14 divisors.
*/
