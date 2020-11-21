package com.group31.controller;

public class Validation {

    public static boolean validBoardMove(int playerX, int playerY, int maxBoardX, int maxBoardY,
                                         Movement.Move moveDirection) {

        final int minBoardX = 0;
        final int minBoardY = 0;

        return switch (moveDirection) {
            case DOWN -> !(playerX == minBoardX);
            case UP -> !(playerX == maxBoardX);
            case LEFT -> !(playerY == minBoardY);
            case RIGHT -> !(playerY == maxBoardY);
        };

    }

}
