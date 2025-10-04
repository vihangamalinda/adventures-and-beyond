package tile.registry;

public class TileKey {

    /**
     * Dedicated number according to following pattern
     * Pure Tile -> 0-20
     * composite Tile -> 21-99
     */

    /**
     * Pure Tile : 0-20
     */

    public static final String GRASS_NORMAL = "00";
    public static final String GRASS_DETAILED_01 = "01";
    public static final String SAND_PATH = "02";
    public static final String TREE_SIMPLE = "03";
    public static final String BRICK_WALL = "04";
    public static final String WATER_NORMAL = "05";
    public static final String WATER_DETAIL_01 = "06";
    public static final String WATER_DETAIL_02 = "07";


    /**
     *  Composite Tile -> 21-99
     */

    /**
     * Grass & Sand composition
     */

    //  composites:  grass-out-sand-in pattern
    public static final String GRASS_OUT_SAND_IN_01 = "21";
    public static final String GRASS_OUT_SAND_IN_02 = "22";
    public static final String GRASS_OUT_SAND_IN_03 = "23";
    public static final String GRASS_OUT_SAND_IN_04 = "24";
    public static final String GRASS_OUT_SAND_IN_05 = "25";
    public static final String GRASS_OUT_SAND_IN_06 = "26";
    public static final String GRASS_OUT_SAND_IN_07 = "27";
    public static final String GRASS_OUT_SAND_IN_08 = "28";
    public static final String GRASS_OUT_SAND_IN_09 = "29";


    //  composites:  sand-out-grass-in pattern
    public static final String SAND_OUT_GRASS_IN_01 = "30";
    public static final String SAND_OUT_GRASS_IN_02 = "31";
    public static final String SAND_OUT_GRASS_IN_03 = "32";
    public static final String SAND_OUT_GRASS_IN_04 = "33";

    /**
     * Grass & Water composition
     */

    //  composites:  grass-out-water-in pattern
    public static final String GRASS_OUT_WATER_IN_01 = "34";
    public static final String GRASS_OUT_WATER_IN_02 = "35";
    public static final String GRASS_OUT_WATER_IN_03 = "36";
    public static final String GRASS_OUT_WATER_IN_04 = "37";
    public static final String GRASS_OUT_WATER_IN_05 = "38";
    public static final String GRASS_OUT_WATER_IN_06 = "39";
    public static final String GRASS_OUT_WATER_IN_07 = "40";
    public static final String GRASS_OUT_WATER_IN_08 = "41";
    public static final String GRASS_OUT_WATER_IN_09 = "42";

    //  composites:  water-out-grass-in pattern
    public static final String WATER_OUT_GRASS_IN_01 = "43";
    public static final String WATER_OUT_GRASS_IN_02 = "44";
    public static final String WATER_OUT_GRASS_IN_03 = "45";
    public static final String WATER_OUT_GRASS_IN_04 = "46";

}
