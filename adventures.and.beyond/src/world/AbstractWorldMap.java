package world;

import entity.manager.EntityManager;
import entity.manager.EntityManagerFactory;
import entity.player.Player;
import tile.registry.TileRegistry;
import tile.registry.TileRegistryFactory;

import java.awt.*;

import static helper.Constant.*;

public abstract class AbstractWorldMap implements DrawableWorldMap,WorldMapInformation{

    private final int[][] mapTileMatrix;
    private final EntityManager entityManager;
    private final TileRegistry tileRegistry;
    private final InteractableObject[] interactableObjects;
    protected AbstractWorldMap(int[][] mapTileMatrix,String worldMapKey) {
        this.mapTileMatrix = mapTileMatrix;
        this.entityManager = EntityManagerFactory.getInstance();
        this.tileRegistry = TileRegistryFactory.getInstance();
        this.interactableObjects = InteractableObjectManagerFactory.getInstance().getInteratableObjectsByWorldMapKey(worldMapKey);
    }

    @Override
    public InteractableObject[] getInteractableObjects() {
        return interactableObjects;
    }

    protected int getDrawMapStarterRow(Player player) {
        int drawMapRow = (player.getCurrentRowOnWorldMap() - (MAX_SCREEN_ROW / 2));

        if (drawMapRow < 0) {
            drawMapRow = 0;
        } else if ((drawMapRow + MAX_SCREEN_ROW) > MAX_WORLD_ROWS) {
            drawMapRow = MAX_WORLD_ROWS - MAX_SCREEN_ROW;
        }
//        System.out.println("Currently drawing starting from row:"+drawMapRow);
        return drawMapRow;
    }

    protected int getDrawMapStarterCol(Player player) {
        int drawMapCol = (player.getCurrentColOnWorldMap()) - (MAX_SCREEN_COLUMN / 2);

        if (drawMapCol < 0) {
            drawMapCol = 0;
        } else if (drawMapCol + MAX_SCREEN_COLUMN > MAX_WORLD_COLUMNS) {
            drawMapCol = MAX_WORLD_COLUMNS - MAX_SCREEN_COLUMN;
        }
        return drawMapCol;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }

    protected int[][] getMapTileMatrix() {
        return mapTileMatrix;
    }

    protected TileRegistry getTileRegistry() {
        return tileRegistry;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Player player =this.getEntityManager().getPlayer();;

        int drawMapCol = getDrawMapStarterCol(player);


        int drawMapRow = getDrawMapStarterRow(player);

        drawWorldMap(graphics2D, drawMapRow, drawMapCol);

    }

    protected abstract void drawWorldMap(Graphics2D graphics2D,int drawMapRow,int drawMapCol);


    protected int getTileKeyByRowAndCol(int row, int col) {
        boolean isInvalid = row < 0 || col < 0 || row > MAX_WORLD_ROWS - 1 || col > MAX_WORLD_COLUMNS - 1;
        if (isInvalid) {
            String message = String.format("Custom Error Given row and col are not within range. Row: %d, Col: %d", row, col);
            throw new RuntimeException(message);
        }
        return this.mapTileMatrix[row][col];
    }
}
