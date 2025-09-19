import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    //Screen settings
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale;// 48*48 tile

    // maintianing 4:3 size ratio on width to height ratio
    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int maxScreenWidth = maxScreenColumn * tileSize; //768 pixels
    final int getMaxScreenHeight = maxScreenRow * tileSize; //576 pixels

    public  GamePanel(){
        this.setPreferredSize(new Dimension(maxScreenWidth,getMaxScreenHeight));// defining panel size
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // improve game's rendering performance
    }
}
