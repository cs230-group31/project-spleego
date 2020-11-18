package com.group31.settings;

import java.util.HashMap;
import java.util.Map;

public class Settings {

    private static HashMap<String, String> settings;

    private static final int SETTING_KEY = 0;
    private static final int SETTING_VALUE = 1;

    public static void updateSettings(String[] args) {
        for (String setting : args) {
            String[] settingNameValue = setting.split(":");
            if (settings.containsKey(settingNameValue[SETTING_KEY])) {
                settings.put(settingNameValue[SETTING_KEY], settingNameValue[SETTING_VALUE]);
            }
        }
    }

    public static void setAllSettings(HashMap<String, String> settingsFromFile) {
        settings = settingsFromFile;
    }

    public static Map<String, String> getAllSettings() {
        return settings;
    }

}
