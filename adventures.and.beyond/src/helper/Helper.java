package helper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helper {
    //Screen settings
    public static final int ORIGINAL_TILE_SIZE = 48;// 16*16 tile
    static final int UP_SCALE = 1;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * UP_SCALE;// 48*48 tile

    // maintaining 4:3 size ratio on width to height ratio
    public final static int MAX_SCREEN_COLUMN = 16;
    public final static int MAX_SCREEN_ROW = 12;

    public static final int WINDOW_MAX_SCREEN_WIDTH = MAX_SCREEN_COLUMN * TILE_SIZE;//768 pixels
    public static final int WINDOW_MAX_SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;//576 pixels

    public static final int FRAME_RATE_PER_SECOND = 60;//fps=60

    public static BufferedImage getImage(String imgPath) {
        try {
            InputStream resource = Helper.class.getResourceAsStream(imgPath);
            if (resource == null) {
                throw new IOException("Custom error:Image resource is null.Given path: " + imgPath);
            }

            return ImageIO.read(resource);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static BufferedReader readTextFile(String filePath){
        try{
            InputStream resource =Helper.class.getResourceAsStream(filePath);

            if (resource == null) {
                throw new IOException("Custom error:Image resource is null.Given path: " + filePath);
            }

            return new BufferedReader(new InputStreamReader(resource));
        }catch (Exception exception){
            System.out.println("Custom Error: Error occurred when loading the map. Given File path: "+filePath);
            exception.fillInStackTrace();
        }
        return null;
    }

}
