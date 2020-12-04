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
import com.group31.tile_manager.silk_bag.SilkBag;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    /**
     * Specifies which environment the app is running in.
     */
    // Either 'dev' for development or 'prod' for production.
    // Behaviour changes when set to 'dev': settings regenerated.
    private static final String ENV = "dev";

    /**
     * Rows in a gameboard. (TESTING).
     */
    private static final int BOARD_ROWS = 7;
    /**
     * Columns in a gameboard. (TESTING).
     */
    private static final int BOARD_COLS = 7;

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
        boolean allowFileCreation = true;

        // Requested directory.
        String settingsDirectory = "data/settings/";
        String settingsFileName = "settings.txt";
        try {
            FileManager.setDirectory(settingsDirectory, allowFileCreation);
            if (!FileManager.fileExists(settingsFileName)) {
                FileManager.write(DefaultSettings.getDefaultSettingsArray(), settingsFileName);
                return DefaultSettings.getDefaultSettings();
            } else {
                return getSettingsFromArray(FileManager.read(settingsFileName));
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
        int settingKey = 0;
        int settingValue = 1;
        String delimiter = ";";
        for (String setting : settingsArray) {
            String[] settingNameValue = setting.split(delimiter);
            settings.put(settingNameValue[settingKey], settingNameValue[settingValue]);
        }
        return settings;
    }

    /**
     * Initialises the Leaderboard.
     * @return New instance of Leaderboard.
     */
    private static Leaderboard initLeaderBoard() {
        return null; //new Leaderboard("");
    }

    /**
     * Initialises SilkBag.
     * @return A new instance of SilkBag.
     */
    private static SilkBag initSilkBag() {
//        int maxTiles = Settings.getSettingAsInt("max_tiles");
//        return new SilkBag(maxTiles);
        return new SilkBag(2);
    }

    /**
     * Creates a new gameboard.
     * @return New instance of gameboard.
     */
    private static Gameboard initGameboard() {
        return new Gameboard(BOARD_ROWS, BOARD_COLS);
    }

    /**
     * Initialises players.
     * @return New instance of player depending on how many players are in the game.
     */
    private static Player[] initPlayers() {
        int numPlayers = 2;
        Player[] players = new Player[numPlayers];

        for (int i = 0; i <= numPlayers - 1; i++) {
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
