package helper;

import main.KeyHandler;

public class Timer {
    public static long getStartingTime() {
        return System.nanoTime();
    }

    public static void evaluvateTimeSpent(long startTime) {
        boolean isOnDevMood = KeyHandler.getInstance().isOnDevMood();
        if (isOnDevMood) {
            System.out.println("Time spent" + (System.nanoTime() - startTime));
        }
    }
}
