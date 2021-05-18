/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import sokoban.BoardBuildingPackage.Board;
import sokoban.PlayerMovesPackage.PlayerMoves;

/**
 *
 * @author jcdru
 */
public class BoardCheckerTest {

    /**
     * Test of winCheck method, of class BoardChecker.
     */
    @Test
    public void testWinCheck() {
        System.out.println("winCheck");
        Board analysed = new Board("TestBoard", 8, 8);
        analysed.addHorizontalWall(0, 0, 8);
        analysed.addHorizontalWall(0, 8, 8);
        analysed.addVerticalWall(7, 0, 8);
        analysed.addTarget(0, 2);
        analysed.addBox(3, 2);
        boolean expResult = false;
        boolean result = BoardChecker.winCheck(analysed);
        assertEquals(expResult, result);
    }

    /**
     * Test of legitMove method, of class BoardChecker.
     */
    @Test
    public void testLegitMove() {
        System.out.println("legitMove");
        Board analysed = null;
        Point start = null;
        Point destination = null;
        boolean expResult = false;
        boolean result = BoardChecker.legitMove(analysed, start, destination);
        assertEquals(expResult, result);
    }

    /**
     * Test of movableBoxCheck method, of class BoardChecker.
     */
    @Test
    public void testMovableBoxCheck() {
        System.out.println("movableBoxCheck");
        Board theBoard = null;
        Point theBox = null;
        PlayerMoves.Moves direction = null;
        boolean expResult = false;
        boolean result = BoardChecker.movableBoxCheck(theBoard, theBox, direction);
        assertEquals(expResult, result);
    }

}
