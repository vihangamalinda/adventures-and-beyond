package tile;

import helper.Loader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.Constant.TILE_SIZE;


public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] mapTileMatrix;
    public TileManager(GamePanel gamePanel){
        this.gamePanel =gamePanel;
        this.tiles = initializeTiles();
        this.mapTileMatrix=loadMapMatrix();
    }

    public int[][] getMapTileMatrix() {
        return mapTileMatrix;
    }

    public void setMapTileMatrix(int[][] mapTileMatrix) {
        this.mapTileMatrix = mapTileMatrix;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        this.tiles = tiles;
    }


    private  Tile[] initializeTiles(){
      Tile tileGrass =createTile("/tiles/grass_2.png");
      Tile tilePath =createTile("/tiles/path.png");
      Tile tileWater =createTile("/tiles/water_custom.png");
      Tile tileWall =createTile("/tiles/wall.png");
      Tile tileRockWall =createTile("/tiles/rock_wall.png");


        return new Tile[]{tileGrass,tilePath,tileWater,tileWall,tileRockWall};
    }

    public Tile getTileByIndex(int tileIndex){
        int arrLength = this.tiles.length;
        if(tileIndex<0 || tileIndex> arrLength -1){
            System.out.println("Custom Error: Given index is not withing arr length. Array length: "+arrLength+ ", given index: "+tileIndex);
            return  null;
        }
       return this.tiles[tileIndex];
    }

    private Tile createTile(String imgPath){
        BufferedImage image =Loader.getImage(imgPath);
        return new Tile(image,false);
    }

    public void draw(Graphics2D graphics2D){
//        graphics2D.drawImage(getTileByIndex(0).getBufferedImage(),0,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(1).getBufferedImage(),48,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(2).getBufferedImage(),96,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);


        for (int row=0;row<this.mapTileMatrix.length;row++){
            int[] columValues =this.mapTileMatrix[row];
            for (int col=0;col<columValues.length;col++){
                int positionX =col* Constant.TILE_SIZE;
                int positionY =row* Constant.TILE_SIZE;
                BufferedImage image =getTileByIndex(columValues[col]).getBufferedImage();
                graphics2D.drawImage(image,positionX,positionY, Constant.TILE_SIZE, Constant.TILE_SIZE,null);
            }
        }

    }

    private int[][] loadMapMatrix(){
        return getMapMatrix("/maps/map1.txt");
    }

}
