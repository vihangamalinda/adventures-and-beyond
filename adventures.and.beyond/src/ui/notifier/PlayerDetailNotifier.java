package ui.notifier;

import entity.manager.EntityManagerFactory;
import entity.player.Player;
import helper.CustomColours;

import java.awt.*;

import static helper.Constant.TILE_SIZE;
import static ui.RegisteredFonts.STANDARD_ARIAL;
import static ui.UserInterfaceIcons.ICON_KEY_IMAGE;

public class PlayerDetailNotifier extends AbstractDetailNotifier {

    private final Player player;
    private final int starterX;
    private final int starterY;
    private final int width;
    private final int height;

    public PlayerDetailNotifier(boolean isActive) {
        super(isActive,CustomColours.BLACK_01_LOW_OPACITY,STANDARD_ARIAL);
        this.player = EntityManagerFactory.getInstance().getPlayer();
        this.starterX=10;
        this.starterY=10;
        this.width =TILE_SIZE * 4;
        this.height =TILE_SIZE * 2;
    }

    private void updatedMessage() {
        String message = "x " + this.player.hasManyKey();
        notifyMessage(message);
    }

    @Override
    public void notifyMessage(String message) {
        this.setMessage(message);
    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(this.getBackgroundColour());
        graphics2D.fillRect(this.getStarterX(), this.getStarterY(), this.getWidth(), this.getHeight());
    }

    public int getStarterX() {
        return starterX;
    }

    public int getStarterY() {
        return starterY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    protected void drawBackgroundBorder(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setColor(CustomColours.GOLD_01);
        graphics2D.drawRoundRect(this.getStarterX(),this.getStarterY(),this.getWidth()+5,this.getHeight()+5,10,10);
    }

    @Override
    protected void drawContent(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawImage(ICON_KEY_IMAGE, TILE_SIZE / 4, TILE_SIZE / 2, null);
        updatedMessage();
        graphics2D.drawString(this.getMessage(), this.starterX+60, this.starterY+50);
    }
}
