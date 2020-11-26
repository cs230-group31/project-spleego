package com.group31.playerPak;

import javafx.scene.image.Image;

public class PlayerData {

    /**
     * stores players name.
     */
    private String name;
    /**
     * stores players sprite.
     */
    private Image sprite;
    /**
     * stores players wins.
     */
    private int wins;
    /**
     * stores players losses.
     */
    private int losses;
    /**
     * stores the number of time the player has played.
     */
    private int played;

    /**
     *  constructor for PlayerData.
     * @param name name used for player name
     * @param sprite image for playerSprite
     */
    public PlayerData(String name, Image sprite) {

        this.name = name;
        this.sprite = sprite;
        this.wins = 0;
        this.losses = 0;
        this.played = 0;
    }

    /**
     * gets player name.
     * @return name
     */
    public String getName() {

        return this.name;
    }
    /**
     * gets player sprite.
     * @return sprite
     */
    public Image getSprite() {

        return this.sprite;
    }
    /**
     * gets player wins.
     * @return wins
     */
    public int getWins() {

        return this.wins;
    }
    /**
     * gets player losses.
     * @return losses
     */
    public int getLosses() {

        return this.losses;
    }
    /**
     * gets the number of time the player has played.
     * @return played
     */
    public int getPlayed() {

        return this.played;
    }

    /**
     * checks if player has won or not.
     * @param didWin boolean for if won
     */
    public void setWinOrLoss(boolean didWin) {

        if (didWin) {
            wins++;
        } else {
            losses++;
        }

        played++;

    }



}
