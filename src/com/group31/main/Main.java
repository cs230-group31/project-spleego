package com.group31.main;

import com.group31.services.ApiRequest;

public final class Main {

    /**
     * Initialises the components and runs the app.
     *
     * @param args Args passed in at runtime.
     */
    public static void main(final String[] args) {
        // start program here
        System.out.println(new ApiRequest("http://cswebcat.swansea.ac.uk/", "puzzle").getResponse());
        System.out.println(new ApiRequest("https://api.openweathermap.org/data/2.5/", "weather?q=swansea", "abebd2c5cf413635e368dc078e9f616f", "&appid=").getResponse());
    }
}
