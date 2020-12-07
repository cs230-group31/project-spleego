package com.group31.settings;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author liamdp
 */
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

        defaultSettings.put("tile_route_id_0", "a,b,c,d");
        defaultSettings.put("tile_route_id_1", "b,d");
        defaultSettings.put("tile_route_id_2", "a,c");
        defaultSettings.put("tile_route_id_3", "a,b");
        defaultSettings.put("tile_route_id_4", "b,c");
        defaultSettings.put("tile_route_id_5", "c,d");
        defaultSettings.put("tile_route_id_6", "d,a");
        defaultSettings.put("tile_route_id_7", "a,b,c");
        defaultSettings.put("tile_route_id_8", "b,c,d");
        defaultSettings.put("tile_route_id_9", "c,d,a");
        defaultSettings.put("tile_route_id_10", "d,a,b");
        defaultSettings.put("num_tile_routes", "11");
        defaultSettings.put("max_tiles", "10");

        defaultSettings.put("tile_spacing", "3");
        defaultSettings.put("table_image_url", "resources/images/table.png");

        defaultSettings.put("tile_width", "64");
        defaultSettings.put("tile_height", "64");
        defaultSettings.put("tile_images_url", "resources/images/tiles/");

        defaultSettings.put("tile_weight_fire", "15");
        defaultSettings.put("tile_weight_freeze", "15");
        defaultSettings.put("tile_weight_backtrack", "10");
        defaultSettings.put("tile_weight_doublemove", "10");
        defaultSettings.put("tile_weight_floortile", "50");

        defaultSettings.put("serialized_objects_folder", "data/serializing/serialized/");

        defaultSettings.put("tutorial_text", "TUTORIAL TEXT GOES HERE!");

        defaultSettings.put("tiles_fed_below", "0,2,4,5,7,8,9");
        defaultSettings.put("tiles_fed_above", "0,2,3,6,7,9,10");
        defaultSettings.put("tiles_fed_left", "0,1,5,6,8,9,10");
        defaultSettings.put("tiles_fed_right", "0,1,3,4,7,8,10");

        defaultSettings.put("level_files", "data/level_files/");


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
