package com.group31.saveload;

import com.group31.controller.Controller;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.PlayerProfile;
import com.group31.services.serializer.Serializer;

import java.util.ArrayList;

/**
 * @author liam, Aaron
 */
public class Save {

    /**
     * Saves the gameboard and players.
     * @param profiles player profiles
     */
    // Save the leaderboard, and the controller.
    // The controller has all the information on the game, so it's the only thing we need to save.
    public static void saveAll(ArrayList<PlayerProfile> profiles) {
        String object = "player";
        for (PlayerProfile profile : profiles) {
            try {
                PlayerProfile deserializedProfile = (PlayerProfile) Serializer.deserialize(String.format(
                        "PlayerProfile_%s", profile.getName()), object);
                deserializedProfile.addPlayerToGame(Controller.getInstance());
                deserializedProfile.save();
            } catch (ObjectNeverSerialized e) {
                Logger.log(e.getMessage(), Logger.Level.ERROR);
            }
        }
        Controller.getInstance().save();
    }
}
