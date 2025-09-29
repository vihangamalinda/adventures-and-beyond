package entity;

import directionEnum.Direction;
import helper.Constant;
import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helper.Constant.*;
import static helper.PlayerSpriteManager.getPlayerImageByIndex;

public class Player extends Entity {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;


    private ArrayList<String> collectedKeyCode;

    private int screenPositionX;
    private int screenPositionY;
    private static final int movementSpeed = 4;

    public int getScreenPositionX() {
        return screenPositionX;
    }

    public void setScreenPositionX(int screenPositionX) {
        this.screenPositionX = screenPositionX;
    }

    public int getScreenPositionY() {
        return screenPositionY;
    }

    public void setScreenPositionY(int screenPositionY) {
        this.screenPositionY = screenPositionY;
    }

    int frameIndex = 0;
    int counter = 0;


    public Player(int positionX, int positionY, GamePanel gamePanel, KeyHandler keyHandler) {
        super(positionX, positionY, movementSpeed, Direction.FACING_FORWARD, true, new Rectangle(PLAYER_SOLID_AREA_START_X, PLAYER_SOLID_AREA_START_Y, PLAYER_SOLID_AREA_WIDTH, PLAYER_SOLID_AREA_HEIGHT), false);
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.collectedKeyCode = new ArrayList<>();
        this.initializeCentralizeCamera();
    }

    public void update() {
        // handle idle or not
        checkIdleState();

        changeDirection();

        this.gamePanel.getCollisionDetector().checkCollision(this);
        this.gamePanel.getCollisionDetector().checkObjectCollision(this);

//        System.out.println(!this.isOnCollision());
        if (!this.isOnCollision()) {
            performMovement();
        }


        counter++;

    }

    public ArrayList<String> getCollectedKeyCode() {
        return collectedKeyCode;
    }

    public void collectKeyCode(String keyCode) {
        if (!hasKeyCode(keyCode)) {
            this.collectedKeyCode.add(keyCode);
        }
    }

    public void setCollectedKeyCode(ArrayList<String> collectedKeyCode) {
        this.collectedKeyCode = collectedKeyCode;
    }

    public int hasManyKey(){
        return this.collectedKeyCode.size();
    }

    private void checkIdleState() {
        boolean hasUpPressed = keyHandler.upPressed;
        boolean hasDownPressed = keyHandler.downPressed;
        boolean hasLeftPressed = keyHandler.leftPressed;
        boolean hasRightPressed = keyHandler.rightPressed;

        boolean isIdle = !hasUpPressed && !hasDownPressed && !hasLeftPressed && !hasRightPressed;
        setIdle(isIdle);
    }

    private void changeDirection() {
        Direction direction = this.getDirection();
        if (this.keyHandler.upPressed) {
            direction = Direction.FACING_BACKWARD;
        } else if (this.keyHandler.downPressed) {
            direction = Direction.FACING_FORWARD;
        } else if (this.keyHandler.leftPressed) {
            direction = Direction.FACING_LEFTWARD;
        } else if (this.keyHandler.rightPressed) {
            direction = Direction.FACING_RIGHTWARD;
        }
        if (!this.isIdle()) {
            this.setDirection(direction);
        }
    }

    private void performMovement() {

        if (this.keyHandler.upPressed) {
            this.moveUpDirection();
        } else if (this.keyHandler.downPressed) {
            this.moveDownDirection();
        } else if (this.keyHandler.leftPressed) {
            this.moveLeftDirection();
        } else if (this.keyHandler.rightPressed) {
            this.moveRightDirection();
        }
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

        int scaledPlayer = 48 * PLAYER_UP_SCALE;
//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillRect( this.screenPositionX,  this.screenPositionY, scaledPlayer, scaledPlayer);
        BufferedImage image = getPlayerImageByIndex(this.getDirection(), this.isIdle(), this.frameIndex);
//        BufferedImage image =imageArr[index];


//        graphics2D.drawImage(image, this.screenPositionX, this.screenPositionY, scaledPlayer, scaledPlayer, null);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(this.screenPositionX, this.screenPositionY, scaledPlayer, scaledPlayer);

        drawSolidArea(graphics2D);
//        logPlayerCurrentRowAndCol();

//        graphics2D.setColor(Color.WHITE);
//        graphics2D.fillRect(this.getPositionX() +200, this.getPositionY() +300, Helper.TILE_SIZE, Helper.TILE_SIZE);
//        BufferedImage blueImg =Helper.blueImg;
//        graphics2D.drawImage(blueImg,this.getPositionX() +200,this.getPositionY() +300,Helper.TILE_SIZE,Helper.TILE_SIZE,null);
    }

