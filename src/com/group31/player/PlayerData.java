package com.group31.player;

import javafx.scene.image.Image;

public class PlayerData {

    /**
     * Player to store data about.
     */
    private final Player player;
    /**
     * Stores players name.
     */
    private String name;
    /**
     * Stores players sprite.
     */
    private Image sprite;
    /**
     * Stores players wins.
     */
    private int wins;
    /**
     * Stores players losses.
     */
    private int losses;
    /**
     * Stores the number of time the player has played.
     */
    private int played;

    /**
     * Player data constructor.
     * @param player Player to store data about.
     */
    public PlayerData(Player player) {
        this.player = player;
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
