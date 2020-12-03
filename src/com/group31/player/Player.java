package com.group31.player;

import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

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
     * UUID of an instance of the Player class.
     */
    private final String instanceUuid;

    /**
     * Stores where the player was a turn ago.
     */
    private int[] lastTurn;
    /**
     * Stores where the player was two turns ago.
     */
    private int[] lastLastTurn;

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
        this.instanceUuid = UUID.randomUUID().toString();
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
     * Moves the player to the location x,y in the gameboard.
     * @param x x coordinate of the destination.
     * @param y y coordinate of the destination.
     */
    public void setLocation(int x, int y) {
        this.location = new int[]{x, y};
    }

    /**
     * Puts a tile on the gameboard.
     * @param tile Tile we are adding to the gameboard.
     * @param insertX X coordinate of the place we will add the tile.
     * @param insertY Y coordinate of the place we will add the tile.
     */
    public void playTile(Tile tile, int insertX, int insertY) {
    }

    /**
     * Gets this instance's UUID.
     * @return This instance's UUID.
     */
    public String getUuid() {
        return this.instanceUuid;
    }

    /**
     * @return where the player was 1 turn ago.
     */
    public int[] getLastTurn() {
        return lastTurn;
    }

    /**
     * @return where the player was 2 turns ago.
     */
    public int[] getLastLastTurn() {
        return lastLastTurn;
    }


}
