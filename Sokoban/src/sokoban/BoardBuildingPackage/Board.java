/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Class representing data about a game board.
 *
 * @author jchampol
 */
public class Board {

    /**
     * So that we don't have to use the system.out.println :
     */
    private static final PrintStream out = System.out;

    /**
     * A short sentence describing the board.
     */
    private final String DESCRIPTION;

    /**
     * The width of the board
     */
    private int width;

    /**
     * The height of the board
     */
    private int height;

    /**
     * The player's position on the board
     */
    private Point playerPosition;

    /**
     * A list of all the winning positions, the one containing targets.
     */
    private ArrayList<Point> winningPositions = new ArrayList<Point>();

    /**
     * A list of all the walls' positions, the points that cannot be reached.
     */
    private ArrayList<Point> wallPositions = new ArrayList<Point>();

    /**
     * The list of all the boxs' positions, the ones that can be moved.
     */
    private ArrayList<Point> boxPositions = new ArrayList<Point>();

    /**
     * Parameterized constructor of the Board class.
     *
     * @param description the description of the board : simple, hard ...
     * @param width the width of the board.
     * @param height the height of the board.
     */
    public Board(String description, int width, int height) {
        this.DESCRIPTION = description;
        this.width = width;
        this.height = height;
        playerPosition = new Point(-1, -1); //Initialized in  aposition that doesn't exist, conevntionnally.
    }

    /**
     * Method used to set the width of the board, useful in the text board
     * builder where we don't know the width of the Board in the beginning.
     *
     * @param newWidth the new width of the board
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
    }

    /**
     * Return the width of the actual board.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Method used to set the height of the board, useful in the text board
     * builder where we don't know the height of the Board in the beginning.
     *
     * @param newHeight the new height of the board.
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * Return the height of the actual board.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    // The accessors :
    /**
     * Method returning a copy of winning positions, useful in the BoardChecker
     * class. We don't want to be able to modify this list outside of the class.
     *
     * @return a copy of winningPositions ArrayList.
     */
    public ArrayList<Point> getWinningpositions() {
        return (ArrayList<Point>) this.winningPositions.clone();
    }

    /**
     * Method returning a copy of BoxPositions, useful in the BoardChecker
     * class. We don't want to be able to modify this list outside of the class.
     *
     * @return a copy of boxPositions ArrayList.
     */
    public ArrayList<Point> getBoxPositions() {
        return (ArrayList<Point>) this.boxPositions.clone();
    }

    /**
     * Method returning a copy of wallPositions, useful in the BoardChecker
     * class.
     *
     * @return a copy of wallPositions ArrayList.
     */
    public ArrayList<Point> getWallPositions() {
        return (ArrayList<Point>) this.wallPositions.clone();
    }

    /**
     * Method the current player's position.
     *
     * @return a point, the position of the player.
     */
    public Point getPlayerPosition() {
        return this.playerPosition;
    }

    /**
     * Method returning the despcription of the board.
     *
     * @return a string, the description
     */
    public String getDescription() {
        return this.DESCRIPTION;
    }

    /**
     * Method adding a length sized horizontal wall from a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     * @param length the size of the wall
     */
    public void addHorizontalWall(int x, int y, int length) {
        Point startingPoint = new Point(x, y);
        if (this.inTheBoardCheck(startingPoint) && !startingPoint.equals(playerPosition) && (winningPositions.isEmpty()
                || boxPositions.isEmpty()) || !winningPositions.contains(startingPoint)
                && !boxPositions.contains(startingPoint) && !startingPoint.equals(playerPosition)) {
            wallPositions.add(startingPoint);
        } else {
            out.println("Cette position est déjà occupée.");
        }
        for (int i = 1; i <= length; i++) {
            Point newPoint = new Point(x, y + i);
            if (this.inTheBoardCheck(newPoint) && !newPoint.equals(playerPosition) && (winningPositions.isEmpty()
                    || boxPositions.isEmpty()) || !winningPositions.contains(newPoint)
                    && !boxPositions.contains(newPoint) && !newPoint.equals(playerPosition)) {
                wallPositions.add(newPoint);
            }
        }
    }

