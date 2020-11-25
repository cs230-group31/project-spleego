package com.group31.playerPak;

import javafx.scene.image.Image;

public class PlayerData {


    private String name;
    private Image sprite;
    private int wins;
    private int losses;
    private int played;


    public PlayerData (String name, Image sprite) {

        this.name = name;
        this.sprite = sprite;
        wins = 0;
        losses = 0;
        played = 0;
    }


    public String getName() {

        return name;
    }
    public Image getSprite() {

        return sprite;
    }
    public int getWins() {

        return wins;
    }
    public int getLosses() {

        return losses;
    }
    public int getPlayed() {

        return played;
    }


    public void setWinOrLoss(boolean didWin){

        if (didWin){
            wins ++;
        }else{
            losses ++;
        }

        played ++;

    }



}
