/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import sokoban.BoardBuildingPackage.Board;
import sokoban.PlayerMovesPackage.PlayerMoves.Moves;

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
        return ( //First case, moving a player to an empty case (only option).
                (analysed.inTheBoardCheck(destination) && !analysed.getWallPositions().contains(destination)
                && (analysed.inTheBoardCheck(start) && (!analysed.getBoxPositions().contains(start))
                && (!analysed.getWallPositions().contains(start))))
                //Second case, moving a board to an empty case or a target.
                || (analysed.inTheBoardCheck(destination) && !analysed.getWallPositions().contains(destination)
                //Can go anywhere but not on a wall
                && (analysed.inTheBoardCheck(start) && (analysed.getBoxPositions().contains(start)
                && (!analysed.getWallPositions().contains(start)) && !analysed.getWinningpositions().contains(start)))));
        // we are not moving a player but a box
    }

    /**
     * Method telling if a box can be moved in a certain direction or not
     *
     * @param theBoard the Board we are completing
     * @param theBox the box we want to move, supposing it's a box
     * @param direction the direction were we move
     * @return true iff the move can be done without leaving the board, false
     * otherwise
     */
    public static boolean movableBoxCheck(Board theBoard, Point theBox, Moves direction) {
        int x;
        int y;
        Point voisin = new Point(-1, -1);
        switch (direction) {
            case L:
                x = (int) (theBox.getX() - 1);
                y = (int) theBox.getY();
                voisin = new Point(x, y);
                break;
            case R:
                x = (int) (theBox.getX() + 1);
                y = (int) theBox.getY();
                voisin = new Point(x, y);
                break;
            case U:
                x = (int) theBox.getX();
                y = (int) (theBox.getY() + 1);
                voisin = new Point(x, y);
                break;
            case D:
                x = (int) theBox.getX();
                y = (int) (theBox.getY() - 1);
                voisin = new Point(x, y);
                break;
        }
        return (theBoard.getBoxPositions().contains(theBox) && theBoard.inTheBoardCheck(voisin));
    }
}
