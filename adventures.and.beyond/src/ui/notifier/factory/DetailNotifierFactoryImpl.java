package ui.notifier.factory;

import entity.manager.EntityManager;
import entity.player.Player;
import ui.notifier.*;

public class DetailNotifierFactoryImpl implements DetailNotifierFactory {

    private static final String[] definedTypes = {"PlayerNotifier", "GameNotifier", "MainNotifier", "DialogueNotifier"};
    private final Player player;

    public DetailNotifierFactoryImpl(EntityManager entityManager) {
        this.player = entityManager.getPlayer();
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
        return new PlayerDetailNotifier(true,player);
    }
}
