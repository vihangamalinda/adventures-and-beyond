package ui.notifier;

import helper.CustomColours;

import java.awt.*;

import static helper.Constant.*;
import static ui.RegisteredFonts.STANDARD_ARIAL;

public class DialogueDetailNotifier extends AbstractDetailNotifier {
    private Font font;

    private int startX;
    private int startY;
    private int width;
    private int height;

    public DialogueDetailNotifier(boolean isActive) {
        super(isActive, CustomColours.BLACK_01_LOW_OPACITY);
        this.font = STANDARD_ARIAL;
        this.configureBackgroundSize();
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void configureBackgroundSize() {
        int starterX = 1 * TILE_SIZE;
        int starterY = (MAX_SCREEN_ROW - 3) * TILE_SIZE;
        int width = (MAX_SCREEN_COLUMN - 2) * TILE_SIZE;
        int height = TILE_SIZE * 2;

        this.setStartX(starterX);
        this.setStartY(starterY);
        this.setWidth(width);
        this.setHeight(height);
    }

    @Override
    public void notifyMessage(String message) {
        this.setMessage(message);
        this.setActive(true);
    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(this.getBackgroundColour());

        graphics2D.fillRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight());

        this.drawBackgroundBorder(graphics2D);
    }

    @Override
    protected void drawBackgroundBorder(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setColor(CustomColours.GOLD_01);

        graphics2D.drawRoundRect(this.getStartX(), this.getStartY(), this.getWidth(), this.getHeight(),10,10);
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        if (this.isActive()) {

            this.drawBackgroud(graphics2D);
            graphics2D.setFont(this.font);
            graphics2D.setColor(Color.BLACK);
            int starterX =this.getStartX() +TILE_SIZE;
            int starterY = this.getStartY()+TILE_SIZE;
            graphics2D.drawString(this.getMessage(), starterX, starterY);
        }
    }


}
