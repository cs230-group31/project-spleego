package com.group31.services;

import com.group31.exceptions.NoFilesInDir;
import com.group31.exceptions.NoSuchDirectory;
import com.group31.logger.Logger;
import javafx.scene.image.Image;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    /**
     * Directory of the file we are reading/writing to.
     */
    private static String directory;

    /**
     * Sets the directory that FileManager should look in, creates directory if allowed to and the requested
     * directory doesn't exist.
     * @param requestedDirectory Directory we want to set/create.
     * @param allowCreation Allows the creation of a directory.
     * @throws NoSuchDirectory If the directory does not exist, and we are not allowed to create it.
     */
    public static void setDirectory(String requestedDirectory, boolean allowCreation) throws NoSuchDirectory {
        if (Files.exists(Paths.get(requestedDirectory))) {
            directory = requestedDirectory;
            Logger.log(String.format("Directory changed to %s", directory), Logger.Level.INFO);
        } else if (allowCreation) {
            String logMessage = makeDir(requestedDirectory)
                    ? String.format("Directory '%s' was created.", requestedDirectory)
                    : String.format("Error creating directory '%s'.", requestedDirectory);
            directory = requestedDirectory;
            Logger.log(logMessage, Logger.Level.INFO);
        } else {
            Logger.log("Request made to change file directory but requested directory does not exist.",
                    Logger.Level.ERROR);
            throw new NoSuchDirectory("Request made to change file directory but requested directory does not exist.");
        }
    }
    /**
     * Writes to a file.
     * @param content Content to write.
     * @param fileName Filename to write to.
     * @throws NoSuchDirectory If directory does not exist.
     * @throws IOException If file does not exist.
     */
    public static void write(String[] content, String fileName) throws NoSuchDirectory, IOException {
        checkDirectorySet();

        File file = new File(directory, fileName);
        BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
        for (String line : content) {
            writer.write(line);
            writer.newLine();
        }
        Logger.log(String.format("Successfully wrote %s to %s.", fileName, directory), Logger.Level.INFO);
        writer.close();
    }

    /**
     * Reads from a file.
     * @param fileName Filename to read from.
     * @return Contents of the file.
     * @throws NoSuchDirectory If the directory does not exist.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static String[] read(String fileName) throws NoSuchDirectory, FileNotFoundException {
        checkDirectorySet();

        File file = new File(directory, fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<String> content = new ArrayList<>();
        while (scanner.hasNextLine()) {
            content.add(scanner.nextLine());
        }
        String[] settings = new String[content.size()];
        Logger.log(String.format("Successfully read %s from %s.", fileName, directory), Logger.Level.INFO);
        return content.toArray(settings);
    }

    /**
     * Serializes an object to the filesystem.
     * @param object Object to serialize.
     * @param identifier Name of the file (without extension).
     * @throws IOException If the file cannot be created/found.
     * @throws NoSuchDirectory If the directory cannot be set/created.
     */
    public static void serializeWrite(Object object, String identifier) throws IOException, NoSuchDirectory {
        checkDirectorySet();
        Logger.log(String.format("Attempting to serialize %s to %s.", identifier, directory), Logger.Level.INFO);
        FileOutputStream fileOut = new FileOutputStream(String.format("%s%s.ser", directory, identifier));
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(object);
        objectOut.close();
        fileOut.close();
        Logger.log(String.format("Successfully serialized %s to %s.", identifier, directory), Logger.Level.INFO);
    }

    /**
     * Deserializes a serialized file from the filesystem.
     * @param identifier Filename (without extension).
     * @return An object representing the deserialized file.
     * @throws NoSuchDirectory If the directory cannot be found.
     * @throws IOException If the file cannot be found.
     * @throws ClassNotFoundException If we cannot deserialize the file into an object.
     */
    public static Object deserializeRead(String identifier)
            throws NoSuchDirectory, IOException, ClassNotFoundException {
        checkDirectorySet();
        Logger.log(String.format("Attempting to deserialize %s%s.", directory, identifier), Logger.Level.INFO);
        FileInputStream fileIn = new FileInputStream(String.format("%s%s.ser", directory, identifier));
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object object = objectIn.readObject();
        objectIn.close();
        fileIn.close();
        Logger.log(String.format("Successfully deserialized %s from %s.", identifier, directory), Logger.Level.INFO);
        return object;
    }

    /**
     * Gets all files in a directory.
     * @return All files in the set directory.
     * @throws NoSuchDirectory If the directory is not set.
     */
    public static File[] getAllFilesInDir() throws NoSuchDirectory {
        checkDirectorySet();
        return new File(directory).listFiles();
    }

    /**
     * Gets all files in a directory which name names that contain the search term.
     * @param searchTerm Term to look for in each file name.
     * @return Files that have the search term in their name.
     * @throws NoSuchDirectory If the directory has not been set/cannot be found.
     * @throws NoFilesInDir If there are no files in the directory.
     */
    public static File[] getAllFilesInDir(String searchTerm) throws NoSuchDirectory, NoFilesInDir {
        checkDirectorySet();
        File[] files = new File(directory).listFiles();
        if (files.length < 1) {
            throw new NoFilesInDir(String.format("No files found in %s", directory));
        }
        ArrayList<File> matchingFiles = new ArrayList<>();
        for (File file : files) {
            if (file.getName().toLowerCase().contains(searchTerm)) {
                matchingFiles.add(file);
            }
        }
        return matchingFiles.toArray(new File[0]);
    }

    /**
     * Deletes a file.
     * @param fileName Name of file to delete.
     * @throws NoSuchDirectory If the directory has not been set.
     * @throws FileNotFoundException If the file cannot be found.
     */
    public static void deleteFile(String fileName) throws NoSuchDirectory, FileNotFoundException {
        checkDirectorySet();

        String dirFileName = String.format("%s%s", directory, fileName);
        if (Files.exists(Paths.get(dirFileName))) {
            File fileToDelete = new File(dirFileName);
            if (fileToDelete.delete()) {
                Logger.log(String.format("%s was deleted from %s.", fileName, directory), Logger.Level.INFO);
            } else {
                Logger.log(String.format("Failed to delete %s from %s.", fileName, directory), Logger.Level.ERROR);
            }
        } else {
            throw new FileNotFoundException(String.format(
                    "Failed to delete %s from %s. File does not exist", fileName, directory));
        }
    }

    /**
     * Reads an image from a file.
     * @param imageFileName image's file name
     * @param imageFileExtension image's file extension (without '.'. For example: 'png')
     * @param properties image's properties. Index 0 being the width, index 1 being the height
     * @param preserveRatio preserves the image's ratio. (Recommended: true)
     * @return image from a file
     * @throws NoSuchDirectory if the directory has not been set
     * @throws IOException if the image file cannot be found
     */
    public static Image readImage(String imageFileName, String imageFileExtension, double[] properties,
                                  boolean preserveRatio) throws NoSuchDirectory, IOException {
        checkDirectorySet();

        int tileWidthIndex = 0;
        int tileHeightIndex = 1;
        String imageFileLocation = String.format("%s%s.%s", directory, imageFileName, imageFileExtension);
        FileInputStream imageFile = new FileInputStream(imageFileLocation);
        Image image = new Image(imageFile, properties[tileWidthIndex],
                properties[tileHeightIndex], preserveRatio, false);
        imageFile.close();
        return image;
    }

    /**
     * Creates a directory.
     * @param requestedDirectory Directory path to create.
     * @return If the directory has been made or not.
     */
    private static boolean makeDir(String requestedDirectory) {
        File file = new File(requestedDirectory);
        return file.mkdirs();
    }
    /**
     * Ensures the directory has been set, if not, throw an error.
     * @throws NoSuchDirectory If the directory does not exist.
     */
    private static void checkDirectorySet() throws NoSuchDirectory {
        if (directory == null) {
            throw new NoSuchDirectory("Directory not set.");
        }
    }

    /**
     * Checks if a file exists in the FileManager's current directory.
     * @param fileName Location of the file we want to check.
     * @return If the file exists or not.
     */
    public static boolean fileExists(String fileName) {
        return Files.exists(Paths.get(String.format("%s%s", directory, fileName)));
    }
}
