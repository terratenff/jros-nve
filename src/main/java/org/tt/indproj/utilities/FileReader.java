package org.tt.indproj.utilities;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.nio.file.Files;

import java.util.ArrayList;

/**
 * Utility class that reads simple text files and returns their contents
 * in a manageable format.
 * @author terratenff
 */
public class FileReader {
    /**
     * Default path, from which most text files can be found.
     */
    private static final String PATH = "src/main/resources/textfiles/";

    /**
     * Separator for paragraphs.
     */
    private static final String PARAGRAPH_SEPARATOR = "%P%";

    /**
     * Convenience function: collects everything from specified
     * text file.
     * @param filename Name of the text file, suffix included.
     * @return Contents of the text file, as a singular String.
     * Returns "ERROR" if an IOException occurred.
     * @note This function does not recognize line breaks.
     */
    private static String getFileContents(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(PATH + filename)));
        } catch(IOException e) {
            return "ERROR";
        }
    }

    /**
     * Reads specified text file, and returns the contents of the text file
     * as a String array of discovered paragraphs.
     * @param filename Name of the text file
     * @return Paragraphs of the text file as a String array.
     * @note Paragraphs are recognized with the separator PARAGRAPH_SEPARATOR.
     */
    public static String[] getParagraphs(String filename) {
        String content = getFileContents(filename);
        String[] paragraphs = content.split(PARAGRAPH_SEPARATOR);
        return paragraphs;
    }
}
