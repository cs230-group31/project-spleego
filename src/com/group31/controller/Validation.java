package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;
import com.group31.exceptions.TileNotFound;
import com.group31.gameboard.Gameboard;
import com.group31.graphics.Game;
import com.group31.logger.Logger;
import com.group31.settings.Settings;
import com.group31.tile_manager.FloorTile;
import com.group31.tile_manager.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Validation {

    /**
     * Minimum X coordinate of the board.
     */
    private static final int MIN_BOARD_X = 0;

    /**
     * Minimum Y coordinate of the board.
     */
    private static final int MIN_BOARD_Y = 0;

    /**
     * Checks if a board move is valid.
     * @param playerX PLayer's X co-ordinate.
     * @param playerY PLayer's Y co-ordinate.
     * @param maxBoardX Board's maximum X co-ordinate.
     * @param maxBoardY Board's maximum Y co-ordinate.
     * @param moveDirection The direction the player has moved.
     * @return If the move is valid or not.
     * @throws InvalidMoveDirection If the moveDirection is not valid.
     */
    public static boolean validBoardMove(int playerX, int playerY, int maxBoardX, int maxBoardY,
                                         Movement.Move moveDirection) throws InvalidMoveDirection {

        switch (moveDirection) {
            case DOWN:
                return !(playerX == MIN_BOARD_X);
            case UP:
                return !(playerX == maxBoardX);
            case LEFT:
                return !(playerY == MIN_BOARD_Y);
            case RIGHT:
                return !(playerY == maxBoardY);
            default:
                throw new InvalidMoveDirection("Move not valid.");
        }
    }

    public static boolean validRouting(int currentTileId, int neighbourTileId, String neighbourDirection) {
        int[] tileAcceptsRouteBelow = new int[] {0, 2, 4, 5, 7, 8, 9};
        int[] tileAcceptsRouteAbove = new int[] {0, 2, 3, 6, 7, 9, 10};
        int[] tileAcceptsRouteLeft = new int[] {0, 1, 5, 6, 8, 9, 10};
        int[] tileAcceptsRouteRight = new int[] {0, 1, 3, 4, 7, 8, 10};

        List<int[]> tileAcceptsRouteBelowList = Arrays.asList(tileAcceptsRouteBelow);
        List<int[]> tileAcceptsRouteAboveList = Arrays.asList(tileAcceptsRouteAbove);
        List<int[]> tileAcceptsRouteLeftList = Arrays.asList(tileAcceptsRouteLeft);
        List<int[]> tileAcceptsRouteRightList = Arrays.asList(tileAcceptsRouteRight);

        if (neighbourDirection.equals("up") && tileAcceptsRouteAboveList.contains(currentTileId)) {
            if (tileAcceptsRouteBelowList.contains(neighbourTileId)) {
                return true;
            } else {
                return false;
            }
        } else if (neighbourDirection.equals("down") && tileAcceptsRouteBelowList.contains(currentTileId)) {
            if (tileAcceptsRouteAboveList.contains(neighbourTileId)) {
                return true;
            } else {
                return false;
            }
        } else if (neighbourDirection.equals("left") && tileAcceptsRouteLeftList.contains(currentTileId)) {
            if (tileAcceptsRouteRightList.contains(neighbourTileId)) {
                return true;
            } else {
                return false;
            }
        } else if (neighbourDirection.equals("right") && tileAcceptsRouteRightList.contains(currentTileId)) {
            if (tileAcceptsRouteLeftList.contains(neighbourTileId)) {
                return true;
            } else {
                return false;
            }
        }
        return false;


    }

}
