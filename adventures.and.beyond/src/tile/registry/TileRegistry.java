package tile.registry;

import helper.Loader;
import tile.Tile;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import static helper.ImageScaler.getStandardScaledImage;
import static java.util.Objects.isNull;

public class TileRegistry {

    private final HashMap<String, Tile> tileHashMap;


    public static TileRegistry getInstance(){
        return Holder.INSTANCE;
    }

    public Tile getTileByKey(int tileKey){
        String keyStr =getKeyStrValue(tileKey);
        Tile tile= this.tileHashMap.get(keyStr);

        if(isNull(tile)){
           System.out.println("Custom Error: Given key is not registered on tileMap. Given key"+tileKey);
        }
        return tile;
    }

    private String getKeyStrValue(int tileKey){
        if(tileKey >=0 && tileKey <=9){
            return "0"+ tileKey;
        }

        return String.valueOf(tileKey);
    }

    private static class Holder{
        private final static TileRegistry INSTANCE = new TileRegistry();
    }

    private TileRegistry(){
        this.tileHashMap = new HashMap<>();
        registerTiles();
    }

    private void registerTiles(){
       registerPureTiles();
       registerCompositeTiles();
    }
    private void registerPureTiles(){
        registerTile(TileKey.GRASS_NORMAL,TilePath.GRASS_NORMAL,true);
        registerTile(TileKey.GRASS_DETAILED_01,TilePath.GRASS_DETAILED_01,true);
        registerTile(TileKey.SAND_PATH,TilePath.SAND_PATH,true);
        registerTile(TileKey.TREE_SIMPLE,TilePath.TREE_SIMPLE,false);
        registerTile(TileKey.BRICK_WALL,TilePath.BRICK_WALL,false);
        registerTile(TileKey.WATER_NORMAL,TilePath.WATER_NORMAL,false);
        registerTile(TileKey.WATER_DETAIL_01,TilePath.WATER_DETAIL_01,false);
        registerTile(TileKey.WATER_DETAIL_02,TilePath.WATER_DETAIL_02,false);
    }
    private void registerCompositeTiles(){
        registerGrassSandCompositeTiles();
        registerGrassWaterCompositeTiles();
    }

    private void registerGrassSandCompositeTiles() {
        boolean canCollide = true;

        //  composites:  grass-out-sand-in pattern
        registerTile(TileKey.GRASS_OUT_SAND_IN_01, TilePath.GRASS_OUT_SAND_IN_01, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_02, TilePath.GRASS_OUT_SAND_IN_02, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_03, TilePath.GRASS_OUT_SAND_IN_03, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_04, TilePath.GRASS_OUT_SAND_IN_04, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_05, TilePath.GRASS_OUT_SAND_IN_05, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_06, TilePath.GRASS_OUT_SAND_IN_06, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_07, TilePath.GRASS_OUT_SAND_IN_07, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_08, TilePath.GRASS_OUT_SAND_IN_08, canCollide);
        registerTile(TileKey.GRASS_OUT_SAND_IN_09, TilePath.GRASS_OUT_SAND_IN_09, canCollide);

        //  composites:  sand-out-grass-in pattern
        registerTile(TileKey.SAND_OUT_GRASS_IN_01, TilePath.SAND_OUT_GRASS_IN_01, canCollide);
        registerTile(TileKey.SAND_OUT_GRASS_IN_02, TilePath.SAND_OUT_GRASS_IN_02, canCollide);
        registerTile(TileKey.SAND_OUT_GRASS_IN_03, TilePath.SAND_OUT_GRASS_IN_03, canCollide);
        registerTile(TileKey.SAND_OUT_GRASS_IN_04, TilePath.SAND_OUT_GRASS_IN_04, canCollide);

    }

    private void registerGrassWaterCompositeTiles(){
        boolean canCollide =false;

        //  composites:  grass-out-water-in pattern
        registerTile(TileKey.GRASS_OUT_WATER_IN_01,TilePath.GRASS_OUT_WATER_IN_01,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_02,TilePath.GRASS_OUT_WATER_IN_02,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_03,TilePath.GRASS_OUT_WATER_IN_03,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_04,TilePath.GRASS_OUT_WATER_IN_04,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_05,TilePath.GRASS_OUT_WATER_IN_05,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_06,TilePath.GRASS_OUT_WATER_IN_06,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_07,TilePath.GRASS_OUT_WATER_IN_07,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_08,TilePath.GRASS_OUT_WATER_IN_08,canCollide);
        registerTile(TileKey.GRASS_OUT_WATER_IN_09,TilePath.GRASS_OUT_WATER_IN_09,canCollide);

        //  composites:  water-out-grass-in pattern
        registerTile(TileKey.WATER_OUT_GRASS_IN_01,TilePath.WATER_OUT_GRASS_IN_01,canCollide);
        registerTile(TileKey.WATER_OUT_GRASS_IN_02,TilePath.WATER_OUT_GRASS_IN_02,canCollide);
        registerTile(TileKey.WATER_OUT_GRASS_IN_03,TilePath.WATER_OUT_GRASS_IN_03,canCollide);
        registerTile(TileKey.WATER_OUT_GRASS_IN_04,TilePath.WATER_OUT_GRASS_IN_04,canCollide);
    }

    private void registerTile(String tileKey,String tileImgPath,boolean canCollied){
        BufferedImage image = getImage(tileImgPath);
        Tile tile = new Tile(image,canCollied);
        this.tileHashMap.put(tileKey,tile);
    }

    private BufferedImage getImage(String imgPath) {
        BufferedImage originalImage = Loader.getImage(imgPath);
        return getStandardScaledImage(originalImage);
    }
}
