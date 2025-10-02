package entity;

import directionEnum.Direction;
import helper.Constant;
import main.CollisionDetector;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static entity.PlayerConstant.*;
import static helper.Constant.*;
import static entity.PlayerSpriteManager.getPlayerImageByIndex;

public class Player extends Entity {
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


    public Player(int positionX, int positionY) {
        super(positionX, positionY, movementSpeed, Direction.FACING_FORWARD, true, new Rectangle(PLAYER_SOLID_AREA_START_X, PLAYER_SOLID_AREA_START_Y, PLAYER_SOLID_AREA_WIDTH, PLAYER_SOLID_AREA_HEIGHT), false);
        this.collectedKeyCode = new ArrayList<>();
        this.initializeCentralizeCamera();
    }

    public void update() {
        // handle idle or not
        checkIdleState();

        changeDirection();

        CollisionDetector collisionDetector = CollisionDetector.getInstance();
        collisionDetector.checkCollision(this);
        collisionDetector.checkObjectCollision(this);

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

    public int hasManyKey() {
        return this.collectedKeyCode.size();
    }

    public void removeKey(String keyCode) {
        if (this.hasKeyCode(keyCode)) {
            this.collectedKeyCode.remove(keyCode);
        }
    }

    private void checkIdleState() {
        KeyHandler keyHandler = KeyHandler.getInstance();
        boolean hasUpPressed = keyHandler.isUpPressed();
        boolean hasDownPressed = keyHandler.isDownPressed();
        boolean hasLeftPressed = keyHandler.isLeftPressed();
        boolean hasRightPressed = keyHandler.isRightPressed();

        boolean isIdle = !hasUpPressed && !hasDownPressed && !hasLeftPressed && !hasRightPressed;
        setIdle(isIdle);
    }

    private void changeDirection() {
        Direction direction = this.getDirection();
        KeyHandler keyHandler = KeyHandler.getInstance();

        if (keyHandler.isUpPressed()) {
            direction = Direction.FACING_BACKWARD;
        } else if (keyHandler.isDownPressed()) {
            direction = Direction.FACING_FORWARD;
        } else if (keyHandler.isLeftPressed()) {
            direction = Direction.FACING_LEFTWARD;
        } else if (keyHandler.isRightPressed()) {
            direction = Direction.FACING_RIGHTWARD;
        }
        if (!this.isIdle()) {
            this.setDirection(direction);
        }
    }

    private void performMovement() {
        KeyHandler keyHandler = KeyHandler.getInstance();
        if (keyHandler.isUpPressed()) {
            this.moveUpDirection();
        } else if (keyHandler.isDownPressed()) {
            this.moveDownDirection();
        } else if (keyHandler.isLeftPressed()) {
            this.moveLeftDirection();
        } else if (keyHandler.isRightPressed()) {
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
        graphics2D.drawRect(this.screenPositionX, this.screenPositionY, scaledPlayer, scaledPlayer);

        graphics2D.drawImage(image, this.screenPositionX, this.screenPositionY, null);
        drawSolidArea(graphics2D);
//        logPlayerCurrentRowAndCol();
//        logPlayerScreenColRow();

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

    private void logPlayerScreenColRow() {
        String message = String.format("Player Screen (Row,Col):(%d,%d)", this.getPlayerAbsoluteScreenY() / TILE_SIZE, this.getPlayerAbsoluteScreenX() / TILE_SIZE);
        System.out.println(message);
    }

    private void drawSolidArea(Graphics2D graphics2D) {
        graphics2D.setColor(Color.RED);
        Rectangle rectangle = this.getSolidArea();
        graphics2D.drawRect(this.screenPositionX + rectangle.x, this.screenPositionY + rectangle.y, rectangle.width, rectangle.height);
    }

    private void initializeCentralizeCamera() {
        this.screenPositionX = (Constant.WINDOW_MAX_SCREEN_WIDTH) / 2 - (this.getPlayerCenterX());
        this.screenPositionY = (Constant.WINDOW_MAX_SCREEN_HEIGHT) / 2 - (this.getPlayerCenterY());
//        this.screenPositionX = (Constant.WINDOW_MAX_SCREEN_WIDTH) / 2 - (centerX);
//        this.screenPositionY = (Constant.WINDOW_MAX_SCREEN_HEIGHT) / 2 - (centerY);
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

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

    public void setKeyHandler(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
    }

//    public int getPlayerAbsoluteCenterX() {
//        int centerX = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
//        return this.getWorldPositionX() + centerX;
//    }

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
        int playerImgActualCenterY = (Constant.TILE_SIZE / 4) * PLAYER_UP_SCALE;
        return ((TILE_SIZE / 2) * PLAYER_UP_SCALE) + playerImgActualCenterY;
    }

    public int getPlayerAbsoluteScreenX() {
        return this.screenPositionX + this.getPlayerCenterX();
    }

    public int getPlayerAbsoluteScreenY() {
        return this.screenPositionY + this.getPlayerCenterY();
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


        return new Rectangle(this.getPlayerAbsoluteWorldPositionX() + x, this.getPlayerAbsoluteWorldPositionY() + y, currentSolidArea.width, currentSolidArea.height);
    }

    private int getPlayerAbsoluteWorldPositionY() {
        return this.getWorldPositionY() + this.getPlayerCenterY();
    }

    private int getPlayerAbsoluteWorldPositionX() {
        return this.getWorldPositionX() + this.getPlayerCenterX();
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

//    public int getPlayerAbsoluteCenterY() {
//        int centerY = (Constant.TILE_SIZE / 2) * PLAYER_UP_SCALE;
//        int playerImgActualCenterY = (Constant.TILE_SIZE / 4) * PLAYER_UP_SCALE;
//        return this.getWorldPositionY() + centerY + playerImgActualCenterY;
//    }
}
