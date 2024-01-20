#include <iostream>
#include "chocolat.h"
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <string>
using namespace std;

Chocolat::Chocolat()
{
    _nature = CNOIR;
    _poids = POIDS;
}

Chocolat::Chocolat(const string& nature, const int& poids)
{
    _nature = _stringVersNature(nature);
    _poids = poids;
}

Chocolat::Chocolat(const int &poids)
{
    _nature = rand() % 3;
    _poids = poids;
}

string Chocolat::_natureVersString(int n) const
{
    if(n !=0 && n !=1 && n!=2) throw string ("nature incorrecte");
    string s;
    switch(n) {
        case 0: s = "noir"; break;
        case 1: s = "blanc"; break;
        case 2: s = "lait"; break;
    }
    return s;
}


int Chocolat::_stringVersNature(const string& n) const
{   // retourne la nature équivalente à la string
    if (n != NOIR && n != BLANC && n != LAIT) throw string ("nature incorrecte");
    int nature;
    if (n==NOIR) nature = CNOIR;
    else if (n==BLANC) nature = CBLANC;
    else nature = CLAIT;
    return nature;
}


ostream& operator << (ostream& os, const Chocolat& choc)
{
    os << choc.lePoids() << "g, " << choc.laNature();
    return os;
}


bool operator < (const Chocolat& c1, const Chocolat& c2)
{
    return c1.lePoids() < c2.lePoids();
}

