package ui.notifier;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.PlAIN_ARIAL_XSM;

public class GameDetailNotifier extends AbstractDetailNotifier{
    private Font font;

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

    private void setFont(Font font){
        this.font =font;
    }

    private void setMessage(String message){
        this.message=message;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        if(this.isActive()){
            this.setFont(PlAIN_ARIAL_XSM);
            graphics2D.setFont(this.font);

            drawBackgroud(graphics2D);


            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(this.message,TILE_SIZE/3,TILE_SIZE*4);
        }

    }
    @Override
    public void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        Rectangle2D stringBounds = graphics2D.getFontMetrics().getStringBounds(this.message, graphics2D);
        int textLength = (int) stringBounds.getWidth();
        int textHeight =(int) stringBounds.getHeight();
        graphics2D.fillRect(10,(TILE_SIZE*4)-30,textLength+20,textHeight+40);
    }

}
