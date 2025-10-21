package world.worldmap;

import helper.Constant;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.Constant.MAX_SCREEN_COLUMN;
import static helper.Constant.TILE_SIZE;

public class WorldMap extends AbstractWorldMap{
    public WorldMap(int[][] mapTileMatrix,String worldMapKey) {
        super(mapTileMatrix,worldMapKey);
    }

    @Override
    public int getTileKey(int rowIndex, int colIndex) {
        return this.getTileKeyByRowAndCol(rowIndex, colIndex);
    }

    public boolean canTileBeCollided(int rowIndex, int colIndex){
        int tileKey =this.getTileKeyByRowAndCol(rowIndex,colIndex);
        return this.getTileRegistry().couldTileTypeBeCollided(tileKey);
    }


    @Override
    protected void drawWorldMap(Graphics2D graphics2D, int drawMapRow, int drawMapCol) {
        for (int windowRow = 0; windowRow < Constant.MAX_SCREEN_ROW; windowRow++) {
            int[] columValues = this.getMapTileMatrix()[drawMapRow];
            int currentMapColumn = drawMapCol;
            for (int windowCol = 0; windowCol < MAX_SCREEN_COLUMN; windowCol++) {
                int windowPositionX = windowCol * TILE_SIZE;
                int windowPositionY = windowRow * TILE_SIZE;
                int tileKey = columValues[currentMapColumn];

                BufferedImage image = this.getTileRegistry().getImageByTileKey(tileKey);

                graphics2D.drawImage(image, windowPositionX, windowPositionY, null);

                drawScale(graphics2D, windowPositionX, windowPositionY);
                drawRowAndColNumbers(graphics2D, drawMapRow, currentMapColumn, windowPositionX, windowPositionY);
                currentMapColumn++;
            }
            drawMapRow++;
        }
    }

    private void drawRowAndColNumbers(Graphics2D graphics2D, int row, int col, int windowStartX, int windowStartY) {
        String message = String.format("(%d,%d)", row, col);
        int tileHalf = TILE_SIZE / 2;
        Font boldFont = new Font("Arial", Font.BOLD, 14);
        graphics2D.setFont(boldFont);

        graphics2D.drawString(message, windowStartX + 5, windowStartY + tileHalf);

    }

    protected void drawScale(Graphics2D graphics2D, int windowPositionX, int windowPositionY) {
        int thickness = 1;
        Stroke oldStroke = graphics2D.getStroke();
        graphics2D.setStroke(new BasicStroke(thickness));
        graphics2D.drawRect(windowPositionX, windowPositionY, TILE_SIZE, TILE_SIZE);
        graphics2D.setStroke(oldStroke);
    }

}
