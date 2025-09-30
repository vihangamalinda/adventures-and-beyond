package ui.notifier;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.PlAIN_ARIAL_XSM;

public class GameDetailNotifier extends AbstractDetailNotifier{

    private String message;
    public GameDetailNotifier(boolean isActive) {
        super(isActive);
        this.message ="";
    }

    private void triggerNotification(String message){
        this.setMessage(message);
    }

    public void notifyForPeriod(String message, int seconds){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Sheduled task");
            this.setActive(false);
            this.message ="";
        };


        this.message = message;
        this.setActive(true);

        scheduledExecutorService.schedule(task,seconds, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

    }

    private void setMessage(String message){
        this.message=message;
    }

    public void draw(Graphics2D graphics2D){
        if(this.isActive()){
            graphics2D.setFont(PlAIN_ARIAL_XSM);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(this.message,TILE_SIZE/4,TILE_SIZE*3);
        }

    }
}
