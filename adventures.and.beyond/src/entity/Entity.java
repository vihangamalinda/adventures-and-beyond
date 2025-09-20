package entity;

import directionEnum.Direction;

public class Entity {
    private int positionX;
    private int positionY;
    private int speed;
    private Direction direction;
    private boolean isIdle;

    public Entity(int positionX, int positionY, int speed,Direction direction,boolean isIdle) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.direction=direction;
        this.isIdle=isIdle;
    }

    public int getPositionX() {
        return positionX;
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

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
