package entity.npc.character;

import directionEnum.Direction;
import entity.npc.CharacterSpriteManager;
import entity.npc.NonPlayerCharacter;
import entity.player.Player;
import helper.DrawHelper;
import main.CollisionDetector;

import java.awt.*;
import java.awt.image.BufferedImage;

import static directionEnum.Direction.*;
import static helper.Constant.TILE_SIZE;

public class Human extends NonPlayerCharacter {
    private static final int speed =3;

    public Human(int worldPositionX, int worldPositionY, Direction direction, boolean onCollision,String characterAnimationKey) {
        super(worldPositionX, worldPositionY, speed, direction, false, new Rectangle(10,10,30,30), onCollision,characterAnimationKey);
    }

    @Override
    public void associateWithPlayer() {
        super.associateWithPlayer();
        this.performInteraction();
    }

    @Override
    public void performInteraction() {
        System.out.println("interaction");
    }

    @Override
    protected void changeDirection() {
        this.changeDirectionRandomly();
    }
}
