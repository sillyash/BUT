/// YOUR NAME HERE: MERIENNE Ash
/// YOUR GROUP NUMBER HERE:

#include <iostream>
#include <string>
#include <vector>
using namespace std;


void vecCout(vector<float> vec) {
    // Display content of vec
    for (int i = 0; i < vec.size(); i++) {
        cout << vec[i] << "  ";
    }
}

bool vecIsNull(vector<float> vec) {
    // Returns true if all the values in vec are null
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] != 0) {
            return false;
        }
    }
    return true;
}

float vecAvg(vector<float> vec) {
    // Returns average value of the elements in vec
    float avg = 0;
    for (int i = 0; i < vec.size(); i++) {
        avg += vec[i];
    }
    avg /= vec.size();
    return avg;
}

int vecMax(vector<float> vec) {
    // Returns the index of the max value in vec
    float max = vec[0];
    int iMax = 0;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] > max) {
            max = vec[i];
            iMax = i;
        }
    }
    return iMax;
}

int vecMin(vector<float> vec) {
    // Returns the index of the min value in vec
    float min = vec[0];
    int iMin = 0;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] < min) {
            min = vec[i];
            iMin = i;
        }
    }
    return iMin;
}

vector<float> vecNeg(vector<float> vec) {
    // Returns the vector containing only negative values from vec
    vector<float> vectorNeg;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] < 0) {
            vectorNeg.push_back(vec[i]);
        }
    }
    return vectorNeg;
}

vector<float> indNeg(vector<float> vec) {
    // Returns the vector containing only negative values index from vec
    vector<float> vectorNeg;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] < 0) {
            vectorNeg.push_back(i);
        }
    }
    return vectorNeg;
}

vector<float> vecPos(vector<float> vec) {
    // Returns the vector containing only positive values from vec
    vector<float> vectorPos;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] > 0) {
            vectorPos.push_back(vec[i]);
        }
    }
    return vectorPos;
}

vector<float> indPos(vector<float> vec) {
    // Returns the vector containing only positive values index from vec
    vector<float> vectorPos;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] > 0) {
            vectorPos.push_back(i);
        }
    }
    return vectorPos;
}

vector<float> vecNoNull(vector<float> vec) {
    // Returns the vector vec without its null values
    vector<float> vectorPos;
    for (int i = 0; i < vec.size(); i++) {
        if (vec[i] != 0) {
            vectorPos.push_back(vec[i]);
        }
    }
    return vectorPos;
}


int main() {
    // Declare variables and constants
    const double STOP = -999.9;
    vector<float> myVector;
    vector<float> vectorNeg;
    vector<float> vectorPos;
    int iMax, iMin;
    double num;

    // Asks for input until STOP, and puts it in myVector
    do {
        cout << "Enter a real number, -999.9 to stop." << endl;
        cin >> num;
        if (num != STOP) {
            myVector.push_back(num);

        }
    }
    while (num != STOP);

    // Display content
    cout << endl << "myVector:  "; vecCout(myVector);

    // Checks if the vector is not full of 0's or is empty
    if (vecIsNull(myVector)) {
        cout << endl << "Vector is full of zeros or empty. Cannot continue." << endl;
        return 0;
    }

    // Remove null values from myVector and display it
    myVector = vecNoNull(myVector);
    cout << endl << "Without null values:  "; vecCout(myVector);
    // Display average
    cout << endl << "Average:  " << vecAvg(myVector) << endl;

    // Store Max and Min index
    iMax = vecMax(myVector);
    iMin = vecMin(myVector);
    // Display max and min and their index
    cout << "Maximum:  " << myVector[iMax] << ", Index:  " << iMax << endl;
    cout << "Minimum:  " << myVector[iMin] << ", Index:  " << iMin << endl;

    // Create vectors of index of negatives and positives
    vectorNeg = indNeg(myVector);
    vectorPos = indPos(myVector);
    // Display vectors of index
    cout << "indNeg:  "; vecCout(vectorNeg);
    cout << endl << "indPos:  "; vecCout(vectorPos);
    cout << endl;

    // Display vectors of negatives and vector of positives
    // using the indexes stored in vectorNeg and vectorPos
    cout << "Negative values:  ";
    for (int i = 0; i < vectorNeg.size(); i++) {
        cout << myVector[vectorNeg[i]] << "  ";
    }
    cout << endl << "Positive values:  ";
    for (int i = 0; i < vectorPos.size(); i++) {
        cout << myVector[vectorPos[i]] << "  ";
    }
    cout << endl;
}

/*
TESTS
>>> 56 2 0 -9 -45.2 -999.9
Expected response:
myVector:  56  2  0  -9  -45.2
Without null values:  56  2  -9  -45.2
Average:  0.95
Maximum:  56, Index:  0
Minimum:  -45.2, Index:  3
indNeg:  2  3
indPos:  0  1
Negative values:  -9  -45.2
Positive values:  56  2

>>> 0 0 0 -999.9
Expected response:
myVector:  0 0 0
Vector is full of zeros or empty. Cannot continue.

>>> 1 -999.9
Expected response
myVector:  1
Without null values:  1
Average:  1
Maximum:  1, Index:  0
Minimum:  1, Index:  0
indNeg:
indPos:  0
Negative values:
Positive values:  1

>>> -999.9
Expected response:
myVector:
Vector is full of zeros or empty. Cannot continue.
*/

