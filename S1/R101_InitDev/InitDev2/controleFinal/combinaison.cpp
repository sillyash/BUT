#include <iostream>
#include "combinaison.h"
#include "pion.h"

Pion Combinaison::getPion(int indice)const{
  return _tab.at(indice);
}
 int Combinaison::getDifficulte()const{
   return _tab.size();
 }

bool Combinaison::compatibles(const Combinaison &c)const{
  return getDifficulte() == c.getDifficulte();
}

void  Combinaison::affiche ()const{
  cout << "Difficulte " << getDifficulte() << endl;
  for(int i=0;i<_tab.size();i++)
    _tab[i].affiche();
}


Combinaison::Combinaison(const int& difficulte)
{
    Pion p;
    char couleur;
    for (int i=0; i<difficulte; i++)
    {
        // rand()%5 renvoie une valeur entre 0 et 4
        couleur = COULEURS[rand()%5];
        p = Pion(couleur);
        _tab.push_back(p);
    }
}

bool operator == (const Combinaison& c1, const Combinaison& c2)
{
    // on regarde deja si les vecteurs ont la meme taille
    if (c1.getDifficulte() != c2.getDifficulte()) return false;

    // on parcourt les vecteurs de pions.
    for (int i=0; i<c1.getDifficulte(); i++)
    {
        if (!(c1.getPion(i) == c2.getPion(i))) return false;
    }
    return true;
}


void Combinaison::modifPion(const int& indPion, const Pion& pion)
{
    if (indPion <= getDifficulte() && indPion >= 0) _tab.at(indPion) = pion;
    else throw invalid_argument("L'indice demande n'existe pas.");
}


istream& operator >> (istream& is, Combinaison& c)
{
    char couleur;
    int difficulte;
    Pion pion;

    cout << "Entrez une combinaison (longueur, et pour chaque Pion, sa couleur)\n>>> ";

    is >> difficulte;
    c = Combinaison(difficulte);

    for (int i=0; i<difficulte; i++)
    {
        cout << "Pion " << i << ": ";
        is >> couleur;
        pion = Pion(couleur);
        c.modifPion(i,pion);
    }

    return is;
}

