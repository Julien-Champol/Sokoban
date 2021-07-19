/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import org.junit.Assert;
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
        theBoard.addBox(3, 1);
        theBoard.addBox(6, 2);
        /*
          0 1 2 3 4 5 6 7
        0 # # # # # # # #
        1 # . x . C . . #
        2 # . . . . . . #
        3 # C . C C C C #
        4 # . . . . . . #
        5 # . . . . . . #
        6 # . C . . . . #
        7 # # # # # # # #
         */
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
        Point theThirdBox = new Point(3, 1);
        Assert.assertFalse(BoardChecker.movableBoxCheck(theBoard, theThirdBox, direction2));
        Point theFourthBox = new Point(6, 2);
        Assert.assertFalse(BoardChecker.movableBoxCheck(theBoard, theFourthBox, PlayerMoves.Moves.U));
        Point theFifthBox = new Point(3, 6);
        Assert.assertFalse(BoardChecker.movableBoxCheck(theBoard, theFifthBox, PlayerMoves.Moves.L));
        Point theSixthBox = new Point(1, 4);
        Assert.assertFalse(BoardChecker.movableBoxCheck(theBoard, theSixthBox, PlayerMoves.Moves.D));
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
        theBoard.addHorizontalWall(5, 6, 2);
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
        /*
          0 1 2 3 4 5 6 7
        0 # # # # # # # #
        1 # C x . . C C #
        2 # . . . . . . #
        3 # . . . . . C #
        4 # . . . . . # #
        5 # C . . . . # #
        6 # C . . . . C #
        7 # # # # # # # #
         */
 /* Assertions */
        Point theBox = new Point(5, 6);
        //Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(3, 6);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(1, 1);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(1, 6);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(1, 5);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(5, 1);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(6, 1);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));
        theBox = new Point(6, 6);
        Assert.assertTrue(BoardChecker.trapCaseCheck(theBoard, theBox));

        /*
         0 1 2 3 4 5 6 7
        0 # # # # # # # #
        1 # . . . . . . #
        2 # . . . . . . #
        3 # . . . . . . #
        4 # . . . . . # #
        5 # # . . . . # #
        6 # . C P . . . #
        7 # # # # # # # #
         */
        Board theSecondBoard = new Board("secondTestBoard", 8, 8);
        theSecondBoard.addHorizontalWall(0, 0, 8);
        theSecondBoard.addHorizontalWall(7, 0, 8);
        theSecondBoard.addVerticalWall(0, 7, 8);
        theSecondBoard.addVerticalWall(0, 0, 8);
        theSecondBoard.addHorizontalWall(4, 6, 2);
        theSecondBoard.addHorizontalWall(5, 6, 2);
    }

    /**
     * Test of mustGetTraped method, of class BoardChecker.
     */
    @Test
    public void testWillGetTraped() {
        System.out.println("mustGetTrapCheck");
        Board theBoard = new Board("TestBoard", 8, 8);
        theBoard.addHorizontalWall(0, 0, 8);
        theBoard.addHorizontalWall(7, 0, 8);
        theBoard.addVerticalWall(0, 7, 8);
        theBoard.addVerticalWall(0, 0, 8);
        theBoard.addHorizontalWall(2, 6, 2);
        theBoard.addHorizontalWall(4, 6, 2);
        theBoard.addHorizontalWall(5, 6, 2);
        theBoard.addTarget(1, 2);
        theBoard.addBox(5, 6);
        theBoard.addBox(1, 1);
        theBoard.addBox(1, 5);
        theBoard.addBox(5, 1);
        theBoard.addBox(6, 1);
        theBoard.addBox(6, 5);
        theBoard.displayBoard();
        /*
          0 1 2 3 4 5 6 7
        0 # # # # # # # #
        1 # C x . . C . #
        2 # . . . . . # #
        3 # . . . . . . #
        4 # . . . . . # #
        5 # C . . . . # #
        6 # C . . . C . #
        7 # # # # # # # #
         */
 /* Assertions */
        Point box = new Point(1, 5);
        Assert.assertTrue(BoardChecker.mustGetTraped(theBoard, box));
        box = new Point(6, 5);
        Assert.assertTrue(BoardChecker.mustGetTraped(theBoard, box));
    }
}
