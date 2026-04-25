package world.manager;

import helper.Loader;
import object.interactable.objects.InteractableObject;
import world.worldmap.AbstractWorldMap;
import world.worldmap.DrawableWorldMap;
import world.worldmap.WorldMap;
import world.worldmap.WorldMapInformation;

import java.awt.*;

public class WorldMapManagerImpl implements WorldMapManager {
    private final DrawableWorldMap drawableWorldMap;
    private final WorldMapInformation worldMapInformation;

    WorldMapManagerImpl() {
        AbstractWorldMap worldMap = new WorldMap(loadMapMatrix(), "worldMap_01");
        this.drawableWorldMap = worldMap;
        this.worldMapInformation = worldMap;

    }

    @Override
    public InteractableObject[] getInteractableObject() {
        return this.worldMapInformation.getInteractableObjects();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        drawableWorldMap.draw(graphics2D);
    }

    @Override
    public int getTileKeyByRowAndCol(int rowIndex, int colIndex) {
        return this.worldMapInformation.getTileKey(rowIndex, colIndex);
    }

    @Override
    public boolean canTileBeCollided(int rowIndex, int colIndex) {
        return this.worldMapInformation.canTileBeCollided(rowIndex, colIndex);
    }

    private int[][] loadMapMatrix() {
        return Loader.getMapMatrix("/maps/updated/world_map_01.txt");
    }
}
