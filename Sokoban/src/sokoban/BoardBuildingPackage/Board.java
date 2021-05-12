/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Class representing data about a game board.
 *
 * @author jchampol
 */
public class Board {

    private final String DESCRIPTION;

    private final int WIDTH;

    private final int HEIGHT;

    private char[][] board;

    private Point playerPosition;

    private ArrayList<Point> winningPositions;

    private ArrayList<Point> wallPositions;

    private ArrayList<Point> boxPositions;

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

    /**
     * Method adding a length sized horizontal wall from a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     * @param length the size of the wall
     */
    public void addHorizontalWall(int x, int y, int length) {

    }

    /**
     * Method adding a length sized vertical wall from a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     * @param length the size of the wall
     */
    public void addVerticalWall(int x, int y, int length) {

    }

    /**
     * Method adding a box at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void addBox(int x, int y) {

    }

    /**
     * Method moving a box to a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void moveBox(int x, int y) {

    }

    /**
     * Method adding a target at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void addTarget(int x, int y) {

    }

    /**
     * Method seting the player's initial position at a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void setPlayerPosition(int x, int y) {

    }

    /**
     * Method used to move the player to a given point.
     *
     * @param x the point's absciss
     * @param y the point's ordinate
     */
    public void movePlayer(int x, int y) {

    }

    /**
     * Method used to display the game board at screen.
     */
    public void displayBoard() {

    }
}
