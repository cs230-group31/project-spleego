package com.group31.main;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.MainMenu;
import com.group31.leaderboard.Leaderboard;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.controller.Controller;
import com.group31.services.FileManager;
import com.group31.settings.DefaultSettings;
import com.group31.settings.Settings;
import com.group31.tile_manager.Tile;
import com.group31.tile_manager.silk_bag.SilkBag;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    /**
     * Specifies which environment the app is running in.
     */
    // Either 'dev' for development or 'prod' for production.
    // Behaviour changes when set to 'dev': settings regenerated.
    private static final String ENV = "dev";

    /**
     * Initialises the components and runs the app.
     * @param args Args passed in at runtime.
     */
    public static void main(String[] args) {
        // Initialise settings.
        HashMap<String, String> settings = initSettings();
        Settings.setAllSettings(settings);

        if (ENV.equals("dev")) {
            Settings.setAllSettings(DefaultSettings.getDefaultSettings());
        }

        // Testing.
        Settings.dumpSettingsToConsole();

        // Initialise components.
        Leaderboard leaderboard = initLeaderBoard();
        SilkBag silkbag = initSilkBag();
        Gameboard gameboard = initGameboard();
        try {
            gameboard.genBoard(silkbag);
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        Player[] players = initPlayers();

        // Initialise controller.
        initController(players, gameboard, silkbag);

        // Start GUI.
        String[] launchArgs = {};
        MainMenu.run(launchArgs);
    }

    /**
     * Initialises settings.
     * @return HashMap containing all settings.
     */
    private static HashMap<String, String> initSettings() {
        // Allow the file manager to create the requested directory.
        final boolean ALLOW_FILE_CREATION = true;

        // Requested directory.
        final String SETTINGS_DIRECTORY = "data/settings/";
        final String SETTINGS_FILE_NAME = "settings.txt";
        try {
            FileManager.setDirectory(SETTINGS_DIRECTORY, ALLOW_FILE_CREATION);
            if (!FileManager.fileExists(SETTINGS_FILE_NAME)) {
                FileManager.write(DefaultSettings.getDefaultSettingsArray(), SETTINGS_FILE_NAME);
                return DefaultSettings.getDefaultSettings();
            } else {
                return getSettingsFromArray(FileManager.read(SETTINGS_FILE_NAME));
            }
        } catch (NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        Logger.log("Failed to save settings to a file, using default settings.", Logger.Level.WARNING);

        // If no settings can be loaded from a file, use the default settings.
        return DefaultSettings.getDefaultSettings();
    }

    /**
     * Builds Hashmap of settings from array.
     * @param settingsArray Array of settings.
     * @return Hashmap of settings.
     */
    private static HashMap<String, String> getSettingsFromArray(String[] settingsArray) {
        HashMap<String, String> settings = new HashMap<>();
        final int SETTING_KEY = 0;
        final int SETTING_VALUE = 1;
        final String DELIMITER = ";";
        for (String setting : settingsArray) {
            String[] settingNameValue = setting.split(DELIMITER);
            settings.put(settingNameValue[SETTING_KEY], settingNameValue[SETTING_VALUE]);
        }
        return settings;
    }

    /**
     * Initialises the Leaderboard.
     * @return New instance of Leaderboard.
     */
    private static Leaderboard initLeaderBoard() {
        // TODO: load stats, initialises, pass back
        return null; //new Leaderboard("");
    }

    /**
     * Initialises SilkBag.
     * @return A new instance of SilkBag.
     */
    private static SilkBag initSilkBag() {
        // TODO: loads max tiles, tiles inside if save game etc, initialises with tiles, creates new instance, pass back
        final int MAX_TILES = Settings.getSettingAsInt("max_tiles");
        return new SilkBag(MAX_TILES);
    }

    /**
     * Initialises Gameboard.
     * @return A new instance of Gameboard.
     */
    private static Gameboard initGameboard() {
        // TODO: loads size, saved state if any, initialises, pass back
        // TODO: board_rows/cols should be based on either predefined board size or randomly generated board size
        final int BOARD_ROWS = 5;
        final int BOARD_COLS = 5;
        return new Gameboard(BOARD_ROWS, BOARD_COLS);
    }

    /**
     * Initialises players.
     * @return New instance of player depending on how many players are in the game.
     */
    private static Player[] initPlayers() {
        // TODO: loads players if any saved or creates new players (however many are asked for at runtime), pass back
        // TODO: Set based on settings passed in before game starts.
        final int NUMBER_OF_PLAYERS = 2;
        Player[] players = new Player[NUMBER_OF_PLAYERS];

        for (int i = 0; i <= NUMBER_OF_PLAYERS - 1; i++) {
            players[i] = new Player(null, null, null, null);
        }

        return players;
    }

    /**
     * Initialises controller.
     * @param players Array of players that are playing (2-4).
     * @param gameboard Instance of the Gameboard.
     * @param silkbag Instance of the SilkBag
     */
    private static void initController(Player[] players,
                                       Gameboard gameboard,
                                       SilkBag silkbag) {

        // Get instance of controller as Controller is a singleton.
        Controller controller = Controller.getInstance();
        // Initialise controller.
        controller.init(players, gameboard, silkbag);
    }
}
