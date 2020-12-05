package com.group31.graphics.view_controllers;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.saveload.Load;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import java.io.File;
import java.util.ArrayList;

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
        try {
            Controller.setInstance(Load.loadController(identifier));
        } catch (ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

}
