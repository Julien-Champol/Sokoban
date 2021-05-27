/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.PlayerMovesPackage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

        ArrayList<Point> laterMoves = new ArrayList<Point>();

        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() - 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.L)) {
            //int z = y - 1; // The box has new coordinates
            //Point newBoxPosition = new Point(x, z);
            //theBoard.moveBox(newPosition, newBoxPosition); //We move the box.
            //Loop to add the box to move to the container

            //Adding the first box
            laterMoves.add(newPosition);
            //Adding the other boxes
            boolean serial = true;
            while (serial && y >= 0) {
                y--;
                Point newBoxPosition = new Point(x, y);
                if (BoardChecker.movableBoxCheck(theBoard, newBoxPosition, Moves.L)) { //Check if we can move to serialBox
                    laterMoves.add(newBoxPosition);
                    Point serialBox = new Point(x, y);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            Collections.reverse(laterMoves);
            for (Point box : laterMoves) {
                Point next = new Point((int) box.getX(), (int) box.getY() - 1);
                theBoard.moveBox(box, next);
            }

            //Finally, moving the player
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

        ArrayList<Point> laterMoves = new ArrayList<Point>();

        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() + 1);
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.R)) {

            laterMoves.add(newPosition);

            boolean serial = true;
            while (serial && y < theBoard.getWidth()) {
                y++;
                Point newBoxPosition = new Point(x, y);
                if (BoardChecker.movableBoxCheck(theBoard, newBoxPosition, Moves.R)) {
                    laterMoves.add(newBoxPosition);
                    Point serialBox = new Point(x, y);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            Collections.reverse(laterMoves);
            for (Point box : laterMoves) {
                Point next = new Point((int) box.getX(), (int) box.getY() + 1);
                theBoard.moveBox(box, next);
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

        ArrayList<Point> laterMoves = new ArrayList<Point>();

        int x = (int) (theBoard.getPlayerPosition().getX() - 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.U)) {
            laterMoves.add(newPosition);

            boolean serial = true;
            while (serial && y < theBoard.getWidth()) {
                x--;
                Point newBoxPosition = new Point(x, y);
                if (BoardChecker.movableBoxCheck(theBoard, newBoxPosition, Moves.U)) {
                    laterMoves.add(newBoxPosition);
                    Point serialBox = new Point(x, y);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            Collections.reverse(laterMoves);
            for (Point box : laterMoves) {
                Point next = new Point((int) box.getX() - 1, (int) box.getY());
                theBoard.moveBox(box, next);
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

        ArrayList<Point> laterMoves = new ArrayList<Point>();

        int x = (int) (theBoard.getPlayerPosition().getX() + 1);
        int y = (int) theBoard.getPlayerPosition().getY();
        Point newPosition = new Point(x, y);
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.D)) {
            laterMoves.add(newPosition);

            boolean serial = true;
            while (serial && y < theBoard.getWidth()) {
                x++;
                Point newBoxPosition = new Point(x, y);
                if (BoardChecker.movableBoxCheck(theBoard, newBoxPosition, Moves.D)) {
                    laterMoves.add(newBoxPosition);
                    Point serialBox = new Point(x, y);
                    newBoxPosition = serialBox;
                } else {
                    serial = false;
                }
            }

            Collections.reverse(laterMoves);
            for (Point box : laterMoves) {
                Point next = new Point((int) box.getX() + 1, (int) box.getY());
                theBoard.moveBox(box, next);
            }

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.D)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
    }
}
