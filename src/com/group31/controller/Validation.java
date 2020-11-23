package com.group31.controller;

import com.group31.exceptions.InvalidMoveDirection;

public class Validation {

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

        final int minBoardX = 0;
        final int minBoardY = 0;

        switch (moveDirection) {
            case DOWN:
                return !(playerX == minBoardX);
            case UP:
                return !(playerX == maxBoardX);
            case LEFT:
                return !(playerY == minBoardY);
            case RIGHT:
                return !(playerY == maxBoardY);
            default:
                throw new InvalidMoveDirection("Move not valid.");
        }
    }
}
