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
    private static boolean startingMenu;

    /**
     * True if a board is currently being choosed.
     */
    private static boolean choosing;

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

        /**
         * The player chooses his board
         */
        boardChoosingInterface();

        try {
            if (startingMenu) {
                /**
                 * Welcome message
                 */
                System.out.println("_________________________________________________________________");
                System.out.println("Welcome in Sokoban ! ");
                System.out.println("You have to put every boxes on the targets, walls can't be hit.");
                System.out.println("Type L to move left");
                System.out.println("Type R to move right");
                System.out.println("Type U to move up");
                System.out.println("Type D to move down");
                System.out.println("Type /quit to leave the game at any time.");
                System.out.println("Enjoy your game !");
                System.out.println("_________________________________________________________________");
                assistanceDialog();
                currentBoard.displayBoard();
            }

            while (inGame) {
                /* Test du reste */
                analyseSequence();
                winOrNotDialog();
                currentBoard.displayBoard();
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
        String returned = in.nextLine().trim().toUpperCase();
        if (returned.equalsIgnoreCase("/QUIT")) {
            quitWithDialog();
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
        for (char actu : readPlayerEntry().toCharArray()) {
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
        boolean choosingAssistance = true;
        while (choosingAssistance) {
            try {
                System.out.println("___________________________________________");
                System.out.println("       ASSISTANCE CHOOSING INTERFACE");
                System.out.println("                                           ");
                System.out.println("  The game assistance will tell you if you ");
                System.out.println("  try to do something that will kill your  ");
                System.out.println("              winning chances,  ");
                System.out.println("___________________________________________");
                System.out.println(" ");
                System.out.println(" Would you like to play with the game assistance ? ");
                System.out.println(" Answer with yes or no please. ");
                System.out.println(" ");

                String entry = readPlayerEntry().toLowerCase();
                switch (entry) {
                    case "y":
                    case "yes":
                        assisted = true;
                        choosingAssistance = false;
                        break;

                    case "n":
                    case "no":
                        assisted = false;
                        choosingAssistance = false;
                        break;
                    default:
                        System.out.println("Invalid entry, try again please.");
                }
            } catch (GamePlayerLeavesException e) {
                System.out.println(e.toString());
                choosingAssistance = false;
                startingMenu = false;
                inGame = false;
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

                String entry = readPlayerEntry();
                switch (entry) {
                    case "1":
                        myDatabase.listBoards();
                        System.out.println("Board id ?");
                        String boardId = readPlayerEntry().toLowerCase();
                        currentBoard = myDatabase.get(boardId);
                        inGame = true;
                        startingMenu = true;
                        choosing = false;
                }
            } catch (SQLiteException | NullPointerException e) {
                System.out.println(e.toString());
                System.out.println("Board not found try again please");
            } catch (GamePlayerLeavesException e) {
                System.out.println(e.toString());
                choosing = false;
                startingMenu = false;
            }
        }
    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of winning.
     */
    public static void winOrNotDialog() {
        if (BoardChecker.winCheck(currentBoard)) {
            inGame = false;
            System.out.println("Congratulations, all the boxes have been put in the right place !");
            System.out.println("Here is your game sequence : " + allMoves.toString());
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
}
