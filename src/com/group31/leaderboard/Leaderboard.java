package com.group31.leaderboard;

import com.group31.controller.Controller;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.services.FileManager;
import com.group31.services.serializer.Serializer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

public class Leaderboard {

    /**
     * List of players to display on the leaderboard.
     */
    private static ArrayList<Player> players = new ArrayList<>();

    /**
     * Initialises the leaderboard.
     * @param directory The directory where the players file's are kept.
     */
    public static void initialise(String directory) {
        String object = "controller";
        try {
            FileManager.setDirectory(directory, true);
            for (File controller : FileManager.getAllFilesInDir()) {
                String rawFileName = controller.getName().replaceFirst("[.][^.]+$", "");
                Controller controllerFromFile = (Controller) Serializer.deserialize(rawFileName, object);
                players.addAll(controllerFromFile.getPlayers());
            }
        } catch (NoSuchDirectory | ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Gets the leaderboard data.
     * @return Leaderboard data.
     */
    public static ObservableList<Player> getLeaderboardData() {
        return FXCollections.observableArrayList(players);
    }
}
