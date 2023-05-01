package it.polimi.ingsw.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.TypeEnum;

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

    /**
     * This function returns the path of the configuration files
     * @return the path of the configuration files
     */
    public static String getConfigurationPath() {
        return configurationPath;
    }

    /**
     * This function returns the path of the test files
     * @return the path of the test files
     */
    public static String getTestFilesPath() {
        return testFilesPath;
    }

    /* ************************************************************************************************************
     *                          END OF PATH PUNCTIONS
     *                          START OF JSON FUNCTIONS
     ************************************************************************************************************ */

    /**
     * This function loads a board from a json file
     * @param filePath the path of the file
     * @return the board
     * @throws IOException if the file is not found
     */
    public static Board loadBoardFromFile(String filePath) throws IOException {

        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, Board.class);
    }

    /**
     * This function loads a bookshelf from a json file containing only one bookshelf
     * @param filePath the path of the file
     * @return the bookshelf
     * @throws IOException if the file is not found
     */
    public static Bookshelf loadBookshelfFromFile(String filePath) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filePath)));
        return gson.fromJson(json, Bookshelf.class);
    }

    /**
     * This function loads a bookshelf from a json file containing multiple bookshelves and returns the one at the
     * specified index
     * @param filePath the path of the file
     * @param index the index of the bookshelf to load
     * @return the bookshelf
     * @throws IOException if the file is not found
     */
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
        // todo è possibile farla funzionale?
    }

    /**
     * This function saves an object to a json file
     * @param filePath the path of the file
     * @param object the object to save
     * @throws IOException if the file is not found
     */
    public static void saveToFile(String filePath, Object object) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }

    /* ************************************************************************************************************
     *                          END OF JSON FUNCTIONS
     *                         START OF CUSTOM FUNCTIONS
     ************************************************************************************************************ */

    /**
     * This function returns a random item
     * @return the random item
     */
    public static Item getRandomItem() {
        int random = (int) (Math.random() * 6);
        switch (random) {
            case 0:
                return new Item(TypeEnum.CATS);
            case 1:
                return new Item(TypeEnum.BOOKS);
            case 2:
                return new Item(TypeEnum.TROPHIES);
            case 3:
                return new Item(TypeEnum.PLANTS);
            case 4:
                return new Item(TypeEnum.GAMES);
            case 5:
                return new Item(TypeEnum.FRAMES);
            default:
                return null;
        }
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM FUNCTIONS
     ************************************************************************************************************ */

}
