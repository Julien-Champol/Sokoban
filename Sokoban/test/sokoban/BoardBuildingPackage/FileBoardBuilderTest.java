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
public class FileBoardBuilderTest {

    public FileBoardBuilderTest() {
    }

    /**
     * Test of fileReader method, of class FileBoardBuilder.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testFileReader() throws Exception {
        System.out.println("fileReader");
        FileBoardBuilder instance = new FileBoardBuilder("D:\\DUT\\Informatique\\AlgoProg\\sokobanchampol\\Boards\\testBoards\\testBoard.txt");
        TextBoardBuilder result = instance.fileReader();
        result.build().displayBoard();
        Point playerPos = new Point(3, 1);
        assertEquals(result.build().getPlayerPosition(), playerPos);
        Point boxPos = new Point(2, 3);
        assertTrue(result.build().getBoxPositions().contains(boxPos));
        Point targetPos = new Point(1, 2);
        assertTrue(result.build().getWinningPositions().contains(targetPos));
    }

    /**
     * Test of build method, of class FileBoardBuilder.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testBuild() throws Exception {
        System.out.println("build");
        FileBoardBuilder instance = new FileBoardBuilder("D:\\DUT\\Informatique\\AlgoProg\\sokobanchampol\\Boards\\testBoards\\testBoard2.txt");
        TextBoardBuilder result = instance.fileReader();
        result.build().displayBoard();
        assertEquals(result.build().getDescription(), "No name found");
        Point playerPos = new Point(1, 2);
        assertEquals(result.build().getPlayerPosition(), playerPos);
    }

}
