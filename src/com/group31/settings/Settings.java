package com.group31.settings;


import com.group31.logger.Logger;
import java.util.HashMap;
import java.util.Map;

public class Settings {

    /**
     * Hashmap containing all the settings.
     */
    private static HashMap<String, String> settings;

    /**
     * Index of the setting's key.
     */
    private static final int SETTING_KEY = 1;
    /**
     * Index of the setting's value.
     */
    private static final int SETTING_VALUE = 1;

    /**
     * Updates settings value if the key exists.
     * @param args Settings to change.
     */
    public static void updateSettings(String[] args) {
        for (String setting : args) {
            String[] settingNameValue = setting.split(":");
            if (settings.containsKey(settingNameValue[SETTING_KEY])) {
                settings.put(settingNameValue[SETTING_KEY],
                        settingNameValue[SETTING_VALUE]);
            }
        }
    }

    /**
     * Initialises settings.
     * @param allSettingsData All settings (keys and values).
     */
    public static void setAllSettings(HashMap<String, String> allSettingsData) {
        settings = allSettingsData;
    }

    /**
     * Gets all the settings.
     * @return All settings.
     */
    public static Map<String, String> getAllSettings() {
        return settings;
    }

    /**
     * Gets a setting.
     * @param key Setting name.
     * @return Setting value.
     */
    public static String get(String key) {
        if (settings.containsKey(key)) {
            return settings.get(key);
        }
        // TODO: find a better way to handle this.
        return null;
    }

    /**
     * Gets a setting and parses it to a double.
     * @param key Setting name.
     * @return Setting value.
     */
    public static Double getDouble(String key) {
        if (settings.containsKey(key)) {
            return Double.parseDouble(settings.get(key));
        }
        // TODO: find a better way to handle this.
        return 1.0;
    }

    /**
     * Writes all the settings to the console.
     * For debugging purposes only!
     */
    public static void dumpSettingsToConsole() {
        for (String key : settings.keySet()) {
            Logger.log(String.format("%s : %s", key, settings.get(key)), Logger.Level.VERBOSE);
        }
    }
}
