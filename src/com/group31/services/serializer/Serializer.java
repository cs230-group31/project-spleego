package com.group31.services.serializer;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.player.Player;
import com.group31.services.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Serializer {

    /**
     * List of identifiers, to keep track of what has been serialized.
     */
    private static ArrayList<String> identifiers = new ArrayList<>();

    /**
     * Serialized file extension.
     */
    private static final String FILE_EXTENSION = "ser";

    /**
     * Directory where the identifiers file is stored.
     */
    private static final String IDENTIFIERS_FILE_LOCATION = "data/serializing/";

    /**
     * Directory where the serialized files is stored.
     */
    private static final String SERIALIZED_OBJECTS_FOLDER = "data/serializing/serialized/";

    /**
     * Serializes an object.
     * @param object Object to serialize.
     * @param identifier Filename (excluding extension).
     * @param folder folder where the files will be stored (no '/'). For example: 'Players'.
     */
    public static void serialize(Object object, String identifier, String folder) {
        try {
            String directory = String.format("%s%s/", SERIALIZED_OBJECTS_FOLDER, folder);
            FileManager.setDirectory(directory, true);
            if (!identifiers.contains(identifier)) {
                FileManager.serializeWrite(object, identifier);
                identifiers.add(identifier);
                saveIdentifiers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchDirectory e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Deserializes a file into an instance of Player.
     * @param identifier File name (excluding extension).
     * @return Instance of player.
     */
    public static Player deserializePlayer(String identifier) throws ObjectNeverSerialized {
        readIdentifiers();
            try {
                if (identifiers.contains(identifier)) {
                    String fileName = String.format("%s.%s", identifier, FILE_EXTENSION);
                    String serializedPlayersFolder = String.format("%sPlayers/", SERIALIZED_OBJECTS_FOLDER);
                    FileManager.setDirectory(serializedPlayersFolder, false);
                    if (FileManager.fileExists(fileName)) {
                        identifiers.remove(identifier);
                        Player playerToReturn = (Player) FileManager.deserializeRead(identifier);
                        FileManager.deleteFile(String.format("%s.ser", identifier));
                        saveIdentifiers();
                        return playerToReturn;
                    } else {
                        throw new FileNotFoundException("File to deserialize was not found.");
                    }
                } else {
                    throw new ObjectNeverSerialized(
                            "Object has not been serialized, or serialized file cannot be found.");
                }
            } catch (NoSuchDirectory | IOException | ClassNotFoundException e) {
                Logger.log(e.getMessage(), Logger.Level.ERROR);
            }
        return null;
    }

    /**
     * Saves the identifiers to a file.
     */
    private static void saveIdentifiers() {
        String identifiersFileName = "identifiers.txt";
        try {
            FileManager.setDirectory(IDENTIFIERS_FILE_LOCATION, true);
            String[] content = identifiers.toArray(new String[0]);
            FileManager.write(content, identifiersFileName);
        } catch (NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Reads identifiers from the identifiers file.
     */
    private static void readIdentifiers() {
        String identifiersFileName = "identifiers.txt";
        boolean allowCreation = false;
        try {
            FileManager.setDirectory(IDENTIFIERS_FILE_LOCATION, allowCreation);
            String[] contents = FileManager.read(identifiersFileName);
            identifiers.addAll(Arrays.asList(contents));
        } catch (NoSuchDirectory | FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }
}
