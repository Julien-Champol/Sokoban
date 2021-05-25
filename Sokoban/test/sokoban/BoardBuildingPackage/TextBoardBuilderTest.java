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
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testAddRow() throws Exception {
        System.out.println("addRow");
        TextBoardBuilder instance = new TextBoardBuilder("testBoard");
        instance.addRow("#########");
        instance.addRow("#...x...#");
        instance.addRow("#.....C.#");
        instance.addRow("#....P..#");
        instance.addRow("#########");
        Board testAddRow = instance.build();
        testAddRow.displayBoard();
        Point playerPosition = new Point(3, 5);
        assertEquals(testAddRow.getPlayerPosition(), playerPosition);
        Point boxPosition = new Point(2, 6);
        assertTrue(testAddRow.getBoxPositions().contains(boxPosition));
        Point targetPosition = new Point(1, 4);
        assertTrue(testAddRow.getWinningpositions().contains(targetPosition));
    }

    /**
     * Test of build method, of class TextBoardBuilder.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        TextBoardBuilder instance = new TextBoardBuilder("testBoard2");
        instance.addRow("##########");
        instance.addRow("#.x.x.C..#");
        instance.addRow("#.....C.##");
        instance.addRow("#....P..##");
        instance.addRow("##########");
        Board testAddRow = instance.build();
        testAddRow.displayBoard();
        Point playerPosition = new Point(3, 5);
        assertEquals(testAddRow.getPlayerPosition(), playerPosition);
        Point boxPosition = new Point(1, 6);
        assertTrue(testAddRow.getBoxPositions().contains(boxPosition));
        Point boxPosition2 = new Point(2, 6);
        assertTrue(testAddRow.getBoxPositions().contains(boxPosition2));
        Point targetPosition = new Point(1, 2);
        assertTrue(testAddRow.getWinningpositions().contains(targetPosition));
        Point targetPosition2 = new Point(1, 4);
        assertTrue(testAddRow.getWinningpositions().contains(targetPosition2));
        Point wall1 = new Point(0, 9);
        assertTrue(testAddRow.getWallPositions().contains(wall1));
        Point wall2 = new Point(2, 9);
        assertTrue(testAddRow.getWallPositions().contains(wall2));
        Point wall3 = new Point(4, 9);
        assertTrue(testAddRow.getWallPositions().contains(wall3));
    }

}
