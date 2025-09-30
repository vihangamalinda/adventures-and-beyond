package object.interactable.objects;

import entity.Player;
import sound.SoundKey;
import sound.SoundManager;
import ui.UserInterfaceManager;

import static object.ObjectResourcePath.TREASURE_IMG_PATH;

public class TreasureObject extends InteractableObject {
    private final String treasureNumber;
    private final String openCode;

    public TreasureObject(boolean onCollision, int worldPositionX, int worldPositionY, String treasureNumber, String openCode, boolean isActive) {
        super(TREASURE_IMG_PATH, "Treasure", onCollision, worldPositionX, worldPositionY, isActive);
        this.treasureNumber = treasureNumber;
        this.openCode = openCode;
    }

    @Override
    public void performAction(Player player) {
        String openCode = this.openCode;
        if (player.hasKeyCode(openCode)) {
            SoundManager.getInstance().performSoundEffects(SoundKey.TREASURE_BOX_OPENING, 2);
            player.removeKey(openCode);
            this.setActive(false);
        } else {
            String message = "Player does not have correct key: "+this.openCode;
            UserInterfaceManager.getInstance().notifyGameDetails(message);
            player.setOnCollision(true);
        }
    }
}
