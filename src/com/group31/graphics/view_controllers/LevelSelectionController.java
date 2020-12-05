package com.group31.graphics.view_controllers;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.services.FileManager;
import com.group31.settings.Settings;
import java.io.File;
import java.util.ArrayList;

public class LevelSelectionController {

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

}
