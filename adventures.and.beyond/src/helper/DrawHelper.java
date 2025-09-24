package helper;

import entity.Player;

import static helper.Constant.*;

public class DrawHelper {

    public static boolean isWithinWindow(int worldPositionX,int worldPositionY,Player player){
        int objectColumn = worldPositionX/TILE_SIZE;
        int objectRow = worldPositionY/TILE_SIZE;


        int currentWindowStartColum = getCurrentWindowStartColum(player);
        int currentWindowEndColumn = getCurrentWindowEndColumn(player);

        int currentWindowStartRow = getCurrentWindowStartRow(player);
        int currentWindowEndRow = getCurrentWindowEndRow(player);

        boolean isObjectColWithinWindowCol = objectColumn >=currentWindowStartColum  && objectColumn <=currentWindowEndColumn;
        boolean isObjectRowWithinWindowRow = objectRow>=currentWindowStartRow && objectRow<=currentWindowEndRow;
        return  isObjectColWithinWindowCol && isObjectRowWithinWindowRow;
    }

    private static int getCurrentWindowEndRow(Player player) {
        return player.getCurrentRowOnWorldMap() + MAX_SCREEN_ROW/2;
    }

    private static int getCurrentWindowStartRow(Player player) {
        return player.getCurrentRowOnWorldMap() - MAX_SCREEN_ROW / 2;
    }

    private static int getCurrentWindowEndColumn(Player player) {

        return player.getCurrentColOnWorldMap()  + MAX_SCREEN_COLUMN/2;
    }

    private static int getCurrentWindowStartColum(Player player) {
        return player.getCurrentColOnWorldMap() - MAX_SCREEN_COLUMN / 2;
    }
}
