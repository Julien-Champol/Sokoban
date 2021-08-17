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
import org.sqlite.SQLiteErrorCode;
import org.sqlite.SQLiteException;
import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the data base containing the boards.
 *
 * @author jcdru
 */
public class DataBase {

    private final Connection actualConnection;

    /**
     * The path to the database.
     */
    private final String PATH = "D:\\DUT\\Informatique\\AlgoProg\\sokobanchampol\\Sokoban\\data\\librairie.sqlite3";

    private final String URL = "jdbc:sqlite:" + PATH;

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
     * Method loading the driver and establishing a connection
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
     * Method used to create the tables BOARD and ROWS of the dataBase.
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
            System.out.println("Creating BOARDS impossible : " + e.toString());
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
            System.out.println("Creating ROWS impossible : " + e.toString());
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
            System.out.println("Adding in Boards impossible : " + e.getMessage());
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
            System.out.println("Adding in ROWS impossible : " + e.getMessage());
        }
    }

    /**
     * Method used to remove a board from the dataBase using its board_id
     *
     * @param id the id of the board to remove
     * @throws org.sqlite.SQLiteException
     */
    public void remove(String id) throws SQLiteException {

        //Checking the id
        if (!this.contains(id)) {
            throw new SQLiteException("Board not found try again", SQLiteErrorCode.SQLITE_OK);
        }

        // Deletion of the board from the BOARDS table
        String sql = "DELETE FROM BOARDS WHERE BOARDS.board_id = ?";
        try (PreparedStatement ps = actualConnection.prepareStatement(sql);) {
            ps.setString(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println("Remove from Boards impossible : " + e.getMessage());
        }

        // Deletion of the board from the ROWS table
        String sql2 = "DELETE FROM ROWS WHERE ROWS.board_id = ?";
        try (PreparedStatement ps2 = actualConnection.prepareStatement(sql2);) {
            ps2.setString(1, id);
            ps2.execute();
        } catch (SQLException e) {
            System.out.println("Remove from rows impossible : " + e.getMessage());
        }

        System.out.println("Removed from database.");
    }

    /**
     * Method used to list and display the boards contained in the data base
     *
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    public void listBoards() throws BuilderException {
        String sql = "SELECT * FROM BOARDS";
        try (Statement ps = actualConnection.createStatement();) {
            ResultSet r = ps.executeQuery(sql);
            while (r.next()) {
                if (r.getString(1) != null) {
                    System.out.println("_______________________________________________________________________________");
                    System.out.println("Board id : " + "     Name : " + "    nb_rows: " + " nb_cols: ");
                    System.out.println(r.getString(1) + "      " + r.getString(2) + "    " + r.getInt(3) + "          " + r.getInt(4));
                    showBoard(r.getString(1));
                }

            }
        } catch (SQLException e) {
            System.out.println("List displaying impossible : " + e.getMessage());
        }
    }

    /**
     * Method used to show a particular board contained in the database
     *
     * @param id
     * @throws sokoban.ExceptionsPackage.BuilderException
     * @throws org.sqlite.SQLiteException
     */
    public void showBoard(String id) throws BuilderException, SQLiteException {
        System.out.println("_______________________________________________________________________________");
        this.get(id).displayBoard();
    }

    /**
     * Method used to pull a board from the database
     *
     * @param id the board's id
     * @return an isntance of the Board class
     * @throws sokoban.ExceptionsPackage.BuilderException
     * @throws org.sqlite.SQLiteException
     */
    public Board get(String id) throws BuilderException, SQLiteException {

        //Checking the id
        if (!this.contains(id)) {
            throw new SQLiteException("Board not found try again", SQLiteErrorCode.SQLITE_OK);
        }

        // TextBoardBuilder initialization with the Board's name.
        TextBoardBuilder theBoardWeGet = new TextBoardBuilder("tempo");
        String sql = "SELECT BOARDS.name FROM BOARDS WHERE BOARDS.board_id = ?";
        try (PreparedStatement ps = actualConnection.prepareStatement(sql);) {
            ps.setString(1, id);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                if (r.getString(1) != null) {
                    theBoardWeGet = new TextBoardBuilder(r.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Name pulling impossible : " + e.getMessage());
        }

        String sql2 = "SELECT ROWS.description FROM ROWS WHERE ROWS.board_id = ?";
        try (PreparedStatement ps2 = actualConnection.prepareStatement(sql2);) {
            ps2.setString(1, id);
            ResultSet r = ps2.executeQuery();
            while (r.next()) {
                if (r.getString(1) != null) {
                    theBoardWeGet.addRow(r.getString(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Rows pulling impossible : " + e.getMessage());
        }
        return theBoardWeGet.build();
    }

    /**
     * Boolean method used to tell if the given id is contained in the dataBase
     *
     * @param id
     * @return
     */
    public boolean contains(String id) {
        boolean contained = false;
        String sql = "SELECT BOARDS.board_id FROM BOARDS WHERE BOARDS.board_id = ?";
        try (PreparedStatement ps = actualConnection.prepareStatement(sql);) {
            ps.setString(1, id);
            ResultSet r = ps.executeQuery();
            while (r.next()) {
                if (r.getString(1) != null && r.getString(1).contains(id)) {
                    contained = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Name pulling impossible : " + e.getMessage());
        }
        return contained;
    }
}
