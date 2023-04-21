package it.polimi.ingsw.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
         ************************************************************************************************************ */

    private final static String configurationPath = "src/main/resources/configurations/";
    private final static String testFilesPath = "src/test/testFiles/";

    /* ************************************************************************************************************
     *                          END OF ATTRIBUTES DECLARATION
     *                          START OF PATH FUNCTIONS
     ************************************************************************************************************ */

    public static String getConfigurationPath() {
        return configurationPath;
    }

    public static String getTestFilesPath() {
        return testFilesPath;
    }

    /* ************************************************************************************************************
     *                          END OF PATH PUNCTIONS
     *                          START OF JSON FUNCTIONS
     ************************************************************************************************************ */

    public static Board loadBoardFromFile(String filePath) throws IOException {

        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, Board.class);
    }

    public static Bookshelf loadBookshelfFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, Bookshelf.class);
    }

    public static Bookshelf loadBookshelfFromFile(String filePath, int index) throws IOException {

        String jsonString = "";
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                jsonString += (char) character;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse the JSON string into an array of Person objects
        Gson gson = new Gson();
        Bookshelf[] bookshelves = gson.fromJson(jsonString, Bookshelf[].class);

        return bookshelves[index];
    }

    public static void saveToFile(String filePath, Object object) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }

    /* ************************************************************************************************************
     *                          END OF JSON FUNCTIONS
     ************************************************************************************************************ */
}
