package com.group31.services;

import com.group31.exceptions.NoSuchDirectory;
import com.group31.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static String directory;

    public static void setDirectory(String requestedDirectory, boolean allowCreation) throws NoSuchDirectory {
        if (Files.exists(Paths.get(requestedDirectory))) {
            directory = requestedDirectory;
        } else if (allowCreation) {
            String logMessage = makeDir(requestedDirectory) ?
                    "File was created." :
                    String.format("Error creating file %s.", requestedDirectory);
            Logger.log(logMessage, Logger.Level.VERBOSE);
        } else {
            Logger.log("Request made to change file directory but requested directory does not exist.",
                    Logger.Level.ERROR);
            throw new NoSuchDirectory();
        }
    }

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

    public static String[] read(String fileName) throws NoSuchDirectory, FileNotFoundException {
        checkDirectorySet();

        File file = new File(directory, fileName);
        Scanner scanner = new Scanner(file);
        ArrayList<String> content = new ArrayList<>();
        while (scanner.hasNextLine()) {
            content.add(scanner.nextLine());
        }
        return (String[]) content.toArray();
    }

    private static boolean makeDir(String requestedDirectory) {
        File file = new File(requestedDirectory);
        return file.mkdirs();
    }

    private static void checkDirectorySet() throws NoSuchDirectory {
        if (directory == null) {
            throw new NoSuchDirectory("Directory not set.");
        }
    }

}
