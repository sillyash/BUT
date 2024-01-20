#include <iostream>
#include <fstream>
#include "piece.h"
#include "appartement.h"

using namespace std;


int main()
{
    // output
    string fileName;
    ofstream output;
    Piece test;
    Piece R1(10, 12, "Bedroom");

    // input test
    ifstream input;
    vector<Piece> rooms;
    int nbRooms;
    string teststring;


    // OUTPUT ROOM TEST
    try {
        fileName = "test";
        output.open(fileName);
        if(!(output.is_open())) throw(fileName);
        test.writeFile(output);
        R1.writeFile(output);
        output.close();
    }
    catch (const string & s) {
        cerr<< "Problem encountered while opening file: " << s;
    }


    // INPUT ROOM TEST
    try {
        fileName = "test";
        input.open(fileName);
        if(!(input.is_open())) throw(fileName);
        if (!input.eof()) {
            R1 = Piece(input);
            rooms.push_back(R1);
        }
        input.close();
        for (int i=0; i<rooms.size(); i++) rooms[i].afficher();
        cout << endl;
    }
    catch (const string & s) {
        cerr << "Problem encountered while opening file : " << s;
    }

    return 0;
}
