package com.group31.services;

import com.group31.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiRequest {
    private final String baseUrl;
    private final String route;
    private String token;
    private String tokenIdentifier;

    public ApiRequest(String baseUrl, String route) {
        this.baseUrl = baseUrl;
        this.route = route;
    }

    public ApiRequest(String baseUrl, String route, String token, String tokenIdentifier) {
        this.baseUrl = baseUrl;
        this.route = route;
        this.token = token;
        this.tokenIdentifier = tokenIdentifier;
    }

    public String getResponse() {
        HttpURLConnection connection = null;
        try {
            URL url = token == null ? buildUrl(route) : buildUrlWithToken(route);
            connection = connectionGet(url);
            return read(connection);
        } catch (Exception e) {
            Logger.log(e.toString(), Logger.Level.ERROR);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    private String read(HttpURLConnection connection) throws IOException {
        InputStream responseStream = connection.getInputStream();
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(responseStream));
        return responseReader.readLine();
    }

    private HttpURLConnection connectionGet(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        return connection;
    }

    private URL buildUrl(String route) throws MalformedURLException {
        return new URL(String.format("%s%s", baseUrl, route));
    }

    private URL buildUrlWithToken(String route) throws MalformedURLException {
        return new URL(String.format("%s%s%s%s", baseUrl, route, tokenIdentifier, token));
    }
}
