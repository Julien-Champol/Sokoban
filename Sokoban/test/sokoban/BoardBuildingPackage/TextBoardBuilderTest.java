/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jcdru
 */
public class TextBoardBuilderTest {

    /**
     * Test of addRow method, of class TextBoardBuilder.
     * @throws java.lang.Exception
     */
    @Test
    public void testAddRow() throws Exception {
        System.out.println("addRow");
        TextBoardBuilder instance = new TextBoardBuilder("testBoard");
        instance.addRow("#########");
        instance.addRow("#...x...#");
        instance.addRow("#.....c.#");
        instance.addRow("#....P..#");
        instance.addRow("#########");
        Board testAddRow = instance.build();
        testAddRow.displayBoard();
        Point playerPosition = new Point(5, 3);
        assertEquals(testAddRow.getPlayerPosition(), playerPosition);
        Point boxPosition = new Point(6, 2);
        assertTrue(testAddRow.getBoxPositions().contains(boxPosition));
        Point targetPosition = new Point(4, 1);
        assertTrue(testAddRow.getWinningpositions().contains(targetPosition));
    }

    /**
     * Test of build method, of class TextBoardBuilder.
     * @throws java.lang.Exception
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        TextBoardBuilder instance = null;
        Board expResult = null;
        Board result = instance.build();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
