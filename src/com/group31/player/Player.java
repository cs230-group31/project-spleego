package com.group31.player;

import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Player {

    /**
     * Player's name.
     */
    private final String name;

    /**
     * Player's sprite.
     */
    private final Image sprite;

    /**
     * Player's colour.
     */
    private final Color colour;

    /**
     * Player's location.
     */
    private final int[] location;

    /**
     * Stores the player's hand.
     */
    private final ArrayList<Tile> hand = new ArrayList<>();

    /**
     * Stores the previous location of the player.
     */
    private int[] prevLocation;

    /**
     * Stores the previous previous location.
     */
    private int[] prevPrevLocation;

    /**
     * // TODO: better desc for this please.
     * Player constructor.
     * @param name Player's name.
     * @param sprite Player's sprite.
     * @param colour Player's colour.
     * @param location Player's location.
     */
    public Player(String name, Image sprite, Color colour, int[] location) {
        this.name = name;
        this.sprite = sprite;
        this.colour = colour;
        this.location = location;
    }

    /**
     * Gets the player name.
     * @return player name
     */
    public String getPlayerName() {
        return this.name;
    }

    /**
     * Gets the player sprite.
     * @return Player sprite.
     */
    public Image getPlayerSprite() {
        return this.sprite;
    }

    /**
     * Gets the player colour.
     * @return Player colour.
     */
    public Color getPlayerColour() {

        return this.colour;
    }

    /**
     * Gets the player location.
     * @return player location.
     */
    public int[] getPlayerLocation() {

        return this.location;
    }

    /**
     * Gets the player's last turn.
     * @return Player's last turn.
     */
    public int[] getLastTurn() {

        return this.prevLocation;
    }

    /**
     * Gets the player 2 turns back.
     * @return Player last last turn.
     */
    public int[] getLastLastTurn() {
        return this.prevPrevLocation;
    }

    /**
     * Puts a tile on the gameboard.
     * @param tile Tile we are adding to the gameboard.
     * @param insertX X coordinate of the place we will add the tile.
     * @param insertY Y coordinate of the place we will add the tile.
     */
    public void playTile(Tile tile, int insertX, int insertY) {
        // TODO: Put tile on gameboard.
    }

    // TODO: This should go in the controller.
//    /**
//     * will move the player to a new location.
//     *
//     * @param movX coordinate x to move to
//     * @param movY coordinate y to move to
//     */
//    public void movePlayer(int movX, int movY) {
//        this.prevPrevLocation = this.prevLocation;
//        this.prevLocation = this.location;
//        // section above just stores last 2 locations
//
//        this.location[x] = movX;
//        this.location[y] = movY;
//    }

    // TODO: This should be in the controller.
//    /**
//     * Takes a tile from the silkbag and appends it the the player's hand.
//     * @param tile identifier for the object tile
//     */
//    public void drawTile(Tile tile) {
//        hand.add(tile);
//    }

}
