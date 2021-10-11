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
    private VideoList videoList;
    private DetailPanel detailPanel;
    private AddVideoForm addVideoForm;
    private UpdateProfile updateProfile;
    private StatisticsPane statisticsPane;
    private Professor professor;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private CardLayout cardLayout;
    private List<Listener> listeners = new ArrayList<>();


    public ProfessorHomePage(SceneManager sceneManager, Professor professor) {
        this.listeners.add(sceneManager);
        this.professor = professor;

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setupCentralPanel();
        setupLeftPanel();

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

    }

    public void setupCentralPanel(){
        cardLayout = new CardLayout();
        this.centerPanel = new JPanel();
        centerPanel.setLayout(cardLayout);

        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void setupLeftPanel(){
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
        else if(event.getClass().equals(DeleteVideoEvent.class)) {
            removeVideoFromList(((DeleteVideoEvent) event).getVideo());
        }
        else if(event.getClass().equals(UpdateProfileRequestEvent.class)) {
            openUpdateProfileForm();
        }
        else if(event.getClass().equals(UpdateProfileEvent.class)) {
            professor = ((UpdateProfileEvent) event).getProfessor();
            detailPanel = new DetailPanel(professor, this);
            rightPanel.removeAll();
            rightPanel.add(detailPanel.getMainPanel());
            openVideoList();
        }
        else if(event.getClass().equals(LogoutEvent.class)){
            dispatchLogoutEvent((LogoutEvent) event);
        }
        else if (event.getClass().equals(GoToStatisticsEvent.class)){
            GoToStatisticsEvent e = ((GoToStatisticsEvent) event);
            goToStatisticsPage(e.getVideo());
        }
    }

    private void goToStatisticsPage(Video video) {
        /*
            statisticsPane = new StatisticsPane(video, this);
            centerPanel.add(statisticsPane.getMainPanel());
            cardLayout.next(centerPanel);

         */


    }

    public Professor getProfessor() {
        return professor;
    }

    public void dispatchLogoutEvent(LogoutEvent event){
        if (statisticsPane != null){
            //statisticsPane.dismissVideo();
        }

        for (Listener listener : listeners)
            listener.listen(event);
    }

    public void openNewVideoForm(){
        addVideoForm = new AddVideoForm(this);
        centerPanel.add(addVideoForm.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void openVideoList(){
        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void addNewVideoToList(Video video){
        List<Video> list = professor.getVideoList();
        list.add(video);
        professor.setVideoList(list);

        videoList = new VideoList(professor, this);
        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void removeVideoFromList(Video video){
        professor.removeVideo(video);
        videoList = new VideoList(professor, this);

        centerPanel.add(videoList.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void openUpdateProfileForm(){
        updateProfile = new UpdateProfile(this, professor);
        centerPanel.add(updateProfile.getMainPanel());
        cardLayout.next(centerPanel);
    }
}
