package ui;

import main.GamePanel;
import ui.notifier.GameDetailNotifier;
import ui.notifier.MainDetailNotifier;
import ui.notifier.PlayerDetailNotifier;

import java.awt.*;

public class UserInterfaceManager {

    private final PlayerDetailNotifier playerDetailNotifier;

    // Add game notifier related logic
    private final GameDetailNotifier gameDetailNotifier;

    private final MainDetailNotifier mainDetailNotifier;

    private static class Holder{
        private static final UserInterfaceManager INSTANCE = new UserInterfaceManager();
    }

    private UserInterfaceManager(){
        this.playerDetailNotifier = new PlayerDetailNotifier(true);
        this.gameDetailNotifier = new GameDetailNotifier(false);
        this.mainDetailNotifier =new MainDetailNotifier(false);
    }

    public static UserInterfaceManager getInstance(){
        return Holder.INSTANCE;
    }

    public void notifyGameDetails(String message){
        this.gameDetailNotifier.notifyForPeriod(message,1);
    }

    public void triggerMainNotification(String message){
        this.mainDetailNotifier.triggerNotification(message);
    }

    public void draw(Graphics2D graphics2D){
        this.playerDetailNotifier.draw(graphics2D);
        this.gameDetailNotifier.draw(graphics2D);
        this.mainDetailNotifier.draw(graphics2D);
    }
}
