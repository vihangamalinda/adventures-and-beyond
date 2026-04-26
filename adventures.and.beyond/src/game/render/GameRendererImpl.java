package game.render;

import entity.manager.EntityManager;

import helper.Timer;

import ui.UserInterfaceManager;
import world.manager.WorldMapManager;


import java.awt.*;

public class GameRendererImpl implements GameRenderer {
    private final EntityManager entityManager;
    private final UserInterfaceManager userInterfaceManager;
    private final WorldMapManager worldMapManager;
    public GameRendererImpl(EntityManager entityManager, UserInterfaceManager userInterfaceManager, WorldMapManager worldMapManager) {
        this.entityManager = entityManager;
        this.userInterfaceManager =userInterfaceManager;
        this.worldMapManager = worldMapManager;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        long startTime = Timer.getStartingTime();
        this.worldMapManager.draw(graphics2D);

        this.entityManager.drawEntities(graphics2D);

        // Notify
        this.userInterfaceManager.draw(graphics2D);

        Timer.evaluvateTimeSpent(startTime);
    }
}
