#include <iostream>
#include <string>
#include <vector>
using namespace std;

#ifndef VECTORS_H_
#define VECTORS_H_

/// procedure display(vector<string>)
/// parameters : (G) vec : vector<string>
/// displays the content of vec
void display(vector<string> vec);


/// procedure normalization(string str)
/// parameters : (G/R) str : string
/// sets all the characters in the string to uppercase
void normalization(string& str);


/// function search(vector<string> vec, string str)
/// parameters : (G) vec : vector<string>
///              (G) str : string
/// returns the index of str or -1 if not found.
int search(vector<string> vec, string str);


/// procedure replace(vector<string> &vec, string str, string newstr)
/// parameters : (G/R) vec : vector<string>
/// searches and replaces the first occurence of str in vec
/// if str isn't in vec, does nothing
void replace(vector<string>& vec, string str, string newstr);

#endif
