package tile;

import entity.Player;
import helper.Constant;
import helper.Loader;
import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.Constant.*;


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

        Player player=gamePanel.getPlayer();
//        int drawCol =(player.getWorldPositionX()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);
//        int drawRow =(player.getWorldPositionY()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);

        int drawMapCol = getDrawMapStarterCol(player);


        int drawMapRow = getDrawMapStarterRow(player);







//        System.out.println("Player worldMapPositions, (X,Y): ("+player.getWorldPositionX()+":"+player.getWorldPositionY()+")");
//        System.out.println("Player worldMapPositions, (currentRow,currentColumn): ("+(player.getWorldPositionY()/TILE_SIZE)+":"+(player.getWorldPositionX()/TILE_SIZE)+")");
//        System.out.println("Player Draw, (drawRow,drawCol): ("+drawMapRow+":"+drawMapCol+")");


        for (int windowRow = 0; windowRow< Constant.MAX_SCREEN_ROW; windowRow++){
            int[] columValues =this.mapTileMatrix[drawMapRow];
            int currentMapColumn=   drawMapCol;
            for (int windowCol = 0; windowCol< MAX_SCREEN_COLUMN; windowCol++){
                int windowPositionX =windowCol* TILE_SIZE;
                int windowPositionY =windowRow* TILE_SIZE;
                BufferedImage image =getTileByIndex(columValues[currentMapColumn]).getBufferedImage();

                graphics2D.drawImage(image,windowPositionX,windowPositionY, TILE_SIZE, TILE_SIZE,null);
                currentMapColumn++;
            }
            drawMapRow++;
        }

    }

    private static int getDrawMapStarterRow(Player player) {
        int drawMapRow =(player.getPlayerAbsoluteCenterY()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);

        if(drawMapRow<0){
            drawMapRow=0;
        }else if((drawMapRow+MAX_SCREEN_ROW)>MAX_WORLD_ROWS){
            drawMapRow =MAX_WORLD_ROWS-MAX_SCREEN_ROW;
        }
        return drawMapRow;
    }

    private static int getDrawMapStarterCol(Player player) {
        int drawMapCol =(player.getPlayerAbsoluteCenterX()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);

        if(drawMapCol<0){
            drawMapCol=0;
        }else if(drawMapCol+MAX_SCREEN_COLUMN>MAX_WORLD_COLUMNS){
            drawMapCol =MAX_WORLD_COLUMNS-MAX_SCREEN_COLUMN;
        }
        return drawMapCol;
    }

    private int[][] loadMapMatrix(){
        return getMapMatrix("/maps/map1.txt");
    }

}
