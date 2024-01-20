#ifndef BOITECHOCO_H
#define BOITECHOCO_H
#include <vector>
#include <string>
#include <iostream>
using namespace std;

class BoiteChoco{
private:
    string _nom; // nom de la boite
    vector<Chocolat> _contenu;   // les Chocolats contenus dans la boite
public :
    BoiteChoco();
    BoiteChoco(string, int);
    affiche(ostream&) const;
};

ostream& operator << (ostream&, const BoiteChoco&);

#endif // BOITECHOCO_H
