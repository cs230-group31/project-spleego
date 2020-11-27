package com.group31.playerPak;

import com.group31.tile_manager.Tile;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Player {
    /** stores player name.*/
    private String playerName;
    /** stores the player sprite.*/
    private Image playerSprite;
    /** stores the player colour.*/
    private Color colour;
    /** stores the player location.*/
    private int[] playerLocation; // location is kept in array size of 2
    /** stores the playerhand.*/
    private ArrayList<Tile> playerHand = new ArrayList<>();
    /** stores the previous location of the player.*/
    private int[] prevLocation;
    /** stores the previous previous location.*/
    private int[] prevPrevLocation;
    /** refrences which element x is on array for location.*/
    private final int x = 0;
    /** refrences which element y is on array for location.*/
    private final int y = 1; // these are to identify x and y
    /**
     * constructor for Player.
     *
     * @param playerData used to store object PlayerData
     * @param location   used to store location of player in array
     */
    public Player(PlayerData playerData, int[] location) {

        //this.playerData = playerData; (wasnt used for anything)
        this.playerLocation = location;
        this.playerName = playerData.getName();
        this.playerSprite = playerData.getSprite();
    }

    /**
     * gets the player name.
     *
     * @return player name
     */
    public String getPlayerName() {

        return this.playerName;
    }

    /**
     * gets the player sprite.
     *
     * @return player sprite
     */
    public Image getPlayerSprite() {

        return this.playerSprite;
    }

    /**
     * gets the player colour.
     *
     * @return player colour
     */
    public Color getPlayerColour() {

        return this.colour;
    }

    /**
     * gets the player location.
     *
     * @return player location
     */
    public int[] getPlayerLocation() {

        return this.playerLocation;
    }

    /**
     * gets the player's last turn.
     *
     * @return player last turn
     */
    public int[] getLastTurn() {

        return this.prevLocation;
    }

    /**
     * gets the player 2 turns back.
     *
     * @return player last last turn
     */
    public int[] getLastLastTurn() {

        return this.prevPrevLocation;
    }

    /**
     * something.
     *
     * @param theTile s
     * @param locX    s
     * @param locY    s
     */
    public void playTile(Tile theTile, int locX, int locY) {

        //not sure how it will be implemented atm
    }

    /**
     * will move the player to a new location.
     *
     * @param movX coordinate x to move to
     * @param movY coordinate y to move to
     */
    public void movePlayer(int movX, int movY) {

        this.prevPrevLocation = this.prevLocation;
        this.prevLocation = this.playerLocation;
        // section above just stores last 2 locations

        this.playerLocation[x] = movX;
        this.playerLocation[y] = movY;

    }

    /**
     * adds tile from silk bag to playerhand.
     * @param tile identifier for the object tile
     */
    public void drawTile(Tile tile) {

        playerHand.add(tile);

    }
}
