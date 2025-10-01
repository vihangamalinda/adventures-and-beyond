package ui.notifier;

import java.awt.*;

public abstract class AbstractDetailNotifier {
    private boolean isActive;

    AbstractDetailNotifier(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    // need to have a method to set background size of the canvas on that message should be displayed (opacity should be considered)
    public abstract void drawBackgroud(Graphics2D graphics2D);

    public abstract void draw(Graphics2D graphics2D);
}
