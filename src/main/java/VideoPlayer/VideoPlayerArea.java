package VideoPlayer;

import EventManagement.*;
import InteractionList.InteractionPanel;
import StudentHomePage.StudentHomePageScene;
import entities.Question;
import entities.Video;
import sceneManager.SceneManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerArea extends JComponent implements Listener { //controler
    private VideoPlayerAreaModel model;
    private VideoPlayerAreaView view;
    private List<Listener> listeners = new ArrayList<>();

    public VideoPlayerArea(SceneManager sceneManager, StudentHomePageScene studentHomePageScene, Video video, String username) {
        this.listeners.add(sceneManager);
        this.listeners.add(studentHomePageScene);

        model = new VideoPlayerAreaModel(this, video, username);
        view = new VideoPlayerAreaView(this);
    }

    /**
     * The VideoPlayerArea will ask the VideoBox to stop the video.
     */
    public void stopVideoPlaying(){
        view.stopVideoPlaying();
    }

    /**
     * The VideoPlayerArea will ask the VideoBox to play the video after it's been frozen (paused).
     */
    public void unlockVideoPlaying(){
        view.unlockVideoPlaying();
    }

    public VideoPlayerAreaView getView () {
            return view;
    }

    public JPanel getMainPanel () {
            return view.getMainPanel();
        }

    /**
     * This method asks the view to dismiss the video.
      */
    public void dismissVideo(){
        view.dismissVideo();
    }

    public VideoPlayerAreaModel getModel() {
        return model;
    }

    /**
     * Delete the question from the interaction panel of the view.
     * @param question: question to be deleted
     */
    public void deleteQuestion(Question question){
        VideoBox videoBox = view.getVideoBox();
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

    /**
     * This method dispatch NewQuestionEvent to above listeners.
     * @param event: a  NewQuestionEvent to be dispatched
     */
    private void dispatchNewQuestionEvent(NewQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    /**
     * This method dispatch UpdateQuestionEvent to above listeners.
     * @param event: a  UpdateQuestionEvent to be dispatched
     */
    private void dispatchUpdateQuestionEvent(UpdateQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    /**
     * This method dispatch DeleteQuestionEvent to above listeners.
     * @param event: a  DeleteQuestionEvent to be dispatched
     */
    private void dispatchDeleteQuestionEvent(DeleteQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }
}
