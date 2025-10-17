package ui.notifier;

import helper.CustomColours;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static helper.Constant.*;
import static ui.RegisteredFonts.BOLD_ARIAL_XXL;

public class MainDetailNotifier extends AbstractDetailNotifier {

    private Font font;

    private int backgroundWidth;
    private int backgroundHeight;
    private final int starterX;
    private final int starterY;


    public MainDetailNotifier(boolean isActive) {
        super(isActive, CustomColours.BLACK_01_LOW_OPACITY);
        this.starterX=(WINDOW_MAX_SCREEN_WIDTH / 4) - 20;
        this.starterY=(WINDOW_MAX_SCREEN_HEIGHT / 2) - 100;
    }

    public void triggerNotification(String message) {
        this.setActive(true);
        this.setMessage(message);
        this.setFont(BOLD_ARIAL_XXL);
    }

    private void setFont(Font font) {
        this.font = font;
    }

    public int getStarterX() {
        return starterX;
    }

    public int getStarterY() {
        return starterY;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if (this.isActive()) {
//            this.setFont(BOLD_ARIAL_XXL);
            this.drawBackgroud(graphics2D);

            graphics2D.setFont(this.font);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(this.getMessage(), this.getStarterX()+20, this.getStarterY()+100);
        }
    }

    @Override
    public void notifyMessage(String message) {
        this.setActive(true);
        this.setMessage(message);
        this.setFont(BOLD_ARIAL_XXL);
    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(this.getBackgroundColour());

        configureBackgroundHeightAndWidth(graphics2D);

        graphics2D.fillRect(this.getStarterX(), this.getStarterY(), this.getBackgroundWidth(), this.getBackgroundHeight());

        drawBackgroundBorder(graphics2D);
    }

    private void configureBackgroundHeightAndWidth(Graphics2D graphics2D) {
        Rectangle2D stringBounds = graphics2D.getFontMetrics().getStringBounds(this.getMessage(), graphics2D);
        int textLength = (int) stringBounds.getWidth();
        int textHeight = (int) stringBounds.getHeight();
        graphics2D.fillRect((WINDOW_MAX_SCREEN_WIDTH / 4) - 20, (WINDOW_MAX_SCREEN_HEIGHT / 2) - 100, textLength + 240, textHeight + 100);
        setBackgroundWidth(textLength + 240);
        setBackgroundHeight(textHeight + 100);
    }

    private int getBackgroundWidth() {
        return backgroundWidth;
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public void setBackgroundHeight(int backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
    }

    private void setBackgroundWidth(int backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
    }

    private void resetBackgroundWidthHeight(){
        this.setBackgroundHeight(0);
        this.setBackgroundWidth(0);
    }


    @Override
    protected void drawBackgroundBorder(Graphics2D graphics2D) {
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.setColor(CustomColours.GOLD_01);
        graphics2D.drawRoundRect(this.getStarterX(),this.getStarterY(),this.getBackgroundWidth() ,this.getBackgroundHeight(),10,10);
    }
}
