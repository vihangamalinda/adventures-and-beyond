package world.manager;

import object.interactable.objects.InteractableObject;

public interface WorldMapManager {

    InteractableObject[] getInteractableObject();
    int getTileKeyByRowAndCol(int rowIndex, int colIndex);
    boolean canTileBeCollided(int rowIndex, int colIndex);
}
