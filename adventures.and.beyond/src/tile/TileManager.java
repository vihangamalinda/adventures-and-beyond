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

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = initializeTiles();
        this.mapTileMatrix = loadMapMatrix();
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


    private Tile[] initializeTiles() {
        Tile tileGrass = createTile("/tiles/grass_2.png",true);
        Tile tilePath = createTile("/tiles/path.png",true);
        Tile tileWater = createTile("/tiles/water_custom.png",false);
        Tile tileWall = createTile("/tiles/wall.png",false);
        Tile tileRockWall = createTile("/tiles/rock_wall.png",false);

        Tile tileTest = createTile("/tiles/grass_custom.png",true);


        return new Tile[]{tileGrass, tilePath, tileWater, tileWall, tileRockWall, tileTest};
    }

    public Tile getTileByIndex(int tileIndex) {
        int arrLength = this.tiles.length;
        if (tileIndex < 0 || tileIndex > arrLength - 1) {
            System.out.println("Custom Error: Given index is not withing arr length. Array length: " + arrLength + ", given index: " + tileIndex);
            return null;
        }
        return this.tiles[tileIndex];
    }

    private Tile createTile(String imgPath,boolean canCollide) {
        BufferedImage image = Loader.getImage(imgPath);
        return new Tile(image, canCollide);
    }

    public void draw(Graphics2D graphics2D) {
//        graphics2D.drawImage(getTileByIndex(0).getBufferedImage(),0,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(1).getBufferedImage(),48,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(2).getBufferedImage(),96,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);

        Player player = gamePanel.getPlayer();
//        int drawCol =(player.getWorldPositionX()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);
//        int drawRow =(player.getWorldPositionY()/TILE_SIZE)- (MAX_SCREEN_COLUMN/2);

        int drawMapCol = getDrawMapStarterCol(player);


        int drawMapRow = getDrawMapStarterRow(player);


//        System.out.println("Player worldMapPositions, (X,Y): ("+player.getWorldPositionX()+":"+player.getWorldPositionY()+")");
//        System.out.println("Player worldMapPositions, (currentRow,currentColumn): ("+(player.getWorldPositionY()/TILE_SIZE)+":"+(player.getWorldPositionX()/TILE_SIZE)+")");
//        System.out.println("Player Draw, (drawRow,drawCol): ("+drawMapRow+":"+drawMapCol+")");


        for (int windowRow = 0; windowRow < Constant.MAX_SCREEN_ROW; windowRow++) {
            int[] columValues = this.mapTileMatrix[drawMapRow];
            int currentMapColumn = drawMapCol;
            for (int windowCol = 0; windowCol < MAX_SCREEN_COLUMN; windowCol++) {
                int windowPositionX = windowCol * TILE_SIZE;
                int windowPositionY = windowRow * TILE_SIZE;
                BufferedImage image = getTileByIndex(columValues[currentMapColumn]).getBufferedImage();

                graphics2D.drawImage(image, windowPositionX, windowPositionY, TILE_SIZE, TILE_SIZE, null);

                drawScale(graphics2D, windowPositionX, windowPositionY);

                currentMapColumn++;
            }
            drawMapRow++;
        }

    }

    private void drawScale(Graphics2D graphics2D, int windowPositionX, int windowPositionY) {
        int thickness = 2;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(windowPositionX, windowPositionY, TILE_SIZE, TILE_SIZE);
        graphics2D.setStroke(oldStroke);
    }

    private int getDrawMapStarterRow(Player player) {
        int drawMapRow = (player.getWorldPositionY() / TILE_SIZE) - (MAX_SCREEN_COLUMN / 2);

        if (drawMapRow < 0) {
            drawMapRow = 0;
        } else if ((drawMapRow + MAX_SCREEN_ROW) > MAX_WORLD_ROWS) {
            drawMapRow = MAX_WORLD_ROWS - MAX_SCREEN_ROW;
        }
        return drawMapRow;
    }

    private int getDrawMapStarterCol(Player player) {
        int drawMapCol = (player.getWorldPositionX() / TILE_SIZE) - (MAX_SCREEN_COLUMN / 2);

        if (drawMapCol < 0) {
            drawMapCol = 0;
        } else if (drawMapCol + MAX_SCREEN_COLUMN > MAX_WORLD_COLUMNS) {
            drawMapCol = MAX_WORLD_COLUMNS - MAX_SCREEN_COLUMN;
        }
        return drawMapCol;
    }

    public int getTileTypeIndexByRowAndCol(int row, int col) {
        boolean isInvalid = row < 0 || col < 0 || row > MAX_WORLD_ROWS - 1 || col > MAX_WORLD_COLUMNS - 1;
        if (isInvalid) {
            String message = String.format("Custom Error Given row and col are not within range. Row: %d, Col: %d", row, col);
            throw new RuntimeException(message);
        }
        return this.mapTileMatrix[row][col];
    }

    private int[][] loadMapMatrix() {
        return Loader.getMapMatrix("/maps/world_map_01.txt");
    }

}
