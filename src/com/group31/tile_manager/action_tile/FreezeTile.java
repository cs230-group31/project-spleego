package com.group31.tile_manager.action_tile;

import com.group31.gameboard.Gameboard;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.tile_manager.FloorTile;
import javafx.scene.image.Image;

public class FreezeTile extends ActionTile {

    /**
     * Tile that freezes a player in place.
     * @param currentImage the Tile's image
     * @param id id of the tile
     */
    public FreezeTile(int id, Image currentImage) {
        super(id,  currentImage);
    }

    /**
     * Freezes the target tile and all tiles around it.
     * @param players the players currently in game
     * @param gameboard the board of tiles
     * @param coordX the x coordinate of target tile
     * @param coordY the y coordinate of target tile
     * @return
     */
    public void freezeEffect(Player[] players, Gameboard gameboard, int coordX, int coordY) {
        FloorTile[][] floorTile = gameboard.getBoardState();

        floorTile[coordX + 1][coordY + 1].setOnIce(true);
        floorTile[coordX + 1][coordY - 1].setOnIce(true);
        floorTile[coordX - 1][coordY + 1].setOnIce(true);
        floorTile[coordX - 1][coordY - 1].setOnIce(true);
        floorTile[coordX + 1][coordY].setOnIce(true);
        floorTile[coordX - 1][coordY].setOnIce(true);
        floorTile[coordX][coordY + 1].setOnIce(true);
        floorTile[coordX][coordY - 1].setOnIce(true);

        Logger.log("ice effect activated", Logger.Level.INFO);
    }

}
