package helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Loader {
    public static int[][] getMapMatrix(String filePath) {
        BufferedReader reader = readTextFile(filePath);
        assert reader != null;
        int totalRows = Constant.MAX_WORLD_ROWS;
        int totalColumns = Constant.MAX_WORLD_COLUMNS;

        int[][] matrix = new int[totalRows][totalColumns];
        try {

            for (int row = 0; row < totalRows; row++) {

                String line = reader.readLine();
                String[] numericStr = line.split("");
                System.out.println("");
                for (int col = 0; col < totalColumns; col++) {
                    int value = Integer.parseInt(numericStr[col]);
                    System.out.print(value);
                    matrix[row][col] = value;
                }

            }
        } catch (IOException exception) {
            System.out.println("Custom Error:Error occurred when reading the map line by line.FilePath: " + filePath);
            exception.fillInStackTrace();
        }
        return matrix;
    }

    private static BufferedReader readTextFile(String filePath) {
        try {
            InputStream resource = Constant.class.getResourceAsStream(filePath);

            if (resource == null) {
                throw new IOException("Custom error:Image resource is null.Given path: " + filePath);
            }

            return new BufferedReader(new InputStreamReader(resource));
        } catch (Exception exception) {
            System.out.println("Custom Error: Error occurred when loading the map. Given File path: " + filePath);
            exception.fillInStackTrace();
        }
        return null;
    }

    public static BufferedImage getImage(String imgPath) {
        try {
            InputStream resource = Constant.class.getResourceAsStream(imgPath);
            if (resource == null) {
                throw new IOException("Custom error:Image resource is null.Given path: " + imgPath);
            }

            return ImageIO.read(resource);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

}
