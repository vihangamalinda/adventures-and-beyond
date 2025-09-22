package helper;



public class Constant {
    //Screen settings
    public static final int ORIGINAL_TILE_SIZE = 48;// 16*16 tile
    static final int UP_SCALE = 1;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * UP_SCALE;// 48*48 tile

    // maintaining 4:3 size ratio on width to height ratio
    public final static int MAX_SCREEN_COLUMN = 16;
    public final static int MAX_SCREEN_ROW = 12;

    public static final int WINDOW_MAX_SCREEN_WIDTH = MAX_SCREEN_COLUMN * TILE_SIZE;//768 pixels
    public static final int WINDOW_MAX_SCREEN_HEIGHT = MAX_SCREEN_ROW * TILE_SIZE;//576 pixels

    public static final int FRAME_RATE_PER_SECOND = 60;//fps=60

    public static final int PLAYER_UP_SCALE =1;

    // World settings
    public final static int MAX_WORLD_ROWS = 50;
    public final static int MAX_WORLD_COLUMNS = 50;
    public final static int WORLD_MAP_WIDTH = TILE_SIZE * MAX_WORLD_COLUMNS;
    public final static int WORLD_MAP_HEIGHT = TILE_SIZE * MAX_WORLD_ROWS;

}
