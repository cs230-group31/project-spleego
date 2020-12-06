package com.group31.player;

import com.group31.controller.Controller;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerProfile implements Serializable {

    // USE SIMPLE THING FROM JFX FOR LEADERBOARD, LET THEM CO-EXIST WITH
    // these vars, and when these vars are changed just call a method to
    // update the simplex vars as well.

    private final String name;

    private int wins;

    private int losses;

    private int gamesPlayed;

    private ArrayList<String> gamesParticipating;

    public PlayerProfile(String name) {
        this.name = name;

        this.wins = 0;
        this.losses = 0;
        this.gamesPlayed = 0;
    }

    public String getName() {
        return this.name;
    }

    public void incrementGamesWon() {
        this.wins++;
        incrementGamesPlayed();
    }

    public void incrementGamesLost() {
        this.losses++;
        incrementGamesPlayed();
    }

    private void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public ArrayList<String> getGamesParticipating() {
        return gamesParticipating;
    }

    public void addPlayerToGame(Controller controller) {
        this.gamesParticipating.add(controller.getUuid());
    }
}
