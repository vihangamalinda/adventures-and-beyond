package object.interactable.objects;

import entity.Player;
import sound.SoundKey;
import sound.SoundManager;
import ui.UserInterfaceManager;

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
        String openCode = this.openCode;
        if (player.hasKeyCode(openCode)) {
            SoundManager.getInstance().performSoundEffects(SoundKey.DOOR_OPENING, 1);
//            soundManager.setSoundEffectMusicClip(SoundKey.DOOR_OPENING);
//            soundManager.playSoundEffectForPeriod(1);
            player.removeKey(openCode);
            this.setActive(false);
        } else {
            String message ="Player does not have key";
            UserInterfaceManager.getInstance().notifyGameDetails(message);
            System.out.println("Player does not have key");
            player.setOnCollision(true);
        }
    }
}
