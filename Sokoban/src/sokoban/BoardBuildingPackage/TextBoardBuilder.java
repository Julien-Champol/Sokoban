/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import java.awt.Point;
import sokoban.ExceptionsPackage.BuilderExcpetion;

/**
 * Class representing data about the BoardBuilder using text.
 *
 * @author jcdru
 */
public class TextBoardBuilder implements BoardBuilder {

    private final String DESCRIPTION;

    private Board textBuildBoard;

    private int width;
    private int x = 0;
    private int y = 0;

    public TextBoardBuilder(String description) {
        this.DESCRIPTION = description;
        textBuildBoard = new Board(DESCRIPTION, -1, -1); //Initialized to -1, -1 so that we see it has to be modified.
    }

    /**
     * Method used to add rows to a board.
     *
     * @param row
     */
    public void addRow(String row) {
        width = row.length();
        y++;
        for (var c : row.toCharArray()) {
            switch (c) {
                case 'C':
                    this.textBuildBoard.addBox(x, y);
                case 'x':
                    this.textBuildBoard.addTarget(x, y);
                case 'P':
                    Point playerPosition = new Point(x, y);
                    this.textBuildBoard.setPlayerPosition(playerPosition);
            }
        }
    }

    @Override
    public Board build() throws BuilderExcpetion {
        this.textBuildBoard.addHorizontalWall(0, 0, width);
        Board pasDerreur = new Board("pasDerreur", 0, 0);
        return pasDerreur;
    }
}
