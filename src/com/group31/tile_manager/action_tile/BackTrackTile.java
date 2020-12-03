package com.group31.tile_manager.action_tile;

import com.group31.gameboard.Gameboard;
import com.group31.player.Player;
import com.group31.tile_manager.FloorTile;
import javafx.scene.image.Image;

public class BackTrackTile extends ActionTile {

    /**
     * Tile that forces a user back two turns.
     * @param currentImage Tile's image.
     */
     public BackTrackTile(Image currentImage) {
        super(currentImage);
     }

     public int[] backTrack(Player player, Gameboard board){
         int[] newCoords;
         int[] lastTurn = player.getLastTurn();
         int[] lastLastTurn = player.getLastLastTurn();
         FloorTile[][] gameboard = board.getBoardState();
         FloorTile lastTurnTile = gameboard[lastTurn[FloorTile.X]][lastTurn[FloorTile.Y]];
         FloorTile lastLastTurnTile = gameboard[lastLastTurn[FloorTile.X]][lastLastTurn[FloorTile.Y]];

         if (lastTurnTile.isOnFire() || lastTurnTile.isOnIce()){
             newCoords = lastLastTurn;
         }else{
             newCoords = lastTurn;
         }
         return newCoords;
     }
}
