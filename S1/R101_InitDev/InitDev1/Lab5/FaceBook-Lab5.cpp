/// HBM  CTB JL 2023
/// Lab5, 2D vectors
/// Lines and columns represent members of the network
/// Each element (i,j) contains a boolean indicating if i and j are friends.

/// YOUR NAME HERE : MERIENNE Ash

#include <iostream>
#include <vector>
using namespace std;

void veccout2D(vector<vector<bool> > vec) {
    // Displays the content of vec, a 2D vector
    for (int i=0; i<vec.size(); i++) {
            cout << endl;
        for (int j=0; j<vec.size(); j++) {
            cout << vec[i][j] << "  ";
        }
    }
    cout << endl;
}

void displayFriends(vector<vector<bool> > vec, vector<string> names) {
    /// Question 2 and 3
    // Displays the list of friends for each member
    for (int i=0; i<vec.size(); i++) {
            cout << endl;
            cout << names[i] << " is friends with: ";
        for (int j=0; j<vec.size(); j++) {
            if (vec[i][j] and j!=i) {
                cout << names[j] << " ";
            }
        }
    }
}

vector<vector<bool> > updateFriends(vector<vector<bool> > vec, int memb1, int memb2) {
    /// Question 4
    // Updates the 2D vector to make memb1 and memb2 friends
    vec[memb1][memb2] = true;
    vec[memb2][memb1] = true;
    return vec;
}

int maxMember(vector<vector<bool> > vec) {
    /// Question 5
    int max = 0;
    int memberMax;
    int mem;
    // For each member
    for (int i=0; i<vec.size(); i++) {
        mem = 0;
        // In the list of his friends
        for (int j=0; j<vec.size(); j++) {
            // Count each friend
            if (vec[i][j] and j!=i) {
                mem++;
            }
            // If the member has the most friends, he becomes the memberMaxtschism tool rating

            if (mem > max) {
                max = mem;
                memberMax = i;
            }
        }
    }
    return memberMax;
}

int commonFriends(vector<vector<bool> > vec, int memb1, int memb2) {
    /// Question 6
    // Returns the number of friends that are friends with memb1 and memb2
    int nbFriends = 0;
    for (int i=0; i < vec.size(); i++) {
        // if memb1 and memb2 have a friend in common
        if ((vec[memb1][i] == true) and (vec[memb2][i] == true)) {
            nbFriends++;
        }
    }
    return nbFriends;
}

bool isCorrect(vector<vector<bool> > vec) {
    /// Question 7
    // Returns true if every "friendship" is symmetrical
    // (i.e 1 is friends with 2 so 2 is friends with 1)
    for (int i=0; i < vec.size(); i++) {
        for (int j=0; j<vec.size(); j++) {
            if (vec[i][j] != vec[j][i]) {
                return false;
            }
        }
    }
    return true;
}


int main(){
    vector<vector<bool> > friends(4); /// the network of friends.
    vector<string> names = {"Jean", "Rachel", "Chris", "Lucy"}; /// Q8

    int memb1, memb2, memberMax;
    bool inputOK;
    char userContinue;

    /// Q1 : initialize the network. All elements are false, except for those in the diagonal
    /// (members are friends with themselves)
    for (int i=0; i < friends.size(); i++) {
        friends[i] = vector<bool>(4,false);
        friends[i][i] = true; // Every member is friend with themselves
    }

    /// Question 4
    do {
        // User input
        cout << "Which members are now friends ? >>> ";
        cin >> memb1 >> memb2;

        // Check if memb1 and memb2 are in vector and not equal
        inputOK = ((memb1 != memb2) and (memb1 < friends.size()) and (memb2 < friends.size()));

        // Display error if input is incorrect
        if (not inputOK) {
            cout << "Error, you have to enter 2 different members that already exist. Try again." << endl;
        }
        else {
            // Display results and ask if user wants to continue
            friends = updateFriends(friends, memb1, memb2);
            cout << "Do you want to continue ? (y/n) >>> ";
            cin >> userContinue;
        }
    }
    while ((not inputOK) or (userContinue == 'y'));

    // Display friend list
    cout << endl;
    displayFriends(friends, names);
    cout << endl;

    // Display vector 2D
    veccout2D(friends);
    cout << endl;

    // Display the member with the most friends
    memberMax = maxMember(friends);
    cout << "The member with the most friends is "
    << names[memberMax] << "." << endl << endl;


    /// Question 6
    do {
        // User input
        cout << "Enter 2 members (ID only) to check their common friends. >>> ";
        cin >> memb1 >> memb2;
        cout << endl;

        // Check if memb1 and memb2 are in vector and not equal
        inputOK = ((memb1 != memb2) and (memb1 < friends.size()) and (memb2 < friends.size()));

        // Display error if input is incorrect
        if (not inputOK) {
            cout << "Error, you have to enter 2 different members that already exist. Try again." << endl;
        }
        else {
            // Display results
            cout << names[memb1] << " and " << names[memb2] << " have "
            << commonFriends(friends, memb1, memb2) << " friends in common." << endl;
        }
    }
    while (not inputOK);

    /// Question 7
    if (isCorrect(friends)) {
        cout << endl << "The vector is symmetrical." << endl;
    }
    else {
        cout << endl << "The vector isn't symmetrical." << endl;
    }

    cout << endl;
    return 0;
}


/*
TESTS

-----Question 4-----
>>> 4 1
expected: Error, try again
result: same as expected

>>> 1 4
expected: Error, try again
result: same as expected

>>> 2 2
expected: Error, try again
result: same as expected

>>> 3 0 y 2 1 n
expected: code runs and outputs correct friends
result: same as expected


-----Question 5-----
>>> 2 1 y 2 0 y 3 0 n
expected: code outputs 0 as the member with most friends
result: same as expected

>>> 2 2 2 0 y 2 1 y 2 3 n
expected: error, try again, then code outputs 2 as the member with most friends
result: same as expected


-----Question 6-----
>>> 2 1 y 2 0 y 1 0 y 1 3 n (<-creating friends) 2 1
expected: 2 and 1 have 3 friends in common (0, 1 and 2)
result: same as expected

>>> 2 1 y 2 0 y 1 0 y 1 3 n (<-creating friends) 0 3
expected: 0 and 3 have 1 friends in common (1)
result: same as expected


-----Question 7-----
>>> 2 1 y 2 0 y 1 0 y 1 3 n
expected: vector is symmetrical
result: same as expected

For this question, since my function updateFriends always updates
the friendship on both sides, the vector will always be symmetrical.


-----Question 8-----
>>> 2 1 y 2 0 y 1 0 y 1 3 n
expected result:
    Jean is friends with: Rachel Chris
    Rachel is friends with: Jean Chris Lucy
    Chris is friends with: Jean Rachel
    Lucy is friends with: Rachel

    The member with the most friends is Rachel.

result: same as expected

*/

