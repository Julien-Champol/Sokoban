/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

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
                System.out.println("Welcome in Sokoban ! ");
                System.out.println("You have to put every boxes on the targets, walls can't be hit.");
                System.out.println("        _______________________________");
                System.out.println("To move a case, follow these instructions :");
                System.out.println("The player is represented by the letter 'P'");
                System.out.println("Put the player next to the case you want to move and try one ");
                System.out.println("of the list's game commands, sometimes the walls will  ");
                System.out.println("block you, here is the main challenge of the game.");
                System.out.println("  A target is symbolized by the letter 'x'.                 ");
                System.out.println("        _______________________________");
                System.out.println("Type L to move left");
                System.out.println("Type R to move right");
                System.out.println("Type U to move up");
                System.out.println("Type D to move down");
                System.out.println("Type /quit to leave the game at any time.");
                System.out.println("Type /info to read the rules at any time.");
                System.out.println("Type /abort to leave the party at any time.");
                System.out.println("Enjoy your game !");
                System.out.println("_________________________________________________________________");
                startingMenu = false;
                settingGame = true;
            }

            while (gameLaunched) {
                while (settingGame) {
                    /**
                     * The player chooses his board
                     */
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
        } catch (GamePlayerLeavesException | NullPointerException e) {
            e.toString();
        }
    }

    /**
     * Method used to read the Player entry and to return it.
     *
     * @return the player's entry to uppercase.
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static String readPlayerEntry() throws GamePlayerLeavesException {
        System.out.println("Enter your command here :");
        String returned = in.nextLine().trim();
        if (returned.equalsIgnoreCase("/QUIT")) {
            quitWithDialog();
        } else if (returned.equalsIgnoreCase("/INFO")) {
            displayInfo();
        } else if (returned.equalsIgnoreCase("/ABORT")) {
            goBackToMenu();
        } else if (returned.equalsIgnoreCase("/TRAP")) {
            if (assisted) {
                PlayerMoves.moveBack(currentBoard);
            }
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
                System.out.println("__________________________________________________");
                System.out.println(" ");
                System.out.println(" Would you like to play with the game assistance ? ");
                System.out.println(" Answer with yes or no please. ");
                System.out.println(" ");

                String entry = readPlayerEntry();
                switch (entry) {
                    case "y":
                    case "yes":
                        assisted = true;
                        choosingAssistance = false;
                        settingGame = false;
                        break;

                    case "n":
                    case "no":
                        assisted = false;
                        choosingAssistance = false;
                        settingGame = false;
                        break;
                    default:
                        System.out.println("Invalid entry, try again please.");
                }
            } catch (GamePlayerLeavesException e) {
                System.out.println(e.toString());
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
                System.out.println("Type '1' to enter the menu and choose your board.");

                String entry = readPlayerEntry();
                switch (entry) {
                    case "1":
                        myDatabase.listBoards();
                        System.out.println("The Board id is displayed in the first column.");
                        System.out.println("Board id ?");
                        String boardId = readPlayerEntry();
                        currentBoard = myDatabase.get(boardId);
                        choosingAssistance = true;
                        inGame = true;
                        choosing = false;
                }
            } catch (SQLiteException | NullPointerException e) {
                System.out.println(e.toString());
                System.out.println("Board not found try again please");
            } catch (GamePlayerLeavesException e) {
                System.out.println(e.toString());
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
        System.out.println("_________________________________________________________________");
        System.out.println("You have to put every boxes on the targets, walls can't be hit.");
        System.out.println("        _______________________________");
        System.out.println("To move a case, follow these instructions :");
        System.out.println("The player is represented by the letter 'P'");
        System.out.println("Put the player next to the case you want to move and try one ");
        System.out.println("of the list's game commands, sometimes the walls will  ");
        System.out.println("block you, here is the main challenge of the game.");
        System.out.println("  A target is symbolized by the letter 'x'.                 ");
        System.out.println("        _______________________________");
        System.out.println("Type L to move left");
        System.out.println("Type R to move right");
        System.out.println("Type U to move up");
        System.out.println("Type D to move down");
        System.out.println("Type /quit to leave the game at any time.");
        if (assisted) {
            System.out.println("Assistance is on");
        } else {
            System.out.println("Assistance is off");
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
}
