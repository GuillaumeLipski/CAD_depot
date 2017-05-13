# CAD_depot

Pour compiler le projet, il faut utiliser Ant.
Une fois la commande ant effectué:
cd bin
java Main

Fonctionnement du jeu :
Après avoir lancé le programme, il faut créer un nouveau niveau en allant dans Fichier/Nouveau
Une fenêtre de selection permettra de choisir l'époque, le mode de tir et la stratégie de chacun des deux joueurs.


Epoques disponibles:
  Moderne (Tous les bateaux ont deux points de vie par case)
  18eme (Les bateaux n'ont qu'un point de vie par case)
  
 Mode de tir disponible:
  Classique (Seulement choix de la case à viser)
  
  Stratégies disponibles:
    Humain (L'utilisateur sera solicité pour chaque choix)
    Aléatoire (Chaque choix sera défini aléatoire sans à priori)
    
Le jeu se termine lorsque la flotte de l'un des deux joueurs est complétement détruite.
Pour relancer une partie, il faut recréer un nouveau niveau.
