package com.group31.player;

import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Salar, Zero, Liam, Aaron
 */
public class Player implements Serializable {

    /**
     * Player's name.
     */
    private final String name;

    /**
     * Player's sprite.
     */
    private transient Image sprite;

    /**
     * Player's current location on the board.
     */
    private int[] location;

    /**
     * Stores the player's hand.
     */
    private final ArrayList<Tile> hand;

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
     * Number of wins the Player has.
     */
    private int wins;

    /**
     * Number of losses the Player has.
     */
    private int losses;

    /**
     * Number of games the player has played.
     */
    private int gamesPlayed;

    /**
     * Class for a player who has or is playing the game.
     * @param name human name of the player
     * @param sprite player's picture
     * @param location player's current location on the board
     */
    public Player(String name, Image sprite, int[] location) {
        this.name = name;
        this.sprite = sprite;
        this.location = location;

        this.instanceUuid = UUID.randomUUID().toString();
        this.wins = 0;
        this.losses = 0;
        this.gamesPlayed = 0;
        this.hand = new ArrayList<>();
    }

    /**
     * Class for a player who has or is playing the game.
     * @param name human name of the player
     * @param location player's current location on the board
     */
    public Player(String name, int[] location) {
        this.name = name;
        this.instanceUuid = UUID.randomUUID().toString();
        this.wins = 0;
        this.losses = 0;
        this.gamesPlayed = 0;
        this.hand = new ArrayList<>();
        this.location = location;

        this.sprite = null;
    }

    /**
     * Returns the player's name.
     * @return the player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the player's wins.
     * @return the player's wins
     */
    public int getWins() {
        return this.wins;
    }

    /**
     * Return the player's losses.
     * @return the player's losses
     */
    public int getLosses() {
        return this.losses;
    }

    /**
     * Returns the number of games a player has participated in.
     * @return the number of games a player has participated in
     */
    public int getGamesPlayed() {
        return this.gamesPlayed;
    }

    /**
     * Increment the number of wins a player has.
     */
    public void incrementWins() {
        this.wins++;
        setGamesPlayed();
    }

    /**
     * Sets the Player's sprite.
     * @param sprite image to set as the player's sprite
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    /**
     * Increment the number of wins a player has.
     */
    public void incrementLosses() {
        this.losses++;
        setGamesPlayed();
    }

    /**
     * Sets the number of games played by a player.
     */
    private void setGamesPlayed() {
        this.gamesPlayed = this.wins + this.losses;
    }

    /**
     * Returns the player's sprite.
     * @return the player's sprite
     */
    public Image getSprite() {
        return this.sprite;
    }

    /**
     * Returns the player location.
     * @return the player location
     */
    public int[] getCurrentLocation() {
        return this.location;
    }

    /**
     * Moves the player to the location x,y in the gameboard.
     * @param x x coordinate of the destination
     * @param y y coordinate of the destination
     */
    public void setLocation(int x, int y) {
        this.location = new int[]{x, y};
    }

    /**
     * Puts a tile on the gameboard.
     * @param tile tile we are adding to the gameboard
     * @param insertX x coordinate of the place we will add the tile
     * @param insertY y coordinate of the place we will add the tile
     */
    public void playTile(Tile tile, int insertX, int insertY) {
    }

    /**
     * Recieves a Tile and adds it to this player's hand.
     * @param tile the tile being added to the player's hand
     */
    public void recieveTile(Tile tile) {
        hand.add(tile);
    }

    /**
     * Returns the hand of the player.
     * @return the player's hand
     */
    public ArrayList<Tile> getHand() {
        return this.hand;
    }

    /**
     * Returns an instance's UUID.
     * @return an instance's UUID
     */
    public String getUuid() {
        return this.instanceUuid;
    }

    /**
     * Returns the co-ordinates of the player 1 turn ago.
     * @return the co-ordinates of the player 1 turn ago
     */
    public int[] getLastTurn() {
        return lastTurn;
    }

    /**
     * Returns the co-ordinates of the player 2 turns ago.
     * @return the co-ordinates of the player 2 turns ago
     */
    public int[] getLastTwoTurns() {
        return lastLastTurn;
    }


}
