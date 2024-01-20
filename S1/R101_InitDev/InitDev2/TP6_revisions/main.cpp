#include <iostream>
#include <string>
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include "chocolat.h"
#include "boitechoco.h"
using namespace std;

int main()
{
    srand(time(NULL));

    Chocolat c1("blanc", 20);    // chocolat blanc de 20g
    Chocolat c2;                // chocolat noir de 30g
    Chocolat c3(10);            // chocolat de 10g, nature tirée au sort
    Chocolat c4("blanc") ;      // chocolat blanc de 30g

    cout << c1 << endl;
    cout << c2 << endl;
    cout << c3 << endl;
    cout << c4 << endl;

    cout << endl << "c1 plus leger que c2: " << (c1 < c2) << endl << endl;

    BoiteChoco b1("Test",4);

    cout << b1 << endl;

    return 0;
}
