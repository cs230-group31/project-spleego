package com.group31.main;

import com.group31.services.ApiRequest;
import com.group31.services.PuzzleSolver;

public class Main {

    /**
     * Initialises the components and runs the app.
     *
     * @param args Args passed in at runtime.
     */
    public static void main(String[] args) {
        // start program here

        // Testing:
        System.out.println(getMotd("http://cswebcat.swansea.ac.uk/", "puzzle", "message",
                "?solution="));
    }

    /**
     * Gets the MOTD.
     * @param urlBase URL base for the API.
     * @param puzzleRoute The URL route of the puzzle text.
     * @param messageRoute The URL route of the message text.
     * @param tokenIdentifier The tag in the URL that identifies the token.
     * @return The response from the API as a string.
     */
    private static String getMotd(String urlBase, String puzzleRoute, String messageRoute,
                                  String tokenIdentifier) {
        ApiRequest request = new ApiRequest(urlBase, puzzleRoute);
        String puzzle = PuzzleSolver.solvePuzzle(request.getResponse());
        return new ApiRequest(urlBase, messageRoute, puzzle, tokenIdentifier).getResponse();
    }
}
