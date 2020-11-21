package com.group31.controller;

public class Controller {

    private Player[] players;
    private Gameboard gameboard;
    private Silkbag silkbag;
    private int[] permittedPlacePoint;
    private FileManager fileManager;

    public Controller(Player[] players, Gameboard gameboard, Silkbag silkbag, Leaderboard leaderboard) {
        this.players = players;
        this.gameboard = gameboard;
        this.silkbag = silkbag;
    }

    public Tile takeTile(Player player) {
        // give tile to player from silkbag
    }

    public void movePlayer(int x, int y) {
        // move a player on a board
    }

    public void drawTileOnBoard(Tile tile, int x, int y) {
        // draw a tile on the gameboard
    }

    public boolean validatePlayerMove(Player player, int playerX, int playerY, Movement.Move direction) {
        int maxBoardX = gameboard.maxX;
        int maxBoardY = gameboard.maxY;
        boolean validMove = validatePlayerMove(playerX, playerY, maxBoardX, maxBoardY, direction);
    }

    public void saveGame() {
        // save the game passing info to the file reader
    }

}
