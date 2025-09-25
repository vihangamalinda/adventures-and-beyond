package object;

import java.awt.image.BufferedImage;

import static object.ObjectResourcePath.KEY_IMG_PATH;

public class KeyObject extends InteractableObject{
    private String keyNumber;

    public KeyObject(boolean onCollision, int worldPositionX, int worldPositionY,String keyNumber) {
        super(KEY_IMG_PATH, "key", onCollision, worldPositionX, worldPositionY);
        this.keyNumber= keyNumber;
    }
}
