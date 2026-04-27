package ui.notifier.factory;

import ui.notifier.AbstractDetailNotifier;

public interface DetailNotifierFactory {
    AbstractDetailNotifier create(String creationType);

    String[] getTypeList();
}
