package entity.manager;

import entity.player.Player;

import java.awt.*;

public interface EntityManager {
    void drawEntities(Graphics2D graphics2D);

    void updateEntities();

    Player getPlayer();
}
