/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.HashSet;
import java.util.Scanner;
import sokoban.BoardBuildingPackage.Board;
import sokoban.PlayerMovesPackage.PlayerMoves;

/**
 * Class representing data about a Player.
 *
 * @author jchampol
 */
public class Player {

    /**
     * True if a game is being completed false otherwise.
     */
    private static boolean inGame;
    /**
     * The user's command line entry.
     */
    private static Scanner in = new Scanner(System.in);

    /**
     * This the Board that is being currently completed by the player.
     */
    private static Board currentBoard;
    /**
     * The HashSet is containing all the Boards that will be played during the
     * game.
     */
    private HashSet gameBoards = new HashSet<Board>();

    /**
     * All the moves of the player are stored in this HashSet. So that it's
     * easier to display them in the end of the game.
     */
    private HashSet allMoves = new HashSet<PlayerMoves>();

    /**
     * Main method of the player class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board testNuméro1 = new Board("Test.", 6, 6);
        testNuméro1.addBox(0, 3);
        testNuméro1.addBox(0, 2);
        testNuméro1.addHorizontalWall(0, 4, 3);
        testNuméro1.displayBoard();
    }

    /**
     * Method used to read the Player entry and to return it.
     *
     * @return the player's entry to uppercase.
     */
    public static String readPlayerEntry() {
        return "";
    }

    /**
     * Method used to analyse the sequence entered by the player and call the
     * corresponding method.
     */
    public static void analyseSequence() {

    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of winning.
     */
    public static void winDialog() {

    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of loosing.
     */
    public static void quiWithDialog() {
        //NOTE : Quit will be turnt into QUIT with the sequence analysis method.
    }

    /**
     * Method used to switch to the nextBoard if the current one is complete.
     *
     * @return the next board the player will complete
     */
    public static Board nextBoard() {
        Board a = new Board(" ", 0, 0);
        return a;
    }

}
