package ui;

import main.GamePanel;
import ui.notifier.PlayerDetailNotifier;

import java.awt.*;

public class UserInterfaceManager {

    private final PlayerDetailNotifier playerDetailNotifier;

    public UserInterfaceManager(GamePanel gamePanel){
        this.playerDetailNotifier = new PlayerDetailNotifier(gamePanel.getPlayer());
    }

    public void draw(Graphics2D graphics2D){
        this.playerDetailNotifier.draw(graphics2D);
    }
}
