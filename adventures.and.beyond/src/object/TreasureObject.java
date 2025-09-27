package object;

import entity.Player;
import sound.SoundKey;
import sound.SoundManager;

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
        if (player.hasKeyCode(this.openCode)) {
            SoundManager soundManager =SoundManager.getInstance();
            soundManager.setSoundEffectMusicClip(SoundKey.TREASURE_BOX_OPENING);
            soundManager.playSoundEffectForPeriod(2);
            this.setActive(false);
        } else {
            player.setOnCollision(true);
        }
    }
}
