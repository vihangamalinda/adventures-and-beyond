package ui.notifier;

import ui.DrawableNotifier;
import ui.Notifiable;

import java.util.List;

public interface DetailNotifierFactory {
    AbstractDetailNotifier create(String creationType);

    String[] getTypeList();
}
