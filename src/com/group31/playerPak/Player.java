package com.group31.playerPak;

import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import javafx.scene.paint.Color;
import java.util.Random;

public class Player {


    private String playerName;
    private Image playerSprite;
    private Color colour;
    private int[] playerLocation; // location is kept in array size of 2
    private Tile[] playerHand;
    private PlayerData playerData;
    private int[] prevLocation;
    private int[] prevPrevLocation;
    private Random playerHandSize = new Random();
    private int hanSize = playerHandSize.nextInt(15); //set size of player hand to 15(dont know how big)



    public Player (@NotNull PlayerData playerData, int[] location){

        this.playerData = playerData;
        this.playerData = playerData;
        playerLocation = location;
        playerName = playerData.getName();
        playerSprite = playerData.getSprite();
    }

    public String getPlayerName(){

        return playerName;
    }

    public Image getPlayerSprite(){

        return playerSprite;
    }

    public Color getPlayerColour(){

        return colour;
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

        //not sure how it will be implemented atm
    }

    public void movePlayer(int movX, int movY){

        prevPrevLocation = prevLocation;
        prevLocation = playerLocation;
        // section above just stores last 2 locations

        playerLocation[0] = movX;
        playerLocation[1] = movY;

    }

    public Tile drawTile(){

        return playerHand[hanSize];// draws random tile size up to 15

    }
}
