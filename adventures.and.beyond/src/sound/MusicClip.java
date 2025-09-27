package sound;

import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static helper.Loader.loadSoundFile;
import static java.util.Objects.isNull;

public class MusicClip {
    private Clip clip;

    MusicClip(){
    }

    public void setUpSoundFile(URL soundFilePath){
        this.clip = loadSoundFile(soundFilePath);
    }

    public void play(){
        if(isNull(this.clip))return;

        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        if (isNull(this.clip))return;
        this.clip.stop();
    }

    public void playForOnce(){
        if(isNull(this.clip))return;

        this.clip.loop(1);
    }
    public void playForTimePeriod(int seconds){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            System.out.println("Sheduled task");
            this.stop();
        };


        this.playForOnce();

        scheduledExecutorService.schedule(task, seconds, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
    }
}
