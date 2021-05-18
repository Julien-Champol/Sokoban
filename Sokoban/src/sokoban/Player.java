/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.ArrayList;
import sokoban.BoardAnalysisPackage.BoardChecker;
import sokoban.BoardBuildingPackage.Board;
import sokoban.ExceptionsPackage.GamePlayerLeavesException;
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
    private static ArrayList<PlayerMoves.Moves> allMoves = new ArrayList<PlayerMoves.Moves>();

    /**
     * Main method of the player class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Board initialization */
        Board analysed = new Board("TestBoard", 8, 8);
        analysed.addHorizontalWall(0, 0, 8);
        analysed.addHorizontalWall(0, 7, 8);
        analysed.addVerticalWall(7, 0, 8);
        analysed.addVerticalWall(0, 0, 8);
        analysed.addTarget(1, 2);
        analysed.addBox(3, 2);
        Point player = new Point(4, 2);
        analysed.setPlayerPosition(player);
        currentBoard = analysed;
        inGame = true;
        currentBoard.displayBoard();
        try {
            while (inGame) {
                /* Test du reste */
                analyseSequence();
                winDialog();
                currentBoard.displayBoard();
            }
        } catch (GamePlayerLeavesException e) {
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
        System.out.println("Bienvenue, vous pouvez entrer une séquence à jouer ou bien l'instruction /quit pour quitter la partie.");
        System.out.println("Veuillez entrer la commande ici :");
        String returned = in.nextLine().trim().toUpperCase();
        if (returned.equalsIgnoreCase("/QUIT")) {
            quiWithDialog();
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
                    allMoves.add(PlayerMoves.Moves.L);
                    break;
                case 'R':
                    PlayerMoves.moveRight(currentBoard);
                    allMoves.add(PlayerMoves.Moves.R);
                    break;
                case 'U':
                    PlayerMoves.moveUp(currentBoard);
                    allMoves.add(PlayerMoves.Moves.U);
                    break;
                case 'D':
                    PlayerMoves.moveDown(currentBoard);
                    allMoves.add(PlayerMoves.Moves.D);
                    break;
            }
        }
    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of winning.
     */
    public static void winDialog() {
        if (BoardChecker.winCheck(currentBoard)) {
            inGame = false;
            System.out.println("Félicitations, vous avez acheminé toutes les caisses !");
            System.out.println(allMoves.toString());
        }
    }

    /**
     * Method used to display a message and switch the attributes to the good
     * value in case of loosing.
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static void quiWithDialog() throws GamePlayerLeavesException {
        System.out.println("Le joueur a quitté la partie.");
        throw new GamePlayerLeavesException("Le joueur a quitté la partie");
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
