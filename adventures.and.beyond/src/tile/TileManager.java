package tile;

import helper.Helper;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import static helper.Helper.getImage;
import static helper.Helper.readTextFile;

public class TileManager {
    private GamePanel gamePanel;
    private Tile[] tiles;
    private int[][] mapTileMatrix;
    public TileManager(GamePanel gamePanel){
        this.gamePanel =gamePanel;
        this.tiles = initializeTiles();
        this.mapTileMatrix = new int[12][16];
        loadMapMatrix();
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
        BufferedImage image =getImage(imgPath);
        return new Tile(image,false);
    }

    public void draw(Graphics2D graphics2D){
//        graphics2D.drawImage(getTileByIndex(0).getBufferedImage(),0,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(1).getBufferedImage(),48,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(2).getBufferedImage(),96,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);

        graphics2D.drawImage(getTileByIndex(3).getBufferedImage(),96+48,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
        for (int row=0;row<this.mapTileMatrix.length;row++){
            int[] columValues =this.mapTileMatrix[row];
            for (int col=0;col<columValues.length;col++){
                int positionX =col*Helper.TILE_SIZE;
                int positionY =row*Helper.TILE_SIZE;
                BufferedImage image =getTileByIndex(columValues[col]).getBufferedImage();
                graphics2D.drawImage(image,positionX,positionY, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
            }
        }

    }

    private void loadMapMatrix(){
        BufferedReader reader = readTextFile("/maps/map1.txt");
        assert reader != null;

        try {
            for (int row = 0; row < Helper.MAX_SCREEN_ROW; row++) {

                String line = reader.readLine();
                String[] numericStr = line.split("");
                System.out.println("");
                for (int col = 0; col < Helper.MAX_SCREEN_COLUMN; col++) {
                    int value = Integer.parseInt(numericStr[col]);
                   System.out.print(value);
                   this.mapTileMatrix[row][col]=value;
                }

            }
        }catch (IOException exception){
            System.out.println("Custom Error:Reading the map line by line");
            exception.fillInStackTrace();
        }
    }

}
