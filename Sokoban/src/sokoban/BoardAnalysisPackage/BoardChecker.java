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
     * @param start the point the player/box comes from
     * @param destination the point the player/box wants to join.
     * @return
     */
    public static boolean legitMove(Board analysed, Point start, Point destination) {
        return ( //First case, moving a player to an empty case.
                (analysed.inTheBoardCheck(destination) && !analysed.getWallPositions().contains(destination)
                && !analysed.getWinningpositions().contains(destination) // A player can't go on a winning position
                && !analysed.getBoxPositions().contains(destination) //Nor on a box
                && (analysed.inTheBoardCheck(start) && (!analysed.getBoxPositions().contains(start)))) // we are not moving a box but a player

                || (analysed.inTheBoardCheck(start)
                && analysed.getBoxPositions().contains(start) // we are moving a box not a player
                && !analysed.getWallPositions().contains(destination)));
    }

}
