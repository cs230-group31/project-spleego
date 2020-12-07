package com.group31.graphics.view_controllers;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.graphics.Game;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.player.PlayerProfile;
import com.group31.saveload.Load;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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

    public static Player[] initPlayers(ArrayList<PlayerProfile> profiles) {
        ArrayList<Player> players = new ArrayList<>();
        String fileName = "default level.txt";
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

    public static ArrayList<String> getGamesWithPlayerParticipation(ArrayList<PlayerProfile> profiles) {
//        HashSet<String> controllerUuids = new HashSet<>();
//        ArrayList<String> tempControllerIds = new ArrayList<>();
//        for (PlayerProfile profile : profiles) {
//            tempControllerIds.addAll(Arrays.asList(profile.getGamesParticipating()));
//        }
//        for (String id : tempControllerIds) {
//            if (Collections.frequency(tempControllerIds, id) >= profiles.size()) {
//                controllerUuids.add(id);
//            }
//        }
//        tempControllerIds.clear();
//        tempControllerIds.addAll(controllerUuids);
//        return tempControllerIds;

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
}
