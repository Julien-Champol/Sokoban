/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class representing data about the data base containing the boards.
 *
 * @author jcdru
 */
public class DataBase {

    private Connection actualConnection;

    private String chemin = "data\\librairie.sqlite3";

    private String URL = "jdbc:sqlite:" + chemin;

    /**
     * Constructor of the Database class.
     *
     * @throws java.sql.SQLException
     */
    public DataBase() throws SQLException {
        this.actualConnection = DriverManager.getConnection(URL);
        loadDriverAndConnect();
    }

    /**
     * Method establishing a connection
     */
    public static void loadDriverAndConnect() {
        String sqlite_driver = "org.sqlite.JDBC";
        try {
            Class.forName(sqlite_driver);
        } catch (ClassNotFoundException ex) {
            System.err.println("* Driver " + sqlite_driver + " introuvable.");
            System.exit(1);
        }
    }

    /**
     * Method used to create the table and the rows of the dataBase.
     */
    public void createDataBase() {
        //BOARDS table creation
        String sql = "CREATE TABLE IF NOT EXISTS BOARDS (\n"
                + "     board_id TEXT PRIMARY KEY,\n"
                + "   	name TEXT NOT NULL,\n"
                + "     nb_rows INTEGER NOT NULL,\n"
                + "	nb_cols INTEGER NOT NULL\n"
                + "   );";
        try {
            Statement boardsCreation = actualConnection.createStatement();
            boardsCreation.execute(sql);
        } catch (SQLException e) {
            System.out.println("Création de BOARDS impossible : " + e.toString());
        }

        //ROWS table creation
        String sql2 = "CREATE TABLE IF NOT EXISTS ROWS (\n"
                + "     board_id INTEGER NOT NULL,\n"
                + "   	row_num INTEGER NOT NULL,\n"
                + "     description TEXT NOT NULL\n"
                + "   );";
        try {
            Statement rowCreation = actualConnection.createStatement();
            rowCreation.execute(sql2);
        } catch (SQLException e) {
            System.out.println("Création de ROWS impossible : " + e.toString());
        }
    }

    /**
     * Method used to add a Board to the database
     *
     * @param id the id of the board in the dataBase
     * @param theBoard the board we are adding
     * @throws java.sql.SQLException
     */
    public void add(String id, Board theBoard) throws SQLException {
        // Insetion in the BOARDS table
        String sql = "INSERT INTO BOARDS (board_id,name,nb_rows,nb_cols) VALUES(?,?,?,?)";
        try (PreparedStatement ps = actualConnection.prepareStatement(sql);) {
            ps.setString(1, id);
            System.out.println(theBoard.getDescription());
            ps.setString(2, theBoard.getDescription());
            ps.setInt(3, theBoard.getHeight());
            ps.setInt(4, theBoard.getWidth());
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Ajout dans BOARDS impossible : " + e.getMessage());
        }
        
        // Insertion in the ROWS table
        String sql2 = "INSERT INTO ROWS (board_id,row_num,description) VALUES(?,?,?)";
        try (PreparedStatement ps2 = actualConnection.prepareStatement(sql2);) {
            for (int i = 0; i < theBoard.getHeight(); i++) {
                ps2.setString(1, id);
                ps2.setInt(2, i);
                ps2.setString(3, theBoard.getRow(i));
                ps2.execute();
            }
        } catch (SQLException e) {
            System.out.println("Ajout dans ROWS impossible : " + e.getMessage());
        }
       
        /*
        try (Statement addingInBoards = actualConnection.createStatement();) {
            addingInBoards.execute(sql);
        } catch (SQLException e) {
            System.out.println("Ajout du Board impossible : " + e.getMessage());
        }

        try (Statement addingInRows = actualConnection.createStatement();) {
            addingInRows.execute(sql2);
        } catch (SQLException ex) {
            System.out.println("Ajout du Board impossible : " + ex.getMessage());
        }*/
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
