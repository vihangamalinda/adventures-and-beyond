package entity.npc;

import entity.player.Player;

import java.awt.*;

public interface DrawableNonPlayerCharacter {
    void draw(Graphics2D graphics2D, Player player);
}
