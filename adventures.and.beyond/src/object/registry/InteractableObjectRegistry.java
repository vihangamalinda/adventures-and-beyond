package object.registry;

import object.interactable.objects.InteractableObject;

import java.util.HashMap;

public interface InteractableObjectRegistry {
    HashMap<String, InteractableObject[]> getRegisteredInteractableObjectMap();
}
