package jrosnve.utilities;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

public class FileReader {
    private static String getFileContents(String pathToFile) {
        try {
            return new String(Files.readAllBytes(Paths.get(pathToFile)));
        } catch(IOException e) {
            return "ERROR";
        }
    }

    public static String[] getParagraphs(String pathToFile) {
        String content = getFileContents(pathToFile);
        String[] paragraphs = content.split("%N%");
        return paragraphs;
    }
}
