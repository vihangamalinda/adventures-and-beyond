package game.panel;

import game.render.GameRenderer;
import helper.Constant;
import input.KeyHandler;
import sound.SoundManager;

import javax.swing.*;
import java.awt.*;

import static sound.SoundKey.THEME_1_KEY;

public class GamePanel extends JPanel {


    // Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    private final GameRenderer gameRenderer;

    public GamePanel(GameRenderer gameRenderer,
                     KeyHandler keyHandler) {
        this.setPreferredSize(new Dimension(Constant.WINDOW_MAX_SCREEN_WIDTH,
                                            Constant.WINDOW_MAX_SCREEN_HEIGHT));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
        // Registering key handler to component
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

//        playThemeMusic();
        this.gameRenderer = gameRenderer;
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


/**
 * Game loop iteration is control by **Thread sleeping technique**
 * <p>
 * Game loop iteration is control by Delta time (Accumulator)
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
//@Override
//public void run() {
//        double drawInterval = (double) 1000000000 / Constant.FRAME_RATE_PER_SECOND; // 0.0166 seconds
//        double delta = 0;
//        long lastTime = System.nanoTime();
//
//        while (this.gameThread != null) {
//           /*
//           Game loop :
//           Responsibilities of game loop:
//                1) UPDATE : update information such as character positions
//
//                2) DRAW : draw the screen with the updated information
//            */
//            long currentTime = System.nanoTime();
//            delta += (currentTime - lastTime) / drawInterval;
//            lastTime = currentTime;
//
//            if (delta >= 1) {
//                // Update responsibility
//                update();
//                // Draw
//                repaint();
//                delta--;
//            }
//        }
//}
