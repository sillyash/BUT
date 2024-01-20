#include <iostream>
#include "chocolat.h"
#include "boitechoco.h"
#include <stdlib.h>     /* srand, rand */
#include <time.h>       /* time */
#include <string>
using namespace std;

BoiteChoco::BoiteChoco()
{
    _nom = "classique";
    _contenu = {};
}


BoiteChoco::BoiteChoco(string nom, int nbChoc)
{
    _nom = nom;
    for (int i=0; i<nbChoc; i++)
    {
        _contenu.push_back(Chocolat(20));
    }
}


BoiteChoco::affiche(ostream& os) const
{
    os << _nom << ":" << endl;
    for (int i=0; i<_contenu.size(); i++)
    {
        os << "\t" << _contenu[i] << endl;
    }
}


ostream& operator << (ostream& os, const BoiteChoco& bc)
{
    bc.affiche(os);
    return os;
}

