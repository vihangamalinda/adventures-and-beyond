package tile.registry;

import tile.Tile;

import java.awt.image.BufferedImage;

public interface TileRegistry {
    boolean couldTileTypeBeCollided(int tileType);

    BufferedImage getImageByTileKey(int tileType);

    Tile getTileByKey(int tileKey);
}
