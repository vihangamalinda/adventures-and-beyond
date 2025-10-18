package ui.notifier.registry;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.util.List;

public interface DetailNotifierRegistry {
    Notifiable getPlayerDetailNotifier();
    Notifiable getGameDetailNotifier();
    Notifiable getMainDetailNotifier();
    Notifiable getDialogueDetailNotifier();

    List<DrawableNotifier> getRegisteredDrawableNotifierList();
}
