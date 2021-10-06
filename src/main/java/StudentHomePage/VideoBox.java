package StudentHomePage;

import EventManagement.*;
import entities.Video;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VideoBox implements Listener {//controller

    private VideoBoxModel model;
    private VideoBoxUI UI;
    private List<Listener> listeners = new ArrayList<>();

    public VideoBox(Video video){
        new NativeDiscovery().discover();
        model = new VideoBoxModel(video);
        UI = new VideoBoxUI(this);

//        UI.installUI();
    }
    public VideoBox(VideoPlayerArea videoPlayerArea ,Video video, String username){
        new NativeDiscovery().discover();
        this.listeners.add(videoPlayerArea);

        model = new VideoBoxModel(video, username);
        UI = new VideoBoxUI(this);
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
        if (event.getClass().equals(FreezeEvent.class)){
            UI.freezeVideo();
        }
        else if (event.getClass().equals(NewQuestionEvent.class)){
            UI.restartVideoAfterFreeze();
            dispatchNewQuestionEvent((NewQuestionEvent) event);
        }
        else if (event.getClass().equals(RestartAfterFreezeEvent.class)){
            UI.restartVideoAfterFreeze();
        }
        else if (event.getClass().equals(UpdateQuestionEvent.class)){
            dispatchUpdateQuestionEvent((UpdateQuestionEvent) event);
        }
    }

    private void dispatchNewQuestionEvent(NewQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    private void dispatchUpdateQuestionEvent(UpdateQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

}
