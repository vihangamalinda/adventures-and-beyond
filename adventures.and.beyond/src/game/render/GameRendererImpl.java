package game.render;

import entity.manager.EntityManager;
import entity.player.Player;
import helper.Timer;
import ui.UserInterfaceManager;
import world.manager.WorldMapManager;

import java.awt.*;

public class GameRendererImpl implements GameRenderer {
    private final EntityManager entityManager;
    private final UserInterfaceManager userInterfaceManager;
    private final WorldMapManager worldMapManager;
    private final Timer timer;

    public GameRendererImpl(EntityManager entityManager,
                            UserInterfaceManager userInterfaceManager,
                            WorldMapManager worldMapManager,
                            Timer timer) {
        this.entityManager = entityManager;
        this.userInterfaceManager = userInterfaceManager;
        this.worldMapManager = worldMapManager;
        this.timer = timer;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        long startTime = this.timer.getStartingTime();
        Player player = this.entityManager.getPlayer();
        this.worldMapManager.draw(graphics2D,player);

        this.entityManager.drawEntities(graphics2D);

        // Notify
        this.userInterfaceManager.draw(graphics2D);

        this.timer.evaluvateTimeSpent(startTime);
    }
}
