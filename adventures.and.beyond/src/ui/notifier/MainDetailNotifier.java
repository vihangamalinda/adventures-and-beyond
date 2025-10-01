package ui.notifier;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static helper.Constant.*;
import static ui.RegisteredFonts.BOLD_ARIAL_XXL;

public class MainDetailNotifier extends AbstractDetailNotifier{
    private String message;
    private Font font;

    public MainDetailNotifier(boolean isActive) {
        super(isActive);
    }

    public void triggerNotification(String message){
        this.setActive(true);
        this.setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }
    private void setFont(Font font){
        this.font=font;
    }

    @Override
    public void draw(Graphics2D graphics2D){
        if(this.isActive()){
            this.setFont(BOLD_ARIAL_XXL);
            this.drawBackgroud(graphics2D);

            graphics2D.setFont(this.font);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(this.message,WINDOW_MAX_SCREEN_WIDTH/4,WINDOW_MAX_SCREEN_HEIGHT/2);
        }
    }
    @Override
    public void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        Rectangle2D stringBounds = graphics2D.getFontMetrics().getStringBounds(this.message, graphics2D);
        int textLength = (int) stringBounds.getWidth();
        int textHeight =(int) stringBounds.getHeight();
        graphics2D.fillRect((WINDOW_MAX_SCREEN_WIDTH/4) -20,(WINDOW_MAX_SCREEN_HEIGHT/2 )-100,textLength+240,textHeight+100);
    }
}
