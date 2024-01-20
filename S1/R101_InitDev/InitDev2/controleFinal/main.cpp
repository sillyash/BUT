#include "pion.h"
#include "combinaison.h"
#include <ctime>
#include <iostream>
using namespace std;

int main()
{   try
    {
        srand(time(NULL));

        Combinaison aDeviner(4);
        Combinaison proposition;

        cout << "La combinaison à deviner est de niveau 4." << endl;
        cin >> proposition;
        while (!(proposition == aDeviner))
        {
            cout << "Dommage" << endl;
            cin >> proposition;
        }
        cout << "Bravo!" << endl;

    }
    catch(const runtime_error& ru){
        cerr << "Runtime error occured: " << ru.what() << endl;
    }
    catch(const exception& ex){
        cerr << "Exception error occured: " << ex.what() << endl;
    }
    catch (const invalid_argument& inv) {
        cerr << "Invalid argument: " << inv.what() << endl;
    }
    catch(...){
        // catch any other unknown errors
        cerr << "Unknown failure occured. Possible memory corruption." << endl;
    }


  return 0;
}
