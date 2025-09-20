package entity;

import helper.Helper;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(int positionX, int positionY, int speed, GamePanel gamePanel, KeyHandler keyHandler) {
        super(positionX, positionY, speed);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    public void update() {
        if (keyHandler.upPressed) {
            this.moveUpDirection();
        } else if (keyHandler.downPressed) {
            this.moveDownDirection();
        } else if (keyHandler.leftPressed) {
            this.moveLeftDirection();
        } else if (keyHandler.rightPressed) {
            this.moveRightDirection();
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.getPositionX(), this.getPositionY(), Helper.TILE_SIZE, Helper.TILE_SIZE);
    }

    private void moveUpDirection() {
        int newPositionY = this.getPositionY() - this.getSpeed();
        this.setPositionY(newPositionY);
    }

    private void moveDownDirection() {
        int newPositionY = this.getPositionY() + this.getSpeed();
        this.setPositionY(newPositionY);
    }

    private void moveLeftDirection() {
        int newPositionX = this.getPositionX() - this.getSpeed();
        this.setPositionX(newPositionX);
    }

    private void moveRightDirection() {
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
