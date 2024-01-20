/// Search Lab, R101-2 2023-2024
/// main.cpp

#include <iostream>
#include <stdexcept>    /// exception out-of-range
#include <vector>
#include <fstream>
#include <string>
#include <cmath>

#include "student.h"
#include "schoolClass.h"
#include "piece.h"
#include "appartement.h"

using namespace std;

int main() {
    // ----------------- PART 3 --------------------
    bool test1, test2, test3, test4;

    Piece R0(12, 8, "room0");
    Piece R1(10, 5, "room1");
    Piece R2(8, 7, "room2");
    Piece R3(11, 5, "room3");
    Piece R4(9, 8, "room4");
    Piece R5(7, 7, "room5");
    Piece R6(12, 8, "room6");
    Piece R7(12, 8, "room7");
    Piece R8(12, 8, "room8");
    Piece R9(12, 8, "room9");
    Appartement A1;
    A1.ajoutPiece(R0);
    A1.ajoutPiece(R0);
    A1.ajoutPiece(R1);
    A1.ajoutPiece(R2);
    A1.ajoutPiece(R2);
    A1.ajoutPiece(R2);
    A1.ajoutPiece(R3);
    A1.ajoutPiece(R4);
    A1.ajoutPiece(R4);
    A1.ajoutPiece(R4);
    A1.ajoutPiece(R5);
    A1.ajoutPiece(R6);
    A1.ajoutPiece(R6);
    A1.ajoutPiece(R7);
    A1.ajoutPiece(R8);
    A1.ajoutPiece(R6);
    A1.ajoutPiece(R9);
    A1.ajoutPiece(R9);
    A1.ajoutPiece(R9);

    A1.afficher(); cout << endl;


    // ----------------- TESTS PART 3 --------------------
    cout << "Test of searchRoom...\t\t";
    test1 = (A1.searchRoom("room0") == 1);
    test2 = (A1.searchRoom("room3") == 6);
    test3 = (A1.searchRoom("room9") == 17);
    test4 = (A1.searchRoom("dsgya") == -1);
    if (test1 && test2 && test3 && test4) {
        cout << "Success!" << endl;
    }
    else {
        cout << "Failed." << endl;
        cout << "searchRoom(\"room0\") = " << A1.searchRoom("room0") << " (expected 1)." << endl;
        cout << "searchRoom(\"room3\") = " << A1.searchRoom("room3") << " (expected 6)." << endl;
        cout << "searchRoom(\"room9\") = " << A1.searchRoom("room9") << " (expected 17)." << endl;
    }

    cout << "Test of searchFirst...\t\t";
    test1 = (A1.searchFirst("room0") == 0);
    test2 = (A1.searchFirst("room3") == 6);
    test3 = (A1.searchFirst("room9") == 16);
    test4 = (A1.searchRoom("dsgya") == -1);
    if (test1 && test2 && test3 && test4) {
        cout << "Success!" << endl;
    }
    else {
        cout << "Failed." << endl;
        cout << "searchFirst(\"room0\") = " << A1.searchFirst("room0") << " (expected 0)." << endl;
        cout << "searchFirst(\"room3\") = " << A1.searchFirst("room3") << " (expected 6)." << endl;
        cout << "searchFirst(\"room9\") = " << A1.searchFirst("room9") << " (expected 16)." << endl;
    }

    cout << "Test of insertionSort...\t"; // LAB5
    A1.insertionSort();
    if (A1.searchFirst(49) == 0 && A1.searchFirst(1) == -1 && A1.searchFirst(96) == 9) {
        cout << "Success!" << endl;
    } else {
        cout << "Failed." << endl; A1.afficher();
        cout << "searchFirst(49) = " << A1.searchFirst(49) << " (expected 0)." << endl;
        cout << "searchFirst(1) = " << A1.searchFirst(1) << " (expected -1)." << endl;
        cout << "searchFirst(96) = " << A1.searchFirst(96) << " (expected 9)." << endl;
    }


    // -----------------------------------------------

    // wait for input between part 3 and 4
    string wait;
    cout << endl << "Part 3 complete! Press any key to execute part 4...";
    getline(cin, wait); cout << endl;


    // ------------------ PART 4 ---------------------
    try
    {
        SchoolClass s1;
        s1.init("studentList.txt");
        s1.display();

        // --------------- TESTS ---------------
        cout << "test of searchStudent...\t\t";
        // random student
        if ((s1.searchStudent("1K", "MEUNIER") == 161) && (s1.searchStudent("1M", "THERENE") == 193) && (s1.searchStudent("1A", "ABBADI") == 0)) cout << "Success!" << endl;
        else {
            cout << "Failed: s1.searchStudent(\"1K\", \"MEUNIER\") = " << s1.searchStudent("1K", "MEUNIER") << " (expected 161)" << endl;
            cout << "Failed: s1.searchStudent(\"1M\", \"THERENE\") = " << s1.searchStudent("1M", "THERENE") << " (expected 193)" << endl;
            cout << "Failed: s1.searchStudent(\"1A\", \"ABBADI\") = " << s1.searchStudent("1A", "ABBADI") << " (expected 0)" << endl;
        }
    }

    catch (string msg) /// if error while opening file
    {
        cout << "ERROR : problem while opening file : " << msg << endl;
    }

    catch (invalid_argument e)
    {
        cerr << "ERROR : " << e.what() << endl;
    }

    catch(out_of_range e)
    {
        cerr << "ERROR : " << e.what() << endl;
    }

    return 0;
}
