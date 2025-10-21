package main;

import entity.Entity;
import entity.manager.EntityManagerFactory;
import entity.npc.NonPlayerCharacter;
import entity.player.Player;
import object.interactable.objects.InteractableObject;
import tile.registry.TileRegistry;
import tile.registry.TileRegistryFactory;
import world.manager.WorldMapManagerImpl;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class CollisionDetector {

    private static class Holder {
        private static final CollisionDetector INSTANCE = new CollisionDetector();
    }

    public CollisionDetector() {
    }

    public static CollisionDetector getInstance() {
        return Holder.INSTANCE;
    }

    public void checkTileCollision(Entity entity) {
        Rectangle solidArea = entity.getSolidArea();
        int entityLeftWorldX = entity.getWorldPositionX() + solidArea.x;
        int entityRightWorldX = entity.getWorldPositionX() + solidArea.x + solidArea.width;
        int entityTopWorldY = entity.getWorldPositionY() + solidArea.y;
        int entityBottomWorldY = entity.getWorldPositionY() + solidArea.y + solidArea.height;

        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
        int entityRightCol = entityRightWorldX / TILE_SIZE;
        int entityTopRow = entityTopWorldY / TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / TILE_SIZE;

        int tileTypeNum1 = -1;
        int tileTypeNum2 = -1;

        int speed = entity.getSpeed();
        WorldMapManagerImpl worldMapManagerImpl = WorldMapManagerImpl.getInstance();

        switch (entity.getDirection()) {
            case FACING_BACKWARD -> {
                int nextRow = (entityTopWorldY - speed) / TILE_SIZE;
                tileTypeNum1 = worldMapManagerImpl.getTileKeyByRowAndCol(nextRow, entityLeftCol);
                tileTypeNum2 = worldMapManagerImpl.getTileKeyByRowAndCol(nextRow, entityRightCol);
            }
            case FACING_FORWARD -> {
                int nextRow = (entityBottomWorldY + speed) / TILE_SIZE;
                tileTypeNum1 = worldMapManagerImpl.getTileKeyByRowAndCol(nextRow, entityLeftCol);
                tileTypeNum2 = worldMapManagerImpl.getTileKeyByRowAndCol(nextRow, entityRightCol);
            }
            case FACING_LEFTWARD -> {
                int nextCol = (entityLeftWorldX - speed) / TILE_SIZE;
                tileTypeNum1 = worldMapManagerImpl.getTileKeyByRowAndCol(entityTopRow, nextCol);
                tileTypeNum2 = worldMapManagerImpl.getTileKeyByRowAndCol(entityBottomRow, nextCol);
            }
            case FACING_RIGHTWARD -> {
                int nextCol = (entityRightWorldX + speed) / TILE_SIZE;
                tileTypeNum1 = worldMapManagerImpl.getTileKeyByRowAndCol(entityTopRow, nextCol);
                tileTypeNum2 = worldMapManagerImpl.getTileKeyByRowAndCol(entityBottomRow, nextCol);
            }
        }

        boolean isAtIdle = tileTypeNum1 == -1 && tileTypeNum2 == -1;
        if (isAtIdle) {
            entity.setOnCollision(false);
        } else {
            TileRegistry tileRegistry = TileRegistryFactory.getInstance();
            boolean collideOne = tileRegistry.couldTileTypeBeCollided(tileTypeNum1);
            boolean collideTwo =tileRegistry.couldTileTypeBeCollided(tileTypeNum2);

            boolean canCollied = collideOne && collideTwo;
            entity.setOnCollision(!canCollied);
        }
    }

    public void checkObjectCollision(Player player) {

        InteractableObject[] interactableObjects =  WorldMapManagerImpl.getInstance().getInteractableObject();;
//        InteractableObject[] interactableObjects = InteractableObjectManager.getInstance().getInteractableObjects();

        for (int i = 0; i < interactableObjects.length; i++) {
            InteractableObject obj = interactableObjects[i];

            boolean isColliding = obj.doCollideWithPlayer(player);
            if (isColliding && obj.isActive()) {
                obj.performAction(player);
            }

        }
    }

    public void checkCharacterPlayerCollision(NonPlayerCharacter character) {


        Player player = EntityManagerFactory.getInstance().getPlayer();
        boolean isColliding = character.doCollideWithPlayer(player);


        if (isColliding) {
            player.setOnCollision(true);
            character.associateWithPlayer();
        } else {
            character.disassociateWithPlayer();
        }

    }
}
