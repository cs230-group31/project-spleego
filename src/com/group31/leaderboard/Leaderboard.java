package com.group31.leaderboard;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.services.FileManager;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Leaderboard {

//    /**An arraylist that stores player data. */
//    private ArrayList<PlayerData> players = new ArrayList<>();
//    // not sure why theres a 2 dimentional array in the uml. I could change the above.
//
//    /**
//     * reads file that stores player information.
//     * @param file file that stores player info
//     */
//    public Leaderboard(String file) {
//
//    }
//
//    /**
//     *  gets list of players.
//     * @return list of playerdata called players
//     */
//    public ArrayList<PlayerData> getPlayerArray() {
//        return this.players;
//    }
//    /**
//     * adds player data to leaderboard.
//     * @param info info identifies playerdata that will be used
//     */
//    public void addPlayerData(PlayerData info) {
//
//        players.add(info);
//    }

    private static ArrayList<Player> players;
    private static String directory;
    private static String fileName;

    public static void initialise(String requestedDir, String requestedFileName) {
        directory = requestedDir;
        fileName = requestedFileName;
        try {
            FileManager.setDirectory(requestedDir, false);
            // sort out next line
            // players = FileManager.read(requestedFileName);
        } catch (NoSuchDirectory e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static Player[] getLeaderboardData() {
        return (Player[]) players.toArray();
    }

    private static void saveLeaderboard() {
        if (directory != null && fileName != null) {
            // TODO: convert player array to string array. Have fun with that!
            //FileManager.write();
        } else {
            Logger.log("Leaderboard not saved. Directory of file name non existent.", Logger.Level.WARNING);
        }
    }

}
