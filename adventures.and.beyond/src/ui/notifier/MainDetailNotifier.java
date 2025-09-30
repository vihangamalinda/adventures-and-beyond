package ui.notifier;

import java.awt.*;

import static helper.Constant.*;
import static ui.RegisteredFonts.BOLD_ARIAL_XXL;

public class MainDetailNotifier extends AbstractDetailNotifier{
    private String message;
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

    public void draw(Graphics2D graphics2D){
        if(this.isActive()){
            graphics2D.setFont(BOLD_ARIAL_XXL);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(this.message,WINDOW_MAX_SCREEN_WIDTH/4,WINDOW_MAX_SCREEN_HEIGHT/2);
        }
    }
}
