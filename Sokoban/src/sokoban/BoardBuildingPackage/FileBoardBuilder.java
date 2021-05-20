/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the BoardBuilder using files.
 *
 * @author jcdru
 */
public class FileBoardBuilder implements BoardBuilder {

    @Override
    public Board build() throws BuilderException {
        Board pasDerreur = new Board("pasDerreur", 0, 0);
        return pasDerreur;
    }
}
