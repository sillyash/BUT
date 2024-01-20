// HBM,JL, CTB 2023
// handling a sports team : French women's soccer team 2023
// g++ -std=c++14

#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main( )
{
    vector<string> team = {"DURAND","BACHA","RENARD","GEYORO","DIANI","SOMMER"};

    string player;
    // additional variables
    int nbAdd; /// number of players to add
    int i,j,l;
    bool found ;  /// true if the player is found

    /// Question 2: first and last elements of the vector
    /// be sure to carefully choose the indices
    cout << "First: " << team[0] << " " << team.front();
    cout << ", last: "<< team[team.size()-1] << " " << team.back() << endl;

    /// Question 3: classic display loop
    /// Be sure to display the vector after
    /// each modification to check its display.
    cout << endl << endl << "Displaying in order " << endl ;
    for (i=0 ; i<team.size() ; i++)
    {
        cout << team[i] << endl;
    }

    /// Question 4: same thing in reverse order, careful with the loop bounds
    cout << endl << endl << "Displaying in reverse order " << endl ;
    for (i=team.size()-1 ; i>=0 ; i--)
    {
        cout << team[i] << endl;
    }

    /// Question 5: adding a few players
    /// Input how many players to add
    cout << "How many players do you want to add? ";
    cin >> nbAdd;
    while(nbAdd<0)
    {
        cout << "The entered number is not valid!" << endl;
        cout << "How many players do you want to add? ";
        cin >> nbAdd;
    }
    /// Loop to add the players
    for(i=0; i<nbAdd; i++)
    {
        cout << "Input a name: ";
        cin >> player; /// use `getline` if the name could contain spaces
        /// let's make sure that the entered names are stored in unpper case letters (question 8)
        l=player.length();
        for (int j=0 ; j<l; j++)
            player[j]=toupper(player[j]);
        /// adding at the end
        team.push_back(player);
    }
    cout <<  endl << "--  Displaying vector with new players " << endl ;
    for (i=0 ; i<team.size() ; i++)
    {
        cout << team[i] << endl;
    }

    /// question 6 : search
    cout << "What player are you looking for? " ;
    cin >> player ;
    l=player.length();
    for (int j=0 ; j<l; j++)
        player[j]=toupper(player[j]);
    /// search loop
    i=0;
    found = false ;
    while ( i<team.size() && ! found )
    {
        found = (team[i] == player) ;
        i++ ;
    }
    if ( !found )
        cout << endl << player << " is not present in the vector" << endl ;
    else
    {
        cout << endl << player << " is found at index "
             <<  i-1 << ". Verification: " << team[i-1] << endl ;
        /// question 7 : replacement
        cout << "Who is her replacement? " ;
        cin>> player;
        l=player.length();
        for (int j=0 ; j<l; j++)
            player[j]=toupper(player[j]);
        team[i-1] = player;
        cout <<  endl << "--  Displaying the team after replacement " << endl ;
        for (i=0 ; i<team.size() ; i++)
        {
            cout << team[i] << endl;
        }
    }

    return 0;
}
