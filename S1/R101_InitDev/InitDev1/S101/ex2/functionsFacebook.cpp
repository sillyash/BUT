#include <iostream>
#include <vector>
#include "functionsFacebook.h"

using namespace std;


/// initializes the social network with NBMEMBERS
vector<vector<bool>> initialization(){
    vector<vector<bool> > friends;

    for (int i=0; i<NBMEMBERS; i++) {
        friends.push_back(vector<bool> (NBMEMBERS,false));
        friends[i][i] = true;
    }
    return friends;
}

/// displays the social network
void display(const vector<vector<bool>> &friends, const vector<string> &friendsNames) {
    for(int i=0; i<friends.size(); i++) {
        cout<< friendsNames[i] << " is friends with:" ;
        for(int j=0; j<i; j++)
            if(friends[i][j]) cout << " " << friendsNames[j];
        for(int j=i+1; j<friends[i].size(); j++)
            if(friends[i][j]) cout << " " << friendsNames[j];
        cout << endl;
    }
}

/// adds to the network a friendship relationship between memb1 and memb2, does nothing if error
void addFriends(vector<vector<bool>> &friends, const vector<string> &friendsNames, string memb1Name, string memb2Name) {
    // check if the members exist in the network
    if (!(checkMemb(friendsNames, memb1Name) && checkMemb(friendsNames, memb2Name))) {
        // exit if someone doesn't exist
        return;
    }
    // converts the names into indexes
    int memb1 = nameToIndex(friendsNames, memb1Name);
    int memb2 = nameToIndex(friendsNames, memb2Name);

    friends[memb1][memb2] = friends[memb2][memb1] = true;
}

/// returns the number of friends in common between memb1 and memb2, -1 if error
int commonFriends(const vector<vector<bool>> &friends, const vector<string> &friendsNames, string memb1Name, string memb2Name) {
    // check if the members exist in the network
    if (!(checkMemb(friendsNames, memb1Name) && checkMemb(friendsNames, memb2Name))) {
        // return -1 if someone doesn't exist
        return -1;
    }
    // converts the names into indexes
    int memb1 = nameToIndex(friendsNames, memb1Name);
    int memb2 = nameToIndex(friendsNames, memb2Name);

    int nbCommonFriends = 0;
    for (int i=0; i<friends.size(); i++) {
        // if they have common friends (we don't count their own friendship)
        if ((i != memb1 && i != memb2) and (friends[memb1][i] == 1) and (friends[memb2][i] == 1)) {
            nbCommonFriends++;
        }
    }
    return nbCommonFriends;
}

/// the groupFriends function returns true if 3 people form a group of
/// friends where each is friends with the other two, and false otherwise.
bool groupFriends(const vector<vector<bool>> &network, const vector<string> &friendsNames, string aName, string bName, string cName) {
    // check if the members exist in the network
    if (!(checkMemb(friendsNames, aName) && checkMemb(friendsNames, bName) && checkMemb(friendsNames, cName))) {
        // return false if someone doesn't exist
        return false;
    }
    // converts the names into indexes
    int a = nameToIndex(friendsNames, aName);
    int b = nameToIndex(friendsNames, bName);
    int c = nameToIndex(friendsNames, cName);
    bool res = false;
    // if each member is friend with the other two
    if ((network[a][b] == 1) && (network[a][c] == 1) && (network[b][c] == 1)) {
        res = true;
    }
    return(res);
}

/// displays menu, input choice, checks it, and returns it
int testModif() {
    int	choice;

    // display possible choices
    cout << endl << "Enter " << endl;
    cout << DISPLAY << " to display the network" << endl;
    cout << ADDFRIENDS << " to add a friendship between 2 members of the network" << endl;
    cout << COMMONFRIENDS << " to check the number of common friends between 2 members of the network" << endl;
    cout << GROUPFRIENDS << " to check if 3 members of the network are a group of friends" << endl;
    cout << QUIT << " to quit the program" << endl;
    cout << ">>> ";
    cin >> choice;

    // Check if the input is correct, ask again if not
    while (!(choice>=DISPLAY && choice<=QUIT)) {
        // remove input in case input is not an int
        fflush(stdin);
        cin.clear();
        // input again
        cout << endl << choice << " is not a valid option, please enter again:" << endl << ">>> ";
        cin >> choice;
    }
    return choice;
}

/// sets all the characters in the string to uppercase
void normalization(string &str) {
    char c;
    // set each character to uppercase
    for(int i=0; i<str.size(); i++)
        str[i] = toupper(str[i]);
}

/// returns the index associated to the name of a member in the network, -1 if not found
int nameToIndex(const vector<string> &friendsNames, string name) {
    // normalizes the string to avoid errors
    normalization(name);

    // search for the name in the names vector
    for (int i=0; i<friendsNames.size(); i++) {
        if (name == friendsNames[i]) {
            // if found, return index
            return i;
        }
    }
    // if not found, return -1
    return -1;
}

/// returns true if memb is a member of the network, else false
bool checkMemb(const vector<string> &friendsNames, string membName) {
    // converts the name into an index
    int memb = nameToIndex(friendsNames, membName);

    // nameToIndex returns -1 if the member isn't found.
    return (!(memb == -1));
}

