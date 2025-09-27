package sound;

import java.net.URL;
import java.util.HashMap;

import static helper.Loader.getResourceURL;

public class SoundManager {
    MusicClip mainMusicClip;
    MusicClip soundEffectMusicClip;
    HashMap<String,URL> soundURL;

    private static class Holder {
        private static final SoundManager INSTANCE = new SoundManager();
    }

    private SoundManager(){
        this.mainMusicClip = new MusicClip();
        this.soundEffectMusicClip = new MusicClip();
        this.soundURL= this.initializeSoundEffects();
    }

    public static SoundManager getInstance() {
        return Holder.INSTANCE;
    }

    private HashMap<String,URL> initializeSoundEffects() {
        HashMap<String,URL> map =new HashMap<>();

        map.put(SoundKey.THEME_1_KEY, getResourceURL(SoundResourcePath.THEME_1_PATH));
        map.put(SoundKey.KEY_COLLECTED, getResourceURL(SoundResourcePath.KEY_COLLECTED));
        map.put((SoundKey.DOOR_OPENING),getResourceURL(SoundResourcePath.DOOR_OPENING));
        map.put((SoundKey.TREASURE_BOX_OPENING),getResourceURL(SoundResourcePath.TREASURE_BOX_OPENING));
        return map;
    }

    public void setSoundFile(String soundKey){
        URL urlPath = getURLBySoundKey(soundKey);
        this.mainMusicClip.setUpSoundFile(urlPath);
    }

    public void setSoundEffectMusicClip(String soundKey){
        URL urlPath = getURLBySoundKey(soundKey);
        this.soundEffectMusicClip.setUpSoundFile(urlPath);
    }

    public void playSoundEffectForPeriod(int seconds){
        this.soundEffectMusicClip.playForTimePeriod(seconds);
    }

    public void performSoundEffects(String soundKey,int seconds){
        this.setSoundEffectMusicClip(soundKey);
        this.playSoundEffectForPeriod(seconds);
    }

    public void playSound(){
        this.mainMusicClip.play();
    }

    public void stop(){
        this.mainMusicClip.stop();
    }

    private URL getURLBySoundKey(String soundKey){
        return this.soundURL.get(soundKey);
    }
}
