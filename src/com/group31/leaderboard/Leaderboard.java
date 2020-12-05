package com.group31.leaderboard;

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
     * @param playersDirectory The directory where the players file's are kept.
     */
    public static void initialise(String playersDirectory) {
        String object = "player";
        try {
            FileManager.setDirectory(playersDirectory, true);
            for (File player : FileManager.getAllFilesInDir()) {
                String rawFileName = player.getName().replaceFirst("[.][^.]+$", "");
                players.add((Player) Serializer.deserialize(rawFileName, object));
            }
        } catch (NoSuchDirectory | ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Adds a player to the list of players.
     * @param player Player to add.
     */
    public static void addPlayer(Player player) {
        // Don't add players that are already there.
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    /**
     * Gets the leaderboard data.
     * @return Leaderboard data.
     */
    public static ObservableList<Player> getLeaderboardData() {
        return FXCollections.observableArrayList(players);
    }

    /**
     * Saves all the players on the leaderboard to the file system.
     */
    public static void save() {
        for (Player player : players) {
            String identifier = String.format("Player_%s", player.getUuid());
            String object = "player";
            Serializer.serialize(player, identifier, object);
        }
    }
}
