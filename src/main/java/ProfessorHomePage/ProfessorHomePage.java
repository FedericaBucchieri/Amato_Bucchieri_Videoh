package ProfessorHomePage;

import EventManagement.*;
import EventManagement.Event;
import entities.Professor;
import entities.Video;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorHomePage implements Listener, Scene {
    //The VideoList component that manages the list of videoListElement
    private VideoList videoList;
    //The lateral blue panel showing all the settings buttons of the professor
    private DetailPanel detailPanel;
    //The form to add a new video
    private AddVideoForm addVideoForm;
    //The form to update the profile of the professor
    private UpdateProfile updateProfile;
    //The statistics pane of a selected video
    private StatisticsPane statisticsPane;
    //The form to modify a selected video
    private ModifyVideoForm modifyVideoForm;
    //The logged progessor
    private Professor professor;
    //The panel containing all the element of the scene
    private JPanel mainPanel;
    //The panel that will host the videoList plus the various forms when they need to be displayed
    private JPanel centerPanel;
    //The panel that will host the detailPanel
    private JPanel rightPanel;
    //The layout that will handle the presentation of various form
    private CardLayout cardLayout;
    //The array of listeners that will handle the events dispatched by PRofessoreHomePage
    private List<Listener> listeners = new ArrayList<>();


    /**
     * Creates a new ProfessorHomePage scene. This scene will present various form using the carLayout on top of its
     * center panel.
     * @param sceneManager the listener of the events dispatched by ProfessorHomePage
     * @param professor the logged professor
     */
    public ProfessorHomePage(SceneManager sceneManager, Professor professor) {
        this.listeners.add(sceneManager);
        this.professor = professor;

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setupCentralPanel();
        setupRightPanel();

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

    }

    /**
     * Sets up the center panel and the card layout of the scene. It creates also the videoList element
     */
    public void setupCentralPanel(){
        cardLayout = new CardLayout();
        this.centerPanel = new JPanel();
        centerPanel.setLayout(cardLayout);

        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * Sets the right panel and the detail panel on top of it
     */
    public void setupRightPanel(){
        this.rightPanel = new JPanel();
        detailPanel = new DetailPanel(professor, this);
        rightPanel.add(detailPanel.getMainPanel());
        rightPanel.setBackground(Color.decode("#42577F"));
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void listen(Event event) {

        if(event.getClass().equals(NewVideoRequestEvent.class)){
            openNewVideoForm();
        }
        else if(event.getClass().equals(NewVideoEvent.class)){
            addNewVideoToList(((NewVideoEvent) event).getVideo());
        }
        else if(event.getClass().equals(CancelEvent.class)){
            openVideoList();
        }
        else if(event.getClass().equals(BackEvent.class)){
            openVideoList();
        }
        else if(event.getClass().equals(DeleteVideoEvent.class)) {
            removeVideoFromList(((DeleteVideoEvent) event).getVideo());
        }
        else if(event.getClass().equals(UpdateProfileRequestEvent.class)) {
            openUpdateProfileForm();
        }
        else if(event.getClass().equals(ModifyVideoEvent.class)) {
            openModifyVideoForm(((ModifyVideoEvent) event).getVideo());
        }
        else if(event.getClass().equals(UpdateProfileEvent.class)) {
            updateProfile(((UpdateProfileEvent) event).getProfessor());
        }
        else if(event.getClass().equals(LogoutEvent.class)){
            dispatchLogoutEvent((LogoutEvent) event);
        }
        else if (event.getClass().equals(GoToStatisticsEvent.class)){
            GoToStatisticsEvent e = ((GoToStatisticsEvent) event);
            goToStatisticsPage(e.getVideo());
        }
    }

    /**
     * Present the statisticsPage related to the given video
     * @param video the video for which to show the statistics
     */
    private void goToStatisticsPage(Video video) {
        statisticsPane = new StatisticsPane(video.getId(), this);
        centerPanel.add(statisticsPane.getScrollPane());
        cardLayout.next(centerPanel);
    }


    public Professor getProfessor() {
        return professor;
    }

    /**
     * If any video is currently playing it will be dismissed (actually, only the statistics pane will play the video)
     * and then dispatch a new LogoutEvent to the above listeners.
     * @param event
     */
    public void dispatchLogoutEvent(LogoutEvent event){
        if (statisticsPane != null){
            statisticsPane.dismissVideo();
        }

        for (Listener listener : listeners)
            listener.listen(event);
    }

    /**
     * Presents on top of the center panel a new addVideoForm
     */
    public void openNewVideoForm(){
        addVideoForm = new AddVideoForm(this);
        centerPanel.add(addVideoForm.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * Presents on top of the center panel a new videoList
     */
    public void openVideoList(){
        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * add the given video to the videoList of the logged professor. Then it shows again the videoList on top of
     * the center panel
     * @param video the new video to add to the video list of the professor
     */
    public void addNewVideoToList(Video video){
        List<Video> list = professor.getVideoList();
        list.add(video);
        professor.setVideoList(list);

        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * Removes the given video from the videoList of the logged professor. Then it shows again the videoList on top of
     * the center panel
     * @param video the video to remove from the video list of the professor
     */
    public void removeVideoFromList(Video video){
        videoList.removeVideo(video);
        videoList = new VideoList(professor, this);

        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * //TODO
     * @param professor
     */
    public void updateProfile(Professor professor){
        detailPanel = new DetailPanel(professor, this);
        rightPanel.removeAll();
        rightPanel.add(detailPanel.getMainPanel());
        openVideoList();
    }

    /**
     * Present a new modifyVideoForm on top of the center panel
     * @param video the video whose parameters are to be modified
     */
    public void openModifyVideoForm(Video video){
        modifyVideoForm = new ModifyVideoForm(this, video);
        centerPanel.add(modifyVideoForm.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * Present a new updateProfile on top of the center panel
     */
    public void openUpdateProfileForm(){
        updateProfile = new UpdateProfile(this, professor);
        centerPanel.add(updateProfile.getMainPanel());
        cardLayout.next(centerPanel);
    }
}
