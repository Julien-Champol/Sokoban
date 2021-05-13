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
    private final int WIDTH;

    /**
     * The height of the board
     */
    private final int HEIGHT;

    /**
     * The player's position on the board
     */
    private Point playerPosition;

    /**
     * A list of all the winning positions, the one containing targets.
     */
    private ArrayList winningPositions = new ArrayList<Point>();

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
        this.WIDTH = width;
        this.HEIGHT = height;
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
     * Method adding a length sized horizontal wall from a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     * @param length the size of the wall
     */
    public void addHorizontalWall(int x, int y, int length) {
        Point startingPoint = new Point(x, y);
        if ((winningPositions.isEmpty() || boxPositions.isEmpty()) || !winningPositions.contains(startingPoint)
                && !boxPositions.contains(startingPoint)) {
            wallPositions.add(startingPoint);
        } else {
            out.println("Cette position est déjà occupée.");
        }
        for (int i = 1; i <= length; i++) {
            Point newPoint = new Point(x + i, y);
            if (!winningPositions.contains(newPoint) && !boxPositions.contains(newPoint)) {
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
        if ((winningPositions.isEmpty() || boxPositions.isEmpty()) || !winningPositions.contains(startingPoint)
                && !boxPositions.contains(startingPoint)) {
            wallPositions.add(startingPoint);
        } else {
            out.println("Cette position est déjà occupée.");
        }
        for (int i = 1; i <= length; i++) {
            Point newPoint = new Point(x, y + i);
            if (winningPositions.isEmpty() || boxPositions.isEmpty()) {
                wallPositions.add(newPoint);
            } else if (!winningPositions.contains(newPoint) && !boxPositions.contains(newPoint)) {
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
        if ((winningPositions.isEmpty() || wallPositions.isEmpty()) || !winningPositions.contains(boxPoint)
                && !wallPositions.contains(boxPoint)) {
            boxPositions.add(boxPoint);
        }
    }

    /**
     * Method moving a box to a given point.
     *
     * @param x the point's actual absciss
     * @param y the point's actual ordinate
     * @param z the point's new absciss
     * @param a the point's new ordinate
     */
    public void moveBox(int x, int y, int z, int a) {
        Point toMove = new Point(x, y);
        Point moved = new Point(z, a);
        if (((wallPositions.isEmpty() || winningPositions.isEmpty()) && !boxPositions.isEmpty())
                || !wallPositions.contains(toMove) && !winningPositions.contains(toMove)
                && !wallPositions.contains(moved)) { // No !winningPositions.contains(moved), the player can move a box to a winning pos.
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
        if ((boxPositions.isEmpty() || wallPositions.isEmpty()) || !boxPositions.contains(newTarget)
                && !wallPositions.contains(newTarget)) {
            winningPositions.add(newTarget);
        }
    }

    /**
     * Method seting the player's initial position at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void setPlayerPosition(int x, int y) {
        Point newPosition = new Point(x, y);
        if ((winningPositions.isEmpty() || wallPositions.isEmpty() || boxPositions.isEmpty())
                || !winningPositions.contains(newPosition) && !wallPositions.contains(newPosition) && !boxPositions.contains(newPosition)) {
            playerPosition = newPosition;
        }
    }

    /**
     * Method used to display the game board at screen.
     */
    public void displayBoard() {
        out.println(DESCRIPTION);
        out.print(" ");
        for (int j = 0; j < WIDTH; j++) { //First line display, the one with the numbers.
            out.print(" " + j);
        }
        out.println();
        for (int i = 0; i < HEIGHT; i++) { //We are filling the board in the height direction, from the top to the bottom, line by line
            out.print(i);
            for (int j = 0; j < WIDTH; j++) {
                Point courant = new Point(i, j);
                if (winningPositions.contains(courant)) {
                    out.print(" " + "X");
                } else if (wallPositions.contains(courant)) {
                    out.print(" " + "#");
                } else if (boxPositions.contains(courant)) {
                    out.print(" " + "C");
                } else if (courant == playerPosition) {
                    out.print(" " + "P");
                } else {
                    out.print(" " + ".");
                }
            }
            out.println();
        }
    }
}
