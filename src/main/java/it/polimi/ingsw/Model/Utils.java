package it.polimi.ingsw.Model;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {

    /* ************************************************************************************************************
     *                          START OF JSON FUNCTIONS
     ************************************************************************************************************ */

    public static Board loadBoardFromFile(String fileName) throws IOException {

        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, Board.class);
    }
    public static void saveToFile(String fileName, Object object) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        FileWriter writer = new FileWriter(fileName);
        writer.write(json);
        writer.close();
    }
    public static Bookshelf loadBookshelfFromFile(String fileName) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, Bookshelf.class);
    }

    /* ************************************************************************************************************
     *                          END OF JSON FUNCTIONS
     ************************************************************************************************************ */
}
