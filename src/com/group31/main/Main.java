package com.group31.main;

import com.group31.services.ApiRequest;
import com.group31.services.PuzzleSolver;

public final class Main {

    /**
     * Initialises the components and runs the app.
     *
     * @param args Args passed in at runtime.
     */
    public static void main(final String[] args) {
        // start program here
        String puzzleHeadContent = "CS-230";
        System.out.println(getMotd("http://cswebcat.swansea.ac.uk/", "puzzle", "message",
                "?solution=", puzzleHeadContent));
    }

    private static String getMotd(String urlBase, String puzzleRoute, String messageRoute,
                                  String tokenIdentifier, String puzzleHeadContent) {
        ApiRequest request = new ApiRequest(urlBase, puzzleRoute);
        String puzzle = PuzzleSolver.solvePuzzle(request.getResponse(), puzzleHeadContent);
        return new ApiRequest(urlBase, messageRoute, puzzle, tokenIdentifier).getResponse();
    }

}
