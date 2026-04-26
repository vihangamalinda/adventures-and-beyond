package main;

import entity.manager.EntityManager;
import entity.manager.EntityManagerFactory;
import game.loop.GameLoop;
import game.loop.GameLoopImpl;
import game.render.GameRenderer;
import game.render.GameRendererImpl;
import game.update.GameUpdater;
import game.update.GameUpdaterImpl;
import helper.Constant;
import helper.Timer;
import input.KeyHandler;
import sound.SoundManager;
import ui.UserInterfaceManager;
import world.manager.WorldMapManager;
import world.manager.WorldMapManagerFactory;

import javax.swing.*;
import java.awt.*;

import static sound.SoundKey.THEME_1_KEY;

public class GamePanel extends JPanel {
    // Game will be run on this thread
    Thread gameThread;

    // Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

//    private final EntityManager entityManager;
//    private final UserInterfaceManager userInterfaceManager;

//    private final GameUpdater gameUpdater;
    private final GameRenderer gameRenderer;
    private final GameLoop gameLoop;

    private static class Holder {
        private static final GamePanel INSTANCE = new GamePanel();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(Constant.WINDOW_MAX_SCREEN_WIDTH, Constant.WINDOW_MAX_SCREEN_HEIGHT));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
        // Registering key handler to component
        this.addKeyListener(KeyHandler.getInstance());
        this.setFocusable(true);

        EntityManager entityManager = EntityManagerFactory.getInstance();
        UserInterfaceManager userInterfaceManager = UserInterfaceManager.getInstance();
        WorldMapManager worldMapManager = WorldMapManagerFactory.getInstance();
//        playThemeMusic();
        GameUpdater gameUpdater = new GameUpdaterImpl(entityManager, userInterfaceManager);
        this.gameRenderer = new GameRendererImpl(entityManager, userInterfaceManager, worldMapManager);
        this.gameLoop = new GameLoopImpl(gameUpdater);
    }

    public static GamePanel getInstance() {
        return Holder.INSTANCE;
    }

//    @Override
    public void startGameThread() {
        this.gameLoop.start(this::repaint);
    }
    public void stopGame() {
        this.gameLoop.stop();
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        Graphics2D graphics2D = (Graphics2D) graphic;
        this.gameRenderer.render(graphics2D);
        //Disposes of this graphics context and releases any system resources that it is using.
        graphics2D.dispose();
    }

    private void playThemeMusic() {
        SoundManager.getInstance().performMainMusicSound(THEME_1_KEY);
    }
}
