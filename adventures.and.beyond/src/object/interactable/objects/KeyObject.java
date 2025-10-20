package object.interactable.objects;

import entity.player.Player;
import object.interactable.objects.builder.KeyObjectBuilder;
import sound.SoundKey;
import sound.SoundManager;
import ui.UserInterfaceManager;

import static object.ObjectResourcePath.KEY_IMG_PATH;

public class KeyObject extends InteractableObject {
    private String keyCode;

    public KeyObject(boolean onCollision, int worldPositionX, int worldPositionY, String keyCode, boolean isActive) {
        super(KEY_IMG_PATH, "key", onCollision, worldPositionX, worldPositionY, isActive);
        this.keyCode = keyCode;
    }

    public static KeyObjectBuilder getBuilder(){
        return  new KeyObjectBuilder();
    }

    @Override
    public void performAction(Player player) {
        player.collectKeyCode(this.keyCode);

        SoundManager.getInstance().performSoundEffects(SoundKey.KEY_COLLECTED, 2);
        UserInterfaceManager.getInstance().notifyGameDetails("Collected key: " + this.keyCode);
        this.setActive(false);
    }
}
