#include <iostream>
#include <vector>

using namespace std;

/// Constantes
const int NBETUD = 20 ;
const int AFFICHENOTES = 1 ;
const int MOY = 2 ;
const int MAJOR = 3 ;
const int QUIT = 0;

#ifndef GESTIONNOTES_H_
#define GESTIONNOTES_H_


/// procédure calcMoyenne()
/// paramètres: (D)   notes: tableau 2D d'entiers
///             (D/R) moy: tableau de rééls (flottants)
/// calcule les moyennes des étudiants et les enregistre dans la case
/// correspondante du vecteur moy
void calcMoyenne(const vector<vector<int>> &notes, vector<float> &moy);


/// fonction invalide()
/// paramètres: (D) c: entier
/// renvoie vrai si cet entier n’est pas égal à une des constantes NBETUD,
/// AFFICHEETUD, AFFICHENOTES, MOY, MAJOR ou QUIT
bool invalide(int c);


/// fonction menu()
/// paramètres: aucun paramètre
/// demande une valeur à l'utilisateur et renvoie la valeur du choix.
int menu();


/// procédure afficheNotes()
/// paramètres: (D)   notes: tableau 2D d'entiers
///             (D)   etudiants: tableau de strings
/// affiche les notes de chaque étudiant
void afficheNotes(const vector<vector<int>> &notes, const vector<string> &etudiants);


/// procédure afficheMoy()
/// paramètres: (D)   moy: tableau de réels
///             (D)   etudiants: tableau de strings
/// affiche la moyenne de chaque étudiant
void afficheMoy(const vector<float> &moy, const vector<string> &etudiants);


/// fonction meilleurEtud()
/// paramètres: (D)   notes: tableau de réels
///             (D)   etudiants: tableau de strings
/// renvoie l'indice de l'étudiant ayant la meilleure moyenne
int meilleurEtud(const vector<float> &moy, const vector<string> &etudiants);

#endif // GESTIONNOTES_H_
