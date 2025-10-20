package object;

import entity.manager.EntityManagerFactory;
import entity.player.Player;
import object.interactable.objects.DoorObject;
import object.interactable.objects.InteractableObject;
import object.interactable.objects.KeyObject;
import object.interactable.objects.TreasureObject;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class InteractableObjectManager {
    private final InteractableObject[] interactableObjects;

    private static class Holder {
        private static final InteractableObjectManager INSTANCE = new InteractableObjectManager();
    }

    private InteractableObjectManager() {
        this.interactableObjects = initializeInteractableObjects();
    }

    public static InteractableObjectManager getInstance() {
        return Holder.INSTANCE;
    }

    private InteractableObject[] initializeInteractableObjects() {

        String keyCode01 = "KEY_07";
        InteractableObject key = KeyObject.getBuilder().onCollision(false).worldPositionX(28 * TILE_SIZE).worldPositionY(17 * TILE_SIZE).keyCode(keyCode01).isActive(true).build();
        InteractableObject door = DoorObject.getBuilder().onCollision(false).worldPositionX(22 * TILE_SIZE).worldPositionY(25 * TILE_SIZE).doorNumber("DOOR_5").openCode(keyCode01).isActive(true).build();

        String keyCode02 = "KEY_08";
        InteractableObject treasure =TreasureObject.getBuilder().onCollision(false).worldPositionX(22 * TILE_SIZE).worldPositionY(27 * TILE_SIZE).treasureNumber("TREASURE_05").openCode(keyCode02).isActive( true).build();
        InteractableObject key2 =KeyObject.getBuilder().onCollision(false).worldPositionX(16 * TILE_SIZE).worldPositionY(11 * TILE_SIZE).keyCode(keyCode02).isActive(true).build();

        return new InteractableObject[]{key, door, treasure, key2};
    }

    public void drawInteractiveObjects(Graphics2D graphics2D) {
        Player player = EntityManagerFactory.getInstance().getPlayer();

        for (int currentIndex = 0; currentIndex <= this.interactableObjects.length - 1; currentIndex++) {
            InteractableObject obj = this.interactableObjects[currentIndex];
            obj.draw(graphics2D, player);
        }
    }

    public InteractableObject[] getInteractableObjects() {
        return interactableObjects;
    }
}
