package ui;

import ui.notifier.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserInterfaceManager {

    private final Notifiable playerDetailNotifier;

    // Add game notifier related logic
    private final Notifiable gameDetailNotifier;

    private final Notifiable mainDetailNotifier;

    private final Notifiable dialogueDetailNotifier;

    private final List<DrawableNotifier> drawableNotifierList;

    private static class Holder {
        private static final UserInterfaceManager INSTANCE = new UserInterfaceManager();
    }

    private UserInterfaceManager() {
        this.drawableNotifierList = new ArrayList<>();
        this.playerDetailNotifier = getPlayerDetailNotifier();
        this.gameDetailNotifier = getGameDetailNotifier();
        this.mainDetailNotifier = getMainDetailNotifier();
        this.dialogueDetailNotifier = getDialogueDetailNotifier();

    }

    private  Notifiable getDialogueDetailNotifier() {
        AbstractDetailNotifier notifier = new DialogueDetailNotifier(false);
        appendDrawableItems(notifier);
        return notifier;
    }

    private Notifiable getMainDetailNotifier() {
        AbstractDetailNotifier notifier = new MainDetailNotifier(false);
        appendDrawableItems(notifier);
        return notifier;
    }

    private Notifiable getGameDetailNotifier() {
        AbstractDetailNotifier notifier = new GameDetailNotifier(false);
        appendDrawableItems(notifier);
        return notifier;
    }

    private  Notifiable getPlayerDetailNotifier() {
        AbstractDetailNotifier notifier = new PlayerDetailNotifier(true);
        appendDrawableItems(notifier);
        return notifier;
    }

    private void appendDrawableItems(AbstractDetailNotifier detailNotifier) {
         this.drawableNotifierList.add(detailNotifier);
    }


    public static UserInterfaceManager getInstance() {
        return Holder.INSTANCE;
    }

    public void notifyGameDetails(String message) {
        this.gameDetailNotifier.notifyMessageForPeriod(message, 1);
    }

    public void triggerMainNotification(String message) {
        this.mainDetailNotifier.notifyMessage(message);
    }

    public void triggerDetialNotification(String message){
        this.dialogueDetailNotifier.notifyMessageForPeriod(message,2);
    }

    public void deactivateMainNotification() {
        this.mainDetailNotifier.deactivate();
    }

    public void draw(Graphics2D graphics2D) {
        for (DrawableNotifier drawableNotifier: this.drawableNotifierList){
            drawableNotifier.draw(graphics2D);
        }
    }
}
