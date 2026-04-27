package object;

import object.interactable.objects.InteractableObject;
import object.registry.InteractableObjectRegistryFactory;

import java.util.HashMap;

import static java.util.Objects.isNull;

public class InteractableObjectManagerImpl implements InteractableObjectManager {
    private final HashMap<String, InteractableObject[]> registeredInteractableObjectMap;

    public InteractableObjectManagerImpl() {
        this.registeredInteractableObjectMap = InteractableObjectRegistryFactory.getInstance().getRegisteredInteractableObjectMap();
    }

    @Override
    public InteractableObject[] getInteratableObjectsByWorldMapKey(String key) {

        InteractableObject[] interactableObjects = this.registeredInteractableObjectMap.get(key);
        if (isNull(interactableObjects)) {
            throw new RuntimeException("Not a registered world map key. World map Key :" + key);
        }
        return interactableObjects;
    }
}
