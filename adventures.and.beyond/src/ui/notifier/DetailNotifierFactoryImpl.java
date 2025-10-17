package ui.notifier;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.util.ArrayList;
import java.util.List;

public class DetailNotifierFactoryImpl implements DetailNotifierFactory {

    private static final String[] definedTypes = {"PlayerNotifier", "GameNotifier", "MainNotifier", "DialogueNotifier"};

    private final List<DrawableNotifier> registeredDrawableNotifiers;

    public DetailNotifierFactoryImpl() {
        this.registeredDrawableNotifiers = new ArrayList<>();
    }

    @Override
    public Notifiable create(String creationType) {
        return switch (creationType) {
            case "MainNotifier" -> getMainDetailNotifier();
            case "DialogueNotifier" -> getDialogueDetailNotifier();
            case "GameNotifier" -> getGameDetailNotifier();
            case "PlayerNotifier" -> getPlayerDetailNotifier();
            default -> null;
        };
    }

    @Override
    public String[] getTypeList() {
        return definedTypes;
    }

    @Override
    public List<DrawableNotifier> getRegisteredDrawableNotifiers() {
        return this.registeredDrawableNotifiers;
    }

    private Notifiable getDialogueDetailNotifier() {
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

    private Notifiable getPlayerDetailNotifier() {
        AbstractDetailNotifier notifier = new PlayerDetailNotifier(true);
        appendDrawableItems(notifier);
        return notifier;
    }

    private void appendDrawableItems(AbstractDetailNotifier detailNotifier) {
        this.registeredDrawableNotifiers.add(detailNotifier);
    }

}
