 # SokobanChampol

Projet Sokoban de Julien CHAMPOL.
Remise avant le pt2.

Avant le 12/05/ Pas de journalisation, réalisation du diagramme de classe.

12/05 Début de la journalisation.
    Utilisation du diagramme de classe pour l'écriture de la signature de toutes les 
    méthodes et de la javadoc. Début de l'implémentation de la partie 1. Maj du diagramme
    de cas d'utilisation.

16/05
    Fin de l'implémentation de la classe PlayerMoves, mais problème de génération de tests.
    Mp mattermost.

17/05
    Junit en version correcte, import de Hamcrest libraries impossible.

18/05
    Import de Hamcrest. Partie 1 commentée, testée, finie.

19/05
    Début lecture fichier.

20/05
    Continuité du jour précédent, les tests ne passent pas.

23/05
    Text Board builder non fonctionnel

24/05
    addRow() fonctionnelle et nettoyée.
    Debugage de addHorizontalWall() et addVerticalWall(), debugage de inTheBoardCheck() (inversion de width et height). 
    Test terminés pour le TextBoardBuilder.

25/05
    FileBoardBuilder démarré.
    FileBoardBuilder terminé, nouveau débugage du TextBoardBuilder, maj du diagramme de classe.
    Tests tous faits et bons, javadoc entière.

26/05
    Début de la partie 3 base de données.
    Pb rencontré : le déplacement d'une série de caisses ne fonctionne pas correctement. Tentative de récurisivité dans 
    movableBoxCheck().

27/05 
    Déplacement réparé. Connection et création de la base de données. Problème avec add.

28/05 
    add semi - fonctionnelle.

29/05
    add fonctionnelle.
    Base de donnée terminée, jeu complétement fonctionnelle.
    Relecture et nettoyage du code. Rédaction du rapport. Remise du Projet.

02/06 
    Refactorisation globale du code.
    Try catch manquant pour la sécurisation des saisies.
    Début de l'ajout de la fonctionnalité abandon quand le joueur ne peut plus gagner.

08/07
    Jeu sécurisé, ajout des cas limites de trapcase à la suite des dernières semaines. 

19/07
    Tous les coups menant à une défaite sont détectés par l'assistance de jeu.

24/07
    Suppression de mustGetTraped code trop complexe à cause des cas limites, 
    choix d'une méthode plus simple de retour arrière en cas de blocage du joueur,
    volonté ajout TODO : /trap pour retour arrière
                         /info pour informations en cours de partie
                         /giveup pour abandonner la partie : retour au choix du board

04/08 
    /giveup remplacé par /abort, /trap semble fonctionnel, tests validés.
    reconnaissance du player a sauté, réparé.
    /trap : cas limites étranges :  Mauvaise conception de la classe player, réglé.

05/08
    Retrait de case contre mur possible à nouveau. Possible après un seul force push.
    Code trop problématique, reconstruction d'un fonctionnement plus simple à partir d'une version antérieure.
    Code fonctionnel et mieux optimisé.

16/08
    Script batch pour appel depuis ligne de commande et ajout du chemin absolu au code.

19/08 
    Problème de lancement sur amchine distante, tentative résolution problème + modification chemins d'accès.
