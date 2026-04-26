package ui.notifier.registry;

import ui.DrawableNotifier;
import ui.Notifiable;
import ui.notifier.AbstractDetailNotifier;
import ui.notifier.factory.DetailNotifierFactory;
import ui.notifier.factory.DetailNotifierFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class DetailNotifierRegistryImpl implements DetailNotifierRegistry {
    private Notifiable playerDetailNotifier;
    private Notifiable gameDetailNotifier;
    private Notifiable mainDetailNotifier;
    private Notifiable dialogueDetailNotifier;

    private final List<DrawableNotifier> drawableNotifierList;
    private final DetailNotifierFactory detailNotifierFactory;

    public DetailNotifierRegistryImpl(DetailNotifierFactory detailNotifierFactory) {
        this.detailNotifierFactory = detailNotifierFactory;
        this.drawableNotifierList = new ArrayList<>();
        this.registerNotifiers();
    }

    private void registerNotifiers() {
        String[] notifierTypes = detailNotifierFactory.getTypeList();
        this.playerDetailNotifier = this.createAndRegisterNotifier(notifierTypes[0]);
        this.gameDetailNotifier = this.createAndRegisterNotifier(notifierTypes[1]);
        this.mainDetailNotifier = this.createAndRegisterNotifier(notifierTypes[2]);
        this.dialogueDetailNotifier = this.createAndRegisterNotifier(notifierTypes[3]);

    }

    private Notifiable createAndRegisterNotifier(String notifierType) {
        AbstractDetailNotifier detailNotifier = detailNotifierFactory.create(notifierType);
        this.appendDrawable(detailNotifier);
        return detailNotifier;
    }

    private void appendDrawable(AbstractDetailNotifier detailNotifier) {
        this.drawableNotifierList.add(detailNotifier);
    }


    @Override
    public Notifiable getPlayerDetailNotifier() {
        return this.playerDetailNotifier;
    }

    @Override
    public Notifiable getGameDetailNotifier() {
        return this.gameDetailNotifier;
    }

    @Override
    public Notifiable getMainDetailNotifier() {
        return this.mainDetailNotifier;
    }

    @Override
    public Notifiable getDialogueDetailNotifier() {
        return this.dialogueDetailNotifier;
    }

    @Override
    public List<DrawableNotifier> getRegisteredDrawableNotifierList() {
        return this.drawableNotifierList;
    }
}
