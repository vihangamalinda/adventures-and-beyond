package collision.detector.tile;

import entity.Entity;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

import java.awt.*;

import static helper.Constant.TILE_SIZE;

public class TileCollisionDetectorImpl implements TileCollisionDetector {

    private final WorldMapManager worldMapManager;


    public TileCollisionDetectorImpl(WorldMapManager worldMapManager) {
        this.worldMapManager = worldMapManager;
    }

    @Override
    public void checkTileCollision(Entity entity) {
        if (entity.isIdle()) return;

        Rectangle solidArea = entity.getSolidArea();
        int entityLeftWorldX = entity.getWorldPositionX() + solidArea.x;
        int entityRightWorldX = entity.getWorldPositionX() + solidArea.x + solidArea.width;
        int entityTopWorldY = entity.getWorldPositionY() + solidArea.y;
        int entityBottomWorldY = entity.getWorldPositionY() + solidArea.y + solidArea.height;

        int entityLeftCol = entityLeftWorldX / TILE_SIZE;
        int entityRightCol = entityRightWorldX / TILE_SIZE;
        int entityTopRow = entityTopWorldY / TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / TILE_SIZE;


        int speed = entity.getSpeed();
//        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
        boolean collideOne = false;
        boolean collideTwo = false;

        switch (entity.getDirection()) {
            case FACING_BACKWARD -> {
                int nextRow = (entityTopWorldY - speed) / TILE_SIZE;
                collideOne = this.worldMapManager.canTileBeCollided(nextRow,
                                                                    entityLeftCol);
                collideTwo = this.worldMapManager.canTileBeCollided(nextRow,
                                                                    entityRightCol);
            }
            case FACING_FORWARD -> {
                int nextRow = (entityBottomWorldY + speed) / TILE_SIZE;
                collideOne = this.worldMapManager.canTileBeCollided(nextRow,
                                                                    entityLeftCol);
                collideTwo = this.worldMapManager.canTileBeCollided(nextRow,
                                                                    entityRightCol);
            }
            case FACING_LEFTWARD -> {
                int nextCol = (entityLeftWorldX - speed) / TILE_SIZE;
                collideOne = this.worldMapManager.canTileBeCollided(entityTopRow,
                                                                    nextCol);
                collideTwo = this.worldMapManager.canTileBeCollided(entityBottomRow,
                                                                    nextCol);
            }
            case FACING_RIGHTWARD -> {
                int nextCol = (entityRightWorldX + speed) / TILE_SIZE;
                collideOne = this.worldMapManager.canTileBeCollided(entityTopRow,
                                                                    nextCol);
                collideTwo = this.worldMapManager.canTileBeCollided(entityBottomRow,
                                                                    nextCol);
            }
        }

        boolean canCollied = collideOne && collideTwo;
        entity.setOnCollision(!canCollied);
    }
}
