package com.group31.tileManager;

public class MovementTile extends Tile{

    public MovementTile(int id, int[] coord, boolean actionTile){
        super(id, coord, actionTile);
        initTile();
    }

    /**
	 * @param id Randomly generates movement tile id.
	 */
    public void setId(int id){
        Random r = new Random();
        int min = 1;
        int max = 9;
        int randomID = r.nextInt(max-min) + min;
        this.id = randomID;
    }

    private void initTile(int id){

        switch (this.id) {
            case 1:
                loadImage("Movement1Img_path");
                break;
            case 2:
                loadImage("Movement2Img_path");
                break;
            case 3:
                loadImage("Movement3Img_path");
                break;
            case 4:
                loadImage("Movement4Img_path");
                break;
            case 5:
                loadImage("Movement5Img_path");
                break;
            case 6:
                loadImage("Movement6Img_path");
                break;
            case 7:
                loadImage("Movement7Img_path");
                break;
            case 8:
                loadImage("Movement8Img_path");
                break;
            case 9:
                loadImage("Movement9Img_path");
                break;
            
        }
    }

/*
    public String setRouting(){
    }*/

    /**
     * change the current tile position on the gameboard
     * @param incAmount amount of movement
     */
    public void incCoords(int[] incAmount){
        //coord[X] += incAmount[X];
        //coord[Y] += incAmount[Y];
    }

    /**
     * 
     * @param true if tile is an action tile
     */
    public void setActionTile(boolean isAction){
        isAction == false;
    }

    /**
     * @param weight Sets the weight.
     */
    public void setWeight(double weight) {
        //this.weight = 0.15;
    }

    private void movementEffect(int id){

        switch (this.id) {
            case 1:
                //movement effect code
                break;
            case 2:
                //movement effect code
                break;
            case 3:
                //movement effect code
                break;
            case 4:
                //movement effect code
                break;
            case 5:
                //movement effect code
                break;
            case 6:
                //movement effect code
                break;
            case 7:
                //movement effect code
                break;
            case 8:
                //movement effect code
                break;
            case 9:
                //movement effect code
                break;
            
        }
    }

}