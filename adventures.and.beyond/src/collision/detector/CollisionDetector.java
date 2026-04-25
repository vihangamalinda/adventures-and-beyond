package collision.detector;

import entity.manager.EntityManager;
import entity.manager.EntityManagerFactory;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

public class CollisionDetector {


//    private static class Holder {
//        private static final CollisionDetector INSTANCE = new CollisionDetector();
//    }

    private final WorldMapManager worldMapManager;
    private final EntityManager entityManager;

    public CollisionDetector() {
        this.worldMapManager = WorldMapManagerFactory.getInstance();
        this.entityManager = EntityManagerFactory.getInstance();

    }

//    public static CollisionDetector getInstance() {
//        return Holder.INSTANCE;
//    }

//    public void checkTileCollision(Entity entity) {
//        if(entity.isIdle())return;
//
//        Rectangle solidArea = entity.getSolidArea();
//        int entityLeftWorldX = entity.getWorldPositionX() + solidArea.x;
//        int entityRightWorldX = entity.getWorldPositionX() + solidArea.x + solidArea.width;
//        int entityTopWorldY = entity.getWorldPositionY() + solidArea.y;
//        int entityBottomWorldY = entity.getWorldPositionY() + solidArea.y + solidArea.height;
//
//        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
//        int entityRightCol = entityRightWorldX / TILE_SIZE;
//        int entityTopRow = entityTopWorldY / TILE_SIZE;
//        int entityBottomRow = entityBottomWorldY / TILE_SIZE;
//
//
//        int speed = entity.getSpeed();
////        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
//        boolean collideOne =false;
//        boolean collideTwo =false;
//
//        switch (entity.getDirection()) {
//            case FACING_BACKWARD -> {
//                int nextRow = (entityTopWorldY - speed) / TILE_SIZE;
//                collideOne = worldMapManager.canTileBeCollided(nextRow,entityLeftCol);
//                collideTwo = worldMapManager.canTileBeCollided(nextRow,entityRightCol);
//            }
//            case FACING_FORWARD -> {
//                int nextRow = (entityBottomWorldY + speed) / TILE_SIZE;
//                collideOne = worldMapManager.canTileBeCollided(nextRow,entityLeftCol);
//                collideTwo = worldMapManager.canTileBeCollided(nextRow,entityRightCol);
//            }
//            case FACING_LEFTWARD -> {
//                int nextCol = (entityLeftWorldX - speed) / TILE_SIZE;
//                collideOne = worldMapManager.canTileBeCollided(entityTopRow,nextCol);
//                collideTwo = worldMapManager.canTileBeCollided(entityBottomRow,nextCol);
//            }
//            case FACING_RIGHTWARD -> {
//                int nextCol = (entityRightWorldX + speed) / TILE_SIZE;
//                collideOne = worldMapManager.canTileBeCollided(entityTopRow,nextCol);
//                collideTwo = worldMapManager.canTileBeCollided(entityBottomRow,nextCol);
//            }
//        }
//
//            boolean canCollied = collideOne && collideTwo;
//            entity.setOnCollision(!canCollied);
//    }
//
//    public void checkObjectCollision(Player player) {
////        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
//        InteractableObject[] interactableObjects = worldMapManager.getInteractableObject();;
////        InteractableObject[] interactableObjects = InteractableObjectManager.getInstance().getInteractableObjects();
//
//        for (int i = 0; i < interactableObjects.length; i++) {
//            InteractableObject obj = interactableObjects[i];
//
//            boolean isColliding = obj.doCollideWithPlayer(player);
//            if (isColliding && obj.isActive()) {
//                obj.performAction(player);
//            }
//
//        }
//    }

//    public void checkCharacterPlayerCollision(NonPlayerCharacter character) {
////        EntityManager entityManager = EntityManagerFactory.getInstance();
//        Player player = entityManager.getPlayer();
//        boolean isColliding = character.doCollideWithPlayer(player);
//
//
//        if (isColliding) {
//            player.setOnCollision(true);
//            character.associateWithPlayer();
//        } else {
//            character.disassociateWithPlayer();
//        }
//
//    }
}
