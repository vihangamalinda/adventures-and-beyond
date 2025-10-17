package ui.notifier;

import java.awt.*;

import static helper.Constant.*;
import static ui.RegisteredFonts.STANDARD_ARIAL;

public class DialogueDetailNotifier extends AbstractDetailNotifier {
    private Font font;

    public DialogueDetailNotifier(boolean isActive) {
        super(isActive);
        this.font = STANDARD_ARIAL;
    }

    @Override
    public void notifyMessage(String message) {
        this.setMessage(message);
        this.setActive(true);
    }

    @Override
    protected void drawBackgroud(Graphics2D graphics2D) {
        graphics2D.setColor(Color.DARK_GRAY);
        int starterX = 1 * TILE_SIZE;
        int starterY = (MAX_SCREEN_ROW - 3) * TILE_SIZE;
        int width = (MAX_SCREEN_COLUMN - 2) * TILE_SIZE;
        graphics2D.fillRect(starterX, starterY, width, TILE_SIZE * 2);
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        if (this.isActive()) {

            this.drawBackgroud(graphics2D);

            graphics2D.setFont(this.font);
            graphics2D.setColor(Color.BLACK);
            int starterX = 2 * TILE_SIZE;
            int starterY = (MAX_SCREEN_ROW - 2) * TILE_SIZE;
            graphics2D.drawString(this.getMessage(), starterX, starterY);
        }
    }


}
