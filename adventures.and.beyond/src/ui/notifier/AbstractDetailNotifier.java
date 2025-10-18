package ui.notifier;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDetailNotifier implements Notifiable, DrawableNotifier {
    private boolean isActive;
    private String message;
    private Color backgroundColour;
    private Font font;

    AbstractDetailNotifier(boolean isActive, Color backgroundColour, Font font) {
        this.isActive = isActive;
        this.message = "";
        this.backgroundColour = backgroundColour;
        this.font = font;
    }

    protected Font getFont() {
        return font;
    }

    protected void setFont(Font font) {
        this.font = font;
    }

    protected Color getBackgroundColour() {
        return this.backgroundColour;
    }

    protected String getMessage() {
        return message;
    }

    protected void setMessage(String message) {
        this.message = message;
    }

    protected boolean isActive() {
        return isActive;
    }

    protected void setActive(boolean active) {
        isActive = active;
    }

    // need to have a method to set background size of the canvas on that message should be displayed (opacity should be considered)
    protected abstract void drawBackgroud(Graphics2D graphics2D);

    protected abstract void drawBackgroundBorder(Graphics2D graphics2D);

    protected abstract void drawContent(Graphics2D graphics2D);


    public void draw(Graphics2D graphics2D) {
        if (this.isActive()) {
            graphics2D.setFont(this.getFont());
            this.drawBackgroud(graphics2D);
            this.drawContent(graphics2D);
            this.drawBackgroundBorder(graphics2D);
        }
    }

    public void notifyMessageForPeriod(String message, int seconds) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Sheduled task");
            this.deactivate();
        };

        notifyMessage(message);

        scheduledExecutorService.schedule(task, seconds, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }

    protected void resetMessage() {
        this.setMessage("");
    }

    protected void resetAttributes() {
        this.resetMessage();
    }

    public abstract void notifyMessage(String message);

    public void deactivate() {
        this.setActive(false);
        this.resetAttributes();
    }

    ;
}
