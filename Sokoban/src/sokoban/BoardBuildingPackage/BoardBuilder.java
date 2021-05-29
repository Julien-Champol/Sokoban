/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.BoardBuildingPackage;

import sokoban.ExceptionsPackage.BuilderException;

/**
 * Class representing data about the BoardBuilder interface. We will use the
 * interface to load data about a board and to build it.
 *
 * @author jcdru
 */
public interface BoardBuilder {

    public Board build() throws BuilderException;

}
