package com.group31.main;

import com.group31.graphics.GUI;
import com.group31.services.ApiRequest;
import com.group31.services.PuzzleSolver;
import com.group31.controller.Controller;
import com.group31.settings.Settings;
import javafx.application.Application;

public class Main {

    /**
     * Initialises the components and runs the app.
     *
     * @param args Args passed in at runtime.
     */
    public static void main(String[] args) {
        // TODO: init settings from a file.

        if (args.length != 0) {
            Settings.updateSettings(args);
        }

        Leaderboard leaderboard = initLeaderBoard();
        Silkbag silkbag = initSilkBag();
        Gameboard gameboard = initGameboard();
        Player[] players = initPlayers();

        initController(players, gameboard, silkbag, leaderboard);

        // start GUI
        Application.launch();

        // TODO: Get user preferences before creating controller.

        // Testing:
        System.out.println(getMotd("http://cswebcat.swansea.ac.uk/", "puzzle", "message",
                "?solution="));
    }

    /**
     * Gets the MOTD.
     * @param urlBase URL base for the API.
     * @param puzzleRoute The URL route of the puzzle text.
     * @param messageRoute The URL route of the message text.
     * @param tokenIdentifier The tag in the URL that identifies the token.
     * @return The response from the API as a string.
     */
    private static String getMotd(String urlBase, String puzzleRoute, String messageRoute,
                                  String tokenIdentifier) {
        ApiRequest request = new ApiRequest(urlBase, puzzleRoute);
        String puzzle = PuzzleSolver.solvePuzzle(request.getResponse());
        return new ApiRequest(urlBase, messageRoute, puzzle, tokenIdentifier).getResponse();
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
