package com.group31.main;

import com.group31.controller.Controller;
import com.group31.settings.Settings;

public class Main {

    Controller controller;

    public static void main(String[] args) {

        // init settings from a file

        if (args.length == 0) {
            Settings.updateSettings(args);
        }
    }

    private static Leaderboard initLeaderBoard() {
        // load stats, initialises, pass back
    }

    private static Silkbag initSilkBag() {
        // loads max tiles, tiles inside if save game etc, initialises with tiles, creates new instance, pass back
    }

    private static Gameboard initGameboard() {
        // loads size, saved state if any, initialises, pass back
    }

    private static Player initPlayers() {
        // loads players if any saved or creates new players (however many are asked for at runtime), pass back
    }

    private static void initController(Player[] players,
                                       Gameboard gameboard,
                                       Silkbag silkbag,
                                       Leaderboard leaderboard) {
        // from here go to the controller, don't come back to main.
    }

}
