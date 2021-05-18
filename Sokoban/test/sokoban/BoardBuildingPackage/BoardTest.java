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
        Point toMove = null;
        Point moved = null;
        Board instance = null;
        instance.moveBox(toMove, moved);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerPosition method, of class Board.
     */
    @Test
    public void testSetPlayerPosition() {
        System.out.println("setPlayerPosition");
        Point newPosition = null;
        Board instance = null;
        instance.setPlayerPosition(newPosition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of inTheBoardCheck method, of class Board.
     */
    @Test
    public void testInTheBoardCheck() {
        System.out.println("inTheBoardCheck");
        Point check = null;
        Board instance = null;
        boolean expResult = false;
        boolean result = instance.inTheBoardCheck(check);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
