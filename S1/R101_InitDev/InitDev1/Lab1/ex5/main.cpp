#include <iostream>
using namespace std;

int main() {
    double var1;
    double var2;
    cout << "Enter the value of the first variable." << endl;
    cin >> var1;
    cout << "Enter the value of the second variable." << endl;
    cin >> var2;

    // display the variables
    cout << var1 << " " << var2 << endl;

    // swap the variables
    double var3 = var1;
    var1 = var2;
    var2 = var3;
    // display again
    cout << var1 << " " << var2 << endl;
}
