package com.group31.services.serializer;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.exceptions.ObjectNeverSerialized;
import com.group31.logger.Logger;
import com.group31.services.FileManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author liam
 */
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
     * @param objectName folder where the files will be stored (no '/'). For example: 'Players'.
     */
    public static void serialize(Object object, String identifier, String objectName) {
        try {
            String directory = String.format("%s%s/", SERIALIZED_OBJECTS_FOLDER, objectName);
            FileManager.setDirectory(directory, true);
            if (!identifiers.contains(identifier)) {
                FileManager.serializeWrite(object, identifier);
                identifiers.add(identifier);
                saveIdentifiers();
            }
        } catch (NoSuchDirectory | IOException e) {
            Logger.log("Error while trying to serialize object: " + e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Deserializes a file into an instance of Player.
     * @param identifier File name (excluding extension).
     * @param object the object to deserialize
     * @return Instance of player.
     * @throws ObjectNeverSerialized if the object is not serialized
     */
    public static Object deserialize(String identifier, String object) throws ObjectNeverSerialized {
            try {
                if (identifiers.contains(identifier)) {
                    String fileName = String.format("%s.%s", identifier, FILE_EXTENSION);
                    String serializedObjectsDir = String.format("%s%s/", SERIALIZED_OBJECTS_FOLDER, object);
                    FileManager.setDirectory(serializedObjectsDir, false);
                    if (FileManager.fileExists(fileName)) {
                        identifiers.removeAll(Collections.singleton(identifier));
                        Object deserializedFile = FileManager.deserializeRead(identifier);
                        FileManager.deleteFile(String.format("%s.ser", identifier));
                        saveIdentifiers();
                        return deserializedFile;
                    } else {
                        throw new FileNotFoundException("File to deserialize was not found.");
                    }
                } else {
                    throw new ObjectNeverSerialized(
                            identifier + " has not been serialized, "
                                    + "or serialized file cannot be found. (Deserializer)");
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
        } catch (NoSuchDirectory | IOException e) {
            Logger.log(e.getMessage(), Logger.Level.ERROR);
        }
    }

    /**
     * Initialise the serializer.
     */
    public static void init() {
        identifiers.clear();
        try {
            String fileName = "identifiers.txt";
            FileManager.setDirectory(IDENTIFIERS_FILE_LOCATION, true);
            if (!FileManager.fileExists(fileName)) {
                saveIdentifiers();
            }
        } catch (NoSuchDirectory noSuchDirectory) {
            Logger.log(noSuchDirectory.getMessage(), Logger.Level.ERROR);
        }
        readIdentifiers();
    }
}
