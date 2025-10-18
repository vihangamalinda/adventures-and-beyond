package ui.notifier;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.util.ArrayList;
import java.util.List;

public class DetailNotifierFactoryImpl implements DetailNotifierFactory {

    private static final String[] definedTypes = {"PlayerNotifier", "GameNotifier", "MainNotifier", "DialogueNotifier"};

    public DetailNotifierFactoryImpl() {
    }

    @Override
    public AbstractDetailNotifier create(String creationType) {
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

    private AbstractDetailNotifier getDialogueDetailNotifier() {
        return new DialogueDetailNotifier(false);
    }


    private AbstractDetailNotifier getMainDetailNotifier() {
        return new MainDetailNotifier(false);
    }


    private AbstractDetailNotifier getGameDetailNotifier() {
        return new GameDetailNotifier(false);
    }

    private AbstractDetailNotifier getPlayerDetailNotifier() {
        return new PlayerDetailNotifier(true);
    }
}
