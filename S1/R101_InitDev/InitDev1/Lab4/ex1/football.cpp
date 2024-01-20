/// JL 2023
/// Managing a sports team : the French women's national football team
/// g++ -std=c++14

/// YOUR NAME HERE: MERIENNE Ash
/// YOUR GROUP NUMBER HERE: 1B

#include <iostream>
#include <string>
#include <vector>
using namespace std;


void veccout(vector<string> vec) {
    // Displays all elements from vec
    for (int i = 0; i < vec.size(); i++) {
        cout << vec[i] << endl;
    }
}

void veccoutReverse(vector<string> vec) {
    // Displays all elements from vec, in reverse order
    for (int i = vec.size()-1; i >= 0; i--) {
        cout << vec[i] << endl;
    }
}

int vecsearch(vector<string> vec, string elem) {
    // Searches an element in vec, returns 0 (false) if it isn't in vec
    bool found = false;
    int i = 0;
    while (i < vec.size() && !found) {
        found = (vec[i] == elem);
        i++;
    }
    if (found) {
        return i;
    }else {
        return 0;
    }
}

//Question 8
string strUpper(string str) {
    // Sets all char from str to uppercase
    for (int x = 0; x < str.size(); x++) {
        str[x] = toupper(str[x]);
    }
    return str;
}

int main() {
    vector<string> team = {"DURAND","BACHA","RENARD","GEYORO","DIANI","SOMMER"};
    int nbPlayers, indexPlayer;
    string playerName;

    // Question 1
    cout << "First member in the team: ";
    cout << team.front() << endl;

    // Question 2
    cout << "Last member in the team: ";
    cout << team.back() << endl;

    // Question 3
    cout << "Members of the team: " << endl;
    veccout(team);
    cout << endl;

    // Question 4
    cout << "Members of the team in reverse order: " << endl;
    veccoutReverse(team);
    cout << endl;

    // Question 5
    cout << "How many players to you want to add?" << endl;
    cin >> nbPlayers;
    // Loop to add nbPlayers to the vector
    for (int i = 0; i < nbPlayers; i++) {
        cout << "Enter the name of the player." << endl;
        cin >> playerName;
        playerName = strUpper(playerName);
        team.push_back(playerName);
    }
    cout << endl << "Members of the team: " << endl;
    veccout(team);
    cout << endl;

    //Question 6
    cout << "Which name do you want to search?"<< endl;
    cin >> playerName;
    // Set the name to uppercase and use function
    playerName = strUpper(playerName);
    indexPlayer = vecsearch(team, playerName);
    // vecsearch returns 0 if nothing is found, so we can also use it as a condition
    // that is why the function always return the index + 1 to avoid an error
    if (indexPlayer) {
        cout << "Player stored in element " << indexPlayer-1 << " of the vector." << endl;
    }else {
        cout << "Unknown player." << endl;
    }

    //Question 7
    if (indexPlayer) {
        cout << "Replace the player by: ";
        cin >> playerName;
        // set to uppercase
        playerName = strUpper(playerName);
        // replace
        team[indexPlayer-1] = playerName;
        cout << endl << "Members of the team: " << endl;
        veccout(team);
    }
}

/*
TESTS

>>> 2 SANDOZ bach DIanI toto
expected results: Program adds SANDOZ and BACH to the list, replaces DIANI by TOTO
results: same as expected

>>> 0 durand titi
expected results: Program replaces DURAND by TITI
results: same as expected

*/
