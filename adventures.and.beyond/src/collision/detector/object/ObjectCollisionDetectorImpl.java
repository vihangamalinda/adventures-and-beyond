package collision.detector.object;

import entity.player.Player;
import object.interactable.objects.InteractableObject;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

public class ObjectCollisionDetectorImpl implements ObjectCollisionDetector {

    private final WorldMapManager worldMapManager;

    public ObjectCollisionDetectorImpl(WorldMapManager worldMapManager) {
        this.worldMapManager = worldMapManager;
    }

    @Override
    public void checkObjectCollision(Player player) {
        //        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
        InteractableObject[] interactableObjects = this.worldMapManager.getInteractableObject();
//        InteractableObject[] interactableObjects = InteractableObjectManager.getInstance().getInteractableObjects();

        for (int i = 0; i < interactableObjects.length; i++) {
            InteractableObject obj = interactableObjects[i];

            boolean isColliding = obj.doCollideWithPlayer(player);
            if (isColliding && obj.isActive()) {
                obj.performAction(player);
            }

        }
    }
}
