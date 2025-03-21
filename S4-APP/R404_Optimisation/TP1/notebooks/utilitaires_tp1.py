from typing import Dict, List, Any

from folium import Map, Marker, GeoJson, Icon


class Erreur(Exception):
    """
    Classe Erreur qui hérite de la classe Exception de base.
    """

    def __init__(self, value):
        self.value = value

    def __str__(self):
        return repr(self.value)


def convertir_graphe(graphe: Dict[str, Dict[str, Dict[str, Any]]], mode: str) -> Dict[str, Dict[str, int | float]]:
    """
    Convertit les fichiers de graphe préparés pour le TP 1 pour extraire les informations de temps ou de distance.
    :param graphe: Le dictionnaire avec toutes les informations
    :param mode: Extraire soit le _temps_, soit la _distance_.
    :return: Un dictionnaire de dictionnaires d'adjacence avec le mode demandé comme poids sur l'arête.
    """
    if mode not in ['temps', 'distance']:
        raise Erreur("Le mode doit être soit 'temps', soit 'distance'")
    graphe_f = {}
    for k in graphe.keys():
        ligne = {}
        for k2 in graphe[k].keys():
            ligne[k2] = graphe[k][k2][mode]
        graphe_f[k] = ligne
    return graphe_f


def sommet_adresse(annuaire: dict, adresse: dict) -> Dict[str, str] | None:
    """
    Prend un dictionnaire de sommets du graphe du réseau de routes d'IdF et une adresse partielle ou complète
    et renvoie le ou les sommets correspondants s'ils existent.
    :param annuaire: Dictionnaire de sommets → adresse.
    :param adresse: L'adresse partielle ou complète recherchée sous forme de dictionnaire. Les clés suivantes sont
    possibles : code_postal (optionnel), ville (obligatoire), rue (optionnel)
    :return: Un dictionnaire adresse(s) → sommet(s)
    """

    # On commence par vérifier que la ville (obligatoire) a été renseignée,
    # si elle n'est pas renseignée, on arrête avec une erreur.
    if 'ville' not in adresse:
        raise Erreur('Il faut indiquer le nom d\'une ville, commune ou village')
    # On va filtrer progressivement : le code postal en premier
    cp = ''
    if 'code_postal' in adresse:
        cp = str(adresse['code_postal'])
    if cp not in annuaire:
        # S'il n'existe pas, on arrête avec une erreur
        raise Erreur(f'Code postal {cp} inconnu')
    ville = adresse['ville']
    # Dans le cas où le code postal est vide, on regarde si on trouve une ville qui correspond
    if cp == '':
        dico_villes = {}
        for code in annuaire:
            if ville in annuaire[code]:
                dico_villes.update(annuaire[code][ville])
        if len(dico_villes) == 0:
            # On ne trouve pas de ville avec ce nom : erreur
            raise Erreur(f'Ville {ville} inconnue')
    elif ville not in annuaire[cp]:
        # Pour ce code postal, le nom de la ville ne correspond pas : erreur
        raise Erreur(f'Ville {ville} inconnue')
    else:
        dico_villes = annuaire[cp][ville]
    # On va maintenant filtrer par rue
    rue = ''
    if 'rue' in adresse:
        rue = adresse['rue']
    if rue not in dico_villes:
        # Il n'y a pas cette rue dans cette ville dans notre annuaire : erreur
        raise Erreur(f'Rue {rue} inconnue dans {ville}')
    rues = dico_villes[rue]
    resultats = {}
    for num in rues:
        resultats[f'{num} {rue}, {cp} {ville}'] = str(rues[num])
    return resultats


def visualisation_route(graphe: Dict[str, Dict[str, Dict[str, any]]], sommets: Dict[str, Dict[str, str]],
                        chemin: List[str], chemin2: None | List[str] = None) -> Map:
    """
    Affiche un chemin sur la carte en marquant deux sommets comme départ et arrivée
    :param graphe: Le graphe des routes d'IdF
    :param sommets: Un dictionnaire avec deux entrées : depart et arrivee, chacune associée à un dictionnaire
    à deux entrées : latitude et longitude.
    :param chemin: Une liste de sommets.
    :param chemin2: Une liste de sommets (optionnelle).
    :return: une carte Folium
    """
    depart = chemin[0]
    arrivee = chemin[-1]
    m = Map(location=[48.866667, 2.333333])
    Marker((sommets[depart]['latitude'], sommets[depart]['longitude'])).add_to(m)
    Marker((sommets[arrivee]['latitude'], sommets[arrivee]['longitude']),
           icon=Icon(color='green')).add_to(m)
    ajouter_chemin_carte(m, graphe, chemin)
    if chemin2 is not None:
        ajouter_chemin_carte(m, graphe, chemin2, False)
    m.fit_bounds([[min(sommets[depart]['latitude'], sommets[arrivee]['latitude']),
                   min(sommets[depart]['longitude'], sommets[arrivee]['longitude'])],
                  [max(sommets[depart]['latitude'], sommets[arrivee]['latitude']),
                   max(sommets[depart]['longitude'], sommets[arrivee]['longitude'])]])
    return m


def visualisation_rails(graphe: Dict[str, Dict[str, Dict[str, any]]], sommets: Dict[str, Dict[str, str]],
                        chemin: List[str], chemin2: None | List[str] = None) -> Map:
    """
    Affiche un chemin sur la carte en marquant deux sommets comme départ et arrivée
    :param graphe: Le graphe du réseau ferré d'IdF
    :param sommets: Un dictionnaire avec deux entrées : depart et arrivee, chacune associée à un dictionnaire
    avec une entrée coordonnees contenant une liste des deux coordonnées
    :param chemin: Une liste de sommets.
    :param chemin2: Une liste de sommets (optionnelle).
    :return: une carte Folium
    """
    depart = chemin[0]
    arrivee = chemin[-1]
    m = Map(location=[48.866667, 2.333333])
    Marker((sommets[depart]['coordonnees'][1], sommets[depart]['coordonnees'][0])).add_to(m)
    Marker((sommets[arrivee]['coordonnees'][1], sommets[arrivee]['coordonnees'][0]),
           icon=Icon(color='green')).add_to(m)
    ajouter_chemin_carte(m, graphe, chemin)
    if chemin2 is not None:
        ajouter_chemin_carte(m, graphe, chemin2, False)
    m.fit_bounds([[min(sommets[depart]['coordonnees'][1], sommets[arrivee]['coordonnees'][1]),
                   min(sommets[depart]['coordonnees'][0], sommets[arrivee]['coordonnees'][0])],
                  [max(sommets[depart]['coordonnees'][1], sommets[arrivee]['coordonnees'][1]),
                   max(sommets[depart]['coordonnees'][0], sommets[arrivee]['coordonnees'][0])]])
    return m


def ajouter_chemin_carte(m: Map, graphe: Dict[str, Dict[str, Dict[str, any]]], chemin: List[str], first=True) -> None:
    for pos, s in enumerate(chemin):
        if pos > 0:
            if first:
                GeoJson(graphe[chemin[pos - 1]][s]['geometry']).add_to(m)
            else:
                GeoJson(graphe[chemin[pos - 1]][s]['geometry'],
                        style_function=lambda x: {'color': '#00CC66', 'fillColor': '#00CC66'}).add_to(m)
