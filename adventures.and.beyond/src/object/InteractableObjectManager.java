package object;

import object.interactable.objects.InteractableObject;

public interface InteractableObjectManager {
    InteractableObject[] getInteratableObjectsByWorldMapKey(String key);
}
