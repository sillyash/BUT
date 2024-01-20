#include <iostream>
#include<vector>
using namespace std;


void vecCout(vector<int> vec) {
    /// Q1
    // Displays the content of vec
    for (int i = 0; i < vec.size(); i++) {
        if (i==vec.size()-1) {
            cout << vec[i];
        }
        else {
            cout << vec[i] << ", ";
        }
    }
}

vector<int> maxVec(vector<int> vec) {
    /// Q3
    // Returns the maximum value of a vector and the number of times it appears
    int max = vec[0];
    int maxNb = 1;

    // For each value in vec
    for (int i = 1; i < vec.size(); i++) {
        // If the value is bigger than max
        if (vec[i] > max) {
            // max is updated
            max = vec[i];
            // the value appears one time
            maxNb = 1;
        }
        else {
            // if the value is equal to max
            if (vec[i] == max) {
                // the value appears one more time
                maxNb++;
            }
        }
    }
    return {max, maxNb};
}


int main() {
    vector<int> v1 = {-2, 3, 7, 1, 2, 3, 7, 3, -2, 7, 0};
    vector<int> v2, maxV1, maxV2;
    int val, n;

    /// Q1
    cout << "V1:  ";
    vecCout(v1);
    cout << endl;

    /// Q2
    // Start at last index of v1, go to 0
    for (int j=v1.size()-1; j >= 0; j--) {
        v2.push_back(v1[j]);
    }
    cout << "V2:  ";
    vecCout(v2);
    cout << endl << endl;

    /// Q3
    maxV1 = maxVec(v1);
    cout << "The largest value is " << maxV1[0] << " and it appears "
    << maxV1[1] << " times." << endl << endl;

    /// Q4
    // Input loop
    cout << "Enter a value and a positive number:  ";
    cin >> val;
    cin >> n;
    int valNb = 0, i = 0;
    bool loopOK = true;

    while(loopOK) {
        // if the value is equal to val
        if (v1[i] == val) {
            // add 1 to valNb
            valNb++;
        }
        i++;
        // Check if we found val n times and if we are still in the vector
        loopOK = (valNb < n) and (i < v1.size());
    }
    // If we exited the loop because we reached the end of the vector
    if (valNb < n) {
        cout << "The value " << val << " doesn't appear at least " << n << " times." << endl;
    }
    // If we exited the loop because we found val n times
    else {
        cout << "The value " << val << " appears at least " << n << " times." << endl;
    }
}

/*
TESTS

>>> 7 2
expected result: The value 7 appears at least 2 times.
result: same as expected

>>> 7 9
expected result: The value 7 doesn't appear at least 9 times.
result: same as expected

>>> 7 0
expected result: The value 7 appears at least 0 times.
result: same as expected

*/

