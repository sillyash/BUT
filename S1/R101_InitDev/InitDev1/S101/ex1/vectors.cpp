#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>
#include <ctype.h>
#include "vectors.h"
using namespace std;


/// displays the content of vec
void display(vector<string> vec){
    for (int i=0; i<vec.size(); i++) {cout << vec[i] << endl;}
    cout << endl;
}

/// sets all the characters in the string to uppercase
void normalization(string& str) {
    char c;
    for(int i=0; i<str.size(); i++)
        str[i] = toupper(str[i]);
}

/// returns the index of str or -1 if not found.
int search(vector<string> vec, string str) {
    normalization(str);

    /// search loop
    int i=0;
    bool found = false ;
    while ( i<vec.size() && ! found ) {
        found = (vec[i] == str) ;
        i++ ;
    }
    if (found) {return i-1;}
    else {return -1;}
}

/// searches and replaces the first occurence of str in vec
void replace(vector<string>& vec, string str, string newstr) {
    normalization(str);
    normalization(newstr);

    int res = search(vec, str);

    if(not(res == -1)) {vec[res] = newstr;}
}


/*
TESTS

-----display-----
general case: vec = {"a","b","c"}, result: display = a b c
error case: vec = {}, result: displays nothing

*/
