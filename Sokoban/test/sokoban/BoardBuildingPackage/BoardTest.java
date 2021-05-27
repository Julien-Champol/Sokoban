/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import sokoban.PlayerMovesPackage.PlayerMoves;
import sokoban.PlayerMovesPackage.PlayerMoves.Moves;

/**
 *
 * @author jcdru
 */
public class BoardTest {

    /**
     * Test of moveBox method, of class Board.
     */
    @Test
    public void testMoveBox() {
        System.out.println("moveBox");
        Board instance = new Board("TestBoard", 8, 8);
        instance.addHorizontalWall(0, 0, 8);
        instance.addHorizontalWall(0, 8, 8);
        instance.addVerticalWall(7, 0, 8);
        instance.addTarget(0, 2);
        instance.addBox(3, 2);
        Point toMove = new Point(3, 2);
        Point moved = new Point(3, 3);
        Point good = new Point(3, 3);
        instance.moveBox(toMove, moved); //The player can be erased, moveBox isn't triggered by movableBoxCheck.
        assertEquals(moved, good);
        instance.displayBoard();
    }

    /**
     * Test of setPlayerPosition method, of class Board.
     */
    @Test
    public void testSetPlayerPosition() {
        System.out.println("setPlayerPosition");
        Board instance = new Board("TestBoard", 8, 8);
        instance.addHorizontalWall(0, 0, 8);
        instance.addHorizontalWall(0, 8, 8);
        instance.addVerticalWall(7, 0, 8);
        instance.addTarget(0, 2);
        instance.addBox(3, 2);
        Point newPosition = new Point(3, 3);
        instance.setPlayerPosition(newPosition);
        assertEquals(newPosition, instance.getPlayerPosition());
        instance.displayBoard();
    }

    /**
     * Test of getRow method, of class Board.
     */
    @Test
    public void testGetRow() {
        System.out.println("getRow");
        Board instance = new Board("TestBoard", 8, 8);
        instance.addHorizontalWall(0, 0, 8);
        instance.addVerticalWall(0, 0, 8);
        instance.addVerticalWall(0, 7, 8);
        instance.addHorizontalWall(7, 0, 8);
        instance.addTarget(1, 2);
        instance.addBox(3, 2);
        Point newPosition = new Point(3, 3);
        instance.setPlayerPosition(newPosition);
        instance.displayBoard();
        assertEquals(instance.getRow(3), "#.CP...#");
    }

    /**
     * Test of inTheBoardCheck method, of class Board.
     */
    @Test
    public void testInTheBoardCheck() {
        System.out.println("inTheBoardCheck");
        Board instance = new Board("TestBoard", 8, 8);
        Point check = new Point(3, 8);
        boolean expResult = false;
        boolean result = instance.inTheBoardCheck(check);
        assertEquals(expResult, result);
    }

}
