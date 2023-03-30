package it.polimi.ingsw.Model.Parser;

import it.polimi.ingsw.Model.BoardTile;
import it.polimi.ingsw.Model.Matrix;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    public static Matrix<BoardTile> parseIntMatrixFromJsonFile(String filePath) throws IOException {
        // Read the contents of the JSON file into a string
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));

        // Parse the JSON string into a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Get the JSONArray containing the matrix
        JSONArray jsonArray = jsonObject.getJSONArray("matrix");

        // Create a new int matrix to hold the values
        int numRows = jsonArray.length();
        int numCols = jsonArray.getJSONArray(0).length();
        Matrix<BoardTile> matrix = new Matrix<BoardTile>(numRows, numCols);

        // Loop through the rows and columns of the matrix and add the values
        for (int i = 0; i < numRows; i++) {
            JSONArray rowArray = jsonArray.getJSONArray(i);
            for (int j = 0; j < numCols; j++) {
                matrix.set(i, j, new BoardTile(rowArray.getInt(j)));
            }
        }

        return matrix;
    }

}
