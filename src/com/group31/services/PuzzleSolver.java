package com.group31.services;

public class PuzzleSolver {

    private static final int LETTER_RANGE = 26;
    private static final int TOKEN_START_INDEX = 0;

    private static final StringBuilder token = new StringBuilder();

    public static String solvePuzzle(String puzzle, String headContent) {
        shiftChars(puzzle);
        addHeadContent(headContent);
        addTailContent();
        return token.toString();
    }

    private static void addTailContent() {
        token.append(token.length());
    }

    private static void addHeadContent(String content) {
        token.insert(TOKEN_START_INDEX, content);
    }

    private static void shiftChars(String puzzle) {
        int length = puzzle.length();
        char[] puzzleArray = puzzle.toCharArray();
        for (int i = 0; i <= length - 1; i++) {
            token.append(shift(puzzleArray[i], i + 1));
        }
    }

    private static char shift(char character, int shiftAmount) {
        if (shiftAmount % 2 == 0) {
            // roll forwards
            if (character + shiftAmount > 'Z') {
                return (char)(character - LETTER_RANGE + shiftAmount);
            } else {
                return (char) (character + shiftAmount);
            }
        } else {
            // roll backwards
            if (character - shiftAmount < 'A') {
                return (char)(character + LETTER_RANGE - shiftAmount);
            } else {
                return (char) (character - shiftAmount);
            }
        }
    }
}
