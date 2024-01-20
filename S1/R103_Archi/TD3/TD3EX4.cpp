#include <iostream>
#include <vector>
using namespace std;

int main ()
{
    int const SIZE = 10;
    double tab[SIZE];

    cout << "Enter the tab values (10 values)   >>> ";
    for (int i=0; i<SIZE; i++)
    {
        cin >> *(tab+i);
    }

    cout << endl << "tab:\t";

    for (double *p = tab+4; p<tab+SIZE; p++)
    {
        cout << *p << "  ";
    }
    cout << endl;

    return 0;
}
