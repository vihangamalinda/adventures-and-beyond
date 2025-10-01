package main;

import entity.PlayerSpriteManager;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        PlayerSpriteManager.initializeSpriteMap();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Adventures and Beyond");

        GamePanel gamePanel = GamePanel.getInstance();
        window.add(gamePanel);
        //to inform window to be sized to fit the preferred size and layouts of subcomponents( gamePanel)
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}