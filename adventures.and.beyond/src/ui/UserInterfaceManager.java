package ui;

import ui.notifier.registry.DetailNotifierRegistry;
import ui.notifier.registry.DetailNotifierRegistryImpl;

import java.awt.*;
import java.util.List;

public class UserInterfaceManager {

    private final Notifiable playerDetailNotifier;

    // Add game notifier related logic
    private final Notifiable gameDetailNotifier;

    private final Notifiable mainDetailNotifier;

    private final Notifiable dialogueDetailNotifier;

    private final List<DrawableNotifier> drawableNotifierList;

    public UserInterfaceManager(DetailNotifierRegistry detailNotifierRegistry) {
        this.playerDetailNotifier = detailNotifierRegistry.getPlayerDetailNotifier();
        this.gameDetailNotifier = detailNotifierRegistry.getGameDetailNotifier();
        this.mainDetailNotifier = detailNotifierRegistry.getMainDetailNotifier();
        this.dialogueDetailNotifier = detailNotifierRegistry.getDialogueDetailNotifier();
        this.drawableNotifierList = detailNotifierRegistry.getRegisteredDrawableNotifierList();
    }


    public void notifyGameDetails(String message) {
        this.gameDetailNotifier.notifyMessageForPeriod(message,
                                                       1);
    }

    public void triggerMainNotification(String message) {
        this.mainDetailNotifier.notifyMessage(message);
    }

    public void triggerDetailNotification(String message) {
        this.dialogueDetailNotifier.notifyMessageForPeriod(message,
                                                           2);
    }

    public void deactivateMainNotification() {
        this.mainDetailNotifier.deactivate();
    }

    public void pauseGameModal() {
        this.triggerMainNotification("Game on Pause");
    }

    public void draw(Graphics2D graphics2D) {
        for (DrawableNotifier drawableNotifier : this.drawableNotifierList) {
            drawableNotifier.draw(graphics2D);
        }
    }
}
