package com.group31.leaderboard;

import com.group31.player.PlayerData;
import java.util.ArrayList;

public class Leaderboard {

    /**An arraylist that stores player data. */
    private ArrayList<PlayerData> players = new ArrayList<>();
    // not sure why theres a 2 dimentional array in the uml. I could change the above.

    /**
     * reads file that stores player information.
     * @param file file that stores player info
     */
    public Leaderboard(String file) {

    }

    /**
     *  gets list of players.
     * @return list of playerdata called players
     */
    public ArrayList<PlayerData> getPlayerArray() {
        return this.players;
    }
    /**
     * adds player data to leaderboard.
     * @param info info identifies playerdata that will be used
     */
    public void addPlayerData(PlayerData info) {

        players.add(info);
    }

}