    private void logPlayerCurrentRowAndCol() {
        String message = String.format("Player current (Row,Col):(%d,%d)", getCurrentRowOnWorldMap(), getCurrentColOnWorldMap());
        System.out.println(message);
        int firstRowDrawnOnColumn = (this.getWorldPositionX() - (WINDOW_MAX_SCREEN_HEIGHT) / 2) / TILE_SIZE;
        System.out.println("Should start from row: " + firstRowDrawnOnColumn);
    }

    private void drawSolidArea(Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        Rectangle rectangle = this.getSolidArea();
        graphics2D.fillRect(this.screenPositionX + rectangle.x, this.screenPositionY + rectangle.y, rectangle.width, rectangle.height);
    }

    private void initializeCentralizeCamera() {
        int centerX = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
        int centerY = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
        int playerImgActualCenterY = (Constant.TILE_SIZE / 4) * PLAYER_UP_SCALE;
//        this.screenPositionX = (Constant.WINDOW_MAX_SCREEN_WIDTH) / 2 - (centerX);
//        this.screenPositionY = (Constant.WINDOW_MAX_SCREEN_HEIGHT) / 2 - (centerY + playerImgActualCenterY);
        this.screenPositionX = (Constant.WINDOW_MAX_SCREEN_WIDTH) / 2 - (centerX);
        this.screenPositionY = (Constant.WINDOW_MAX_SCREEN_HEIGHT) / 2 - (centerY);
    }

    private void moveUpDirection() {
        int newPositionY = this.getWorldPositionY() - this.getSpeed();
        this.setWorldPositionY(newPositionY);
    }

    private void moveDownDirection() {
        int newPositionY = this.getWorldPositionY() + this.getSpeed();
        this.setWorldPositionY(newPositionY);
    }

    private void moveLeftDirection() {
        int newPositionX = this.getWorldPositionX() - this.getSpeed();
        this.setWorldPositionX(newPositionX);
    }

    private void moveRightDirection() {
        int newPositionX = this.getWorldPositionX() + this.getSpeed();
        this.setWorldPositionX(newPositionX);
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

    public int getPlayerAbsoluteCenterX() {
        int centerX = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
        return this.getWorldPositionX() + centerX;
    }

    public int getCurrentColOnWorldMap() {
        return (this.getWorldPositionX() + getPlayerCenterX()) / TILE_SIZE;
    }

    public int getCurrentRowOnWorldMap() {
        return (this.getWorldPositionY() + getPlayerCenterY()) / TILE_SIZE;
    }

    private int getPlayerCenterX() {
        return (TILE_SIZE / 2) * PLAYER_UP_SCALE;
    }

    private int getPlayerCenterY() {
        return (TILE_SIZE / 2) * PLAYER_UP_SCALE;
    }

    public Rectangle getSolidAreaWithWorldPositions() {
        Rectangle currentSolidArea = this.getSolidArea();
        int speed = this.getSpeed();
        boolean isMoving = !this.isIdle();

        int x = 0;
        int y = 0;
        // if player is moving its speed should be included
        if (isMoving) {
            switch (this.getDirection()) {
                case FACING_FORWARD -> y = speed;
                case FACING_BACKWARD -> y = -speed;
                case FACING_LEFTWARD -> x = -speed;
                case FACING_RIGHTWARD -> x = speed;
                default ->
                        System.out.println("Custom Error:this.getDirection() runs default case on player.getSolidAreaWithWorldPosition()");
            }
        }

        return new Rectangle(this.getWorldPositionX() + x, getWorldPositionY() + y, currentSolidArea.width, currentSolidArea.height);
    }

    public boolean hasKeyCode(String keyCode) {
        boolean hasKey = false;
        for (String ownKey : this.collectedKeyCode) {
            if (ownKey.equals(keyCode)) {
                hasKey = true;
                break;
            }
        }
        return hasKey;
    }

    public int getPlayerAbsoluteCenterY() {
        int centerY = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
        int playerImgActualCenterY = (Constant.TILE_SIZE / 4) * PLAYER_UP_SCALE;
        return this.getWorldPositionY() + centerY + playerImgActualCenterY;
    }
}
