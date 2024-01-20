#include <iostream>
#include <ctime>
#include "chocolate.h"

using namespace std;

int main()
{
    try
    {
        srand(time(NULL));          // seed for "random" numbers

        Chocolate c1("white",20);   // a white chocolate weighing 20g
        Chocolate c2;               // a dark chocolate weighing 30g
        Chocolate c3(10);           // a chocolate weighing 10g, with a random type
        Chocolate c4("white");      // a white chocolate weighing 30g

        ChocolateBox cookies("Granny's Old Choco Box", 5);

        // DISPLAY TEST (<< operator)
        cout << c1 << endl << c2 << endl << c3 << endl << c4 << endl << endl;
        cout << endl << cookies << endl;

        // ----- AUTO TESTS -----

        // CHOCOLATE TESTS
        cout << "Test of Chocolate class..." << endl;
            // -- OPERATORS --
                cout << "Test of overloaded operators:\n";
                // comparison (< & >)
                cout << "\tTest of <...\t\t";
                if (c1 < c2) cout << "Success!" << endl;
                else cout << "Failed... (c1 < c2) returns " << (c1<c2) << endl;
                cout << "\tTest of >...\t\t";
                if (c2 > c1) cout << "Success!" << endl;
                else cout << "Failed... (c2 > c1) returns " << (c2>c1) << endl;
            // -- END OF OPERATORS --
        // END OF CHOCOLATE TESTS

        // CHOCOLATEBOX TESTS
        cout << "\nTest of ChocolateBox class..." << endl;
        cookies = ChocolateBox("Test Box",0);
        // addChocolate
        cout << "Test of addChocolate...\t";
        cookies.addChocolate(c1);
        if (cookies.theContent() == vector<Chocolate> {c1}) cout << "Success!" << endl;
        else cout << "Failed..." << endl << cookies << endl;

            // -- OPERATORS --
                cout << "Test of overloaded operators:\n";
                cout << "\tTest of +=...\t";
                cookies += c3;
                if (cookies.theContent() == vector<Chocolate> {c1, c3}) cout << "Success!" << endl;
                else cout << "Failed..." << endl << cookies << endl;
                cout << "\tTest of +...\t";
                cookies = cookies + c2;
                if (cookies.theContent() == vector<Chocolate> {c1, c3, c2}) cout << "Success!" << endl;
                else cout << "Failed..." << endl << cookies << endl;
            // -- END OF OPERATORS --
        // END OF CHOCOLATEBOX TESTS

        // ----- END OF TESTS -----
    }

    catch(const runtime_error& ru){
        cerr << "Runtime error occured: " << ru.what() << endl;
    }
    catch(const exception& ex){
        cerr << "Exception error occured: " << ex.what() << endl;
    }
    catch(...){
        // catch any other unknown errors
        cerr << "Unknown failure occured. Possible memory corruption." << endl;
    }
    return 0;
}
