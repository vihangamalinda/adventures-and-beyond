package ui;

import java.awt.*;

public interface Notifiable {
    void notifyMessageForPeriod(String message,int seconds);
    void notifyMessage(String message);
    void deactivate();

}
