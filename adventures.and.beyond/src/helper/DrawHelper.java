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

    public static int getObjWindowPositionYRespectiveToPlayer(int positionY,Player player){
        int objPlayerMapDifferance = player.getCurrentRowOnWorldMap()-(positionY/TILE_SIZE);
        int playerScreenCol = player.getScreenPositionY()/TILE_SIZE;

        int value;
        if(objPlayerMapDifferance<0){
            // Object is below Player position on Map
            value= playerScreenCol + Math.abs(objPlayerMapDifferance);
        }else{
            // Object is above Player position on Map
            value =playerScreenCol+ (objPlayerMapDifferance*(-1));
        }
        return value*TILE_SIZE;
    }
    public static int getObjWindowPositionXRespectiveToPlayer(int positionX,Player player){
        int objPlayerMapDifferance = player.getCurrentColOnWorldMap()-(positionX/TILE_SIZE);
//        System.out.println("objPlayerMapDifferance"+objPlayerMapDifferance);
        int playerScreenRow= player.getScreenPositionX()/TILE_SIZE;

        int value;
        if(objPlayerMapDifferance<0){
            // Object is leftward to Player position on Map
            value= playerScreenRow + Math.abs(objPlayerMapDifferance);
        }else{
            // Object is rightward ti Player position on Map
            value =playerScreenRow+ (objPlayerMapDifferance*(-1));
        }
        return value*TILE_SIZE;
    }
}
