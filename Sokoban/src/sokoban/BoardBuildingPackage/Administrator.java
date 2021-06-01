/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.sql.SQLException;
import java.util.Scanner;
import org.sqlite.SQLiteException;
import sokoban.ExceptionsPackage.AdminLeavesException;
import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the administrator of the game and his possible
 * actions.
 *
 * @author jcdru
 */
public class Administrator {

    private static final Scanner in = new Scanner(System.in);

    /**
     * The DataBase we are linked to.
     */
    private static DataBase myDatabase;

    private static boolean menu = true;

    private static int i = -1;

    /**
     * The main method of the administrator class.
     *
     * @param args
     * @throws sokoban.ExceptionsPackage.BuilderException
     * @throws java.sql.SQLException
     * @throws sokoban.ExceptionsPackage.AdminLeavesException
     */
    public static void main(String[] args) throws BuilderException, SQLException, AdminLeavesException {
        DataBase myNewDataBase = new DataBase();
        myDatabase = myNewDataBase;
        analyseSequence();
    }

    /**
     * Method used to read the administrator keyboard entry
     *
     * @return
     * @throws sokoban.ExceptionsPackage.AdminLeavesException
     */
    public static String readAdministratorEntry() throws AdminLeavesException {
        System.out.println("Please write here :");
        String returned = in.nextLine().trim();
        if (returned.equalsIgnoreCase("/quit")) {
            quitWithDialog();
        }
        return returned;
    }

    /**
     * Method used to stop the run with a message.
     *
     * @throws sokoban.ExceptionsPackage.AdminLeavesException
     */
    public static void quitWithDialog() throws AdminLeavesException {
        throw new AdminLeavesException("The administrator has left the menu.");
    }

    /**
     * Displays the main menu the administartor uses.
     *
     * @throws sokoban.ExceptionsPackage.AdminLeavesException
     * @throws sokoban.ExceptionsPackage.BuilderException
     * @throws java.sql.SQLException
     */
    public static void analyseSequence() throws AdminLeavesException, BuilderException, SQLException {
        while (menu) {
            try {
                String entry;

                System.out.println("___________________________________________");
                System.out.println("ADMINISTRATION INTERFACE - USE WITH CAUTION");
                System.out.println("___________________________________________");
                System.out.println("1. Create new database");
                System.out.println("2. List boards");
                System.out.println("3. Show board");
                System.out.println("4. Add board from file");
                System.out.println("5. Remove board from database [DANGEROUS]");
                System.out.println("6. Quit.");

                if (i != -1) {
                    entry = readAdministratorEntry();
                } else {
                    entry = "" + i;
                }
                switch (entry) {
                    case "1":
                        i = 1;
                        myDatabase.createDataBase();
                        break;
                    case "2":
                        i = 2;
                        myDatabase.listBoards();
                        break;
                    case "3":
                        i = 3;
                        System.out.println("Board id ?");
                        String boardId = readAdministratorEntry();
                        myDatabase.showBoard(boardId);
                        break;
                    case "4":
                        i = 4;
                        System.out.println("Board id ?");
                        String id = readAdministratorEntry();
                        System.out.println("Write the file path please : ");
                        String path = readAdministratorEntry();
                        FileBoardBuilder theBoard = new FileBoardBuilder(path);
                        Board theFileBuiltBoard = theBoard.build();
                        myDatabase.add(id, theFileBuiltBoard);
                        break;
                    case "5":
                        i = 5;
                        System.out.println("Board id ?");
                        String removeId = readAdministratorEntry();
                        System.out.println("Are you sure about this deletion ?");
                        String confirmation = readAdministratorEntry();
                        if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("y")
                                || confirmation.equalsIgnoreCase("YES")) {
                            myDatabase.remove(removeId);
                        } else {
                            System.out.println("Canceled");
                        }
                        break;
                    case "6":
                        i = 6;
                        quitWithDialog();
                        break;
                }
            } catch (AdminLeavesException e) {
                System.out.println(e.toString());
                menu = false;
            } catch (SQLiteException e) {
                System.out.println("Instruction where the catch has been done : " + i);
                System.out.println(e.toString());
            }
        }
    }
}
