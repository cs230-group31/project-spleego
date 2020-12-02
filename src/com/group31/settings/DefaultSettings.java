package com.group31.settings;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultSettings {

    /**
     * All the default settings for the app.
     * @return All the default settings as a Hashmap.
     */
    public static HashMap<String, String> getDefaultSettings() {
        HashMap<String, String> defaultSettings = new HashMap<>();

        defaultSettings.put("api_url_base", "http://cswebcat.swansea.ac.uk/");
        defaultSettings.put("puzzle_route", "puzzle");
        defaultSettings.put("message_route", "message");
        defaultSettings.put("token_identifier", "?solution=");

        defaultSettings.put("window_height", "720");
        defaultSettings.put("window_width", "1280");
        defaultSettings.put("button_spacing", "10");
        defaultSettings.put("menu_image_url", "resources/images/background 1280 x 720.png");
        defaultSettings.put("title_image_url", "resources/images/title.png");
        defaultSettings.put("title_image_height", "315");
        defaultSettings.put("title_image_width", "210");
        defaultSettings.put("font_family", "Ink Free");
        defaultSettings.put("font_size", "40");
        defaultSettings.put("font_stroke", "1");
        defaultSettings.put("text_wrapping_width", "1240");
        defaultSettings.put("start_button_unpressed_url", "resources/images/start unpressed.png");
        defaultSettings.put("start_button_pressed_url", "resources/images/start pressed.png");
        defaultSettings.put("leaderboard_button_unpressed_url", "resources/images/leaderboard unpressed.png");
        defaultSettings.put("leaderboard_button_pressed_url", "resources/images/leaderboard pressed.png");
        defaultSettings.put("how_play_button_unpressed", "resources/images/how to play unpressed.png");
        defaultSettings.put("how_play_button_pressed", "resources/images/how to play pressed.png");
        defaultSettings.put("settings_button_unpressed", "resources/images/settings unpressed.png");
        defaultSettings.put("settings_button_pressed", "resources/images/settings pressed.png");
        defaultSettings.put("exit_button_unpressed", "resources/images/exit unpressed.png");
        defaultSettings.put("exit_button_pressed", "resources/images/exit pressed.png");

        defaultSettings.put("tile_route_id_0", "abcd");
        defaultSettings.put("tile_route_id_1", "bd");
        defaultSettings.put("tile_route_id_2", "ac");
        defaultSettings.put("tile_route_id_3", "ab");
        defaultSettings.put("tile_route_id_4", "bc");
        defaultSettings.put("tile_route_id_5", "cd");
        defaultSettings.put("tile_route_id_6", "da");
        defaultSettings.put("tile_route_id_7", "abc");
        defaultSettings.put("tile_route_id_8", "bcd");
        defaultSettings.put("tile_route_id_9", "cda");
        defaultSettings.put("tile_route_id_10", "dab");
        defaultSettings.put("num_tile_routes", "11");

        defaultSettings.put("tile_spacing", "3");
        defaultSettings.put("table_image_url", "resources/images/table.png");

        return defaultSettings;
    }

    /**
     * Gets the default settings as an array.
     * @return Array of settings.
     */
    public static String[] getDefaultSettingsArray() {
        ArrayList<String> settings = new ArrayList<>();
        getDefaultSettings().forEach((k, v) -> {
            settings.add(String.format("%s;%s", k, v));
        });
        String[] settingsArray = new String[settings.size()];
        return settings.toArray(settingsArray);
    }

}
