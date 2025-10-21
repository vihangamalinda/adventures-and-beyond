package world;

import helper.Loader;
import object.interactable.objects.InteractableObject;

import java.awt.*;

public class WorldMapManager {
    private DrawableWorldMap drawableWorldMap;
    private WorldMapInformation worldMapInformation;

    static class  Holder{
        private static final WorldMapManager INSTANCE = new WorldMapManager();
    }

    public static WorldMapManager getInstance(){
        return Holder.INSTANCE;
    }
    private WorldMapManager(){
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
