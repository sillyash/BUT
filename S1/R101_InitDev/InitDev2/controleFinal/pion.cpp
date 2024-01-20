#include <iostream>
#include "pion.h"

void  Pion::affiche()const{
  cout << "pion de couleur " << _couleur << endl;
}

Pion::Pion(const char& couleur)
{
    bool coulExiste = false;

    for (int i=0; i<COULEURS.size(); i++)
    {
        if (couleur == COULEURS.at(i)) coulExiste = true;
    }
    if (coulExiste) _couleur = couleur;
    // si la couleur n'existe pas dans COULEURS
    else throw invalid_argument("La couleur specifiee n'existe pas.");
}


bool operator == (const Pion& p1, const Pion& p2)
{
    return p1.getCouleur() == p2.getCouleur();
}
