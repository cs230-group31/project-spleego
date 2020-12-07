package com.group31.leaderboard;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.PlayerProfile;
import com.group31.services.FileManager;
import com.group31.services.serializer.Serializer;
import com.group31.settings.Settings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.ArrayList;

/**
 * @author liam, Abdullah(Moe)
 */
public class Leaderboard {

    /**
     * List of players to display on the leaderboard.
     */
    private static ArrayList<PlayerProfile> players = new ArrayList<>();

    /**
     * Initialises the leaderboard.
     * @param directory The directory where the players file's are kept.
     */
    public static void initialise(String directory) {
        String object = "player";
        try {
            FileManager.setDirectory(directory, true);
            for (File controller : FileManager.getAllFilesInDir()) {
                String rawFileName = controller.getName().replaceFirst("[.][^.]+$", "");
                PlayerProfile playerProfile = (PlayerProfile) Serializer.deserialize(rawFileName, object);
                players.add(playerProfile);
                playerProfile.save();
            }
        } catch (NoSuchDirectory | ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Gets the leaderboard data.
     * @return Leaderboard data.
     */
    public static ObservableList<PlayerProfile> getLeaderboardData() {
        players.clear();
        try {
            String object = "player";
            String directory = String.format("%splayer/", Settings.get("serialized_objects_folder"));
            FileManager.setDirectory(directory, false);
            for (File file : FileManager.getAllFilesInDir()) {
                String identifier = file.getName().replaceFirst("[.][^.]+$", "");
                PlayerProfile profile = (PlayerProfile) Serializer.deserialize(identifier, object);
                players.add(profile);
                profile.save();
            }
        } catch (NoSuchDirectory | ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
        return FXCollections.observableArrayList(players);
    }
}
