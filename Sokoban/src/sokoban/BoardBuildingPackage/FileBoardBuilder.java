/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the BoardBuilder using files.
 *
 * @author jcdru
 */
public class FileBoardBuilder implements BoardBuilder {

    /**
     * The path to the file.
     */
    private final String PATH;

    /**
     * Parameterized constructor of the fileBoardBuilder class.
     *
     * @param filePath the path to the file we want to read
     */
    public FileBoardBuilder(String filePath) {
        this.PATH = filePath;
    }

    /**
     * Method used to read a file and build a board from its lines.
     *
     * @return the textBoardBuilder we have just built
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    public TextBoardBuilder fileReader() throws BuilderException {
        TextBoardBuilder fileBuiltBoard = new TextBoardBuilder("No name found");
        int i = 0;
        try (Scanner scanner = new Scanner(new File(PATH))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (i == 0 && !line.contains("#")) {
                    fileBuiltBoard = new TextBoardBuilder(line);
                    i = -1;
                } else {
                    fileBuiltBoard.addRow(line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found : " + e);
        }
        return fileBuiltBoard;
    }

    /**
     * Method used to return the Board we have built.
     *
     * @return an instance of Board
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    @Override
    public Board build() throws BuilderException {
        return fileReader().build();
    }
}
