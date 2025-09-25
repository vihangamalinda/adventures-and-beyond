package object;

import static object.ObjectResourcePath.TREASURE_IMG_PATH;

public class TreasureObject extends InteractableObject {
    private final String treasureNumber;
    private final String openCode;

    public TreasureObject(boolean onCollision, int worldPositionX, int worldPositionY, String treasureNumber, String openCode, boolean isActive) {
        super(TREASURE_IMG_PATH, "Treasure", onCollision, worldPositionX, worldPositionY, isActive);
        this.treasureNumber = treasureNumber;
        this.openCode = openCode;
    }
}
