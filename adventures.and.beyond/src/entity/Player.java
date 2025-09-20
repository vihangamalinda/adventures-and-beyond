package entity;

import helper.Helper;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;

    public Player(int positionX, int positionY, int speed, GamePanel gamePanel, KeyHandler keyHandler) {
        super(positionX, positionY, speed,Direction.FACING_FORWARD,true);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
    }

    public void update() {
        boolean hasUpPressed =keyHandler.upPressed;
        boolean hasDownPressed = keyHandler.downPressed;
        boolean hasLeftPressed = keyHandler.leftPressed;
        boolean hasRightPressed = keyHandler.rightPressed;

        boolean isIdle =!hasUpPressed && !hasDownPressed && !hasLeftPressed && !hasRightPressed;
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
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.getPositionX(), this.getPositionY(), Helper.TILE_SIZE, Helper.TILE_SIZE);
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
