package VideoPlayer;

import EventManagement.*;
import InteractionList.InteractionPanel;
import ProfessorHomePage.ProfessorHomePageScene;
import ProfessorHomePage.StatisticsPaneView;
import StudentHomePage.StudentHomePageScene;
import entities.Question;
import entities.Video;
import Utils.Utils;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VideoBox implements Listener {//controller

    //The model of the VideoBox element
    private VideoBoxModel model;
    //The UI of the VideoBox element
    private VideoBoxView view;
    //The list of listeners that will listen for event dispatched by this element
    private List<Listener> listeners = new ArrayList<>();


    /**
     * This constructor creates a new VideoBox element, that takes care of playing the video, managing pause/play button, the slider to manage the timeline of the video and the interactions related to the video.
     * It's meant to be used  when the videobox is created by statisticsPage of the professor so it won't use the username of the student and the videoPlayerArea.
     * @param video the video entity (from the db) that will be displayed
     */
    public VideoBox(Video video, StatisticsPaneView statisticsPaneView){
        new NativeDiscovery().discover();//this function check for the VLC library within the local machine where the app is running.
        model = new VideoBoxModel(video);
        view = new VideoBoxView(this, true);
        this.listeners.add(statisticsPaneView);

    }

    /*
     * This constructor creates a new VideoBox element, that takes care of playing the video, managing pause/play button, the slider to manage the timeline of the video and the interactions related to the video.
     * This contructor is meant to be used by the videoBoxAreaView class when it creates the videoBox element.
     * @param videoPlayerArea: the contoller of the videoBoxAreaView that created this video box.
     * @param video the video entity (from the db) that will be displayed
     * @param username the username of the student that is currently posting the interactions and questions.

    public VideoBox(VideoPlayerArea videoPlayerArea ,Video video, String username){
        new NativeDiscovery().discover();
        this.listeners.add(videoPlayerArea);

        model = new VideoBoxModel(video, username, Utils.STUDENT_VIDEO_FRAME_WIDTH, Utils.STUDENT_VIDEO_FRAME_HEIGHT);
        view = new VideoBoxView(this, false);
    }
     */

    public VideoBox(StudentHomePageScene studentHomePageScene , Video video, String username){
        new NativeDiscovery().discover();
        this.listeners.add(studentHomePageScene);

        model = new VideoBoxModel(video, username, Utils.STUDENT_VIDEO_FRAME_WIDTH, Utils.STUDENT_VIDEO_FRAME_HEIGHT);
        view = new VideoBoxView(this, false);
    }

    /**
     * This method allows to delete a question interaction from the interaction panel
     * @param question the question to be deleted
     */
    public void deleteQuestion(Question question){
        getInteractionPanel().deleteQuestion(question);
    }

    /**
     * This method will ask the mediaPLayer of the video to pause the video.
     */
    public void stopVideoPlaying(){
        view.freezeVideo();
    }

    /**
     * This method will restart the video after it's been stopped.
     */
    public void unlockVideoPlaying(){
        view.restartVideoAfterFreeze();
    }


    public JSlider getSlider(){
        return view.getSlider();
    }

    public VideoBoxModel getModel() {
        return model;
    }

    public VideoBoxView getView() {
        return view;
    }

    public int getVideoId(){
        return model.getVideo().getId();
    }

    /**
     * Asks the mediaplayer of the video to cleanly dispose of the media player instance itself and any associated native resources.
     */
    public void dismissVideo() {
        getView().dismissVideo();
    }

    public InteractionPanel getInteractionPanel(){
        return view.getInteractionPanel();
    }

    @Override
    public void listen(Event event) {
        if (event.getClass().equals(FreezeEvent.class)){
            view.freezeVideo();
        }
        else if (event.getClass().equals(NewQuestionEvent.class)){
            view.restartVideoAfterFreeze();
            dispatchNewQuestionEvent((NewQuestionEvent) event);
        }
        else if (event.getClass().equals(RestartAfterFreezeEvent.class)){
            view.restartVideoAfterFreeze();
        }
        else if (event.getClass().equals(UpdateQuestionEvent.class)){
            dispatchUpdateQuestionEvent((UpdateQuestionEvent) event);
        }
        else if (event.getClass().equals(DeleteQuestionEvent.class)){
            dispatchDeleteQuestionEvent((DeleteQuestionEvent) event);
        }
        else if (event.getClass().equals(InteractionPanelCreatedEvent.class)){
            dispatchInteractionPanelCreatedEvent((InteractionPanelCreatedEvent) event);
        }
        else if (event.getClass().equals(InteractionListPopulatedEvent.class)){
            dispatchInteractionListPopulatedEvent((InteractionListPopulatedEvent)event);
        }

    }

    private void dispatchInteractionPanelCreatedEvent(InteractionPanelCreatedEvent event) {
        for (Listener listener : listeners)
            listener.listen(event);
    }

    private void dispatchInteractionListPopulatedEvent(InteractionListPopulatedEvent event) {
        for (Listener listener: listeners){
            listener.listen(event);
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
