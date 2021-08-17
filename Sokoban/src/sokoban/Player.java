/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.ArrayList;
import org.sqlite.SQLiteException;
import sokoban.BoardAnalysisPackage.BoardChecker;
import sokoban.BoardBuildingPackage.Board;
import sokoban.BoardBuildingPackage.DataBase;
import sokoban.ExceptionsPackage.BuilderException;
import sokoban.ExceptionsPackage.GamePlayerLeavesException;
import sokoban.PlayerMovesPackage.PlayerMoves;

/**
 * Class representing data about a Player.
 *
 * @author jchampol
 */
public class Player {

    /**
     * The dataBase where the player chooses his board.
     */
    private static DataBase myDatabase;

    /**
     * True if a game is being completed false otherwise.
     */
    public static boolean inGame;

    /**
     * True if a game is being completed false otherwise.
     */
    private static boolean startingMenu = true;

    /**
     * True if a board is currently being choosed.
     */
    private static boolean choosing;

    /**
     * True if the player is currently choosing his assistance mode
     */
    private static boolean choosingAssistance;

    /**
     * True if the player is actually setting his game
     */
    private static boolean settingGame;

    /**
     * While the user is in the sokoban game, is true.
     */
    private static boolean gameLaunched = true;

    /**
     * True if the assistance mode is on.
     */
    public static boolean assisted;

    /**
     * The user's command line entry.
     */
    private static Scanner in = new Scanner(System.in);

    /**
     * This the Board that is being currently completed by the player.
     */
    private static Board currentBoard;

    /**
     * All the moves of the player are stored in this HashSet. So that it's
     * easier to display them in the end of the game.
     */
    public static final ArrayList<PlayerMoves.Moves> allMoves = new ArrayList<PlayerMoves.Moves>();

    /**
     * Main method of the player class.
     *
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    public static void main(String[] args) throws SQLException, GamePlayerLeavesException, BuilderException {

        /**
         * dataBase initialization
         */
        DataBase myNewDataBase = new DataBase();
        myDatabase = myNewDataBase;

