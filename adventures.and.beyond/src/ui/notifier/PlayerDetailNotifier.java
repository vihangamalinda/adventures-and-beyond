package ui.notifier;

import entity.Player;

import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.STANDARD_ARIAL;
import static ui.UserInterfaceIcons.ICON_KEY_IMAGE;

public class PlayerDetailNotifier {

    private final Player player;
    public PlayerDetailNotifier(Player player){
        this.player =player;
    }

    public void draw(Graphics2D graphics2D){
        graphics2D.setFont(STANDARD_ARIAL);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawImage(ICON_KEY_IMAGE,TILE_SIZE/4,TILE_SIZE/2,null);
        String message = "x "+this.player.hasManyKey();
        graphics2D.drawString(message,70,60);

    }
}
