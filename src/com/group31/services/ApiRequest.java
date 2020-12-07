package com.group31.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Moe, liamdp
 */
public class ApiRequest {

    /**
     * API's base URL. For example "api.someapi.com/"
     */
    private final String baseUrl;
    /**
     * Route of item we want to get. For example "weather/swansea"
     */
    private final String route;
    /**
     * Token to authorise request.
     */
    private String token;
    /**
     * String to tag the token in the URL. For example "?token="
     */
    private String tokenIdentifier;

    /**
     * Creates a new API request without a token.
     * @param baseUrl API's base URL. For example "api.someapi.com/"
     * @param route Route of item we want to get. For example "weather/swansea"
     */
    public ApiRequest(String baseUrl, String route) {
        this.baseUrl = baseUrl;
        this.route = route;
    }

    /**
     * Creates a new API request with a token.
     * @param baseUrl API's base URL. For example "api.someapi.com/"
     * @param route Route of item we want to get. For example "weather/swansea"
     * @param token Token used to authorise the request.
     * @param tokenIdentifier String to tag the token in the URL. For example "?token="
     */
    public ApiRequest(String baseUrl, String route, String token, String tokenIdentifier) {
        this.baseUrl = baseUrl;
        this.route = route;
        this.token = token;
        this.tokenIdentifier = tokenIdentifier;
    }

    /**
     * Gets the response from the server.
     * @return The response as a string.
     * @throws IOException If the response cannot be read.
     */
    public String getResponse() throws IOException {
        URL url = this.token == null ? buildUrl() : buildUrlWithToken();
        HttpURLConnection connection = connectionGet(url);
        String res = read(connection);
        connection.disconnect();
        return res;
    }

    /**
     * Reads the response and converts it into a string.
     * @param connection Connection to the API.
     * @return Content from the API as a string.
     * @throws IOException If no the was an error in the connection to the server.
     */
    private String read(HttpURLConnection connection) throws IOException {
        InputStream responseStream = connection.getInputStream();
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(responseStream));
        return responseReader.readLine();
    }

    /**
     * Creates a connection to the server.
     * @param url Location of the server and resource.
     * @return A connection to the server.
     * @throws IOException If the URL is invalid, or cannot be found.
     */
    private HttpURLConnection connectionGet(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    /**
     * Builds the URL combining the base and route.
     * @return Formed URL with base and route combined.
     * @throws MalformedURLException If the no protocol is provided (http, https etc) or the string cannot be parsed.
     */
    private URL buildUrl() throws MalformedURLException {
        return new URL(String.format("%s%s", this.baseUrl, this.route));
    }

    /**
     * Builds the URL with the token.
     * @return Formed URL with base, route, token identifier and token combined.
     * @throws MalformedURLException If the no protocol is provided (http, https etc) or the string cannot be parsed.
     */
    private URL buildUrlWithToken() throws MalformedURLException {
        return new URL(String.format("%s%s%s%s", this.baseUrl, this.route, this.tokenIdentifier, this.token));
    }
}
