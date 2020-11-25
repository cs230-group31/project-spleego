package com.group31.tileManager;
import com.group31.logger.Logger;

/**
 * this is the goal tile
 */
public class GoalTile extends Tile{

    public GoalTile(String routing, int id, int[] coord, boolean actionTile){
        super(routing, id, coord, actionTile);

    }

    /**
     * when you reach this tile you win the game
     * @param goalReached
     */
    public void gameWon(boolean goalReached){
        Logger.log("you have won", Logger.Level.INFO);
    }

}