package ui;

import main.GamePanel;
import ui.notifier.GameDetailNotifier;
import ui.notifier.PlayerDetailNotifier;

import java.awt.*;

public class UserInterfaceManager {

    private final PlayerDetailNotifier playerDetailNotifier;

    public UserInterfaceManager(GamePanel gamePanel){
        this.playerDetailNotifier = new PlayerDetailNotifier(gamePanel.getPlayer());
    // Add game notifier related logic
    private final GameDetailNotifier gameDetailNotifier;
        this.gameDetailNotifier = new GameDetailNotifier(true);
    }

    public void notifyGameDetails(String message){
        this.gameDetailNotifier.triggerNotification(message);
    }

    public void draw(Graphics2D graphics2D){
        this.playerDetailNotifier.draw(graphics2D);
        this.gameDetailNotifier.draw(graphics2D);
    }
}
