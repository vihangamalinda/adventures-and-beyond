package entity;

import directionEnum.Direction;

public class Entity {
    private int worldPositionX;
    private int worldPositionY;
    private int speed;
    private Direction direction;
    private boolean isIdle;

    public Entity(int worldPositionX, int worldPositionY, int speed, Direction direction, boolean isIdle) {
        this.worldPositionX = worldPositionX;
        this.worldPositionY = worldPositionY;
        this.speed = speed;
        this.direction=direction;
        this.isIdle=isIdle;
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
        this.worldPositionX = worldPositionX;
    }

    public int getWorldPositionY() {
        return worldPositionY;
    }

    public void setWorldPositionY(int worldPositionY) {
        this.worldPositionY = worldPositionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
