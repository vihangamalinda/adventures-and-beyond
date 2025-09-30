package ui.notifier;

import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.PlAIN_ARIAL_XSM;

public class GameDetailNotifier extends AbstractDetailNotifier{

    private String message;
    public GameDetailNotifier(boolean isActive) {
        super(isActive);
        this.message ="";
    }

    public void triggerNotification(String message){
        this.setMessage(message);
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
