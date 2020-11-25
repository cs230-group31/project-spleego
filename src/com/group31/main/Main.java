package com.group31.main;

import com.group31.gameboard.Gameboard;
import com.group31.leaderboard.Leaderboard;
import com.group31.player.Player;
import com.group31.controller.Controller;
import com.group31.settings.Settings;
import com.group31.tileManager.SilkBag;
import javafx.application.Application;

public class Main {

    /**
     * Initialises the components and runs the app.
     *
     * @param args Args passed in at runtime.
     */
    public static void main(String[] args) {
        // TODO: init settings from a file. Awaiting File Manager

        if (args.length != 0) {
            Settings.updateSettings(args);
        }

        Leaderboard leaderboard = initLeaderBoard();
        SilkBag silkbag = initSilkBag();
        Gameboard gameboard = initGameboard();
        Player[] players = initPlayers();

        // start GUI
        Application.launch();

        initController(players, gameboard, silkbag);

    }

    private static Leaderboard initLeaderBoard() {
        // TODO: load stats, initialises, pass back
        return new Leaderboard();
    }

    private static SilkBag initSilkBag() {
        // TODO: loads max tiles, tiles inside if save game etc, initialises with tiles, creates new instance, pass back
        return new SilkBag();
    }

    private static Gameboard initGameboard() {
        // TODO: loads size, saved state if any, initialises, pass back
        return new Gameboard();
    }

    private static Player[] initPlayers() {
        // TODO: loads players if any saved or creates new players (however many are asked for at runtime), pass back

        //set based on settings passed in before game starts.
        int numberOfPlayers = 2;
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i <= numberOfPlayers; i++) {

            players[i] = new Player();

        }

        return players;
    }

    private static void initController(Player[] players,
                                       Gameboard gameboard,
                                       SilkBag silkbag) {

        // start the game.
        Controller controller = new Controller(players, gameboard, silkbag);

    }
}
