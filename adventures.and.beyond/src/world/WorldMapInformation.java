package world;

public interface WorldMapInformation {
    int getTileKey(int rowIndex, int colIndex);
    boolean canTileBeCollided(int rowIndex, int colIndex);
}
