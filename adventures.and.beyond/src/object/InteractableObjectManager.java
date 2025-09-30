package object;

import entity.Player;
import main.GamePanel;
import object.interactable.objects.DoorObject;
import object.interactable.objects.InteractableObject;
import object.interactable.objects.KeyObject;
import object.interactable.objects.TreasureObject;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class InteractableObjectManager {
    private final InteractableObject[] interactableObjects;

    private static class Holder{
        private static final InteractableObjectManager INSTANCE = new InteractableObjectManager();
    }

    private InteractableObjectManager() {
        this.interactableObjects = initializeInteractableObjects();
    }

    public static InteractableObjectManager getInstance(){
        return Holder.INSTANCE;
    }

    private InteractableObject[] initializeInteractableObjects() {
        InteractableObject key = new KeyObject(false, 28 * TILE_SIZE, 10 * TILE_SIZE, "KEY_07", true);
        InteractableObject door = new DoorObject(false, 25 * TILE_SIZE, 9 * TILE_SIZE, "DOOR_5", "KEY_07", true);

        InteractableObject treasure = new TreasureObject(false, 21 * TILE_SIZE, 8 * TILE_SIZE, "TREASURE_05", "KEY_08", true);
        InteractableObject key2 = new KeyObject(false, 16 * TILE_SIZE, 11 * TILE_SIZE, "KEY_08", true);

        return new InteractableObject[]{key, door, treasure, key2};
    }

    public void drawInteractiveObjects(Graphics2D graphics2D) {
        Player player = GamePanel.getInstance().getPlayer();

        for (int currentIndex = 0; currentIndex <= this.interactableObjects.length - 1; currentIndex++) {
            InteractableObject obj = this.interactableObjects[currentIndex];
            obj.draw(graphics2D, player);
        }
    }

    public InteractableObject[] getInteractableObjects() {
        return interactableObjects;
    }
}
