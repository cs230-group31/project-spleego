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
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    /**
     * Specifies which environment the app is running in: "dev" or "prod"
     */
    private static String env = "dev";
    /**
     * Initialises the components and runs the app.
     * @param args Args passed in at runtime.
     */
    public static void main(String[] args) {
        //init settings
        HashMap<String, String> settings = initSettings();
        Settings.setAllSettings(settings);
        if (env.equals("dev")) {
            Settings.setAllSettings(DefaultSettings.getDefaultSettings());
            //Settings.updateSettings(DefaultSettings.getDefaultSettingsArray());
        }


        // testing
        Settings.dumpSettingsToConsole();

        // init components
        Leaderboard leaderboard = initLeaderBoard();
        SilkBag silkbag = initSilkBag();
        Gameboard gameboard = initGameboard();
        gameboard.genBoard(silkbag);
        Player[] players = initPlayers();

        //init the controller
        initController(players, gameboard, silkbag);

        // start GUI
        String[] launchArgs = {};
        MainMenu.run(launchArgs);
    }

    /**
     * Initialises settings.
     * @return HashMap containing all settings.
     */
    private static HashMap<String, String> initSettings() {
        boolean allowFileCreation = true;
        String settingsDirectory = "data/settings/";
        String settingsFile = "settings.txt";
        String delimiter = ",";
        try {
            FileManager.setDirectory(settingsDirectory, allowFileCreation);
            if (!FileManager.fileExists(settingsFile)) {
                FileManager.write(DefaultSettings.getDefaultSettingsArray(), settingsFile);
                return DefaultSettings.getDefaultSettings();
            } else {
                return getSettingsFromArray(FileManager.read(settingsFile));
            }
        } catch (NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        Logger.log("Failed to save settings to a file, using default settings.", Logger.Level.WARNING);
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
        for (String setting : settingsArray) {
            String[] settingNameValue = setting.split(";");
            settings.put(settingNameValue[settingKey], settingNameValue[settingValue]);
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
        return new SilkBag(new ArrayList<Tile>(), 10);
    }

    /**
     * Initialises Gameboard.
     * @return A new instance of Gameboard.
     */
    private static Gameboard initGameboard() {
        // TODO: loads size, saved state if any, initialises, pass back
        int boardRows = 5;
        int boardCols = 5;
        return new Gameboard(boardRows, boardCols);
    }

    /**
     * Initialises players.
     * @return New instance of player depending on how many players are in the game.
     */
    private static Player[] initPlayers() {
        // TODO: loads players if any saved or creates new players (however many are asked for at runtime), pass back

        //set based on settings passed in before game starts.
        int numberOfPlayers = 2;
        Player[] players = new Player[numberOfPlayers];
        for (int i = 0; i <= numberOfPlayers - 1; i++) {

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

        // start the game.
        Controller controller = Controller.getInstance();
        controller.init(players, gameboard, silkbag);
    }
}
