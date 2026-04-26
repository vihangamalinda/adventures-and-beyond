package entity.player;


import animation.service.player.PlayerAnimationService;
import animation.service.player.PlayerAnimationServiceImpl;
import collision.detector.service.PlayerCollisionService;
import collision.detector.service.PlayerCollisionServiceImpl;
import directionEnum.Direction;
import drawable.service.player.PlayerDrawService;
import drawable.service.player.PlayerDrawServiceImpl;
import entity.Entity;
import helper.Constant;
import input.KeyHandler;
import input.model.player.RegisteredPlayerInput;
import input.reader.PlayerInputReader;
import input.reader.PlayerInputReaderImpl;
import input.state.ReadKeyState;
import movement.player.PlayerMovementService;
import movement.player.PlayerMovementServiceImpl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static entity.player.PlayerConstant.*;
import static helper.Constant.*;

public class Player extends Entity {

    private ArrayList<String> collectedKeyCode;
    private int screenPositionX;
    private int screenPositionY;
    private static final int movementSpeed = 4;


    private int frameIndex = 0;
    private int counter = 0;
    private BufferedImage currentPlayerImage;
    private final PlayerMovementService playerMovementService;

    private final PlayerInputReader playerInputReader;
    private final PlayerAnimationService playerAnimationService;
    private final PlayerDrawService playerDrawService;
    private final PlayerCollisionService playerCollisionService;


    public Player(int positionX,
                  int positionY) {
        super(positionX,
              positionY,
              movementSpeed,
              Direction.FACING_FORWARD,
              true,
              new Rectangle(PLAYER_SOLID_AREA_START_X,
                            PLAYER_SOLID_AREA_START_Y,
                            PLAYER_SOLID_AREA_WIDTH,
                            PLAYER_SOLID_AREA_HEIGHT),
              false);
        this.collectedKeyCode = new ArrayList<>();
        this.initializeCentralizeCamera();
        this.playerMovementService = new PlayerMovementServiceImpl();
        ReadKeyState readKeyState = KeyHandler.getInstance().readRegisteredKeyState();
        this.playerInputReader = new PlayerInputReaderImpl(readKeyState);
        this.playerAnimationService = new PlayerAnimationServiceImpl();
        this.playerDrawService = new PlayerDrawServiceImpl();
        this.playerCollisionService = new PlayerCollisionServiceImpl();
    }

    public void update() {
        RegisteredPlayerInput registeredPlayerInput = this.playerInputReader.readPlayerInput();
        this.playerMovementService.updateDirectionAndIdleState(this,
                                                               registeredPlayerInput);
        this.playerCollisionService.checkCollision(this);
        this.playerMovementService.updateWorldPosition(this,
                                                       registeredPlayerInput);
        this.playerAnimationService.updateAnimation(this);
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

    public BufferedImage getCurrentPlayerImage() {
        return this.currentPlayerImage;
    }

    public void setCurrentPlayerImage(BufferedImage currentPlayerImage) {
        this.currentPlayerImage = currentPlayerImage;
    }

    public void draw(Graphics2D graphics2D) {
        this.playerDrawService.draw(graphics2D,
                                    this);
    }


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

    @Override
    public void setWorldPositionX(int worldPositionX) {
        boolean isWithinRange = worldPositionX - WINDOW_MAX_SCREEN_WIDTH / 2 > 0 && worldPositionX + WINDOW_MAX_SCREEN_WIDTH / 2 < WORLD_MAP_WIDTH;
        if (isWithinRange) {
            super.setWorldPositionX(worldPositionX);
        }
    }

    public int getCounter() {
        return this.counter;
    }

    public int getFrameIndex() {
        return this.frameIndex;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setFrameIndex(int frameIndex) {
        this.frameIndex = frameIndex;
    }

    @Override
    public void setWorldPositionY(int worldPositionY) {
        boolean isWithinRange = worldPositionY - WINDOW_MAX_SCREEN_HEIGHT / 2 > 0 && worldPositionY + WINDOW_MAX_SCREEN_HEIGHT / 2 < WORLD_MAP_HEIGHT;
        if (isWithinRange) {
            super.setWorldPositionY(worldPositionY);
        }
    }

    private void logPlayerCurrentRowAndCol() {
        String message = String.format("Player current (Row,Col):(%d,%d)",
                                       getCurrentRowOnWorldMap(),
                                       getCurrentColOnWorldMap());
        System.out.println(message);
        int firstRowDrawnOnColumn = (this.getWorldPositionX() - (WINDOW_MAX_SCREEN_HEIGHT) / 2) / TILE_SIZE;
        System.out.println("Should start from row: " + firstRowDrawnOnColumn);
    }

    private void logPlayerScreenColRow() {
        String message = String.format("Player Screen (Row,Col):(%d,%d)",
                                       this.getPlayerAbsoluteScreenY() / TILE_SIZE,
                                       this.getPlayerAbsoluteScreenX() / TILE_SIZE);
        System.out.println(message);
    }

    private void initializeCentralizeCamera() {
        this.screenPositionX = (Constant.WINDOW_MAX_SCREEN_WIDTH) / 2 - (this.getPlayerCenterX());
        this.screenPositionY = (Constant.WINDOW_MAX_SCREEN_HEIGHT) / 2 - (this.getPlayerCenterY());
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


        return new Rectangle(this.getPlayerAbsoluteWorldPositionX() + x,
                             this.getPlayerAbsoluteWorldPositionY() + y,
                             currentSolidArea.width,
                             currentSolidArea.height);
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
