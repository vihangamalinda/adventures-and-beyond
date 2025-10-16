package entity.npc.character;

import dialogue.manager.DialogueKey;
import dialogue.DialogueManagerFactory;
import directionEnum.Direction;
import entity.npc.NonPlayerCharacter;
import ui.UserInterfaceManager;

import java.awt.*;

public class Human extends NonPlayerCharacter {
    private static final int speed = 3;

    private final String dialogueKey;

    public Human(int worldPositionX, int worldPositionY, Direction direction, boolean onCollision, String characterAnimationKey, String dialogueKey) {
        super(worldPositionX, worldPositionY, speed, direction, false, new Rectangle(10, 10, 30, 30), onCollision, characterAnimationKey);
        this.dialogueKey =dialogueKey;
    }

    @Override
    public void associateWithPlayer() {
        super.associateWithPlayer();
        this.performInteraction();
    }

    @Override
    public void performInteraction() {
        String dialogue = DialogueManagerFactory.getInstance().getDialogueByDialogueKey(this.dialogueKey);
        UserInterfaceManager.getInstance().triggerDetialNotification(dialogue);
        System.out.println(dialogue);
    }

    @Override
    protected void changeDirection() {
        this.changeDirectionRandomly();
    }
}
