#include <iostream>
#include <vector>
#include <string>
using namespace std;

#ifndef FUNCTIONSFACEBOOK_H_
#define FUNCTIONSFACEBOOK_H_

// number of members to initialize the network
const int NBMEMBERS = 4;

// menu options
const int DISPLAY = 1;
const int ADDFRIENDS = 2;
const int COMMONFRIENDS = 3;
const int GROUPFRIENDS = 4;
const int QUIT = 5;


/// procedure initialization()
/// parameters : none
/// initializes the social network with NBMEMBERS
vector<vector<bool>> initialization();


/// procedure display()
/// parameters : (G) vector<vector<bool>> friends
///              (G) vector<string> friendsNames
/// displays the social network
void display(const vector<vector<bool>> &friends, const vector<string> &friendsNames);


/// procedure display()
/// parameters : (G/R) vector<vector<bool>> friends
///              (G) vector<string> friendsNames
///              (G) string memb1Name
///              (G) string memb2Name
/// adds to the network a friendship relationship between memb1 and memb2, does nothing if error
void addFriends(vector<vector<bool>> &friends, const vector<string> &friendsNames, string memb1Name, string memb2Name);


/// function commonFriends()
/// parameters : (G) vector<vector<bool>> friends
///              (G) vector<string> friendsNames
///              (G) string memb1Name
///              (G) string memb2Name
///              (R) int nbCommonFriends
/// returns the number of common friends between memb1 and memb2, -1 if error
int commonFriends(const vector<vector<bool>> &friends, const vector<string> &friendsNames, string memb1Name, string memb2Name);


/// function groupFriends()
/// parameters : (G) vector<vector<bool>> friends
///              (G) vector<string> friendsNames
///              (G) string aName
///              (G) string bName
///              (G) string cName
///              (R) int nbCommonFriends
/// the groupFriends function returns true if 3 people form a group of
/// friends where each is friends with the other two, and false otherwise.
bool groupFriends(const vector<vector<bool>> &network, const vector<string> &friendsNames, string aName, string bName, string cName);


/// function testModif()
/// parameters : (R) int choice
/// displays menu, input choice, checks it, and returns it
int testModif();


/// function normalization()
/// parameters : (G/R) string str
/// sets all the characters in the string to uppercase
void normalization(string &str);


/// function checkMemb()
/// parameters : (G) vector<string> friendsNames
///              (G) int memb
/// returns true if memb is a member of the network, else false
bool checkMemb(const vector<string> &friendsNames, string memb);


/// function nameToIndex()
/// parameters : (G) vector<string> friendsNames
///              (G) string name
/// returns the index associated to the name of a member in the network, -1 if not found
int nameToIndex(const vector<string> &friendsNames, string name);

#endif
