package object;

import entity.Player;

import static object.ObjectResourcePath.DOOR_IMG_PATH;

public class DoorObject extends InteractableObject {
    private final String doorNumber;
    private final String openCode;

    public DoorObject(boolean onCollision, int worldPositionX, int worldPositionY, String doorNumber, String openCode, boolean isActive) {
        super(DOOR_IMG_PATH, "door", onCollision, worldPositionX, worldPositionY, isActive);
        this.doorNumber = doorNumber;
        this.openCode = openCode;
    }

    @Override
    public void performAction(Player player) {
        if (player.hasKeyCode(this.openCode)) {
            this.setActive(false);
        } else {
            System.out.println("Player does not have key");
            player.setOnCollision(true);
        }
    }
}
