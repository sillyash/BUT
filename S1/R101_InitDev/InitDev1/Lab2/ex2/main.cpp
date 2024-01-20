#include <iostream>
using namespace std;


int main() {
    int temp;
    cout << "Input a temperature (integer only)." << endl;
    cin >> temp;
    if (temp<15) {
        cout << "It's too cold!" << endl;
    }else if (temp>25) {
            cout << "It's too hot!" << endl;
    }else {
        cout << "This is good!" << endl;
    }
    return 0;
}

/*
TEST
12 >>> It's too cold!
23 >>> This is good!
35 >>> It's too hot!
-3 >>> It's too cold!
*/
