# R4.A11 - Développement pour applications mobile (Android)

Ash MERIENNE

## Parties du TP effectuées

### Parties obligatoires

- [x] Prise en main
- [x] Interface
- [x] Animation de l'interface ([voir notes](#commandes))
- [x] Plusieurs activités
- [x] Threads & réseau
- [ ] Fragments
- [ ] Préférences

### Parties complémentaires

- [x] Plusieurs langues
- [ ] Plusieurs tailles d'écrans
- [x] Réinitialisation des commandes
- [x] Logo de l'application
- [ ] Serveur Android
- [ ] Afficher les messages du serveur dans une pop-up
- [ ] Visualiser les ingrédients sélectionnés
- [ ] Utiliser une liste d'ingrédients au lieu d'un bouton



## Questions de TP

### Plusieurs activités

Observer les messages de la console (onglet ___Run___) :

- en changeant d'activité ;
- en faisant tourner la tablette ;
- en mettant l'application en arrière-plan ;
- en fermant l'application.

**Retrouve-t-on ce qui a été vu pendant le cours ?**

>Oui, on remarque notamment le `onDestroy` sur la rotation d'écran, ce qui explique le bsoin des Bundle pour sauvegarder les données.


## Notes

### Commandes

J'ai choisi d'implémenter les commandes sous forme OOP : c'est-à-dire que j'ai réalisé les classes `Order`, `Product` et leur implémentation dans `MainActivity`.

Pour avoir la persistance courte durée, j'ai tuilisé l'interface `Parcelable` dans la classe `Order`, qui en quelque sorte "sérialise" l'objet dans un `Bundle`.
