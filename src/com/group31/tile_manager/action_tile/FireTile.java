package com.group31.tile_manager.action_tile;

import com.group31.gameboard.Gameboard;
import com.group31.player.Player;
import com.group31.logger.Logger;
import com.group31.tile_manager.FloorTile;
import javafx.scene.image.Image;

public class FireTile extends ActionTile {

    /**
     * Fire tile ensures no player can step on a tile that is on fire, because it's on fire.
     * @param currentImage Image of the tile.
     * @param id id of the tile
     */
    public FireTile(int id, Image currentImage) {
        super(id,  currentImage);
    }

    /**
     * This method activates fire effect by setting all tiles in a 3x3 area on fire. It does not work if player is
     * in the area.
      * @param players gives the array of players currently playing the game
     * @param gameboard the class gameboard used to change the floortile states
     * @param coordX chosen coordinate on x axis where the tile is to be placed
     * @param coordY chosen coordinate on y axis where the tile is to be placed
     * @return returns the new Board state after the fire effect has been applied
     * */
    public FloorTile[][] fireEffect(Player[] players, Gameboard gameboard, int coordX, int coordY) {
        FloorTile[][] floorTile = gameboard.getBoardState();
        //validates the placement of the firetile using the meathod bellow
        boolean valid = firePlacementValidation(players, coordX, coordY);
        // if true changes the board state if not the state remains the same.
        if (valid) {
            floorTile[coordX + 1][coordY + 1].setOnFire(true);
            floorTile[coordX + 1][coordY - 1].setOnFire(true);
            floorTile[coordX - 1][coordY + 1].setOnFire(true);
            floorTile[coordX - 1][coordY - 1].setOnFire(true);
            floorTile[coordX + 1][coordY].setOnFire(true);
            floorTile[coordX - 1][coordY].setOnFire(true);
            floorTile[coordX][coordY + 1].setOnFire(true);
            floorTile[coordX][coordY - 1].setOnFire(true);
            Logger.log("fire effect activated", Logger.Level.INFO);
        } else {
            Logger.log("Not a valid placement of fire effect!", Logger.Level.INFO);
        }

        return floorTile; // not sure if I even need to return floorTile because I think it has copied its reference
        //could return boolean instead? to check if the tile has been placed.


    }

    /**
     * this checks wheather the placement of the fire effect is valid or not.
     * @param players gives the array of players currently playing the game
     * @param coordX chosen coordinate on x axis where the tile is to be placed
     * @param coordY chosen coordinate on y axis where the tile is to be placed
     * @return a boolean valid which determines if the placement is valid or not
     */
    public boolean firePlacementValidation(Player[] players, int coordX, int coordY) {

        boolean valid = false; // this is used to determine whether coordinates are place in valid area

        int elemX = 0;
        int elemY = 1; // use to determine what element the x and y coordinates are on

        for (int i = 0; i <= players.length - 1; i++) {
            int[] coords = players[i].getCurrentLocation();
            /* looks through coordinates by a 3X3 area around chosen coordinates for any players
            nearby to ensure no players are around */
            if (((coords[elemX] == coordX) && (coords[elemY] == coordY))
                    || ((coords[elemX] == coordX + 1) && (coords[elemY] == coordY + 1))
                    || ((coords[elemX] == coordX + 1) && (coords[elemY] == coordY - 1))
                    || ((coords[elemX] == coordX - 1) && (coords[elemY] == coordY - 1))
                    || ((coords[elemX] == coordX - 1) && (coords[elemY] == coordY + 1))
                    || ((coords[elemX] == coordX + 1) && (coords[elemY] == coordY))
                    || ((coords[elemX] == coordX - 1) && (coords[elemY] == coordY))
                    || ((coords[elemX] == coordX) && (coords[elemY] == coordY + 1))
                    || ((coords[elemX] == coordX) && (coords[elemY] == coordY - 1))) {
                valid = false;
            } else {
                valid = true;
            }
        }

        return valid;
    }

}
