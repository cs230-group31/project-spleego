# Settings

## Description

Settings will store all the settings required in the game. The class does the following:

- Update all settings.
- Change settings if they exist.
- Return all settings.

The settings are stored in a `Hashmap<String, String>` called `settings`. It can be imported into your classed by
adding this import line at the top:

```java
import com.group31.settings.Settings;
```

The class is static so there is no need to create an instance of it.

## Accepts

Current methods in Settings and their parameters:

```java
// requires settings to be passed in prepackaged in a HashMap.
public static void setAllSettings(HashMap<String, String> settingsFromFile)

// Requires changes be passed in as a string array
public static void updateSettings(String[] args)
```

The parameter `String[] args` requires the string array to be formatted in the following way:

```java
["setting_name:setting_value", "setting2_name:setting2_value", "..."]
```

For example:

```java
["screen_size:600x600", "save_file_location:'/data/players/player_xxxxx.txt'", "..."]
```

## Returns

Current methods in settings and their return types:

```java
// returns a hashmap of all the settings.
public static Map<String, String> getAllSettings()
```

## Changing Settings

If you add/remove or change anything in Settings please be sure to update this document with your changes.