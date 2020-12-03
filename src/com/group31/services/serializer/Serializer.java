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
    private static final String IDENTIFIERS_FILE = "data/serializing/";

    /**
     * Directory where the serialized files is stored.
     */
    private static final String SERIALIZED_OBJECTS_FILE = "data/serializing/serialized/";

    /**
     * Serializes an object.
     * @param object Object to serialize.
     * @param identifier Filename (excluding extension).
     */
    public static void serialize(Object object, String identifier) {
        String identifiersFileName = "identifiers.txt";
        try {
            FileManager.setDirectory(SERIALIZED_OBJECTS_FILE, true);
            if (!identifiers.contains(identifier)) {
                FileManager.serializeWrite(object, identifier);
                identifiers.add(identifier);
                saveIdentifiers(identifiersFileName);
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
     * @throws ObjectNeverSerialized If the object was never serialized (the identifier is not in the identifiers list).
     */
    public static Player deserializePlayer(String identifier) throws ObjectNeverSerialized {
        if (identifiers.contains(identifier)) {
            try {
                FileManager.setDirectory(SERIALIZED_OBJECTS_FILE, false);
                String fileName = String.format("%s.%s", identifier, FILE_EXTENSION);
                if (FileManager.fileExists(fileName)) {
                    identifiers.remove(identifier);
                    Player playerToReturn = (Player) FileManager.deserializeRead(identifier);
                    FileManager.deleteFile(String.format("%s.ser", identifier));
                    return playerToReturn;
                } else {
                    throw new FileNotFoundException("File to deserialize was not found.");
                }
            } catch (NoSuchDirectory | IOException | ClassNotFoundException e) {
                Logger.log(e.getMessage(), Logger.Level.ERROR);
            }
        } else {
         throw new ObjectNeverSerialized(
                 "Object has not been serialized, or serialized file cannot be found.");
        }
        return null;
    }

    /**
     * Saves the identifiers to a file.
     * @param fileName Filename to save identifiers to.
     */
    private static void saveIdentifiers(String fileName) {
        try {
            FileManager.setDirectory(IDENTIFIERS_FILE, true);
            String[] content = identifiers.toArray(new String[0]);
            FileManager.write(content, fileName);
        } catch (NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Reads identifiers from the identifiers file.
     * @param directory Directory to read the identifiers file from.
     * @param fileName Name of the identifiers file.
     */
    public static void readIdentifiers(String directory, String fileName) {
        boolean allowCreation = false;
        try {
            FileManager.setDirectory(directory, allowCreation);
            String[] contents = FileManager.read(fileName);
            identifiers.addAll(Arrays.asList(contents));
        } catch (NoSuchDirectory | FileNotFoundException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }
}
