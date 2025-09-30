package main;

import entity.Entity;
import entity.Player;
import object.interactable.objects.InteractableObject;
import tile.Tile;
import tile.TileManager;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class CollisionDetector {
    public CollisionDetector() {
    }

    public void checkCollision(Entity entity) {
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
        TileManager tileManager = TileManager.getInstance();

        switch (entity.getDirection()) {
            case FACING_BACKWARD -> {
                int nextRow = (entityTopWorldY - speed) / TILE_SIZE;
                tileTypeNum1 = tileManager.getTileTypeIndexByRowAndCol(nextRow, entityLeftCol);
                tileTypeNum2 = tileManager.getTileTypeIndexByRowAndCol(nextRow, entityRightCol);
            }
            case FACING_FORWARD -> {
                int nextRow = (entityBottomWorldY + speed) / TILE_SIZE;
                tileTypeNum1 = tileManager.getTileTypeIndexByRowAndCol(nextRow, entityLeftCol);
                tileTypeNum2 = tileManager.getTileTypeIndexByRowAndCol(nextRow, entityRightCol);
            }
            case FACING_LEFTWARD -> {
                int nextCol = (entityLeftWorldX - speed) / TILE_SIZE;
                tileTypeNum1 = tileManager.getTileTypeIndexByRowAndCol(entityTopRow, nextCol);
                tileTypeNum2 = tileManager.getTileTypeIndexByRowAndCol(entityBottomRow, nextCol);
            }
            case FACING_RIGHTWARD -> {
                int nextCol = (entityRightWorldX + speed) / TILE_SIZE;
                tileTypeNum1 = tileManager.getTileTypeIndexByRowAndCol(entityTopRow, nextCol);
                tileTypeNum2 = tileManager.getTileTypeIndexByRowAndCol(entityBottomRow, nextCol);
            }
        }

        boolean isAtIdle = tileTypeNum1 == -1 && tileTypeNum2 == -1;
        if (isAtIdle) {
            entity.setOnCollision(false);
        } else {
            Tile tileType1 = tileManager.getTileByIndex(tileTypeNum1);
            Tile tileType2 = tileManager.getTileByIndex(tileTypeNum2);
            boolean collideOne = tileType1.isCanCollide();
            boolean collideTwo = tileType2.isCanCollide();

            boolean canCollied = collideOne && collideTwo;
            entity.setOnCollision(!canCollied);
        }
    }

    public void checkObjectCollision(Player player) {

        InteractableObject[] interactableObjects = GamePanel.getInstance().interactableObjectManager.getInteractableObjects();
        for (int i = 0; i < interactableObjects.length; i++) {
            InteractableObject obj = interactableObjects[i];

            boolean isColliding = obj.doCollideWithPlayer(player);
            if (isColliding && obj.isActive()) {
                obj.performAction(player);
            }

        }

    }
}
