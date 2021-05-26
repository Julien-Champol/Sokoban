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
        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() - 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.L)) {
            int z = y - 1; // The box has new coordinates
            Point newBoxPosition = new Point(x, z);
            theBoard.moveBox(newPosition, newBoxPosition); //We move the box.
            //TEMP
            boolean serial = true;
            while (z >= 0 && serial) { //Loop to move the serial boxes 
                int a = z - 1;
                Point serialBox = new Point(x, a);
                if (BoardChecker.movableBoxCheck(theBoard, serialBox, Moves.L)) {
                    theBoard.moveBox(newBoxPosition, serialBox);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.L)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
    }

    /**
     * Method called when the player wants to move on his right.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveRight(Board theBoard) {
        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() + 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.R)) {
            int z = y + 1; // The box has new coordinates
            Point newBoxPosition = new Point(x, z);
            theBoard.moveBox(newPosition, newBoxPosition); //We move the box.

            boolean serial = true;
            while (z < theBoard.getWidth() && serial) {
                int a = z + 1;
                Point serialBox = new Point(x, a);
                if (BoardChecker.movableBoxCheck(theBoard, serialBox, Moves.R)) {
                    theBoard.moveBox(newBoxPosition, serialBox);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.R)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
    }

    /**
     * Method called when the player wants to move up.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveUp(Board theBoard) {
        int x = (int) (theBoard.getPlayerPosition().getX() - 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.U)) {
            int a = x - 1; // The box has new coordinates
            Point newBoxPosition = new Point(a, y);
            theBoard.moveBox(newPosition, newBoxPosition); //We move the box.

            boolean serial = true;
            while (a >= 0 && serial) {
                int b = a - 1;
                Point serialBox = new Point(x, b);
                if (BoardChecker.movableBoxCheck(theBoard, serialBox, Moves.U)) {
                    theBoard.moveBox(newBoxPosition, serialBox);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.U)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
    }

    /**
     * Method called when the player wants to move down.
     *
     * @param theBoard the Board the player is completing
     */
    public static void moveDown(Board theBoard) {
        int x = (int) (theBoard.getPlayerPosition().getX() + 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.D)) {
            int a = x + 1; // The box has new coordinates
            Point newBoxPosition = new Point(a, y);
            theBoard.moveBox(newPosition, newBoxPosition); //We move the box.

            boolean serial = true;
            while (a < theBoard.getHeight() && serial) {
                int b = a + 1;
                Point serialBox = new Point(x, b);
                if (BoardChecker.movableBoxCheck(theBoard, serialBox, Moves.D)) {
                    theBoard.moveBox(newBoxPosition, serialBox);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.D)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
    }
}
