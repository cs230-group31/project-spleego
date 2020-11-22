package com.group31.playerPak;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import javafx.scene.paint.Color;

public class Player {


    private String playerName;
    private Image playerSprite;
    private Color Colour;
    private int[] playerLocation;
   // private Tile[] playerHand;
    private PlayerData playerData;
    private int[] prevLocation;
    private int[] prevPrevLocation;




    public Player (@NotNull PlayerData playerData, int[] location) {

        this.playerData = playerData;
        this.playerData = playerData;
        playerLocation = location;
        playerName = playerData.getName();// check on this later
        playerSprite = playerData.getSprite();
    }

    public String getPlayerName(){

        return playerName;
    }
    public Image getPlayerSprite(){

        return playerSprite;
    }

    public Color getPlayerColour(){

        return Colour;

    }

    public int[] getPlayerLocation(){

        return playerLocation;
    }

    public int[] getLastTurn(){

        return prevLocation;
    }

    public int[] getLastLastTurn(){

        return prevPrevLocation;
    }

    public void playTile(Tile theTile, int locX, int locY){

    }

    public void movePlayer(int movX, int movY){

    }
// need to do bottom stuff
   /* public Tile drawTile(){

        return playerHand[];

    }*/
}
