package com.group31.graphics.view_controllers;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author liam
 */
public class LevelSelectionController {

    /**
     * Returns the name of the saved games in a directory.
     * @return the name of the saved games in a directory
     * @throws NoSuchDirectory if the directory does not exist
     */
    public static ArrayList<String> getSavedGamesName() throws NoSuchDirectory {

        ArrayList<String> gameNames = new ArrayList<>();
        String directory = String.format("%scontroller", Settings.get("serialized_objects_folder"));
        FileManager.setDirectory(directory, true);
        File[] gameFiles = FileManager.getAllFilesInDir();
        for (File file : gameFiles) {
            String rawFileName = file.getName().replaceFirst("[.][^.]+$", "");
            gameNames.add(rawFileName);
        }
        return gameNames;
    }

    /**
     * Loads a game.
     * @param identifier Controller file name.
     */
    public static void loadGame(String identifier) {
        String playerSpritesLocation = "resources/images/sprites/";
        String imageExtension = "png";
        try {
            Controller.setInstance(Load.loadController(identifier));
            Player[] players = Controller.getInstance().getPlayers();
            FileManager.setDirectory(playerSpritesLocation, false);
            for (int i = 0; i <= players.length - 1; i++) {
                String imageName = Integer.toString(i);
                Image image = FileManager.readImage(imageName, imageExtension);
                players[i].setSprite(image);
            }
        } catch (ObjectNeverSerialized | NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Returns initialised players.
     * @param profiles list of profiles to get player information from
     * @param fileName filename of the game save to load players from
     * @return initialised players
     */
    public static Player[] initPlayers(ArrayList<PlayerProfile> profiles, String fileName) {
        ArrayList<Player> players = new ArrayList<>();
        String delimiter = ",";
        ArrayList<String> playerLocations = new ArrayList<>();
        try {
            playerLocations = (ArrayList<String>) Load.loadNewGameFromFile(fileName).get("playerLocations");
        } catch (FileNotFoundException | NoSuchDirectory e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        for (int i = 0; i <= profiles.size() - 1; i++) {
            String[] locationsAsString = playerLocations.get(i).split(delimiter);
            int[] locations = new int[]
                    {Integer.parseInt(locationsAsString[0]), Integer.parseInt(locationsAsString[1])};

            Image playerSprite = null;
            try {
                FileManager.setDirectory("resources/images/sprites/", false);
                playerSprite = FileManager.readImage(i + "", "png");
            } catch (NoSuchDirectory | IOException e) {
                Logger.log(e.toString(), Logger.Level.ERROR);
            }
            players.add(new Player(profiles.get(i).getName(), playerSprite, locations));
        }
        return players.toArray(new Player[0]);
    }

    /**
     * Returns the game saves that are common to all players.
     * @param profiles list of player profiles
     * @return the game saves that are common to all players
     */
    public static ArrayList<String> getGamesWithPlayerParticipation(ArrayList<PlayerProfile> profiles) {

        ArrayList<String> gameSaveNames = new ArrayList<>();

        ArrayList<String> gameNames = new ArrayList<>();
        try {
            gameNames = getSavedGamesName();
        } catch (NoSuchDirectory e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }

        ArrayList<String> controllerUuids = new ArrayList<>();
        for (PlayerProfile profile : profiles) {
            controllerUuids.addAll(Arrays.asList(profile.getGamesParticipating()));
        }

        for (String name : gameNames) {
            if (controllerUuids.contains(name) && Collections.frequency(controllerUuids, name) >= profiles.size()) {
                gameSaveNames.add(name);
            }
        }
        return gameSaveNames;
    }

    /**
     * Returns a list of the pre-defined level file names.
     * @return a list of the pre-defined level file names
     */
    public static ArrayList<String> getPredefinedLevelNames() {
        ArrayList<String> fileNames = new ArrayList<>();
        try {
            FileManager.setDirectory(Settings.get("level_files"), false);
            File[] files = FileManager.getAllFilesInDir();
            for (File file : files) {
                String rawFileName = file.getName().replaceFirst("[.][^.]+$", "");
                fileNames.add(rawFileName);
            }
        } catch (NoSuchDirectory e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        return fileNames;
    }
}
