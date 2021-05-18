/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.ExceptionsPackage;

/**
 * Class representing data about the exception thrown when the player quits the
 * game.
 *
 * @author jcdru
 */
public class GamePlayerLeavesException extends Exception {

    public GamePlayerLeavesException(String message) {
        super(message);
    }

}
