package it.polimi.ingsw.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.Model.Board;
import it.polimi.ingsw.Model.Bookshelf;
import it.polimi.ingsw.Model.Item;
import it.polimi.ingsw.Model.TypeEnum;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Utils {

    /* ************************************************************************************************************
     *                          START OF ATTRIBUTES DECLARATION
     ************************************************************************************************************ */

    private final static String configurationPath = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "configurations" + File.separator;
    private final static String testFilesPath = "src" + File.separator + "test" + File.separator + "testFiles" + File.separator;

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
     *                          END OF PATH FUNCTIONS
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
     * @return the bookshelf        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(Utils.getConfigurationPath() + "TUIConfig.json")));
        return gson.fromJson(json, Configuration.class).getItemDimension();
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

        StringBuilder jsonString = new StringBuilder();
        try (FileReader reader = new FileReader(filePath)) {
            int character;
            while ((character = reader.read()) != -1) {
                jsonString.append((char) character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Parse the JSON string into an array of Person objects
        Gson gson = new Gson();
        Bookshelf[] bookshelves = gson.fromJson(jsonString.toString(), Bookshelf[].class);

        return bookshelves[index];
        // todo farla funzionale
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

    public static int getItemDimension() throws IOException {
        /* Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(Utils.getConfigurationPath() + "TUIConfig.json")));
        return gson.fromJson(json, Configuration.class).getItemDimension(); */

        return 5;
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
        return switch (random) {
            case 0 -> new Item(TypeEnum.CATS);
            case 1 -> new Item(TypeEnum.BOOKS);
            case 2 -> new Item(TypeEnum.TROPHIES);
            case 3 -> new Item(TypeEnum.PLANTS);
            case 4 -> new Item(TypeEnum.GAMES);
            case 5 -> new Item(TypeEnum.FRAMES);
            default -> null;
        };
    }

    /* ************************************************************************************************************
     *                          END OF CUSTOM FUNCTIONS
     *                         START OF COLOR FUNCTIONS
     ************************************************************************************************************ */

    /**
     * This function returns a string with the specified color
     * @param string the string to color
     * @param color the color
     * @return the colored string
     */
    public static String setColor(String string, String color) {
        return switch (color) {
            case "PLANTS" -> ("\u001B[31m" + string + "\u001B[0m");
            case "CATS" -> ("\u001B[32m" + string + "\u001B[0m");
            case "GAMES" -> ("\u001B[33m" + string + "\u001B[0m");
            case "FRAMES" -> ("\u001B[34m" + string + "\u001B[0m");
            case "TROPHIES" -> ("\u001B[36m" + string + "\u001B[0m");
            case "BOOKS" -> ("\u001B[37m" + string + "\u001B[0m");
            default -> (string);
        };

    }

    /**
     * This function is used to print the board
     * @param board the board to print
     * @throws IOException if the file is not found
     */
    public static void printBoard(Board board) throws IOException {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("  |  0  ||  1  ||  2  ||  3  ||  4  ||  5  ||  6  ||  7  ||  8  |");
        for(int i = 0; i < board.getGameBoard().getColumnDimension(); i++) {
            System.out.println("-----------------------------------------------------------------");
            System.out.print(i + " ");
            for (int j = 0; j < board.getGameBoard().getColumnDimension(); j++) {
                if (board.getGameBoard().get(i, j).getPlacedItem() != null) {
                    System.out.print("|" + board.getGameBoard().get(i, j).getPlacedItem().toString() + "|");
                } else {
                    System.out.print("|" + Item.voidToString() + "|");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-----------------------------------------------------------------");
    }

    /**
     * This function is used to print the bookshelf
     * @param bookshelf the bookshelf to print
     * @throws IOException if the file is not found
     */
    public static void printBookshelf(Bookshelf bookshelf) {

        System.out.println("-------------------------------------");
        System.out.println("  |  0  ||  1  ||  2  ||  3  ||  4  |");

        for(int i = 0; i < bookshelf.getColumnDimension(); i++) {
            System.out.println("-------------------------------------");
            System.out.print(i + " ");
            for (int j = 0; j < bookshelf.getRowDimension(); j++) {
                if (bookshelf.get(i, j) != null) {
                    System.out.print("|" + bookshelf.get(i, j).toString() + "|");
                } else {
                    System.out.print("|" + Item.voidToString() + "|");
                }
            }
            System.out.print("\n");
        }
        System.out.println("-------------------------------------");

    }

    /**
     * This function is used to print the game's logo
     */
    public static void printLogo() {
        try {
            File file = new File(Utils.getConfigurationPath() + "logo.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
