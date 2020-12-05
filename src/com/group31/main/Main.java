package com.group31.main;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.MainMenu;
import com.group31.leaderboard.Leaderboard;
import com.group31.logger.Logger;
import com.group31.controller.Controller;
import com.group31.services.FileManager;
import com.group31.settings.DefaultSettings;
import com.group31.settings.Settings;
import com.group31.tile_manager.silk_bag.SilkBag;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    /**
     * Specifies which environment the app is running in.
     */
    // Either 'dev' for development or 'prod' for production.
    // Behaviour changes when set to 'dev':
    // - settings regenerated from DefaultSettings,
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
    public static void main(String[] args) throws FileNotFoundException {
        // Initialise settings.
        initSettings();

        if (ENV.equals("dev")) {
            Settings.setAllSettings(DefaultSettings.getDefaultSettings());
        }

        // Testing.
        Settings.dumpSettingsToConsole();

        // Initialise components.
        initLeaderBoard();

        // Initialise controller, gameboard and silkbag.
        initController();

        // Start GUI.
        String[] launchArgs = {};
        MainMenu.run(launchArgs);
    }

    /**
     * Initialises settings.
     */
    private static void initSettings() {
        // Allow the file manager to create the requested directory.
        boolean allowFileCreation = true;

        // Requested directory.
        String settingsDirectory = "data/settings/";
        String settingsFileName = "settings.txt";
        try {
            FileManager.setDirectory(settingsDirectory, allowFileCreation);
            if (!FileManager.fileExists(settingsFileName)) {
                FileManager.write(DefaultSettings.getDefaultSettingsArray(), settingsFileName);
                Settings.setAllSettings(DefaultSettings.getDefaultSettings());
            } else {
                Settings.setAllSettings(FileManager.read(settingsFileName));
            }
        } catch (NoSuchDirectory | IOException e) {
            // If no settings can be loaded from a file, use the default settings.
            Settings.setAllSettings(DefaultSettings.getDefaultSettings());
            Logger.log("Failed to save settings to a file, using default settings.", Logger.Level.WARNING);
        }
    }

    /**
     * Initialises the Leaderboard.
     */
    private static void initLeaderBoard() {
        String directory = Settings.get("serialized_objects_folder");
        Leaderboard.initialise(directory + "player/");
    }

    /**
     * Initialises SilkBag.
     * @return A new instance of SilkBag.
     */
    private static SilkBag initSilkBag() throws FileNotFoundException {
//        int maxTiles = Settings.getSettingAsInt("max_tiles");
//        return new SilkBag(maxTiles);
        return new SilkBag(80);
    }

    /**
     * Creates a new gameboard.
     * @return New instance of gameboard.
     */
    private static Gameboard initGameboard() {
        return new Gameboard(BOARD_ROWS, BOARD_COLS);
    }

    /**
     * Initialises controller.
     */
    public static void initController() {

        SilkBag silkbag = null;
        Gameboard gameboard = initGameboard();
        try {
            silkbag = initSilkBag();
            gameboard.genBoard(silkbag);
        } catch (FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        // Get instance of controller as Controller is a singleton.
        Controller controller = Controller.getInstance();
        // Initialise controller.
        controller.init(gameboard, silkbag);
    }
}
