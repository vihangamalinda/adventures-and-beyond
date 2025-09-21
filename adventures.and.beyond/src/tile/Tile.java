package tile;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage bufferedImage;
    boolean isCollided;

    public Tile(BufferedImage bufferedImage, boolean isCollided) {
        this.bufferedImage = bufferedImage;
        this.isCollided = isCollided;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public boolean isCollided() {
        return isCollided;
    }

    public void setCollided(boolean collided) {
        isCollided = collided;
    }
}
