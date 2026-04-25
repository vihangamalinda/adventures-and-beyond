package entity.animation.service.player;

import entity.player.Player;
import entity.player.PlayerSpriteManager;

import java.awt.image.BufferedImage;

public class PlayerAnimationServiceImpl implements PlayerAnimationService {
    private final int COUNTER_THRESHOLD = 30;
    private final int MAX_FRAME_INDEX = 5;

    @Override
    public void updateAnimation(Player player){
        this.updateAnimationState(player);
        this.updateAnimationImage(player);
    }

    private void updateAnimationState(Player player) {
        this.increaseCounter(player);
        boolean isAtCounterThreshold = isCounterAtThreshold(player);

        if (isAtCounterThreshold) {
            boolean isAtMaxFrameIndex = isFrameIndexAtMax(player);

            if (isAtMaxFrameIndex) {
                this.resetFrameIndex(player);
            } else {
                this.increaseFrameIndex(player);
            }
            this.resetCounter(player);
        }
    }

    private void updateAnimationImage(Player player){
        BufferedImage currentPlayerImage = PlayerSpriteManager.getPlayerImageByIndex(player.getDirection(), player.isIdle(), player.getFrameIndex());
        player.setCurrentPlayerImage(currentPlayerImage);
    }

    private boolean isCounterAtThreshold(Player player) {
        return player.getCounter() % COUNTER_THRESHOLD == 0;
    }

    private boolean isFrameIndexAtMax(Player player) {
        return player.getFrameIndex() >= MAX_FRAME_INDEX;
    }

    private void resetCounter(Player player) {
        player.setCounter(0);
    }

    private void resetFrameIndex(Player player) {
        player.setFrameIndex(0);
    }

    private void increaseCounter(Player player) {
        int newCounter = player.getCounter() + 1;
        player.setCounter(newCounter);
    }

    private void increaseFrameIndex(Player player) {
        int newFrameIndex = player.getFrameIndex() + 1;
        player.setFrameIndex(newFrameIndex);
    }

}
