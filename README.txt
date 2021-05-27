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