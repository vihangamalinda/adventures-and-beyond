package entity;

import directionEnum.Direction;

import java.awt.*;

import static helper.Constant.*;

public class Entity {
    private int worldPositionX;
    private int worldPositionY;
    private int speed;
    private Direction direction;
    private boolean isIdle;
    private Rectangle solidArea;
    private boolean onCollision;

    public Entity(int worldPositionX, int worldPositionY, int speed, Direction direction, boolean isIdle,Rectangle solidArea,boolean onCollision) {
        this.worldPositionX = worldPositionX;
        this.worldPositionY = worldPositionY;
        this.speed = speed;
        this.direction = direction;
        this.isIdle = isIdle;
        this.solidArea = solidArea;
        this.onCollision =onCollision;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public boolean isOnCollision() {
        return onCollision;
    }

    public int getWorldPositionX() {
        return worldPositionX;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public void setWorldPositionX(int worldPositionX) {
        boolean isWithinRange = worldPositionX - WINDOW_MAX_SCREEN_WIDTH / 2 > 0 && worldPositionX + WINDOW_MAX_SCREEN_WIDTH / 2 < WORLD_MAP_WIDTH;
        if (isWithinRange) {
            this.worldPositionX = worldPositionX;
        }

    }

    public int getWorldPositionY() {
        return worldPositionY;
    }

    public void setWorldPositionY(int worldPositionY) {
        boolean isWithinRange = worldPositionY - WINDOW_MAX_SCREEN_HEIGHT / 2 > 0 && worldPositionY + WINDOW_MAX_SCREEN_HEIGHT / 2 < WORLD_MAP_HEIGHT;
        if (isWithinRange) {
            this.worldPositionY = worldPositionY;
        }

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
