package com.group31.player;

import com.group31.controller.Controller;
import com.group31.services.serializer.Serializer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Liam,Moe
 */
public class PlayerProfile implements Serializable {
    /**
     * Player's name.
     */
    private final String name;
    /**
     * Amount of wins the player has.
     */
    private int wins;
    /**
     * Amount of losses the player has.
     */
    private int losses;
    /**
     * Amount of games the player has played.
     */
    private int gamesPlayed;
    /**
     * UUIDs of the games that the player has participated in.
     */
    private ArrayList<String> gamesParticipating;

    /**
     * Set their wins, losses, name and games played.
     * @param name
     */
    public PlayerProfile(String name) {
        this.name = name;

        this.wins = 0;
        this.losses = 0;
        this.gamesPlayed = 0;
    }

    /**
     * Returns the players name.
     * @return the players name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Increments the number of games won as well as incrementing the number of games played by 1.
     */
    public void incrementGamesWon() {
        this.wins++;
        incrementGamesPlayed();
    }

    /**
     * Increments the number of games lost as well as incrementing the number of games played by 1.
     */
    public void incrementGamesLost() {
        this.losses++;
        incrementGamesPlayed();
    }

    /**
     * Increments the number of games played by 1.
     */
    private void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    /**
     * Returns the number of games played.
     * @return the number of games played
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Returns the number of losses.
     * @return the number of losses
     */
    public int getLosses() {
        return losses;
    }

    /**
     * Returns the number of wins.
     * @return the number of wins
     */
    public int getWins() {
        return wins;
    }

    /**
     * Returns the number of games the player is participating in.
     * @return the number of games the player is participating in
     */
    public ArrayList<String> getGamesParticipating() {
        return gamesParticipating;
    }

    /**
     * Adds a player to a game.
     * @param controller controller used to add player to a game.
     */
    public void addPlayerToGame(Controller controller) {
        this.gamesParticipating.add(controller.getUuid());
    }

    /**
     * Saves a PlayerProfile to the file system.
     */
    public void save() {
        String object = "player";
        String name = String.format("PlayerProfile_%s", this.name);
        Serializer.serialize(this, name, object);
    }

}
