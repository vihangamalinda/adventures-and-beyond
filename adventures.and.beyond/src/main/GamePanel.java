package main;

import entity.Player;
import helper.Constant;

import object.InteractableObjectManager;
import sound.SoundManager;
import tile.TileManager;
import ui.UserInterfaceManager;

import javax.swing.*;
import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static sound.SoundKey.THEME_1_KEY;

public class GamePanel extends JPanel implements Runnable {
    // Game will be run on this thread
    Thread gameThread;
    final KeyHandler keyHandler;

    // Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    private final Player player;

    private final TileManager tileManager;

    private CollisionDetector collisionDetector;
    public final InteractableObjectManager interactableObjectManager;

    private static class Holder{
        private static final GamePanel INSTANCE = new GamePanel();
    }

    public GamePanel() {
        this.setPreferredSize(new Dimension(Constant.WINDOW_MAX_SCREEN_WIDTH, Constant.WINDOW_MAX_SCREEN_HEIGHT));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        this.player = new Player(28 * TILE_SIZE, 12 * TILE_SIZE, this, this.keyHandler);
        this.tileManager = new TileManager(this);
        this.collisionDetector = new CollisionDetector(this);
        this.interactableObjectManager = new InteractableObjectManager(this);
        playThemeMusic();
    }

    public static GamePanel getInstance(){
        return Holder.INSTANCE;
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /**
     Game loop iteration is control by **Thread sleeping technique**
     **/
//    @Override
//    public void run() {
//        double drawInterval = (double) 1000000000 / this.fps; // 0.0166 seconds
//        double nextDrawInterval = System.nanoTime() + drawInterval;
//
//        while (this.gameThread != null) {
//           /*
//           Game loop :
//           Responsibilities of game loop:
//                1) UPDATE : update information such as character positions
//
//                2) DRAW : draw the screen with the updated information
//            */
//
//
//            // Update responsibility
//            update();
//            // Draw
//            repaint();
//
//            try {
//                double currentTime = System.nanoTime();
//                double remainingMilliSec = (nextDrawInterval - currentTime) / 1000000;
//
//                if (remainingMilliSec > 0) {
//                    Thread.sleep((long) remainingMilliSec);
//                }
//                nextDrawInterval += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    /**
     * Game loop iteration is control by Delta time (Accumulator)
     **/
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / Constant.FRAME_RATE_PER_SECOND; // 0.0166 seconds
        double delta = 0;
        long lastTime = System.nanoTime();

        while (this.gameThread != null) {
           /*
           Game loop :
           Responsibilities of game loop:
                1) UPDATE : update information such as character positions

                2) DRAW : draw the screen with the updated information
            */
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                // Update responsibility
                update();
                // Draw
                repaint();
                delta--;
            }
        }
    }

    public CollisionDetector getCollisionDetector() {
        return collisionDetector;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public void update() {
        this.player.update();
    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D graphics2D = (Graphics2D) graphic;

        this.tileManager.draw(graphics2D);
        this.interactableObjectManager.drawInteractiveObjects(graphics2D);
        this.player.draw(graphics2D);

        // Notify
        UserInterfaceManager.getInstance().draw(graphics2D);

        //Disposes of this graphics context and releases any system resources that it is using.
        graphics2D.dispose();
    }

    public void playThemeMusic() {
        SoundManager.getInstance().performMainMusicSound(THEME_1_KEY);
    }

    public Player getPlayer() {
        return this.player;
    }
}
