package ui.notifier;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.util.List;

public interface DetailNotifierFactory {
    Notifiable create(String creationType);

    String[] getTypeList();

    List<DrawableNotifier> getRegisteredDrawableNotifiers();
}
