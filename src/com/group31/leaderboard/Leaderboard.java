package com.group31.leaderboard;

import com.group31.exceptions.NoFilesInDir;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.services.FileManager;
import com.group31.services.serializer.Serializer;

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
        String searchTerm = "player";
        try {
            FileManager.setDirectory(playersDirectory, false);
            for (File player : FileManager.getAllFilesInDir(searchTerm)) {
                String rawFileName = player.getName().replaceFirst("[.][^.]+$", "");
                players.add(Serializer.deserializePlayer(rawFileName));
            }
        } catch (NoSuchDirectory | NoFilesInDir | ObjectNeverSerialized e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Adds a player to the list of players.
     * @param player Player to add.
     */
    public static void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * Gets the leaderboard data.
     * @return Leaderboard data.
     */
    public static Player[] getLeaderboardData() {
        return (Player[]) players.toArray();
    }

    /**
     * Saves all the players on the leaderboard to the file system.
     */
    private static void saveLeaderboard() {
        for (Player player : players) {
            String identifier = String.format("Player_%s", player.getUuid);
            Serializer.serialize(player, identifier);
        }
    }


}
