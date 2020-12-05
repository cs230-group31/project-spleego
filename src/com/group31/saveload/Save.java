package com.group31.saveload;

import com.group31.controller.Controller;
import com.group31.leaderboard.Leaderboard;

public class Save {

    /**
     * Saves the gameboard and players.
     */
    // Save the leaderboard, and the controller.
    // The controller has all the information on the game, so it's the only thing we need to save.
    public static void saveAll() {

        Controller.getInstance().save();
        Controller.getInstance().addPlayersToLeaderboard();
        Leaderboard.save();
    }
}
