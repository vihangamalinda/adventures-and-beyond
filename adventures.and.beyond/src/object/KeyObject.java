package object;

import java.awt.image.BufferedImage;

import static object.ObjectResourcePath.KEY_IMG_PATH;

public class KeyObject extends InteractableObject {
    private String keyCode;

    public KeyObject(boolean onCollision, int worldPositionX, int worldPositionY, String keyCode, boolean isActive) {
        super(KEY_IMG_PATH, "key", onCollision, worldPositionX, worldPositionY, isActive);
        this.keyCode = keyCode;
    }
}
