package window;

import javax.swing.*;

public class GameWindowImpl implements GameWindow {
    private final JFrame window;

    public GameWindowImpl(String title) {
        this.window = new JFrame(title);
//        this.window.setSize(width, height);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.window.setResizable(false);
    }

    @Override
    public void initialize(JPanel panel) {
        this.window.add(panel);
        //to inform window to be sized to fit the preferred size and layouts of subcomponents( gamePanel)
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);
    }
}
