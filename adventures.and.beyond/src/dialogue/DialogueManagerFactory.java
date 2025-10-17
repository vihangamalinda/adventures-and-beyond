package dialogue;

import dialogue.manager.DialogueManager;
import dialogue.manager.DialogueManagerImpl;

public class DialogueManagerFactory {
    static class Holder {
        private static final DialogueManager INSTANCE = new DialogueManagerImpl();
    }

    public static DialogueManager getInstance() {
        return Holder.INSTANCE;
    }
}
