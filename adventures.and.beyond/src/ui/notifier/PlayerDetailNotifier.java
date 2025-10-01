package ui.notifier;

import entity.Player;
import main.GamePanel;

import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.STANDARD_ARIAL;
import static ui.UserInterfaceIcons.ICON_KEY_IMAGE;

public class PlayerDetailNotifier extends AbstractDetailNotifier {

    private final Player player;

    public PlayerDetailNotifier(boolean isActive) {
        super(isActive);
        GamePanel gamePanel = GamePanel.getInstance();
        this.player = gamePanel.getPlayer();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (this.isActive()) {
            this.drawBackgroud(graphics2D);
            graphics2D.setFont(STANDARD_ARIAL);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(ICON_KEY_IMAGE, TILE_SIZE / 4, TILE_SIZE / 2, null);
            String message = "x " + this.player.hasManyKey();
            graphics2D.drawString(message, 70, 60);
        }
    }

    @Override
    public void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(10, 10, TILE_SIZE * 4, TILE_SIZE * 2);
    }
}
