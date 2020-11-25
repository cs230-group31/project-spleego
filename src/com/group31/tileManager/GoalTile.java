package com.group31.tileManager;

public class GoalTile extends Tile{

    public GoalTile(int id, int[] coord, boolean actionTile){
        super(id, coord, actionTile);
        initTile();
    }

    private void initTile(){
        loadImage("GoalImg_path");
    }

/*
    public String setRouting(){
    }*/

    /**
	 * @param id Sets the id.
	 */
    public void setId(int id){
        this.id = 10;
    }

    /**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     */
    public void setCoords(int[] goalCoords){
        //coord[X] = 15;
        //coord[Y] = 15;
    }

    /**
     * 
     * @param true if tile is an action tile
     */
    public void setActionTile(boolean isAction){
        isAction == false;
    }

    /*
    public void gameWon(boolean goalReached){
        
    }*/

}