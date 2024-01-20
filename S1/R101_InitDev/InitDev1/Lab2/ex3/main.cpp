#include <iostream>
using namespace std;

int findmax(double x,double y,double z) {
    double max;
    max = x;
    if (y > x) {
        max = y;
        if (z > y) {
            max = z;
        }
    }else if (z > x) {
        max = z;
    }else {
        max = x;
    }
    return max;
}

int findmin(double x,double y,double z) {
    double min;
    min = x;
    if (y < x) {
        min = y;
        if (z < y) {
            min = z;
        }
    }else if (z < x) {
        min = z;
    }else {
        min = x;
    }
    return min;
}


int main() {
    // Create and assign values to variables
    double min, max;
    double x, y, z;
    cout << "Input 3 numbers." << endl;
    cin >> x;
    cin >> y;
    cin >> z;
    // Find max & min and display results
    max = findmax(x,y,z);
    min = findmin(x,y,z);
    cout << "The maximum is " << max << "." << endl;
    cout << "The minimum is " << min << "." << endl;
    return 0;
}

/*
TESTS

-2, 0, 3 >>> max is 3, min is -2
3, 3, 3 >>> max is 3, min is 3
9, 0, 2 >>> max is 9, min is 2
3, 10, 0 >>> max is 10, min is 0
*/
