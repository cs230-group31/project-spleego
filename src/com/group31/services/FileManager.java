package com.group31.services;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.logger.Logger;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
        } else if (allowCreation) {
            String logMessage = makeDir(requestedDirectory)
                    ? String.format("Directory '%s' was created.", requestedDirectory)
                    : String.format("Error creating directory '%s'.", requestedDirectory);
            directory = requestedDirectory;
            Logger.log(logMessage, Logger.Level.INFO);
        } else {
            Logger.log("Request made to change file directory but requested directory does not exist.",
                    Logger.Level.ERROR);
            throw new NoSuchDirectory();
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
        return content.toArray(settings);
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
     * Checks if a file exists.
     * @param fileName File we want to check.
     * @return If the file exists or not.
     */
    public static boolean fileExists(String fileName) {
        return Files.exists(Paths.get(directory));
    }
}
