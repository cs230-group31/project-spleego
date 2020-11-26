package com.group31.settings;

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

}
