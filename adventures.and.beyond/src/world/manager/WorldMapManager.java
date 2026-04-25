package world.manager;

import object.interactable.objects.InteractableObject;

import java.awt.*;

public interface WorldMapManager {

    InteractableObject[] getInteractableObject();

    int getTileKeyByRowAndCol(int rowIndex, int colIndex);

    boolean canTileBeCollided(int rowIndex, int colIndex);

    void draw(Graphics2D graphics2D);
}
