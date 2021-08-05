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
import sokoban.ExceptionsPackage.GamePlayerLeavesException;

/**
 *
 * @author jcdru
 */
public class PlayerMovesTest {

    public PlayerMovesTest() {
    }

    /**
     * Test of moveLeft method, of class PlayerMoves.
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    @Test
    public void testMoveLeft() throws GamePlayerLeavesException {
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
        theBoard.addBox(3, 4);
        theBoard.addBox(3, 5);
        Point newPosition = new Point(3, 3);
        theBoard.setPlayerPosition(newPosition);
        System.out.println("Before");
        theBoard.displayBoard();
        PlayerMoves.moveRight(theBoard);
        System.out.println("After");
        theBoard.displayBoard();
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
        System.out.println("Avant move up");
        theBoard.displayBoard();
        PlayerMoves.moveUp(theBoard);
        System.out.println("Après move up");
        theBoard.displayBoard();
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

    /**
     * Test of moveBack method, of class PlayerMoves.
     *
     * @throws sokoban.ExceptionsPackage.GamePlayerLeavesException
     */
    @Test
    public void testMoveBack() throws GamePlayerLeavesException {
        System.out.println("moveBack");
        Board theBoard = new Board("moveBackTestBoard", 12, 12);
        theBoard.addHorizontalWall(0, 0, 12);
        theBoard.addHorizontalWall(11, 0, 12);
        theBoard.addVerticalWall(0, 11, 12);
        theBoard.addVerticalWall(0, 0, 12);
        theBoard.addVerticalWall(9, 1, 0);

        //Left
        theBoard.addBox(5, 4);
        theBoard.addBox(5, 3);
        theBoard.addBox(5, 2);
        //Right
        theBoard.addBox(5, 6);
        theBoard.addBox(5, 7);
        //Down
        theBoard.addBox(6, 5);
        theBoard.addBox(7, 5);
        theBoard.addBox(8, 5);
        //Up   
        theBoard.addBox(3, 5);
        theBoard.addBox(4, 5);
        //

        Point newPosition = new Point(5, 5);
        theBoard.setPlayerPosition(newPosition);
        theBoard.displayBoard();

        PlayerMoves.moveLeft(theBoard);
        theBoard.displayBoard();
        PlayerMoves.moveBack(theBoard);
        theBoard.displayBoard();
        Point valid = new Point(5, 5);
        assertEquals(theBoard.getPlayerPosition(), valid);
        valid = new Point(5, 4);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(5, 3);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(5, 2);
        assertTrue(theBoard.getBoxPositions().contains(valid));

        PlayerMoves.moveRight(theBoard);
        theBoard.displayBoard();
        PlayerMoves.moveBack(theBoard);
        theBoard.displayBoard();
        valid = new Point(5, 5);
        assertEquals(theBoard.getPlayerPosition(), valid);
        valid = new Point(5, 7);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(5, 6);
        assertTrue(theBoard.getBoxPositions().contains(valid));

        PlayerMoves.moveDown(theBoard);
        theBoard.displayBoard();
        PlayerMoves.moveBack(theBoard);
        theBoard.displayBoard();
        valid = new Point(5, 5);
        assertEquals(theBoard.getPlayerPosition(), valid);
        valid = new Point(6, 5);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(7, 5);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(8, 5);
        assertTrue(theBoard.getBoxPositions().contains(valid));

        PlayerMoves.moveUp(theBoard);
        theBoard.displayBoard();
        PlayerMoves.moveBack(theBoard);
        theBoard.displayBoard();
        valid = new Point(5, 5);
        assertEquals(theBoard.getPlayerPosition(), valid);
        valid = new Point(3, 5);
        assertTrue(theBoard.getBoxPositions().contains(valid));
        valid = new Point(4, 5);
        assertTrue(theBoard.getBoxPositions().contains(valid));

        newPosition = new Point(10, 1);
        theBoard.setPlayerPosition(newPosition);
        theBoard.displayBoard();
        PlayerMoves.moveRight(theBoard);
        theBoard.displayBoard();
        PlayerMoves.moveBack(theBoard);
        theBoard.displayBoard();
        assertEquals(theBoard.getPlayerPosition(), newPosition);
    }

}
