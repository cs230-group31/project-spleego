package com.group31.main;

<<<<<<< HEAD
import com.group31.gameboard.Gameboard;
import com.group31.leaderboard.Leaderboard;
import com.group31.player.Player;
import com.group31.controller.Controller;
import com.group31.settings.Settings;
import com.group31.tile_manager.Silkbag;
import javafx.application.Application;
=======
import com.group31.services.ApiRequest;
import com.group31.services.PuzzleSolver;
>>>>>>> origin/merge-master

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
        Silkbag silkbag = initSilkBag();
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
        int numberOfPlayers = 2;
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i <= numberOfPlayers; i++) {

            players[i] = new Player();

        }

        return players;
    }

    private static void initController(Player[] players,
                                       Gameboard gameboard,
                                       Silkbag silkbag) {

        // start the game.
        Controller controller = new Controller(players, gameboard, silkbag);

    }
}
