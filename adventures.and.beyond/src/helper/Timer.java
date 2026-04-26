package helper;

import input.KeyHandler;

public class Timer {
    private KeyHandler keyHandler;

    public Timer(KeyHandler keyHandler){
        this.keyHandler = keyHandler;
    }

    public  long getStartingTime() {
        return System.nanoTime();
    }

    public static void evaluvateTimeSpent(long startTime) {
        boolean isOnDevMood = KeyHandler.getInstance().readRegisteredKeyState().isOnDevMood();
        if (isOnDevMood) {
            System.out.println("Time spent" + (System.nanoTime() - startTime));
        }
    }
}
