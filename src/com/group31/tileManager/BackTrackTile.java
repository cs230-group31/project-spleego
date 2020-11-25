package com.group31.tileManager;

public class BackTrackTile extends Tile{

    public BackTrackTile(int id, int[] coord, boolean actionTile){
        super(id, coord, actionTile);
        initTile();
    }

    private void initTile(){
        loadImage("BackTrackImg_path");
    }

/*
    public String setRouting(){
    }*/

    /**
	 * @param id Sets the id.
	 */
    public void setId(int id){
        this.id = 12;
    }

    /**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     */
    public void incCoords(int[] incAmount){
        coord[X] += incAmount[X];
        coord[Y] += incAmount[Y];
    }

    /**
     * 
     * @param true if tile is an action tile
     */
    public void setActionTile(boolean isAction){
        isAction == true;
    }

    /**
     * @param weight Sets the weight.
     */
    public void setWeight(double weight) {
        //this.weight = 0.15;
    }

    /*
    public void BackTrackEffect(){
        
    }*/

}