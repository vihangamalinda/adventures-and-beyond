import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;// 48*48 tile

    // maintaining 4:3 size ratio on width to height ratio
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = maxScreenColumn * tileSize; //768 pixels
    final int getMaxScreenHeight = maxScreenRow * tileSize; //576 pixels
    // Game will be run on this thread
    Thread gameThread;
    final KeyHandler keyHandler;

    //FPS
    int fps = 60;

    // Set Player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(maxScreenWidth, getMaxScreenHeight));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
        this.keyHandler = new KeyHandler();
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    /*
        Game loop iteration is control by **Thread sleeping technique**
     */
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
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / this.fps; // 0.0166 seconds
        double nextDrawInterval = System.nanoTime() + drawInterval;

        while (this.gameThread != null) {
           /*
           Game loop :
           Responsibilities of game loop:
                1) UPDATE : update information such as character positions

                2) DRAW : draw the screen with the updated information
            */


            // Update responsibility
            update();
            // Draw
            repaint();

            try {
                double currentTime = System.nanoTime();
                double remainingMilliSec = (nextDrawInterval - currentTime) / 1000000;

                if (remainingMilliSec > 0) {
                    Thread.sleep((long) remainingMilliSec);
                }
                nextDrawInterval += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        if (keyHandler.upPressed) {
            playerY -= playerSpeed;
        } else if (keyHandler.downPressed) {
            playerY += playerSpeed;
        } else if (keyHandler.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyHandler.rightPressed) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D graphics2D = (Graphics2D) graphic;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(playerX, playerY, tileSize, tileSize);
        //Disposes of this graphics context and releases any system resources that it is using.
        graphics2D.dispose();
    }
}
