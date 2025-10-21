package world.manager;

import helper.Loader;
import object.interactable.objects.InteractableObject;
import world.worldmap.AbstractWorldMap;
import world.worldmap.DrawableWorldMap;
import world.worldmap.WorldMap;
import world.worldmap.WorldMapInformation;

import java.awt.*;

public class WorldMapManagerImpl {
    private DrawableWorldMap drawableWorldMap;
    private WorldMapInformation worldMapInformation;

    static class  Holder{
        private static final WorldMapManagerImpl INSTANCE = new WorldMapManagerImpl();
    }

    public static WorldMapManagerImpl getInstance(){
        return Holder.INSTANCE;
    }
    private WorldMapManagerImpl(){
        AbstractWorldMap worldMap = new WorldMap(loadMapMatrix(),"worldMap_01");
        this.drawableWorldMap = worldMap;
        this.worldMapInformation =worldMap;

    }
    public InteractableObject[] getInteractableObject(){
        return this.worldMapInformation.getInteractableObjects();
    }

    public void draw(Graphics2D graphics2D){
        drawableWorldMap.draw(graphics2D);
    }

   public int getTileKeyByRowAndCol(int rowIndex, int colIndex){
       return this.worldMapInformation.getTileKey(rowIndex,colIndex);
    }

   public  boolean canTileBeCollided(int rowIndex, int colIndex){
        return this.worldMapInformation.canTileBeCollided(rowIndex,colIndex);
   }

    private int[][] loadMapMatrix() {
        return Loader.getMapMatrix("/maps/updated/world_map_01.txt");
    }
}
