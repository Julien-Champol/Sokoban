/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardAnalysisPackage;

import java.awt.Point;
import java.util.ArrayList;
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
        return (analysed.getWinningPositions().containsAll(analysed.getBoxPositions()));
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
        return ( //First case, moving a player to an empty place (only option).
                (analysed.inTheBoardCheck(destination) && !analysed.getWallPositions().contains(destination)
                && (analysed.inTheBoardCheck(start) && (!analysed.getBoxPositions().contains(start))
                && (!analysed.getWallPositions().contains(start))))
                //Second case, moving a board to an empty case or a target.
                || (analysed.inTheBoardCheck(destination) && !analysed.getWallPositions().contains(destination)
                //Can go anywhere but not on a wall
                && (analysed.inTheBoardCheck(start) && (analysed.getBoxPositions().contains(start)
                && (!analysed.getWallPositions().contains(start)) && !analysed.getWinningPositions().contains(start)))));
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
        Point neighbor = new Point(-1, -1);
        switch (direction) {
            case L:
                x = (int) theBox.getX();
                y = (int) theBox.getY() - 1;
                neighbor = new Point(x, y);
                break;
            case R:
                x = (int) (theBox.getX());
                y = (int) theBox.getY() + 1;
                neighbor = new Point(x, y);
                break;
            case U:
                x = (int) theBox.getX() - 1;
                y = (int) theBox.getY();
                neighbor = new Point(x, y);
                break;
            case D:
                x = (int) theBox.getX() + 1;
                y = (int) theBox.getY();
                neighbor = new Point(x, y);
                break;
        }
        return (theBoard.getBoxPositions().contains(theBox) && !theBoard.getWallPositions().contains(theBox)
                && theBoard.inTheBoardCheck(neighbor) && !theBoard.getWallPositions().contains(neighbor));
    }

    /**
     * Method used to tell if there's any case locked in the board, between two
     * walls for example.
     *
     * @param theBoard
     * @param theBox
     * @return
     */
    public static boolean trapCaseCheck(Board theBoard, Point theBox) {
        ArrayList<Point> neighborsList = new ArrayList<>();

        int lx = (int) theBox.getX();
        int ly = (int) theBox.getY() - 1;
        Point leftNeighbor = new Point(lx, ly);
        neighborsList.add(leftNeighbor);

        int rx = (int) (theBox.getX());
        int ry = (int) theBox.getY() + 1;
        Point rightNeighbor = new Point(rx, ry);
        neighborsList.add(rightNeighbor);

        int ux = (int) theBox.getX() - 1;
        int uy = (int) theBox.getY();
        Point upNeighbor = new Point(ux, uy);
        neighborsList.add(upNeighbor);

        int dx = (int) theBox.getX() + 1;
        int dy = (int) theBox.getY();
        Point downNeighbor = new Point(dx, dy);
        neighborsList.add(downNeighbor);

        return (theBoard.getBoxPositions().containsAll(neighborsList) //Only walls

                /* Walls occupying the problematic positions */
                || theBoard.getWallPositions().contains(leftNeighbor) && theBoard.getWallPositions().contains(rightNeighbor)
                && theBoard.getWallPositions().contains(downNeighbor)) //l,r,u

                || theBoard.getWallPositions().contains(leftNeighbor) && theBoard.getWallPositions().contains(rightNeighbor)
                && theBoard.getWallPositions().contains(upNeighbor) //l,r,d

                || theBoard.getWallPositions().contains(upNeighbor) && theBoard.getWallPositions().contains(downNeighbor)
                && theBoard.getWallPositions().contains(leftNeighbor) //u,d,l

                || theBoard.getWallPositions().contains(upNeighbor) && theBoard.getWallPositions().contains(downNeighbor)
                && theBoard.getWallPositions().contains(rightNeighbor) //u,d,r

                /* Not movable boxes occupying some problematic positions */
                || theBoard.getWallPositions().contains(leftNeighbor) && theBoard.getWallPositions().contains(rightNeighbor)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)//l,r, box on bottom

                || theBoard.getWallPositions().contains(leftNeighbor) && theBoard.getWallPositions().contains(rightNeighbor)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)//l,r, box on top

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getWallPositions().contains(downNeighbor)
                && theBoard.getWallPositions().contains(rightNeighbor) //box on the left, walls down and right

                || theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)
                && theBoard.getWallPositions().contains(downNeighbor)
                && theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)//boxes on both sides

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)
                && theBoard.getWallPositions().contains(rightNeighbor) //boxes on the left and down, wall right

                || theBoard.getWallPositions().contains(leftNeighbor)
                && theBoard.getWallPositions().contains(downNeighbor)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R) //walls on the left and down, box right

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)
                && theBoard.getWallPositions().contains(rightNeighbor) //boxes left and up, wall right

                || theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)
                && theBoard.getWallPositions().contains(leftNeighbor) //wall on the left, boxes on top and right

                || theBoard.getWallPositions().contains(upNeighbor)
                && theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R) //wall on the left and right, box on top

                /* Boxes only */
                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)//boxes on top

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)//boxes on the bottom

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)//boxes on the left

                || theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)//boxes on the right

                || theBoard.getBoxPositions().contains(leftNeighbor) && !movableBoxCheck(theBoard, leftNeighbor, Moves.L)
                && theBoard.getBoxPositions().contains(downNeighbor) && !movableBoxCheck(theBoard, downNeighbor, Moves.D)
                && theBoard.getBoxPositions().contains(rightNeighbor) && !movableBoxCheck(theBoard, rightNeighbor, Moves.R)
                && theBoard.getBoxPositions().contains(upNeighbor) && !movableBoxCheck(theBoard, upNeighbor, Moves.U)//boxes everywhere
                /* Target being a problem */;
    }
}
