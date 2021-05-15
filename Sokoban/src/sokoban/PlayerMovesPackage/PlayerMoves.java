/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.PlayerMovesPackage;

import java.awt.Point;
import sokoban.BoardAnalysisPackage.BoardChecker;
import sokoban.BoardBuildingPackage.Board;

/**
 * Class representing data about the moves the player can do.
 *
 * @author jcdru
 */
public class PlayerMoves {

    public enum Moves {
        L, R, U, D
    };

    /**
     * Method called when the player wants to move on his left.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveLeft(Board theBoard) {
        int x = (int) (theBoard.getPlayerPosition().getX() - 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)) {
            theBoard.setPlayerPosition(newPosition);
        }
    }

    /**
     * Method called when the player wants to move on his right.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveRight(Board theBoard) {
        int x = (int) (theBoard.getPlayerPosition().getX() + 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)) {
            theBoard.setPlayerPosition(newPosition);
        }
    }

    /**
     * Method called when the player wants to move up.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveUp(Board theBoard) {
        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() + 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)) {
            theBoard.setPlayerPosition(newPosition);
        }
    }

    /**
     * Method called when the player wants to move down.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveDown(Board theBoard) {
        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() - 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)) {
            theBoard.setPlayerPosition(newPosition);
        }
    }
}
