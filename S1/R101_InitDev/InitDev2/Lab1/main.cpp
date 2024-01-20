#include <string>
#include <iostream>
#include <vector>
#include <stdexcept>
#include "license.h"

using namespace std;


int main()
{
    License ldef; // default license
    License l("Duck", "Donald", "09/06/1934", "012345", 7);
    License l1("Elvis", "Lennon", "05/02/1964", "245578", 8);
    License l2("Mona", "Lisa", "24/11/1998", "234567", 11);

    cout << endl << "----- Manual tests for display ----------------------------------------" << endl;
    ldef.display();
    cout << endl;
    l.display();
    cout << endl;
    l1.display();
    cout << endl;
    l2.display();

    cout << endl << "----- Automatic tests -------------------------------------------------" << endl;
    bool ok = true;
    if (!(l.getNbpoints() == 7))
    {
        ok = false;
        cout << "Error : l.getNbpoints() = " << l.getNbpoints() << " (expected : 7)" << endl;
    }
    if (ok) cout << "Method getNbpoints correct" << endl;
    ok = true;
    l.withdrawPoints(3);
    if (!(l.getNbpoints() == 4))
    {
        ok = false;
        cout << "Error : after having retrieved 3 points from a license with 7 points, we get a license with "
             << l.getNbpoints() << " points (expected : 4)" << endl;
    }
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    l.withdrawPoints(0);
    if (!(l.getNbpoints() == 7))
    {
        ok = false;
        cout << "Error : after having retrieved 0 points from a license with 7 points, we get a license with "
             << l.getNbpoints() << " points (expected : 7)" << endl;
    }
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    l.withdrawPoints(7);
    if (!(l.getNbpoints() == 0))
    {
        ok = false;
        cout << "Error : after having retrieved 7 points from a license with 7 points, we get a license with "
             << l.getNbpoints() << " points (expected : 0)" << endl;
    }
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    l.withdrawPoints(10);
    if (!(l.getNbpoints() == 0))
    {
        ok = false;
        cout << "Error : after having retrieved 10 points from a license with 7 points, we get a license with "
             << l.getNbpoints() << " points (expected : 0)" << endl;
    }
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    if (ok) cout << "Method withdrawPoints correct" << endl;
    l.decrementPoints();
    if (!(l.getNbpoints() == 6)) {
        ok = false;
        cout << "Error : after retrieving a point from a license with 7 points, we have a license with "
             << l.getNbpoints() << " points (expected: 6)." << endl;
    }
    if (ok) cout << "Method decrementPoints correct" << endl;
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    addPoints(l, 4);
    if (!(l.getNbpoints() == 11)) {
        ok = false;
        cout << "Error : after adding 4 points to a license with 7 points, we get a license with "
             << l.getNbpoints() << "  points (expected: 11)." << endl;
    }
    if (ok) cout << "Function addPoints correct" << endl;
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    l.incrementPoints();
    if (!(l.getNbpoints() == 8)) {
        ok = false;
        cout << "Error : after adding a points to a license with 7 points, we get a license with "
             << l.getNbpoints() << "  points (expected: 8)." << endl;
    }
    if (ok) cout << "Function incrementPoints correct" << endl;
    l = License ("Duck", "Donald", "09/06/1934", "012345", 7);
    if (!(l.getName() == "Duck")) {
        ok = false;
        cout << "Error : getName() returns " << l.getName() << " (expected: Duck)." << endl;
    }
    if (ok) cout << "Function getName correct" << endl;



    cout << endl << "----- Main Program ----------------------------------------------------" << endl;

    /// 6)
    try {
        l = License ("Duck", "Donald", "09/06/1934", "012345", 30);
    }
    catch (std::invalid_argument& err) {
        char ans;
        cerr << endl << err.what() << " Proceed? (Y/N)" << endl;
        cin >> ans;
        cout << endl;
        if (not(ans == 'Y' || ans == 'y'))
        return 10;
    }

    /// 8)
    if (l.compare(l2))
    cout << "same licenses" << endl;

    /// 9)
    vector<License> drivers(10);
    for (int i=0; i<drivers.size(); i++) {
        drivers[i].display();
        cout << endl;
    }


    return 0;
}

/// 1.a) The license.h contains the prototypes of the License class's functions, and the declaration of it's variables.

/// 1.b) The license.cpp file contains the definitions of the License class and it's functions. The purpose of "License::"
///      is to declare that a certain function belongs to the License class. If you remove it, any type of object could use this
///      function, and it would not wok as it uses class attributes.

/// 1.c) The main.cpp file contains declarations of instances of the License class and tests.

/// 1.d) The displays shows multiple variables from an instance and displays them with context and descriptions.

/// 2.b) A compiling error occurs, because License isn't a type std::out knows how to handle.

/// 2.c) A compiling error occurs, because the name of a license is a private variable.

/// 2.d) display() is a function that doesn't alter or change any data, hence the "const" after.
///      on the other hand, withdrawPoints() alters the number of points.

/// 3.b) The licenses are destroyed when you create another one on the same memory address, or at the end of the program.

/// 7) There is a compiling error due to the fact that the License class doesn't know how to handle comparison.

/// 10) 1. There is an error because we only have an empty or a full constructor. We should add default values to the full constructor.
///     2. The empty constructor and the full constructor with default values are conflicting. We should delete the empty constructor.








