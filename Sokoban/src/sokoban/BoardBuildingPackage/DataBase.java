/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

/**
 * Class representing data about the data base containing the boards.
 *
 * @author jcdru
 */
public class DataBase {

    private final String NAME; //The name of the data base

    /**
     * Parameterized constructor of the DataBase class.
     *
     * @param name
     */
    public DataBase(String name) {
        this.NAME = name;
    }

    /**
     * Method loading the Sqlite jdbc driver and establishing a connection
     */
    public void loadDriverAndConnect() {

    }

    /**
     * Method creating the data base, tables etc ...
     */
    public void createDataBase() {

    }

    /**
     * Method used to add a Board to the database
     *
     * @param id the id of the board in the dataBase
     * @param theBoard the board we are adding
     */
    public void add(String id, Board theBoard) {

    }

    /**
     * Method used to remove a board from a dataBase with its id
     *
     * @param id the id of the board to remove
     */
    public void remove(String id) {

    }

    /**
     * Method used to list the boards contained in the data base
     */
    public void listBoards() {

    }

    /**
     * Method used to show the boards contained in the dataBase
     */
    public void showBoards() {

    }
    
    /**
     * Method used to pull a board from the database
     * 
     * @param id the board's id
     * @return an isntance of the Board class
     */
    public Board get(String id) {
        Board tmp = new Board(id, 0, 0);
        return tmp;
    }
}
