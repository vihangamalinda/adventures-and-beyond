package entity.npc;

import directionEnum.Direction;
import entity.Entity;
import entity.player.Player;
import helper.DrawHelper;
import main.CollisionDetector;

import java.awt.*;
import java.awt.image.BufferedImage;

import static directionEnum.Direction.*;
import static directionEnum.Direction.FACING_LEFTWARD;
import static helper.Constant.TILE_SIZE;

public abstract class NonPlayerCharacter extends Entity {

    private final String characterAnimationKey;
    private boolean isOnCollisionWithPlayer;

    private int frameCounter=0;
    private int currentFrameIndex=0;

    public NonPlayerCharacter(int worldPositionX, int worldPositionY, int speed, Direction direction, boolean isIdle, Rectangle solidArea, boolean onCollision, String characterAnimationKey) {
        super(worldPositionX, worldPositionY, speed, direction, isIdle, solidArea, onCollision);
        this.characterAnimationKey = characterAnimationKey;
        this.isOnCollisionWithPlayer =false;
    }

    public void update(){
        checkForCollisions();

        if (shouldUpdatePosition()) {
            updatePosition();
        }

        if (shouldChangeDirection()) {
            changeDirection();
            this.resetFrameCounter();
        }

        if(!isOnCollisionWithPlayer()){
            this.frameCounter++;
        }
    }

    public String getCharacterAnimationKey() {
        return this.characterAnimationKey;
    }

    private boolean isOnCollisionWithPlayer() {
        return isOnCollisionWithPlayer;
    }


    protected void setOnCollisionWithPlayer(boolean onCollisionWithPlayer) {
        isOnCollisionWithPlayer = onCollisionWithPlayer;
    }


    public void associateWithPlayer(){
        this.setOnCollisionWithPlayer(true);
    }
    public void disassociateWithPlayer(){
        this.setOnCollisionWithPlayer(false);
    }

    public Rectangle getSolidAreaWithWorldPositions() {
        Rectangle currentSolidArea = this.getSolidArea();
        int x = 0;
        int y = 0;
        boolean isMoving = !this.isIdle();

        if(isMoving) {
            int speed = this.getSpeed();
            switch (this.getDirection()) {
                case FACING_BACKWARD -> y = -speed;
                case FACING_FORWARD -> y = +speed;
                case FACING_LEFTWARD -> x = -speed;
                case FACING_RIGHTWARD -> x = +speed;
                default -> System.out.println("Custom error");
            }
        }
        return new Rectangle(this.getWorldPositionX() +x, this.getWorldPositionY() +y, currentSolidArea.width, currentSolidArea.height);
    }

    public boolean doCollideWithPlayer(Player player){
        return player.getSolidAreaWithWorldPositions().intersects(getSolidAreaWithWorldPositions());
    }


    public abstract void performInteraction();
    public void draw(Graphics2D graphics2D, Player player){
        BufferedImage image = getCurrentSpriteImage();

        boolean isWithinWindowRange = DrawHelper.isWithinWindow(this.getWorldPositionX(), this.getWorldPositionY(), player);
        if (isWithinWindowRange) {
//            if (this.isActive) {
            int windowPositionX = DrawHelper.getObjWindowPositionXRespectiveToPlayer(this.getWorldPositionX(), player);
            int windowPositionY = DrawHelper.getObjWindowPositionYRespectiveToPlayer(this.getWorldPositionY(), player);

            int width = (int) (TILE_SIZE *1);
            int height = (int) (TILE_SIZE *1);

            graphics2D.drawImage(image, windowPositionX, windowPositionY, width, height, null);
//            }

        }

    }

    private void checkForCollisions() {
        CollisionDetector collisionDetector = CollisionDetector.getInstance();
        collisionDetector.checkTileCollision(this);
        collisionDetector.checkCharacterPlayerCollision(this);
    }

    private boolean shouldUpdatePosition() {
        boolean onUpdateRate = this.frameCounter % 5 == 0;
        return onUpdateRate && !this.isOnCollision() && !this.isOnCollisionWithPlayer();
    }

    private void updatePosition(){
        int currentPositionX = this.getWorldPositionX();
        int currentPositionY =this.getWorldPositionY();
        int speed = this.getSpeed();
        switch (this.getDirection()){
            case FACING_BACKWARD -> this.setWorldPositionY(currentPositionY-speed);
            case FACING_FORWARD -> this.setWorldPositionY(currentPositionY +speed);
            case FACING_LEFTWARD -> this.setWorldPositionX(currentPositionX -speed);
            case FACING_RIGHTWARD -> this.setWorldPositionX(currentPositionX+speed);
        }
    }

    private void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
    }


    private boolean shouldChangeDirection() {
        boolean updateRate = this.frameCounter % 150 == 0;
        return updateRate && !this.isOnCollisionWithPlayer();
    }

    private void resetFrameCounter(){
        this.setFrameCounter(0);
    }



    private BufferedImage getCurrentSpriteImage() {

        BufferedImage[] currentAnimationArr = CharacterSpriteManager.getInstance().getAnimationArray(this.getCharacterAnimationKey(),this.getDirection(),this.isIdle());

        if(shouldUpdateFrameIndex()) {
            updateCurrentFrameIndex();
        }

        return currentAnimationArr[this.currentFrameIndex];
    }

    private boolean shouldUpdateFrameIndex() {
        return this.frameCounter % 20 == 0;
    }

    protected abstract void changeDirection();

    protected void changeDirectionRandomly(){
        int random = (int) (Math.random() *4);
        switch (random){
            case 0->setDirection(FACING_FORWARD);
            case 1->setDirection(FACING_BACKWARD);
            case 2->setDirection(FACING_RIGHTWARD);
            case 3->setDirection(FACING_LEFTWARD);
            default -> {}
        }
        System.out.println("Direction "+this.getDirection().toString());
    }

    private void updateCurrentFrameIndex() {
        int maxFrameLimit = CharacterSpriteManager.getInstance().getCharacterMaxFrameLimit(this.getCharacterAnimationKey());
        if(this.currentFrameIndex>=maxFrameLimit-1){
            this.currentFrameIndex=0;
        }else {
            this.currentFrameIndex++;
        }
    }
}
