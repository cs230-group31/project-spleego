package com.group31.services;

public class PuzzleSolver {

    /**
     * Range of letters.
     */
    private static final int LETTER_RANGE = 26;
    /**
     * Index of the start of the token string.
     */
    private static final int TOKEN_START_INDEX = 0;
    /**
     * The token to pass to the API to authorise the request.
     */
    private static final StringBuilder TOKEN = new StringBuilder();
    /**
     * Content to add to the start of the token.
     */
    private static final String HEAD_CONTENT = "CS-230";

    /**
     * Builds the token.
     * @param puzzle Raw puzzle text.
     * @return Puzzle text with all changes made to decipher it.
     */
    public static String solvePuzzle(final String puzzle) {
        shiftChars(puzzle);
        addHeadContent();
        addTailContent();
        return TOKEN.toString();
    }

    /**
     * Adds the length of the token to the end of the string.
     */
    private static void addTailContent() {
        TOKEN.append(TOKEN.length());
    }

    /**
     * Adds a string to the front of the token.
     */
    private static void addHeadContent() {
        TOKEN.insert(TOKEN_START_INDEX, HEAD_CONTENT);
    }

    /**
     * Loops through each character in the puzzle, shifting it backwards of forwards to decipher it.
     * @param puzzle The raw puzzle text.
     */
    private static void shiftChars(final String puzzle) {
        int length = puzzle.length();
        char[] puzzleArray = puzzle.toCharArray();
        for (int i = 0; i <= length - 1; i++) {
            TOKEN.append(shift(puzzleArray[i], i + 1));
        }
    }

    /**
     * Logic for the shift, taking into account the rollover if the shift passes A or Z.
     * @param character Character we are shifting.
     * @param shiftAmount The amount we are shifting by.
     * @return The shifted character.
     */
    private static char shift(final char character, final int shiftAmount) {
        if (shiftAmount % 2 == 0) {
            // roll forwards
            if (character + shiftAmount > 'Z') {
                return (char) (character - LETTER_RANGE + shiftAmount);
            } else {
                return (char) (character + shiftAmount);
            }
        } else {
            // roll backwards
            if (character - shiftAmount < 'A') {
                return (char) (character + LETTER_RANGE - shiftAmount);
            } else {
                return (char) (character - shiftAmount);
            }
        }
    }
}
