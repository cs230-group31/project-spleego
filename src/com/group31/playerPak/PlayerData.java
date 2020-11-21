package com.group31.playerPak;

import javafx.scene.image.Image;

public class PlayerData {


    public String name;// set to public for testing purpose
    private Image sprite;
    private int wins;
    private int losses;
    private int played;

    //test constructor
    public PlayerData(){

    }

    // test constructor 2
    public PlayerData(String name){
        this.name = name;

    }


    //test constructor 3
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
