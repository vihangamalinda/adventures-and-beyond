package helper;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.PlayerConstant.PLAYER_UP_SCALE;
import static helper.Constant.TILE_SIZE;

public class ImageScaler {
    private static BufferedImage getScaledImage(int upScale, BufferedImage original) {
        BufferedImage scaledImage = new BufferedImage(upScale, upScale, original.getType());
        // need to re-draw the image
        Graphics2D graphics2D = scaledImage.createGraphics();
        graphics2D.drawImage(original, 0, 0, upScale, upScale, null);
        graphics2D.dispose();

        return scaledImage;
    }

    public static BufferedImage getStandardScaledImage(BufferedImage image) {
        return getScaledImage(TILE_SIZE, image);
    }

    public static BufferedImage getPlayerScaledImage(BufferedImage image) {
        int upScale = TILE_SIZE * PLAYER_UP_SCALE;
        return getScaledImage(upScale, image);
    }
}
