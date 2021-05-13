/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import sokoban.BoardBuildingPackage.Board;

/**
 * Class used to store data about the board analysing and victory checking
 * methods.
 *
 * @author jcdru
 */
public class BoardChecker {

    /**
     * Method used to tell if the player is in a winning condition.
     *
     * @param analysed the board we want to analyse
     * @return
     */
    public static boolean winCheck(Board analysed) {
        return (analysed.getWinningpositions().containsAll(analysed.getBoxPositions()));
    }

    /**
     * Method used to tell if the player will be able to move to the given
     * point.
     *
     * @param analysed the board we want to analyse
     * @param destination the point the player wants to join.
     * @return
     */
    public static boolean legitMove(Board analysed, Point destination) {
        return true || false;
    }
    
}
