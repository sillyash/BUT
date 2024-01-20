#include <iostream>
#include <string>
#include <vector>
#include "vectors.h"
using namespace std;

int main( )
{
    vector<string> team = {"DURAND","BACHA","RENARD","GEYORO","DIANI","SOMMER"};

    display(team);


    ///-----TESTS-----
    string s;
    int p;
    // normalization
    s = "abcdef";
    cout << "Test of normalization" << endl << "General case str = " << s << endl;
    normalization(s);
    if (s == "ABCDEF") {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: s = " << s << " (expected: ABCDEF)" << endl << endl;}

    s = "AbCdeF";
    cout << "Test of normalization" << endl << "Special case str = " << s << endl;
    normalization(s);
    if (s == "ABCDEF") {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: s = " << s << " (expected: ABCDEF)" << endl << endl;}

    // search
    cout << "Test of search" << endl << "General case str = RENARD" << endl;
    p = search(team, "RENARD");
    if (p == 2) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: p = " << p << " (expected: {1,2})" << endl << endl;}

    cout << "Test of search" << endl << "Error case str = ZRFZGZ" << endl;
    p = search(team, "ZRFZGZ");
    if (p == -1) {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: p = " << p << " (expected: 0)" << endl << endl;}

    // replace
    cout << "Test of replace" << endl << "General case str = RENARD, newstr = VIPERE" << endl;
    replace(team, "RENARD", "VIPERE");
    if (team[2] == "VIPERE") {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: team[2] = " << team[2] << " (expected: VIPERE)" << endl << endl;}

    cout << "Test of replace" << endl << "Error case str = ZRFZGZ, newstr = LALALA" << endl;
    replace(team, "ZRFZGZ", "LALALA");
    if (team[2] == "VIPERE") {cout << "Test passed!" << endl << endl;}
    else {cout << "Test failed: team[2] = " << team[2] << " (expected: VIPERE)" << endl << endl;}
    ///-----END OF TESTS-----

    return 0;
}