        try {
            if (startingMenu) {
                /**
                 * Welcome message
                 */
                System.out.println("_________________________________________________________________");
                System.out.println("                Welcome in Sokoban ! ");
                System.out.println("        _______________________________                            ");
                System.out.println("     ! SOME EXPLANATIONS ABOUT THE GAME LOGIC !                    ");
                System.out.println("   You have to put every boxes on the targets, walls can't be hit. ");
                System.out.println("                                                                   ");
                System.out.println("       ________________________________                     ");
                System.out.println("      ! SOME EXPLANATIONS ABOUT SYMBOLS !                   ");
                System.out.println("  A target is symbolized by the letter 'x'.                 ");
                System.out.println("  The player is symbolized by the letter 'P'                ");
                System.out.println("  A case is symbolized by the letter 'C'.                   ");
                System.out.println("  A wall is symbolized by the letter '#'.                   ");
                System.out.println("  An empty place is symbolized by the symbol '.'.           ");
                System.out.println("       ________________________________                     ");
                System.out.println("       ! SOME EXPLANATIONS ABOUT MOVES !                    ");
                System.out.println("    Type L to move left                                         ");
                System.out.println("    Type R to move right                                        ");
                System.out.println("    Type U to move up                                           ");
                System.out.println("    Type D to move down                                         ");
                System.out.println("       ________________________________                            ");
                System.out.println("            ! GAME MECHANIC EXAMPLE !                              ");
                System.out.println("   To move a case, follow these instructions :                     ");
                System.out.println("   The player is represented by the letter 'P'                     ");
                System.out.println("   Put the player next to the case you want to move in a place     ");
                System.out.println("   such that he is in the opposite direction of the one where      ");
                System.out.println("   he wants to go. Here is a quick example :                       ");
                System.out.println("                                                                   ");
                System.out.println("   If you want to move the box to the LEFT, place the player on    ");
                System.out.println("     the RIGHT side of the box, as it is shown below :             ");
                System.out.println("        x . . . C P .                                              ");
                System.out.println("     here, if you type L, you will have the following result :     ");
                System.out.println("        x . . C P . .                                              ");
                System.out.println("                                                                   ");
                System.out.println("          sometimes the walls will                                 ");
                System.out.println("   block you, here is the main challenge of the game.              ");
                System.out.println("       ________________________________                            ");
                System.out.println("     ! SOME EXPLANATIONS ABOUT THE COMMANDS !                    ");
                System.out.println("    Type /quit to leave the game at any time.                    ");
                System.out.println("    Type /info to read the rules at any time.                    ");
                System.out.println("    Type /abort to leave the party at any time.                  ");
                System.out.println("    Type /clear to clear the screen at any time.                 ");
                System.out.println("    Enjoy your game !                                            ");
                System.out.println("_________________________________________________________________");
                startingMenu = false;
                settingGame = true;
            }

            while (gameLaunched) {
                while (settingGame) {
                    /**
                     * The player chooses his board
                     */
                    allMoves.clear();
                    boardChoosingInterface();
                    assistanceDialog();
                    currentBoard.displayBoard();
                }

                while (inGame) {
                    /* Test du reste */
                    analyseSequence();
                    winOrNotDialog();
                    currentBoard.displayBoard();
                }
            }
        } catch (NullPointerException e) {

        } catch (GamePlayerLeavesException e) {
            System.out.println("Thanks for playing, bye.");
        }
    }

    /**
     * Method used to read the Player entry and to return it.
     *
     * @return the player's entry ignoring case.
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static String readPlayerEntry() throws GamePlayerLeavesException {
        System.out.println("Enter your command here :");
        String returned = in.nextLine().trim();
        if (returned.equalsIgnoreCase("/QUIT")) {
            returned = "";
            quitWithDialog();
        } else if (returned.equalsIgnoreCase("/INFO")) {
            returned = "";
            displayInfo();
        } else if (returned.equalsIgnoreCase("/ABORT")) {
            returned = "";
            goBackToMenu();
        } else if (returned.equalsIgnoreCase("/TRAP")) {
            returned = "";
            if (assisted) {
                PlayerMoves.moveBack(currentBoard);
            } else {
                System.out.println("Assistance has not been turnt on.");
                System.out.println(" ");
            }
        } else if (returned.equalsIgnoreCase("/CLEAR")) {
            returned = "";
            clear();
        }
        return returned;
    }

    /**
     * Method used to analyse the sequence entered by the player and call the
     * corresponding method.
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static void analyseSequence() throws GamePlayerLeavesException {
        for (char actu : readPlayerEntry().toUpperCase().toCharArray()) {
            switch (actu) {
                case 'L':
                    PlayerMoves.moveLeft(currentBoard);
                    break;
                case 'R':
                    PlayerMoves.moveRight(currentBoard);
                    break;
                case 'U':
                    PlayerMoves.moveUp(currentBoard);
                    break;
                case 'D':
                    PlayerMoves.moveDown(currentBoard);
                    break;
            }
        }
    }

    /**
     * Method allowing the player to choose the mode he wants to play on :
     * assisted or not
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static void assistanceDialog() throws GamePlayerLeavesException {
        while (choosingAssistance) {
            try {
                System.out.println("_________________________________________________");
                System.out.println("       ASSISTANCE CHOOSING INTERFACE");
                System.out.println("                                           ");
                System.out.println("  The game assistance will tell you if you ");
                System.out.println("  try to do something that will kill your  ");
                System.out.println(" chances to move the currently moving box again,  ");
                System.out.println(" in case of moves where you feel like you have    ");
                System.out.println("  been traped, you will be able to type : /trap     ");
                System.out.println("       to come back to your previous move,         ");
                System.out.println("_________________________________________________");
                System.out.println("         ! USE YOUR MOVES WISELY !");
                System.out.println("     AND DON'T EVER FORGET TO ENJOY THE GAME      ");
                System.out.println("__________________________________________________");
                System.out.println(" ");
                System.out.println(" Would you like to play with the game assistance ? ");
                System.out.println(" Answer with yes or no please. ");
                System.out.println(" ");

                String entry = readPlayerEntry();
                switch (entry) {
                    case "y":
                    case "yes":
                        clear();
                        assisted = true;
                        choosingAssistance = false;
                        settingGame = false;
                        break;

                    case "n":
                    case "no":
                        clear();
                        assisted = false;
                        choosingAssistance = false;
                        settingGame = false;
                        break;
                    default:
                        System.out.println("Invalid entry, try again please.");
                }
            } catch (GamePlayerLeavesException e) {
                System.out.println("Thanks for playing, bye.");
                choosingAssistance = false;
                startingMenu = false;
                inGame = false;
                settingGame = false;
                gameLaunched = false;
            }
        }
    }

    /**
     * Displays the interface where the player will choose the Board he wants to
     * play on.
     *
     * @throws GamePlayerLeavesException
     * @throws sokoban.ExceptionsPackage.BuilderException
     * @throws org.sqlite.SQLiteException
     */
    public static void boardChoosingInterface() throws GamePlayerLeavesException, BuilderException, SQLiteException {
        choosing = true;
        while (choosing) {
            try {
                System.out.println("___________________________________________");
                System.out.println("       BOARD CHOOSING INTERFACE");
                System.out.println("___________________________________________");
                System.out.println("1. List boards and choose");
                System.out.println("Type 'start' or '1' ('1' not 'I') to enter the menu and choose your board.");

                String entry = readPlayerEntry();
                switch (entry) {
                    case "start":
                    case "1":
                        clear();
                        showExampleBoard();
                        myDatabase.listBoards();
                        System.out.println("The Board id is displayed in the first column ");
                        System.out.println("of the info rectangle displayed above the ");
                        System.out.println("board, here, 'exampleId' is an example of a boardId. ");
                        System.out.println("Don't try to pull it this board isn't playable. ");
                        System.out.println("Board id ?");
                        String boardId = readPlayerEntry();
                        if (boardId.equals("exampleId")) {
                            System.out.println("This board is not playable.");
                        } else {
                            currentBoard = myDatabase.get(boardId);
                            clear();
                            choosingAssistance = true;
                            inGame = true;
                            choosing = false;
                        }
                }
            } catch (SQLiteException | NullPointerException e) {
                System.out.println("Board not found try again please.");
            } catch (GamePlayerLeavesException e) {
                System.out.println("Thanks for playing, bye");
                choosing = false;
                choosingAssistance = false;
                startingMenu = false;
                settingGame = false;
                gameLaunched = false;
            }
        }
    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of winning.
     */
    public static void winOrNotDialog() {
        if (BoardChecker.winCheck(currentBoard)) {
            System.out.println("Congratulations, all the boxes have been put in the right place !");
            System.out.println("Here is your game sequence : " + allMoves.toString());
            settingGame = true;
            inGame = false;
        }
    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of loosing.
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static void quitWithDialog() throws GamePlayerLeavesException {
        throw new GamePlayerLeavesException("The player left the game");
    }

    /**
     * Method used to display the rules of the sokoban game.
     */
    public static void displayInfo() {
        System.out.println("        _______________________________                            ");
        System.out.println("     ! QUICK REMINDER ABOUT THE GAME LOGIC !                       ");
        System.out.println("   You have to put every boxes on the targets, walls can't be hit. ");
        System.out.println("                                                                   ");
        System.out.println("       ________________________________                     ");
        System.out.println("       ! QUICK REMINDER ABOUT SYMBOLS !                     ");
        System.out.println("  A target is symbolized by the letter 'x'.                 ");
        System.out.println("  The player is symbolized by the letter 'P'                ");
        System.out.println("  A case is symbolized by the letter 'C'.                   ");
        System.out.println("  A wall is symbolized by the letter '#'.                   ");
        System.out.println("       ________________________________                     ");
        System.out.println("       ! QUICK REMINDER ABOUT MOVES !                       ");
        System.out.println("    Type L to move left                                         ");
        System.out.println("    Type R to move right                                        ");
        System.out.println("    Type U to move up                                           ");
        System.out.println("    Type D to move down                                         ");
        System.out.println("    You can type your moves one by one :                        ");
        System.out.println("    'L' then press enter, 'R' then press enetr etc ...          ");
        System.out.println("    or you can make a sequence :                                   ");
        System.out.println("    LRDU then press enter                                          ");
        System.out.println("    Both typing modes are working in the same way.                 ");
        System.out.println("       ________________________________                            ");
        System.out.println("          ! GAME MECHANIC REMINDER !                               ");
        System.out.println("   To move a case, follow these instructions :                     ");
        System.out.println("   The player is represented by the letter 'P'                     ");
        System.out.println("   Put the player next to the case you want to move in a place     ");
        System.out.println("   such that he is in the opposite direction of the one where      ");
        System.out.println("   he wants to go. Here is a quick example :                       ");
        System.out.println("                                                                   ");
        System.out.println("   If you want to move the box to the LEFT, place the player on    ");
        System.out.println("     the RIGHT side of the box, as it is shown below :             ");
        System.out.println("        x . . . C P .                                              ");
        System.out.println("     here, if you type L, you will have the following result :     ");
        System.out.println("        x . . C P . .                                              ");
        System.out.println("                                                                   ");
        System.out.println("          sometimes the walls will                                 ");
        System.out.println("   block you, here is the main challenge of the game.              ");
        System.out.println("       ________________________________                            ");
        System.out.println("      ! QUICK REMINDER ABOUT THE COMMANDS !                      ");
        System.out.println("    Type /quit to leave the game at any time.                    ");
        System.out.println("    Type /info to read the rules at any time.                    ");
        System.out.println("    Type /abort to leave the party at any time.                  ");
        System.out.println("    Type /clear to clear the screen at any time.                 ");
        System.out.println("    Enjoy your game !                                            ");
        System.out.println("_________________________________________________________________");
        if (assisted) {
            System.out.println("Assistance is on");
            System.out.println("You can use /trap to go back.");
        } else {
            System.out.println("Assistance is off");
            System.out.println("You can't use /trap to go back.");
        }
        System.out.println("_________________________________________________________________");
    }

    /**
     * Method used when the player desicdes to give up during the game, goes
     * back to board selection
     */
    public static void goBackToMenu() {
        inGame = false;
        settingGame = true;
    }

    /**
     * Method used to clear the console using the windows "cls" command.
     */
    public static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println("Not able to clear the screen ...");
        }
    }

    /**
     * Method used to print an example of board such that the player as an idea
     * of what the Board id is.
     */
    public static void showExampleBoard() {
        System.out.println("_____________________________!EXAMPLE!_________________________________________");
        System.out.println("Board id : " + "     Name : " + "    nb_rows: " + " nb_cols: ");
        System.out.println("exampleId " + "   exampleBoard  " + "   4 " + "       5 ");
        System.out.println("_______________________________________________________________________________");
        System.out.println("exampleBoard");
        System.out.println("  0 1 2 3 4\n"
                + "0 # # # # #\n"
                + "1 # # . P #\n"
                + "2 # x C . #\n"
                + "3 # # # # #");
    }
}
