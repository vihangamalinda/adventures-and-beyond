package drawable.service.player;

import entity.player.Player;

import java.awt.*;

import static entity.player.PlayerConstant.PLAYER_UP_SCALE;

public class PlayerDrawServiceImpl implements PlayerDrawService {

    public PlayerDrawServiceImpl() {
    }

    @Override
    public void draw(Graphics2D graphics2D,
                     Player player) {
        // Implement the logic to draw the player on the screen
        // This may involve rendering the player's sprite, animations, and any associated effects
        int scaledPlayer = 48 * PLAYER_UP_SCALE;
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(player.getScreenPositionX(),
                            player.getScreenPositionY(),
                            scaledPlayer,
                            scaledPlayer);
        graphics2D.drawImage(player.getCurrentPlayerImage(),
                             player.getScreenPositionX(),
                             player.getScreenPositionY(),
                             null);
        drawSolidArea(graphics2D,
                      player);
    }

    private void drawSolidArea(Graphics2D graphics2D,
                               Player player) {
        graphics2D.setColor(Color.RED);
        Rectangle rectangle = player.getSolidArea();
        graphics2D.drawRect(player.getScreenPositionX() + rectangle.x,
                            player.getScreenPositionY() + rectangle.y,
                            rectangle.width,
                            rectangle.height);
    }
/*
    @Override
    public void draw(Graphics2D graphics2D,Player player) {
        // Implement the logic to draw the player on the screen
        // This may involve rendering the player's sprite, animations, and any associated effects

        //        if (counter % 30 == 0) {
//            if (frameIndex >= 5) {
//                frameIndex = 0;
//            } else {
//                frameIndex++;
//            }
//            counter = 0;
//        }

        int scaledPlayer = 48 * PLAYER_UP_SCALE;
//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillRect( this.screenPositionX,  this.screenPositionY, scaledPlayer, scaledPlayer);
//        BufferedImage image = getPlayerImageByIndex(this.getDirection(), this.isIdle(), this.frameIndex);
//        BufferedImage image =imageArr[index];


//        graphics2D.drawImage(image, this.screenPositionX, this.screenPositionY, scaledPlayer, scaledPlayer, null);
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(player.getScreenPositionX(), player.getScreenPositionY(), scaledPlayer, scaledPlayer);

//        graphics2D.drawImage(image, this.screenPositionX, this.screenPositionY, null);
        graphics2D.drawImage(player.getCurrentPlayerImage(), player.getScreenPositionX(), player.getScreenPositionY(), null);

        drawSolidArea(graphics2D,player);
//        logPlayerCurrentRowAndCol();
//        logPlayerScreenColRow();

//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillRect(this.getPositionX() +200, this.getPositionY() +300, Helper.TILE_SIZE, Helper.TILE_SIZE);
//        BufferedImage blueImg =Helper.blueImg;
//        graphics2D.drawImage(blueImg,this.getPositionX() +200,this.getPositionY() +300,Helper.TILE_SIZE,Helper.TILE_SIZE,null);
    }
 */

}
