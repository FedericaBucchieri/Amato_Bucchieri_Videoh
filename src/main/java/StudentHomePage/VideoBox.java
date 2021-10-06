package StudentHomePage;

import EventManagement.*;
import entities.Video;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;

public class VideoBox implements Listener {//controller

    private VideoBoxModel model;
    private VideoBoxUI UI;

    public VideoBox(Video video){
        new NativeDiscovery().discover();
        model = new VideoBoxModel(video);
        UI = new VideoBoxUI(this);

//        UI.installUI();
    }

    public JSlider getSlider(){
        return UI.getSlider();
    }

    public VideoBoxModel getModel() {
        return model;
    }

    public VideoBoxUI getUI() {
        return UI;
    }

    public int getVideoId(){
        return model.getVideo().getId();
    }

    public void dismissVideo() {
        getUI().dismissVideo();
    }

    @Override
    public void listen(Event event) {
    }

}
