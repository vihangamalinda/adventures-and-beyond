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

    public GamePanel() {
        this.setPreferredSize(new Dimension(maxScreenWidth, getMaxScreenHeight));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
    }

    public void startGameThread() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
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

        }
    }

    public void update() {

    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D graphics2D = (Graphics2D) graphic;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(100, 100, tileSize, tileSize);
        //Disposes of this graphics context and releases any system resources that it is using.
        graphics2D.dispose();
    }
}
