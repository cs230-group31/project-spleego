package com.group31.main;

import com.group31.controller.Controller;
import com.group31.settings.Settings;

public final class Main {

    /*
    Initialises the components and runs the app.
     */
    public static void main(final String[] args) {
        // TODO: init settings from a file.

        if (args.length != 0) {
            Settings.updateSettings(args);
        }

        Leaderboard leaderboard = initLeaderBoard();
        Silkbag silkbag = initSilkBag();
        Gameboard gameboard = initGameboard();
        Player[] players = initPlayers();

        initController(players, gameboard, silkbag, leaderboard);

        // TODO: draw GUI

        // TODO: Get user preferences before creating controller.

    }

    private static Leaderboard initLeaderBoard() {
        // TODO: load stats, initialises, pass back
        return new Leaderboard();
    }

    private static Silkbag initSilkBag() {
        // TODO: loads max tiles, tiles inside if save game etc, initialises with tiles, creates new instance, pass back
        return new Silkbag();
    }

    private static Gameboard initGameboard() {
        // TODO: loads size, saved state if any, initialises, pass back
        return new Gameboard();
    }

    private static Player[] initPlayers() {
        // TODO: loads players if any saved or creates new players (however many are asked for at runtime), pass back

        //set based on settings passed in before game starts.
        int numberOfPlayers = 0;
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i <= numberOfPlayers; i++) {

            players[i] = new Player();

        }

        return players;
    }

    private static void initController(Player[] players,
                                       Gameboard gameboard,
                                       Silkbag silkbag,
                                       Leaderboard leaderboard) {

        // start the game.
        Controller controller = new Controller(players, gameboard, silkbag, leaderboard);
    }
}