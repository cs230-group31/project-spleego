package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;
import com.group31.settings.Settings;

import java.util.Arrays;
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

    /**
     * Takes the current tile and a neighbouring tile, plus the direction to that tile.
     * Checks to see if the neighbouring tile is in the set of tiles that the current
     * tile connects to.
     * @param currentTileId the current tile
     * @param neighbourTileId a tile next to the current tile
     * @param neighbourDirection the direction the neighbouring tile is from the current one
     * @return true if the two tiles connect
     */
    public static boolean validRouting(int currentTileId, int neighbourTileId, String neighbourDirection) {
        String delimiter = ",";
        List<String> tileAcceptsRouteBelowList = Arrays.asList(Settings.get("tiles_fed_below").split(delimiter));
        List<String> tileAcceptsRouteAboveList = Arrays.asList(Settings.get("tiles_fed_above").split(delimiter));
        List<String> tileAcceptsRouteLeftList = Arrays.asList(Settings.get("tiles_fed_left").split(delimiter));
        List<String> tileAcceptsRouteRightList = Arrays.asList(Settings.get("tiles_fed_right").split(delimiter));

        if (neighbourDirection.equals("up") && tileAcceptsRouteAboveList.contains(currentTileId)
                && tileAcceptsRouteBelowList.contains(neighbourTileId)) {
            return true;
        } else if (neighbourDirection.equals("down") && tileAcceptsRouteBelowList.contains(currentTileId)
                && tileAcceptsRouteAboveList.contains(neighbourTileId)) {
            return true;
        } else if (neighbourDirection.equals("left") && tileAcceptsRouteLeftList.contains(currentTileId)
                && tileAcceptsRouteRightList.contains(neighbourTileId)) {
            return true;
        } else if (neighbourDirection.equals("right") && tileAcceptsRouteRightList.contains(currentTileId)
                && tileAcceptsRouteLeftList.contains(neighbourTileId)) {
            return true;
        }

        return false;

    }

}
