package ui.notifier.factory;

import ui.DrawableNotifier;
import ui.Notifiable;
import ui.notifier.AbstractDetailNotifier;

import java.util.List;

public interface DetailNotifierFactory {
    AbstractDetailNotifier create(String creationType);

    String[] getTypeList();
}
