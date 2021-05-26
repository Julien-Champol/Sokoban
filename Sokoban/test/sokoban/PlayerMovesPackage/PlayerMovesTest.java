/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.PlayerMovesPackage;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;
import sokoban.BoardBuildingPackage.Board;

/**
 *
 * @author jcdru
 */
public class PlayerMovesTest {

    public PlayerMovesTest() {
    }

    /**
     * Test of moveLeft method, of class PlayerMoves.
     */
    @Test
    public void testMoveLeft() {
        System.out.println("moveLeft");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(0, 8, 8);
        theBoard.addVerticalWall(7, 0, 8);
        theBoard.addTarget(0, 2);
        theBoard.addBox(3, 2);
        theBoard.addBox(3, 1);
        Point newPosition = new Point(3, 3);
        theBoard.setPlayerPosition(newPosition);
        System.out.println("Before moving left");
        theBoard.displayBoard();
        PlayerMoves.moveLeft(theBoard);
        System.out.println("After moving left");
        theBoard.displayBoard();
        Point valid = new Point(3, 2);
        assertEquals(theBoard.getPlayerPosition(), valid);
    }

    /**
     * Test of moveRight method, of class PlayerMoves.
     */
    @Test
    public void testMoveRight() {
        System.out.println("moveRight");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(0, 8, 8);
        theBoard.addVerticalWall(7, 0, 8);
        theBoard.addTarget(0, 2);
        theBoard.addBox(3, 2);
        Point newPosition = new Point(3, 3);
        theBoard.setPlayerPosition(newPosition);
        PlayerMoves.moveRight(theBoard);
        Point valid = new Point(3, 4);
        assertEquals(theBoard.getPlayerPosition(), valid);
    }

    /**
     * Test of moveUp method, of class PlayerMoves.
     */
    @Test
    public void testMoveUp() {
        System.out.println("moveUp");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(0, 8, 8);
        theBoard.addVerticalWall(7, 0, 8);
        theBoard.addTarget(0, 2);
        theBoard.addBox(3, 2);
        Point newPosition = new Point(3, 3);
        theBoard.setPlayerPosition(newPosition);
        PlayerMoves.moveUp(theBoard);
        Point valid = new Point(2, 3);
        assertEquals(theBoard.getPlayerPosition(), valid);
    }

    /**
     * Test of moveDown method, of class PlayerMoves.
     */
    @Test
    public void testMoveDown() {
        System.out.println("moveDown");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(0, 8, 8);
        theBoard.addVerticalWall(7, 0, 8);
        theBoard.addTarget(0, 2);
        theBoard.addBox(3, 2);
        Point newPosition = new Point(3, 3);
        theBoard.setPlayerPosition(newPosition);
        PlayerMoves.moveDown(theBoard);
        Point valid = new Point(4, 3);
        assertEquals(theBoard.getPlayerPosition(), valid);
    }

}