    /**
     * Method adding a length sized vertical wall from a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     * @param length the size of the wall
     */
    public void addVerticalWall(int x, int y, int length) {
        Point startingPoint = new Point(x, y);
        if (this.inTheBoardCheck(startingPoint) && !startingPoint.equals(playerPosition) && (winningPositions.isEmpty()
                || boxPositions.isEmpty()) || !winningPositions.contains(startingPoint)
                && !boxPositions.contains(startingPoint) && !startingPoint.equals(playerPosition)) {
            wallPositions.add(startingPoint);
        } else {
            out.println("Cette position est déjà occupée.");
        }
        for (int i = 1; i <= length; i++) {
            Point newPoint = new Point(x + i, y);
            if (this.inTheBoardCheck(newPoint) && !startingPoint.equals(playerPosition) && (winningPositions.isEmpty() || boxPositions.isEmpty())
                    || !winningPositions.contains(newPoint) && !boxPositions.contains(newPoint) && !newPoint.equals(playerPosition)) {
                wallPositions.add(newPoint);
            }
        }
    }

    /**
     * Method adding a box at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void addBox(int x, int y) {
        Point boxPoint = new Point(x, y);
        if (this.inTheBoardCheck(boxPoint) && (winningPositions.isEmpty() || wallPositions.isEmpty())
                || !winningPositions.contains(boxPoint) && !wallPositions.contains(boxPoint)) {
            boxPositions.add(boxPoint);
        }
    }

    /**
     * Method moving a box to a given point.
     *
     * @param toMove starting point
     * @param moved destination point
     */
    public void moveBox(Point toMove, Point moved) {
        if (this.inTheBoardCheck(toMove) && this.inTheBoardCheck(moved) && ((wallPositions.isEmpty()
                || winningPositions.isEmpty()) && !boxPositions.isEmpty()) || !wallPositions.contains(toMove)
                && !winningPositions.contains(toMove) && !wallPositions.contains(moved)) { // No !winningPositions.contains(moved), the player can move a box to a winning pos.
            boxPositions.set(boxPositions.indexOf(toMove), moved);
        }
    }

    /**
     * Method adding a target at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void addTarget(int x, int y) {
        Point newTarget = new Point(x, y);
        if (this.inTheBoardCheck(newTarget) && (boxPositions.isEmpty() || wallPositions.isEmpty()) || !boxPositions.contains(newTarget)
                && !wallPositions.contains(newTarget)) {
            winningPositions.add(newTarget);
        }
    }

    /**
     * Method seting the player's initial position at a given point.
     *
     * @param newPosition the new Point.
     */
    public void setPlayerPosition(Point newPosition) {
        if (this.inTheBoardCheck(newPosition) && (winningPositions.isEmpty() || wallPositions.isEmpty() || boxPositions.isEmpty())
                || (!wallPositions.contains(newPosition) && !boxPositions.contains(newPosition))) {
            playerPosition.setLocation(newPosition);
        }
    }

    /**
     * Method checking the presence of a Point in the board.
     *
     * @param check the point we want to analyse
     * @return true iff the point is in the board, false otherwise
     */
    public boolean inTheBoardCheck(Point check) {
        return (check.getX() < this.height && check.getY() < this.width);
    }

    /**
     * Method used to display the game board at screen.
     */
    public void displayBoard() {
        out.println(DESCRIPTION);
        out.print(" ");
        for (int j = 0; j < this.width; j++) { //First line display, the one with the numbers.
            out.print(" " + j);
        }
        out.println();
        for (int i = 0; i < this.height; i++) { //We are filling the board in the height direction, from the top to the bottom, line by line
            out.print(i);
            for (int j = 0; j < this.width; j++) {
                Point courant = new Point(i, j);
                if (winningPositions.contains(courant)) {
                    out.print(" " + "x");
                } else if (wallPositions.contains(courant)) {
                    out.print(" " + "#");
                } else if (boxPositions.contains(courant)) {
                    out.print(" " + "C");
                } else if (courant.equals(playerPosition)) {
                    out.print(" " + "P");
                } else {
                    out.print(" " + ".");
                }
            }
            out.println();
        }
    }
}
