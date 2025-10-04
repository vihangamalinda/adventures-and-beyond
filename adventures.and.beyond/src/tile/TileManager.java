package tile;

import entity.Player;
import helper.Constant;
import helper.Loader;
import main.GamePanel;
import tile.registry.TileRegistry;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.Constant.*;
import static helper.ImageScaler.getStandardScaledImage;
import static java.util.Objects.isNull;


public class TileManager {
    private int[][] mapTileMatrix;

    private static class Holder {
        private static TileManager INSTANCE = new TileManager();
    }

    private TileManager() {
        this.mapTileMatrix = loadMapMatrix();
    }

    public static TileManager getInstance() {
        return Holder.INSTANCE;
    }

    public int[][] getMapTileMatrix() {
        return mapTileMatrix;
    }

    public void setMapTileMatrix(int[][] mapTileMatrix) {
        this.mapTileMatrix = mapTileMatrix;
    }

    public Tile getTileByIndex(int tileKey) {

        Tile tile =TileRegistry.getInstance().getTileByKey(tileKey);
        if (isNull(tile)) {
            System.out.println("Custom Error: Given tile key is not registered. Tile key " + tileKey);
            return null;
        }
        return tile;
    }

    private Tile createTile(String imgPath, boolean canCollide) {
        BufferedImage original = Loader.getImage(imgPath);
        BufferedImage image = getScaledImage(original);
        return new Tile(image, canCollide);
    }

    private BufferedImage getScaledImage(BufferedImage originalImage) {
        return getStandardScaledImage(originalImage);
    }

    public void draw(Graphics2D graphics2D) {
//        graphics2D.drawImage(getTileByIndex(0).getBufferedImage(),0,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(1).getBufferedImage(),48,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);
//        graphics2D.drawImage(getTileByIndex(2).getBufferedImage(),96,0, Helper.TILE_SIZE,Helper.TILE_SIZE,null);

        Player player = GamePanel.getInstance().getPlayer();
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
                int tileKey = columValues[currentMapColumn];

                BufferedImage image = TileRegistry.getInstance().getTileByKey(tileKey).getBufferedImage();

                graphics2D.drawImage(image, windowPositionX, windowPositionY, null);

                drawScale(graphics2D, windowPositionX, windowPositionY);
                drawRowAndColNumbers(graphics2D, drawMapRow, currentMapColumn, windowPositionX, windowPositionY);
                currentMapColumn++;
            }
            drawMapRow++;
        }

    }

    private void drawScale(Graphics2D graphics2D, int windowPositionX, int windowPositionY) {
        int thickness = 1;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(windowPositionX, windowPositionY, TILE_SIZE, TILE_SIZE);
        graphics2D.setStroke(oldStroke);
    }

    private int getDrawMapStarterRow(Player player) {
        int drawMapRow = (player.getCurrentRowOnWorldMap() - (MAX_SCREEN_ROW / 2));

        if (drawMapRow < 0) {
            drawMapRow = 0;
        } else if ((drawMapRow + MAX_SCREEN_ROW) > MAX_WORLD_ROWS) {
            drawMapRow = MAX_WORLD_ROWS - MAX_SCREEN_ROW;
        }
//        System.out.println("Currently drawing starting from row:"+drawMapRow);
        return drawMapRow;
    }

    private int getDrawMapStarterCol(Player player) {
        int drawMapCol = (player.getCurrentColOnWorldMap()) - (MAX_SCREEN_COLUMN / 2);

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

    private void drawRowAndColNumbers(Graphics2D graphics2D, int row, int col, int windowStartX, int windowStartY) {
        String message = String.format("(%d,%d)", row, col);
        int tileHalf = TILE_SIZE / 2;
        Font boldFont = new Font("Arial", Font.BOLD, 14);
        graphics2D.setFont(boldFont);

        graphics2D.drawString(message, windowStartX + 5, windowStartY + tileHalf);

    }

    private int[][] loadMapMatrix() {
        return Loader.getMapMatrix("/maps/updated/world_map_01.txt");
    }

}
