package ui.notifier;

import helper.CustomColours;

import java.awt.*;
import java.awt.geom.Rectangle2D;


import static helper.Constant.*;
import static ui.RegisteredFonts.PlAIN_ARIAL_XSM;

public class GameDetailNotifier extends AbstractDetailNotifier {
    private Font font;

    private int backgroundHeight;
    private int backgroundWidth;
    private final int starterX;
    private final int starterY;



    public GameDetailNotifier(boolean isActive) {

        super(isActive, CustomColours.BLACK_01_LOW_OPACITY);
        this.starterX=10;
        this.starterY=(TILE_SIZE * 4) - 30;
    }

    private void triggerNotification(String message) {
        this.setMessage(message);
    }

    @Override
    public void notifyMessage(String message) {
        this.setMessage(message);
        this.setActive(true);
    }

    private void setFont(Font font) {
        this.font = font;
    }

    protected int getStarterX() {
        return starterX;
    }

    protected int getStarterY() {
        return starterY;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (this.isActive()) {
            this.setFont(PlAIN_ARIAL_XSM);
            graphics2D.setFont(this.font);

            drawBackgroud(graphics2D);


            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(this.getMessage(), this.getStarterX()+6, this.getStarterY()+30);
        }

    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(this.getBackgroundColour());

        configureBackgroundHeightAndWidth(graphics2D);

        graphics2D.fillRect(this.getStarterX(), this.getStarterY(), this.getBackgroundWidth(), this.getBackgroundHeight());

        this.drawBackgroundBorder(graphics2D);
    }

    private void configureBackgroundHeightAndWidth(Graphics2D graphics2D) {
        Rectangle2D stringBounds = graphics2D.getFontMetrics().getStringBounds(this.getMessage(), graphics2D);
        int textLength = (int) stringBounds.getWidth();
        int textHeight = (int) stringBounds.getHeight();
        this.setBackgroundWidth(textLength + 20);
        this.setBackgroundHeight(textHeight + 40);
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public void setBackgroundHeight(int backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
    }

    public int getBackgroundWidth() {
        return backgroundWidth;
    }

    public void setBackgroundWidth(int backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
    }

    private void resetBackgroundWidthHeight(){
        this.setBackgroundHeight(0);
        this.setBackgroundWidth(0);
    }

    @Override
    protected void resetAttributes() {
        super.resetAttributes();
        this.resetBackgroundWidthHeight();
    }


    @Override
    protected void drawBackgroundBorder(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setColor(CustomColours.GOLD_01);

        graphics2D.drawRoundRect(this.getStarterX(),this.getStarterY(),this.getBackgroundWidth() ,this.getBackgroundHeight(),10,10);
    }

}
