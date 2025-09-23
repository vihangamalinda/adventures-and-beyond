package tile;

import java.awt.image.BufferedImage;

public class Tile {
    BufferedImage bufferedImage;
    boolean canCollide;

    public Tile(BufferedImage bufferedImage, boolean canCollide) {
        this.bufferedImage = bufferedImage;
        this.canCollide = canCollide;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }

    public boolean isCanCollide() {
        return canCollide;
    }

    public void setCanCollide(boolean canCollide) {
        this.canCollide = canCollide;
    }
}
