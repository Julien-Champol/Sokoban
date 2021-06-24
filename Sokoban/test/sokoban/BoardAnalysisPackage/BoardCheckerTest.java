/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import static org.junit.Assert.assertEquals;
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
        analysed.displayBoard();
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
        Board analysed = new Board("TestBoard", 8, 8);
        analysed.addHorizontalWall(0, 0, 8);
        analysed.addHorizontalWall(0, 8, 8);
        analysed.addVerticalWall(7, 0, 8);
        analysed.addTarget(0, 2);
        analysed.addBox(3, 2);
        analysed.displayBoard();
        Point start = new Point(3, 3);
        Point destination = new Point(3, 4);
        boolean expResult = true;
        boolean result = BoardChecker.legitMove(analysed, start, destination);
        assertEquals(expResult, result);
    }

    /**
     * Test of movableBoxCheck method, of class BoardChecker.
     */
    @Test
    public void testMovableBoxCheck() {
        System.out.println("movableBoxCheck");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(7, 0, 8);
        theBoard.addVerticalWall(0, 7, 8);
        theBoard.addVerticalWall(0, 0, 8);
        theBoard.addTarget(1, 2);
        //theBoard.addBox(3, 7);
        theBoard.addBox(3, 6);
        theBoard.addBox(3, 5);
        theBoard.addBox(3, 4);
        theBoard.addBox(3, 3);
        theBoard.displayBoard();
        Point theBox = new Point(3, 2);
        PlayerMoves.Moves direction = PlayerMoves.Moves.L;
        boolean expResult = false;
        boolean result = BoardChecker.movableBoxCheck(theBoard, theBox, direction);
        assertEquals(expResult, result);
        Point theSecondBox = new Point(3, 3);
        PlayerMoves.Moves direction2 = PlayerMoves.Moves.R;
        boolean expResult2 = true; // The fact that the box will finally isn't dealt here, it's in player moves
        boolean result2 = BoardChecker.movableBoxCheck(theBoard, theSecondBox, direction2);
        assertEquals(expResult2, result2);
    }

    /**
     * Test of trapCaseCheck method, of class BoardChecker.
     */
    @Test
    public void testTrapCaseCheck() {
        System.out.println("trapcaseCheck");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(7, 0, 8);
        theBoard.addVerticalWall(0, 7, 8);
        theBoard.addVerticalWall(0, 0, 8);
        theBoard.addHorizontalWall(4, 6, 2);
        theBoard.addTarget(1, 2);
        theBoard.addBox(5, 6);
        theBoard.addBox(3, 6);
        theBoard.addBox(1, 1);
        theBoard.addBox(1, 6);
        theBoard.addBox(1, 5);
        theBoard.addBox(5, 1);
        theBoard.addBox(6, 1);
        theBoard.addBox(6, 6);
        theBoard.displayBoard();
    }
}
