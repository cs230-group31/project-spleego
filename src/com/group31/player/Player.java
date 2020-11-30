package com.group31.player;

import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {

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
     * Player's current location on the board.
     */
    private int[] location;

    /**
     * Starting location of the player.
     */
    private final int[] startingLocation;

    /**
     * Stores the player's hand.
     */
    private final ArrayList<Tile> hand = new ArrayList<>();

    /**
     * Stores the previous two turns. (Where they moved from during the past two turns).
     */
    private int[] prevTwoTurns;

    /**
     * Class for a player who has or is playing the game.
     * @param name Player's name.
     * @param sprite Player's sprite.
     * @param colour Player's colour.
     * @param startingLocation Player's location.
     */
    public Player(String name, Image sprite, Color colour, int[] startingLocation) {
        this.name = name;
        this.sprite = sprite;
        this.colour = colour;
        this.startingLocation = startingLocation;
    }

    /**
     * Gets the player name.
     * @return player name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the player sprite.
     * @return Player sprite.
     */
    public Image getSprite() {
        return this.sprite;
    }

    /**
     * Gets the player colour.
     * @return Player colour.
     */
    public Color getColour() {

        return this.colour;
    }

    /**
     * Gets the player location.
     * @return player location.
     */
    public int[] getCurrentLocation() {

        return this.location;
    }

    /**
     * Gets the player's past two turns.
     * @return Player's last two turns.
     */
    public int[] getPrevTwoTurns() {
        return this.prevTwoTurns;
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
