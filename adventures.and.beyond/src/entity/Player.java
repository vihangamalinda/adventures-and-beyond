package entity;

import directionEnum.Direction;
import helper.Helper;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

import static helper.PlayerSpriteManager.getPlayerImageByIndex;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    int frameIndex = 0;
    int counter = 0;

    public Player(int positionX, int positionY, int speed, GamePanel gamePanel, KeyHandler keyHandler) {
        super(positionX, positionY, speed, Direction.FACING_FORWARD, true);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    public void update() {
        boolean hasUpPressed = keyHandler.upPressed;
        boolean hasDownPressed = keyHandler.downPressed;
        boolean hasLeftPressed = keyHandler.leftPressed;
        boolean hasRightPressed = keyHandler.rightPressed;

        boolean isIdle = !hasUpPressed && !hasDownPressed && !hasLeftPressed && !hasRightPressed;
        setIdle(isIdle);

        if (hasUpPressed) {
            this.moveUpDirection();
        } else if (hasDownPressed) {
            this.moveDownDirection();
        } else if (hasLeftPressed) {
            this.moveLeftDirection();
        } else if (hasRightPressed) {
            this.moveRightDirection();
        }

        counter++;

    }

    public void draw(Graphics2D graphics2D) {
        if (counter % 30 == 0) {
            if (frameIndex >= 5) {
                frameIndex = 0;
            } else {
                frameIndex++;
            }
            counter = 0;
        }

        int scaledPlayer = 48 * 2;
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.getPositionX(), this.getPositionY(), scaledPlayer, scaledPlayer);
        BufferedImage image = getPlayerImageByIndex(this.getDirection(), this.isIdle(), this.frameIndex);
//        BufferedImage image =imageArr[index];


        graphics2D.drawImage(image, this.getPositionX(), this.getPositionY(), scaledPlayer, scaledPlayer, null);


//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillRect(this.getPositionX() +200, this.getPositionY() +300, Helper.TILE_SIZE, Helper.TILE_SIZE);
//        BufferedImage blueImg =Helper.blueImg;
//        graphics2D.drawImage(blueImg,this.getPositionX() +200,this.getPositionY() +300,Helper.TILE_SIZE,Helper.TILE_SIZE,null);
    }

    private void moveUpDirection() {
        this.setDirection(Direction.FACING_BACKWARD);
        int newPositionY = this.getPositionY() - this.getSpeed();
        this.setPositionY(newPositionY);
    }

    private void moveDownDirection() {
        this.setDirection(Direction.FACING_FORWARD);
        int newPositionY = this.getPositionY() + this.getSpeed();
        this.setPositionY(newPositionY);
    }

    private void moveLeftDirection() {
        this.setDirection(Direction.FACING_LEFTWARD);
        int newPositionX = this.getPositionX() - this.getSpeed();
        this.setPositionX(newPositionX);
    }

    private void moveRightDirection() {
        this.setDirection(Direction.FACING_RIGHTWARD);
        int newPositionX = this.getPositionX() + this.getSpeed();
        this.setPositionX(newPositionX);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }
}
