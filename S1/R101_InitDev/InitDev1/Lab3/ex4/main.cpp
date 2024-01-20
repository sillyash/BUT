#include <iostream>

using namespace std;

/*
int a(max) {
    cout << "The integers divisible by 7 that are smaller than max are:" << endl;
    for (int i=1; i<max; i++) {
        if (i%7==0) {
            cout << i << endl;
        }
    }
    return 0;
}

int b(max, min) {
    cout << "The integers divisible by 7 that are smaller than max and greater than min are:" << endl;
    for (int i=min; i<max; i++) {
        if (i%7==0) {
            cout << i << endl;
        }
    }
    return 0;
}

int c(max, min) {
    int x=0;
    cout << "The integers divisible by 7 that are smaller than max are:" << endl;
    for (int i=min; i<max; i++) {
        if (i%7==0) {
            cout << i << endl;
            x += 1;
            if (x = 10) {
                cout << endl;
                x = 0;
            }
        }
    }
    return 0;
}
*/

int main() {
    int max;
    int min;
    int x=0;
    cout << "Enter the max (positive number)." << endl;
    cin >> max;
    cout << "Enter the min (positive number)." << endl;
    cin >> min;
    cout << "The integers divisible by 7 that are smaller than max and greater than min are:" << endl;
    for (int i=min; i<max; i++) {
        if (i%7==0) {
            cout << i << " ";
            x += 1;
            if (x >= 10) {
                cout << endl;
                x = 0;
            }
        }
    }
    return 0;
}
