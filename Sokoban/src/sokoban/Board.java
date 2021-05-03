/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.HashSet;

/**
 * Class representing data about a game board.
 *
 * @author jchampol
 */
public class Board {

    private final String description;
    private final int length;
    private final int height;

    private final HashSet printableBoard;

    /**
     * Parameterized constructor of a board.
     *
     * @param description a short description of the board.
     * @param length the length of the board.
     * @param height the height of the board.
     */
    public Board(String description, int length, int height) {
        this.description = description;
        this.length = length;
        this.height = height;
        this.printableBoard = new HashSet(); //gomoku double entrée hashset
    }

    /**
     * Method adding to built a wall of length cases from (x, y) to the right .
     *
     * @param x absciss
     * @param y ordinate
     * @param length wall's size
     */
    public void addHorizontalWall(int x, int y, int length) {
        //todo
    }

    /**
     * Method adding to built a wall of length cases from (x, y) to the bottom.
     *
     * @param x absciss
     * @param y ordinate
     * @param length wall's size
     */
    public void addVerticalWall(int x, int y, int length) {
        //todo
    }

    /**
     * Method adding a box to the board at (x, y).
     *
     * @param x absciss
     * @param y ordinate
     */
    public void addBox(int x, int y) {
        //todo
    }
    
    /**
     * Method adding a target at (x, y).
     * 
     * @param x absciss
     * @param y ordinate
     */
    public void addTarget(int x, int y) {
        //todo
    }
    
    /**
     * Method used to set the (x, y) position0
     * 
     * @param x absciss
     * @param y ordinate
     */
    public void setPosition(int x, int y) {
        //todo
    }
}
