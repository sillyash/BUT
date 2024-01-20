#include <iostream>
using namespace std;
int main() {
    float grade, sumGrades=0, nbGrades=0, average;
    char rep;

    do {
        // User input
        cout << "Input a grade: " << endl;
        cin >> grade;

        // Check if the grade is between 0 and 20
        while (grade < 0 || grade > 20) {
            cout << "Error: grade should range between 0 and 20." << endl;
            cout << "Input again: " << endl;
            cin >> grade;
        }
        // Update sumGrades and nbGrades
        sumGrades = sumGrades + grade;
        nbGrades = nbGrades + 1;

        cout << "More grades? (answer with y or n)" << endl;
        cin >> rep;
    }
    while (rep == 'y');

    // Calculate average and display it
    average = sumGrades / nbGrades;
    cout << "The average is " << average << "." << endl;

    return 0;
}
