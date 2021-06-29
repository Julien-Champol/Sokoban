/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.PlayerMovesPackage;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import sokoban.BoardAnalysisPackage.BoardChecker;
import sokoban.BoardBuildingPackage.Board;
import sokoban.ExceptionsPackage.GamePlayerLeavesException;
import sokoban.Player;
import static sokoban.Player.readPlayerEntry;

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
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    public static void moveLeft(Board theBoard) throws GamePlayerLeavesException {

        // This ArrayList contains the point we'll move when reading the board will be done.
        ArrayList<Point> laterMoves = new ArrayList<Point>();

        int x = (int) theBoard.getPlayerPosition().getX();
        int y = (int) (theBoard.getPlayerPosition().getY() - 1);
        Point newPosition = new Point(x, y);
        
        if (Player.assisted && BoardChecker.trapCaseCheck(theBoard, newPosition)) {
            boolean continueOrNot = true;
            while (continueOrNot) {
                try {
                    System.out.println(" ");
                    System.out.println(" Looks like you are going to loose this case ... Do you really want to make this move ?");
                    System.out.println(" Answer with yes or no please. ");
                    System.out.println(" ");

                    String entry = readPlayerEntry().toLowerCase();
                    switch (entry) {
                        case "y":
                        case "yes":
                            continueOrNot = false;
                            break;

                        case "n":
                        case "no":
                            return;
                        default:
                            System.out.println("Invalid entry, try again please.");
                    }

                } catch (GamePlayerLeavesException e) {
                    System.out.println(e.toString());
                    continueOrNot = false;
                    Player.inGame = false;
                }
            }
        } else {
            System.out.println("Saucisse");
        }
         
        if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.L)) {

            //Adding the first box
            laterMoves.add(newPosition);

            //Adding the other boxes
            boolean serial = true;
            while (serial && y > 0) {
                y--;
                Point newBoxPosition = new Point(x, y);
                if (BoardChecker.movableBoxCheck(theBoard, newBoxPosition, Moves.L)) { //Check if we can move to serialBox
                    laterMoves.add(newBoxPosition);
                } else {
                    serial = false;
                }
            }

            Collections.reverse(laterMoves); //Reversing the list so that we move the boxes in the right order.
            laterMoves.forEach((box) -> {
                Point next = new Point((int) box.getX(), (int) box.getY() - 1);
                theBoard.moveBox(box, next);
            });

            //Finally, moving the player
            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.L)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
        Player.allMoves.add(PlayerMoves.Moves.L);
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
            laterMoves.forEach((box) -> {
                Point next = new Point((int) box.getX(), (int) box.getY() + 1);
                theBoard.moveBox(box, next);
            });

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.R)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
        Player.allMoves.add(PlayerMoves.Moves.R);
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
            laterMoves.forEach((box) -> {
                Point next = new Point((int) box.getX() - 1, (int) box.getY());
                if (BoardChecker.movableBoxCheck(theBoard, box, Moves.U)) {
                    theBoard.moveBox(box, next);
                }
            });

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.U)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
        Player.allMoves.add(PlayerMoves.Moves.U);
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
            laterMoves.forEach((box) -> {
                Point next = new Point((int) box.getX() + 1, (int) box.getY());
                theBoard.moveBox(box, next);
            });

            theBoard.setPlayerPosition(newPosition);
        } else if (BoardChecker.legitMove(theBoard, theBoard.getPlayerPosition(), newPosition)
                && !BoardChecker.movableBoxCheck(theBoard, newPosition, Moves.D)) {
            theBoard.setPlayerPosition(newPosition); // If we don't move no box, then we only move the player.
        }
        Player.allMoves.add(PlayerMoves.Moves.D);
    }
}
