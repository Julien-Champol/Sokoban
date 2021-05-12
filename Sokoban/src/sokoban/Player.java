/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.util.Scanner;
import sokoban.BoardBuildingPackage.Board;

/**
 * Class representing data about a Player.
 * 
 * @author jchampol
 */
public class Player {

    private static boolean inGame;
    private static Scanner in = new Scanner(System.in);
    private static Board currentBoard;

    /**
     * Main method of the player class.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    /**
     * Method used to read the Player entry and to return it.
     * 
     * @return the player's entry to uppercase.
     */
    public static String readPlayerEntry() {
        return "";
    }
    
    /**
     * Method used to analyse the sequence entered by the player and call the corresponding method.
     */
    public static void analyseSequence() {
        
    }
    
    /**
     * Method used to display a message and switch the attributes to the good value in case of winning.
     */
    public static void winDialog() {
        
    }
    
    /**
     * Method used to display a message and switch the attributes to the good value in case of loosing.
     */
    public static void quiWithDialog() {
        //NOTE : Quit will be turnt into QUIT with the sequence analysis method.
    }
    
    /**
     * Method used to switch to the nextBoard if the current one is complete.
     * 
     * @return the next board the player will complete
     */
    public static Board nextBoard() {
       Board a = new Board(" ", 0, 0);
       return a;
    }

}
