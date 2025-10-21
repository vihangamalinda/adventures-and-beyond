package world;

import object.interactable.objects.InteractableObject;

public interface WorldMapInformation {
    int getTileKey(int rowIndex, int colIndex);
    boolean canTileBeCollided(int rowIndex, int colIndex);
    InteractableObject[] getInteractableObjects();
}
