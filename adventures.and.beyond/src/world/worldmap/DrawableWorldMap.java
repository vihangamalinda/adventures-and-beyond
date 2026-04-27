package world.worldmap;

import entity.player.Player;

import java.awt.*;

public interface DrawableWorldMap {
    void draw(Graphics2D graphics2D,
              Player player);
}
