// HB 2022
/// classes Appartement et Piece
/// Fichier appartement.cpp

#include <vector>
#include <iostream>
#include "piece.h"
#include "appartement.h"
#include <string>
#include <cmath>


/// Constructeur vide : permet de construire un objet Appartement sans aucune pièce avec une adresse par défaut
Appartement::Appartement(){
    _adresse=ADRESSE;
}

/// Constructeur : permet de construire un appartement avec une premiere piece passee en parametre
Appartement::Appartement ( const Piece & p, const string& ad){
  _lesPieces.push_back(p);
  _adresse=ad;
 }

/// affichage : affiche les différentes pièces d'un appartement
void Appartement::afficher()const{
  if(_lesPieces.size()==0)
    cout<< "L'appartement est encore en construction... " << endl;
  else{
      cout << "un appartement de " << _lesPieces.size() << " pieces"<<endl;
      for(int i=0; i<_lesPieces.size(); i++) {
            cout << i << ".\t";
        _lesPieces[i].afficher();
      }
  }
}

/// ajoutPiece : ajouter une pièce à un appartement
void Appartement::ajoutPiece(const Piece& p){
  _lesPieces.push_back(p);  /// _lesPieces est un vector, ajout en dernière position
}

/// retraitPiece : supprime une pièce à un indice donné
Piece Appartement::retraitPiece(int i){
  //cout << "Appel retraitPiece avec i=" << i ;
  //cout << "   dans un appartement de " << _lesPieces.size() << " pieces" << endl;
  /// pour déclencher l'exception (protéger les bornes) il faut utiliser at et pas []
  Piece resul(_lesPieces.at(i));
  // on remplacee la piece i par la dernière piece, puis on retire la derniere piece
  _lesPieces[i]=_lesPieces.back();
  _lesPieces.pop_back();
  /// On pourrait utiliser les iterateurs (pas étudiés lors du 1er module)
  /// begin : fonction qui retourne un iterateur sur le début du Vector
  /// _lesPieces.erase(_lesPieces.begin()+i)
  return resul;
}

/// surface : retourne la superficie de l'appartement
float Appartement::surface ( ) const{
  float s = 0 ;
  for(int i=0; i<_lesPieces.size(); i++){
    s += _lesPieces[i].surface();
  }
  return s ;
}

/// plusGrandePiece : retourne l'indice de la plus grande pièce,
/// -1 si appartement vide
int Appartement::plusGrandePiece() const{
  int pgp = -1 ;
  if (_lesPieces.size() > 0 ){
    pgp = 0 ;
    for (int i=1; i<_lesPieces.size(); i++)
      if (_lesPieces[i].surface() > _lesPieces.at(pgp).surface())
	pgp = i ;
  }
  return pgp ;
}

/// ------- compare 2 appartements sur leur surface ---
int Appartement::compare(const Appartement& a)const{
  int resul;
  float surf1,surf2;
  surf1=surface();
  surf2=a.surface();
  if (surf1<surf2)resul =  -1;
  else  if (surf1>surf2)resul= 1;
  else resul= 0;
  return resul;
}

// retourne le nombre de pieces d'un appartement
int Appartement::nbPieces()const{
  return _lesPieces.size();
}

int Appartement::searchRoom(const string& name)const {
    int index;
    int max_bound = _lesPieces.size();
    int min_bound = 0;

    if (_lesPieces.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (_lesPieces[index].nom() == name) {
            return index;
        }
        else if (_lesPieces[index].nom() > name) {
            max_bound = index;
        }
        else if (_lesPieces[index].nom() < name) {
            min_bound = index+1;
        }
    }
    while(min_bound < max_bound);
    return -1;
}

int Appartement::searchFirst(const string& name)const {
    int index;
    int max_bound = _lesPieces.size();
    int min_bound = 0;

    if (_lesPieces.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (_lesPieces[index].nom() == name) {
            // check if index-1 is still a correct index
            if ((index-1 >= 0) && (_lesPieces[index-1].nom() == name)) {
                max_bound = index;
            }
            else return index;
        }
        else if (_lesPieces[index].nom() > name) {
            max_bound = index;
        }
        else if (_lesPieces[index].nom() < name) {
            min_bound = index+1;
        }
    }
    while(min_bound != max_bound);
    return -1;
}


int Appartement::searchRoom(const int& area)const {
    int index;
    int max_bound = _lesPieces.size();
    int min_bound = 0;

    if (_lesPieces.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (_lesPieces[index].surface() == area) {
            return index;
        }
        else if (_lesPieces[index].surface() > area) {
            max_bound = index;
        }
        else if (_lesPieces[index].surface() < area) {
            min_bound = index+1;
        }
    }
    while(min_bound < max_bound);
    return -1;
}


int Appartement::searchFirst(const int& area)const {
    int index;
    int max_bound = _lesPieces.size();
    int min_bound = 0;

    if (_lesPieces.size() == 0) {
        throw invalid_argument("Your vector is empty.");
    }

    do {
        index = min_bound + floor((max_bound - min_bound)/2);
        if (_lesPieces[index].surface() == area) {
            // check if index-1 is still a correct index
            if ((index-1 >= 0) && (_lesPieces[index-1].surface() == area)) {
                max_bound = index;
            }
            else return index;
        }
        else if (_lesPieces[index].surface() > area) {
            max_bound = index;
        }
        else if (_lesPieces[index].surface() < area) {
            min_bound = index+1;
        }
    }
    while(min_bound != max_bound);
    return -1;
}


void Appartement::insertionSort()
{
    Piece temp;
    // sort by area
    for (int i=1; i < _lesPieces.size(); i++) {
        for (int j=0; j < i; j++) {
            if (_lesPieces[j].plusGrande(_lesPieces[i])) {
                temp = _lesPieces[j];
                _lesPieces[j] = _lesPieces[i];
                _lesPieces[i] = temp;
            }
        }
    }
}

