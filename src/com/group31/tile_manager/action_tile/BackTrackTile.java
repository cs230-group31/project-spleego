package com.group31.tile_manager.action_tile;

import com.group31.gameboard.Gameboard;
import com.group31.player.Player;
import com.group31.tile_manager.FloorTile;
import javafx.scene.image.Image;

public class BackTrackTile extends ActionTile {
    /**
     * Coord X.
     */
    public static final int X = 0;
    /**
     * Coord Y.
     */
    public static final int Y = 1;
    /**
     * Tile that forces a user back two turns.
     * @param currentImage Tile's image.
     * @param id id of the tile
     */
     public BackTrackTile(int id, Image currentImage) {
         super(id,  currentImage);
     }
    /**
     * this method implement the backTrack function, which moves back the player
     * to the position two turns ago, if in the way there are tiles on fire it can't
     * get through those tiles.
     * @param player the player who activate the actionTile.
     * @param board to get the gameboard status and check the tiles needed.
     * @return the new position of the player.
     */
    public int[] backTrack(Player player, Gameboard board) {
        int[] newCoords;
        int[] lastTurn = player.getLastTurn();
        int[] lastLastTurn = player.getLastLastTurn();
        FloorTile[][] gameboard = board.getBoardState();
        FloorTile lastTurnTile = gameboard[lastTurn[X]][lastTurn[Y]];
        FloorTile lastLastTurnTile = gameboard[lastLastTurn[X]][lastLastTurn[Y]];

        if (lastTurnTile.isOnFire()) {
            newCoords = player.getCurrentLocation();
        } else if (lastLastTurnTile.isOnFire()) {
            newCoords = lastTurn;
        } else {
            newCoords = lastLastTurn;
        }
        return newCoords;
    }
}
