package ui.notifier;

import entity.manager.EntityManagerFactory;
import entity.player.Player;

import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.STANDARD_ARIAL;
import static ui.UserInterfaceIcons.ICON_KEY_IMAGE;

public class PlayerDetailNotifier extends AbstractDetailNotifier {

    private final Player player;

    public PlayerDetailNotifier(boolean isActive) {
        super(isActive);
        this.player = EntityManagerFactory.getInstance().getPlayer();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (this.isActive()) {
            this.drawBackgroud(graphics2D);
            graphics2D.setFont(STANDARD_ARIAL);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawImage(ICON_KEY_IMAGE, TILE_SIZE / 4, TILE_SIZE / 2, null);
            updatedMessage();
            graphics2D.drawString(this.getMessage(), 70, 60);
        }
    }

    private void updatedMessage() {
        String message = "x " + this.player.hasManyKey();
        notifyMessage(message);
    }

    @Override
    public void notifyMessage(String message) {
        this.setMessage(message);
    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        graphics2D.fillRect(10, 10, TILE_SIZE * 4, TILE_SIZE * 2);
    }
}
