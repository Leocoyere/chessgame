# Chess Game (Java & Next.js)

Un jeu d'échecs développé avec Java pour la logique de jeu et Next.js pour l'interface frontend ( encore en construction ). Le but est de découvrir Java & Next.js.

## Fonctionnalités principales :
- **Logique du jeu :**
  - Implémentation de toutes les pièces d’échecs (Roi, Reine, Pion, Cavalier, Fou, Tour) avec leurs mouvements et règles respectives.
  - Vérification des situations d’échec et de mat.
  - Gestion de la promotion des pions lorsque ceux-ci atteignent le côté opposé du plateau.
  - Système de tour par tour avec la possibilité de déplacer des pièces, capturer des pièces ennemies et effectuer un échec.

- **Interface utilisateur :**
  - Création d'un tableau d’échecs interactif avec Tailwind CSS pour le style et Next.js pour l’architecture du frontend.
  - Gestion des parties avec une interface simple permettant de déplacer les pièces.
  - Affichage dynamique de l'état du jeu (e.g. échec, échec et mat).

## Technologies utilisées :
- **Backend :**
  - Java
- **Frontend :**
  - Next.js
  - Tailwind CSS
  - Redux

## Objectifs à venir :
- **Multijoueur en ligne (PVP) via WebSocket** : Implémentation d'un système de jeu en temps réel pour jouer contre un autre joueur.
- **Ajouter Spring Boot pour gérer le backend** : Implémenter Spring Boot pour la gestion des utilisateurs, l'authentification et exposer des APIs REST pour interagir avec la logique du jeu.
- **Implémentation de l'interface** : Créer une interface intuitive mais minimaliste.
- **Amélioration de l'interface utilisateur** : Ajouter des animations, des notifications d’échec et de mat, etc.
- **Optimisation et tests** : Ajouter des tests unitaires et d'intégration pour vérifier le bon fonctionnement du jeu.

