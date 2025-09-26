package helper;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
            String message = getErrorMessage("Error occurred when reading the map line by line.FilePath: " + filePath);
            System.out.println(message);
            exception.fillInStackTrace();
        }
        return matrix;
    }

    private static BufferedReader readTextFile(String filePath) {
        try {
            InputStream resource = Constant.class.getResourceAsStream(filePath);

            if (resource == null) {
                String message = String.format("Image resource is null.Given path: %s",filePath);
                throw new IOException(getErrorMessage(message));
            }

            return new BufferedReader(new InputStreamReader(resource));
        } catch (Exception exception) {
            String message = String.format("Error occurred when loading the map. Given File path: %s",filePath);
            System.out.println(getErrorMessage(message));
            exception.fillInStackTrace();
        }
        return null;
    }

    public static BufferedImage getImage(String imgPath) {
        try {
            InputStream resource = Constant.class.getResourceAsStream(imgPath);
            if (resource == null) {
                String message = String.format("Image resource is null.Given path: %s",imgPath);
                throw new IOException(getErrorMessage(message));
            }

            return ImageIO.read(resource);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private static String getErrorMessage(String message){

        return String.format("Custom Error: %s",message);
    }

    public static Clip loadSoundFile(URL urlPath) {
        try {
            AudioInputStream inputStream =AudioSystem.getAudioInputStream(urlPath);
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            return clip;
        }catch (Exception exception){
            String message = String.format("Error occurred when loading & opening sound files. url path: %s ", urlPath);
            System.out.println(getErrorMessage(message));
        }
        return null;
    }
}
