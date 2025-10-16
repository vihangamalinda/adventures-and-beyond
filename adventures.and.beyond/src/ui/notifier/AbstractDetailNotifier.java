package ui.notifier;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class AbstractDetailNotifier implements Notifiable {
    private boolean isActive;
    private String message;

    AbstractDetailNotifier(boolean isActive) {
        this.isActive = isActive;
        this.message ="";
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

    public abstract void draw(Graphics2D graphics2D);

    public void notifyMessageForPeriod(String message, int seconds){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Sheduled task");
            this.deactivate();
        };

        notifyMessage(message);

        scheduledExecutorService.schedule(task, seconds, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
    protected void resetMessage(){
        this.setMessage("");
    }

    public abstract void notifyMessage(String message);

    public  void deactivate(){
        this.setActive(false);
        this.resetMessage();
    };
}
