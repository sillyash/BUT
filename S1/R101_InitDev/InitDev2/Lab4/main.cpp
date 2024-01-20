#include <cstdlib>
#include <iostream>
#include <ostream>
#include <vector>

#include "rat.h"

using namespace std;

int main()
{
    cout << "----- Automatic Tests -----------------------------------------------" << endl;
    if (testEmptyConstructorAndGetters()) cout << "Empty constructor and getters ok" << endl;
    if (testConstructorParamAndGetters()) cout << "Constructor with parameters and getters ok" << endl << endl;

    // -------------- * --------------
    cout << "Test of * operator...\t\t";
    if (Rational(1,2)*Rational(3, 8) == Rational(3, 16)) cout << "Success!";
    else cout << "Failed, 1/2*3/8 = " << Rational(1,2)*Rational(3, 8) << endl;

    if (3*Rational(3, 8) == Rational(9, 8)) cout << ", Success!";
    else cout << "Failed, 3 * 3/8 = " << 3*Rational(3, 8) << endl;

    if (Rational(3, 8)*3 == Rational(9, 8)) cout << ", Success!";
    else cout << "Failed, 3 * 3/8 = " << 3*Rational(3, 8) << endl;

    // -------------- + --------------
    cout << endl << "Test of + operator...\t\t";
    if (Rational(1,2)+Rational(3, 8) == Rational(7, 8)) cout << "Success!";
    else cout << "Failed, 1/2+3/8 = " << Rational(1,2)+Rational(3, 8) << endl;

    // -------------- == --------------
    cout << endl <<"Test of == operator...\t\t";
    if (Rational(1,2) == Rational(4, 8)) cout << "Success!";
    else cout << "Failed, 1/2 == 4/8 returns " << (Rational(1,2) == Rational(4,8)) << endl;

    // -------------- - --------------
    cout << endl << "Test of - operator...\t\t";
    if (-Rational(1,2) == Rational(-1, 2)) cout << "Success!";
    else cout << "Failed, -(1/2) = " << -Rational(1,2) << endl;

    // -------------- < --------------
    cout << endl << "Test of < operator...\t\t";
    if (Rational(4,5) < Rational(7,8)) cout << "Success!";
    else cout << "Failed, 4/5 < 7/8 returns " << (Rational(4,5) < Rational(7,8)) << endl;

    // -------------- != --------------
    cout << endl << "Test of != operator...\t\t";
    if (Rational(2,3) != Rational(3,4) && !(Rational(2,3) != Rational(2,3))) cout << "Success!";
    else cout << "Failed." << endl;

    // -------------- <= --------------
    cout << endl << "Test of <= operator...\t\t";
    if (Rational(2,3) <= Rational(3,4) && !(Rational(4,3) <= Rational(2,3))) cout << "Success!";
    else cout << "Failed." << endl;

    cout << endl << endl; // End of tests -------------------------------------------------*


    cout << "----- Main program --------------------------------------------------" << endl;
    cout << "Please enter the numerator and denominator >>> ";
    int n, d;
    cin >> n >> d; cout << endl;

    try
    {
        // Part 1
        Rational r(n,d);
        cout << "Here is your reduced rational: " << "";
        cout << endl;
        cout << "\t<<:\t" << r << endl;
        cout << "\t +:\t" << r+r << endl;
        cout << "\t -:\t" << -r << " (unary)" << endl;
        cout << "\t *:\t" << r*r << endl;

        // Part 2 - Q2
        cout << endl << "Test of input operator (istream) >>> ";
        Rational r2;
        cin >> r2;
        cout << "Created: " << r2 << endl;

        // Part 2 - Q 3&4
        cout << endl << "Test of operators:" << endl;
        cout << "\t1/2 * 3 = " << Rational(1,2)*3 << endl;  // works because of how memory address is handled (integer is multiplied)
        cout << "\t3 * 1/2 = " << 3*Rational(1,2) << endl;  // doesnt compile whitout custom function because of
                                                            // how memory address is handled (cant found integer)

        // Part 2 - Q6
        vector<Rational> ratios = {};
        ratios.push_back(Rational(4,5));
        ratios.push_back(Rational(1,5));
        ratios.push_back(Rational(7,12));
        ratios.push_back(Rational(4,6));
        ratios.push_back(Rational(2,3));
        ratios.push_back(Rational(3,7));
        ratios.push_back(Rational(1,5));
        ratios.push_back(Rational(4,9));
        ratios.push_back(Rational(8,5));
        ratios.push_back(Rational(18,3));
        ratios.push_back(Rational(12,6));
        ratios.push_back(Rational(1,17));

        int maxRatio = 0;
        for (int i=0; i<ratios.size(); i++) {
            if (ratios[maxRatio] < ratios[i]) maxRatio = i;
        }
        cout << endl << "The maximum rational of the vector is at index " << maxRatio
        << " and has a value of " << ratios[maxRatio] << "." << endl;

    }

    catch (const invalid_argument &e)
    {
        cout << "Error : " << e.what() << endl;
    }

    return 0;
}
