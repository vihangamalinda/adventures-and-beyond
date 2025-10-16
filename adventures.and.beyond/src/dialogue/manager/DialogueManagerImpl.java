package dialogue.manager;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class DialogueManagerImpl implements DialogueManager {
    private final Map<String,String> dialogues;

    public DialogueManagerImpl(){
        this.dialogues = new HashMap<>();
        this.registerDialogue();
    }

    private void registerDialogue(){
        register(DialogueKey.DIALOGUE_01, DialogueMessage.DIALOGUE_01_MESSAGE);
        register(DialogueKey.DIALOGUE_02, DialogueMessage.DIALOGUE_02_MESSAGE);
    }

    private void register(String key, String message) {
        this.dialogues.put(key, message);
    }

    @Override
    public String getDialogueByDialogueKey(String dialogueKey) {
        String dialogue = this.dialogues.get(dialogueKey);
        if(isNull(dialogue)){
            System.out.println("Custom Error: Wrong dialogue key. DialogueKey :"+dialogueKey);
        }
        return dialogue;
    }

}
