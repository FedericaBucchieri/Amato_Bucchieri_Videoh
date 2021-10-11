package StudentHomePage;

import EventManagement.*;
import entities.Question;
import entities.Video;
import sceneManager.SceneManager;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerArea extends JComponent implements Listener { //controler
    private VideoPlayerAreaModel model;
    private VideoPlayerAreaUI UI;
    private List<Listener> listeners = new ArrayList<>();

    public VideoPlayerArea(SceneManager sceneManager, StudentHomePageScene studentHomePageScene,  Video video, String username) {
        this.listeners.add(sceneManager);
        this.listeners.add(studentHomePageScene);

        model = new VideoPlayerAreaModel(this, video, username);
        UI = new VideoPlayerAreaUI(this);
    }

    public void stopVideoPlaying(){
        UI.stopVideoPlaying();
    }

    public void unlockVideoPlaying(){
        UI.unlockVideoPlaying();
    }

    public VideoPlayerAreaUI getView () {
            return UI;
    }

    public JPanel getMainPanel () {
            return UI.getMainPanel();
        }

    public void dismissVideo(){
        UI.dismissVideo();
    }

    public VideoPlayerAreaModel getModel() {
        return model;
    }

    public void deleteQuestion(Question question){
        VideoBox videoBox = UI.getVideoBox();
        InteractionPanel interactionPanel = videoBox.getInteractionPanel();
        interactionPanel.deleteQuestion(question);
    }

    @Override
    public void listen(Event event) {
        if(event.getClass().equals(NewQuestionEvent.class)){
            dispatchNewQuestionEvent((NewQuestionEvent) event);
        }
        else if(event.getClass().equals(UpdateQuestionEvent.class)){
            dispatchUpdateQuestionEvent((UpdateQuestionEvent) event);
        }
        else if(event.getClass().equals(DeleteQuestionEvent.class)){
            dispatchDeleteQuestionEvent((DeleteQuestionEvent) event);
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

    private void dispatchDeleteQuestionEvent(DeleteQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }
}
