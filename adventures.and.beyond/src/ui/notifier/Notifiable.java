package ui.notifier;

import java.awt.*;

public interface Notifiable {
    void notifyMessageForPeriod(String message,int seconds);
    void notifyMessage(String message);
    void draw(Graphics2D graphics2D);
    void deactivate();

}
