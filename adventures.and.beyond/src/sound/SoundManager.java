package sound;

import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.HashMap;

import static helper.Loader.loadSoundFile;
import static sound.SoundKey.*;
import static sound.SoundResourcePath.*;

public class SoundManager {
    Clip clip;
    HashMap<String,URL> soundURL;

    private static class Holder {
        private static final SoundManager INSTANCE = new SoundManager();
    }

    private SoundManager(){
            this.soundURL= this.initializeSoundEffects();
    }

    public static SoundManager getInstance() {
        return Holder.INSTANCE;
    }

    private HashMap<String,URL> initializeSoundEffects() {
        HashMap<String,URL> map =new HashMap<>();

        map.put(THEME_1_KEY,getURL(THEME_1_PATH));
        return map;
    }

    public void setSoundFile(String soundKey){
        URL urlPath = soundURL.get(soundKey);
        this.clip =loadSoundFile(urlPath);
    }

    public void playSound(){
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        this.clip.stop();
    }



    private URL getURL(String resourcePath){
        return getClass().getResource(resourcePath);
    }
}
