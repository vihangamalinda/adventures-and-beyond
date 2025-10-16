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
        DetailNotifierFactory detailNotifierFactory = new DetailNotifierFactoryImpl();
        String[] definedTypes =detailNotifierFactory.getTypeList();

        this.playerDetailNotifier = detailNotifierFactory.create(definedTypes[0]);
        this.gameDetailNotifier = detailNotifierFactory.create(definedTypes[1]);
        this.mainDetailNotifier =detailNotifierFactory.create(definedTypes[2]);
        this.dialogueDetailNotifier = detailNotifierFactory.create(definedTypes[3]);
        this.drawableNotifierList = detailNotifierFactory.getRegisteredDrawableNotifiers();
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
