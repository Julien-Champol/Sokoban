/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the BoardBuilder using text.
 *
 * @author jcdru
 */
public class TextBoardBuilder implements BoardBuilder {

    /* The description of the board we'll built */
    private final String DESCRIPTION;

    /* The board that we'll finally be returned */
    private Board textBuildBoard;

    /* The width of the board we'll build */
    private int width = 0;

    /* The height of the board we'll build */
    private int height = 0; //So that for the first addRow we start at x = 0

    /* This is the int we use to give coordinates to the character we read from the file */
    private int y = 0; //So that for the first addRow we start at y = 0

    /* A int set to -1 that allows us not to throw an exception on the first loop turn  of addRow */
    int securedInput = -1;

    /**
     * Parameterized builder of the TextBoardBuilder class.
     *
     * @param description the description of the board we want to build.
     */
    public TextBoardBuilder(String description) {
        this.DESCRIPTION = description;
        textBuildBoard = new Board(DESCRIPTION, -1, -1); //Initialized to -1, -1 so that we see it has to be modified.
    }

    /**
     * Method used to add rows to a board.
     *
     * @param row the row describing the Board
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    public void addRow(String row) throws BuilderException {
        width = row.length();
        if (width != securedInput && securedInput != -1) {
            throw new BuilderException("Row must be a regular int");
        } else {
            securedInput = width;
            height++;
            for (var c : row.toCharArray()) {
                switch (c) {
                    case 'C':
                        this.textBuildBoard.addBox(height, y);
                        y++;
                        break;
                    case 'x':
                        this.textBuildBoard.addTarget(height, y);
                        y++;
                        break;
                    case 'P':
                        Point playerPosition = new Point(height, y);
                        this.textBuildBoard.setPlayerPosition(playerPosition);
                        y++;
                        break;
                    case '.':
                        y++;
                        break;
                }
            }
        }
    }

    /**
     * Method used to return the Board built.
     *
     * @return an instance of Board
     * @throws sokoban.ExceptionsPackage.BuilderException
     */
    @Override
    public Board build() throws BuilderException {
        this.textBuildBoard.setHeight(height);
        this.textBuildBoard.setWidth(width);
        this.textBuildBoard.addHorizontalWall(0, 0, width); //We always add walls on the boarders of the board, safety measure.
        this.textBuildBoard.addVerticalWall(0, 0, height);
        this.textBuildBoard.addVerticalWall(height - 1, width - 1, height);
        this.textBuildBoard.addHorizontalWall(0, width - 1, width);
        return this.textBuildBoard;
    }
}
