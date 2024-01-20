// HBM, JL  2023
// vecteurs à 2 dimensions
// les lignes et les colonnes representent des personnes
// chaque case (i,j) contient  un booleen
// indiquant si i et j sont des amis

#include <iostream>
#include <vector>
using namespace std;

int main(){
    vector <vector <bool> >  friends;  // the network of friends
    int i,j;

    for( i=0; i<4; i++)   {
        friends.push_back(vector <bool>(4,false));  // friend with no one
        friends[i][i]=true; // except herself
    }

    char rep;
    do {
        cout<<"Who are the new friends?";
        cin >> i >> j;
        while(i<0 || j<0|| i>=friends.size()|| j>=friends.size())  {
            cout << "Incorrect values, try again: ";
            cin >> i >> j;
        }
        cout << "OK, 2 new friends :-)"<< endl;
        friends[i][j]=friends[j][i] = true;

        cout << "More friends? (y or Y for yes)";
        cin>>rep;
    }  while(rep =='y' || rep == 'Y');

    for( i=0; i<friends.size(); i++) {
        cout<< i << " is friends with :" ;
        for( j=0; j<i; j++)
            if(friends[i][j]) cout << " " << j;
        for( j=i+1; j<friends[i].size(); j++)
            if(friends[i][j]) cout << " " << j;
        cout << endl;
    }

    /// Person who has the most friends
    int nbF;    // number of friends of i
    int nbMax;  // largest number of friends
    int iMax;   // index of the 1st person with the most friends
    // computing the largest number of friends
    nbMax=0;    // at least 0 friends
    iMax=0;
    for (i=0; i<friends.size(); i++) {
        nbF=0;   // computing the number of friends of i
        for (j=0; j<friends.size(); j++) {
            if(friends[i][j]) nbF++;
        }
        // updating the max
        if (nbF>nbMax) {
            nbMax=nbF;
            iMax=i;
        }
    }
    cout << "The person who has the most friends is " << iMax <<endl;
    return 0;
}

/* TESTS
-- case 1 --
abstract test : the first person has most friends iMax=0
concrete test : friends added = (0,1) (0,2) (0,3) (2,3)
result :The person who has the most friends is  0

-- case 2 --
abstract test : the last person has most friends iMax=3
concrete test :  friends added = (3,1) (0,2) (0,3) (2,3)
result : The person who has the most friends is 3

-- case 3 --
abstract test : a person in the middle has most friends
concrete test : friends added = (3,1) (0,2) (1,2) (2,3)
result : The person who has the most friends is 2

-- case 4 --
abstract test : several people have the most friends
concrete test :  friends added = (3,1) (1,2) (2,3)
result : The person who has the most friends is 1
*/
