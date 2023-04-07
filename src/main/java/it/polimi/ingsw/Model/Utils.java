package it.polimi.ingsw.Model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.awt.print.Book;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    private static String configurationPath = "src/main/resources/configurations";
    private static String testFilesPath = "src/test/testFiles";

    /* ************************************************************************************************************
     *                          START OF JSON FUNCTIONS
     ************************************************************************************************************ */

    public static String getConfigurationPath() {
        return configurationPath;
    }

    public static String getTestFilesPath() {
        return testFilesPath;
    }

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

        Bookshelf bookshelf = bookshelves[index];
        return bookshelf;
    }

    public static void saveToFile(String filePath, Object object) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        FileWriter writer = new FileWriter(filePath);
        writer.write(json);
        writer.close();
    }

    // method used to set up configurations
    /*public static void writeJsonFile() {
        Bookshelf bookshelf1 = new Bookshelf();
        Bookshelf bookshelf2 = new Bookshelf();
        Bookshelf bookshelf3 = new Bookshelf();
        Bookshelf bookshelf4 = new Bookshelf();
        Bookshelf bookshelf5 = new Bookshelf();
        Bookshelf bookshelf6 = new Bookshelf();
        Bookshelf bookshelf7 = new Bookshelf();
        Bookshelf bookshelf8 = new Bookshelf();
        Bookshelf bookshelf9 = new Bookshelf();
        Bookshelf bookshelf10 = new Bookshelf();
        Bookshelf bookshelf11 = new Bookshelf();
        Bookshelf bookshelf12 = new Bookshelf();


        bookshelf1.set(0,0,new Item(TypeEnum.PLANTS));
        bookshelf1.set(0,2,new Item(TypeEnum.FRAMES));
        bookshelf1.set(1,4,new Item(TypeEnum.CATS));
        bookshelf1.set(2,3,new Item(TypeEnum.BOOKS));
        bookshelf1.set(3,1,new Item(TypeEnum.GAMES));
        bookshelf1.set(5,2,new Item(TypeEnum.TROPHIES));

        bookshelf2.set(1,1,new Item(TypeEnum.PLANTS));
        bookshelf2.set(2,0,new Item(TypeEnum.CATS));
        bookshelf2.set(2,2,new Item(TypeEnum.GAMES));
        bookshelf2.set(3,4,new Item(TypeEnum.BOOKS));
        bookshelf2.set(4,3,new Item(TypeEnum.TROPHIES));
        bookshelf2.set(5,4,new Item(TypeEnum.FRAMES));

        bookshelf3.set(1,0,new Item(TypeEnum.FRAMES));
        bookshelf3.set(1,3,new Item(TypeEnum.GAMES));
        bookshelf3.set(2,2,new Item(TypeEnum.PLANTS));
        bookshelf3.set(3,1,new Item(TypeEnum.CATS));
        bookshelf3.set(3,4,new Item(TypeEnum.TROPHIES));
        bookshelf3.set(5,0,new Item(TypeEnum.BOOKS));

        bookshelf4.set(0,4,new Item(TypeEnum.GAMES));
        bookshelf4.set(2,0,new Item(TypeEnum.TROPHIES));
        bookshelf4.set(2,2,new Item(TypeEnum.FRAMES));
        bookshelf4.set(3,3,new Item(TypeEnum.PLANTS));
        bookshelf4.set(4,1,new Item(TypeEnum.BOOKS));
        bookshelf4.set(4,2,new Item(TypeEnum.CATS));

        bookshelf5.set(1,1,new Item(TypeEnum.TROPHIES));
        bookshelf5.set(3,1,new Item(TypeEnum.FRAMES));
        bookshelf5.set(3,2,new Item(TypeEnum.BOOKS));
        bookshelf5.set(4,4,new Item(TypeEnum.PLANTS));
        bookshelf5.set(5,0,new Item(TypeEnum.GAMES));
        bookshelf5.set(5,3,new Item(TypeEnum.CATS));

        bookshelf6.set(0,2,new Item(TypeEnum.TROPHIES));
        bookshelf6.set(0,4,new Item(TypeEnum.CATS));
        bookshelf6.set(2,3,new Item(TypeEnum.BOOKS));
        bookshelf6.set(4,1,new Item(TypeEnum.GAMES));
        bookshelf6.set(4,3,new Item(TypeEnum.FRAMES));
        bookshelf6.set(5,0,new Item(TypeEnum.PLANTS));

        bookshelf7.set(0,0,new Item(TypeEnum.CATS));
        bookshelf7.set(1,3,new Item(TypeEnum.FRAMES));
        bookshelf7.set(2,1,new Item(TypeEnum.PLANTS));
        bookshelf7.set(3,0,new Item(TypeEnum.TROPHIES));
        bookshelf7.set(4,4,new Item(TypeEnum.GAMES));
        bookshelf7.set(5,2,new Item(TypeEnum.BOOKS));

        bookshelf8.set(0,4,new Item(TypeEnum.FRAMES));
        bookshelf8.set(1,1,new Item(TypeEnum.CATS));
        bookshelf8.set(2,2,new Item(TypeEnum.TROPHIES));
        bookshelf8.set(3,0,new Item(TypeEnum.PLANTS));
        bookshelf8.set(4,3,new Item(TypeEnum.BOOKS));
        bookshelf8.set(5,3,new Item(TypeEnum.GAMES));

        bookshelf9.set(0,2,new Item(TypeEnum.GAMES));
        bookshelf9.set(2,2,new Item(TypeEnum.CATS));
        bookshelf9.set(3,4,new Item(TypeEnum.BOOKS));
        bookshelf9.set(4,1,new Item(TypeEnum.TROPHIES));
        bookshelf9.set(4,4,new Item(TypeEnum.PLANTS));
        bookshelf9.set(5,0,new Item(TypeEnum.FRAMES));

        bookshelf10.set(0,4,new Item(TypeEnum.TROPHIES));
        bookshelf10.set(1,1,new Item(TypeEnum.GAMES));
        bookshelf10.set(2,0,new Item(TypeEnum.BOOKS));
        bookshelf10.set(3,3,new Item(TypeEnum.CATS));
        bookshelf10.set(4,1,new Item(TypeEnum.FRAMES));
        bookshelf10.set(5,3,new Item(TypeEnum.PLANTS));

        bookshelf11.set(0,2,new Item(TypeEnum.PLANTS));
        bookshelf11.set(1,1,new Item(TypeEnum.BOOKS));
        bookshelf11.set(2,0,new Item(TypeEnum.GAMES));
        bookshelf11.set(3,2,new Item(TypeEnum.FRAMES));
        bookshelf11.set(4,4,new Item(TypeEnum.CATS));
        bookshelf11.set(5,3,new Item(TypeEnum.TROPHIES));

        bookshelf12.set(0,2,new Item(TypeEnum.BOOKS));
        bookshelf12.set(1,1,new Item(TypeEnum.PLANTS));
        bookshelf12.set(2,2,new Item(TypeEnum.FRAMES));
        bookshelf12.set(3,3,new Item(TypeEnum.TROPHIES));
        bookshelf12.set(4,4,new Item(TypeEnum.GAMES));
        bookshelf12.set(5,0,new Item(TypeEnum.CATS));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Bookshelf[] bookshelves =
                        {bookshelf1, bookshelf2, bookshelf3,
                        bookshelf4, bookshelf5, bookshelf6,
                        bookshelf7, bookshelf8, bookshelf9,
                        bookshelf10, bookshelf10, bookshelf12};
        String json = gson.toJson(bookshelves);

        try (FileWriter writer = new FileWriter("src/main/resources/configurations/CommonGoalsConfiguration.JSON")) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     */

    /* ************************************************************************************************************
     *                          END OF JSON FUNCTIONS
     ************************************************************************************************************ */
}
