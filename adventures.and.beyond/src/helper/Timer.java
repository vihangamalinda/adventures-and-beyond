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


    public  void evaluvateTimeSpent(long startTime) {
        boolean isOnDevMood = this.keyHandler.readRegisteredKeyState().isOnDevMood();
        if (isOnDevMood) {
            System.out.println("Time spent" + (System.nanoTime() - startTime));
        }
    }
}
