package StudentHomePage;

import EventManagement.*;
import EventManagement.Event;
import entities.Video;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StudentHomePageScene implements Listener, Scene{
    private VideoPlayerArea videoPlayerArea;
    private StudentDetailPanel studentDetailPanel;
    private String username;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;
    private CardLayout cardLayout;
    private List<Listener> listeners = new ArrayList<>();

    public StudentHomePageScene(SceneManager sceneManager, Video video, String username) {
        this.username = username;
        this.listeners.add(sceneManager);

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        setupCentralPanel(sceneManager, video);
        setupLeftPanel(sceneManager);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(rightPanel, BorderLayout.EAST);

    }

    public void setupCentralPanel(SceneManager sceneManager, Video video){
        cardLayout = new CardLayout();
        this.centerPanel = new JPanel();
        centerPanel.setLayout(cardLayout);

        videoPlayerArea = new VideoPlayerArea(sceneManager, this, video, username);
        centerPanel.add(videoPlayerArea.getMainPanel());
        cardLayout.next(centerPanel);
    }

    public void setupLeftPanel(SceneManager sceneManager){
        this.rightPanel = new JPanel();
        studentDetailPanel = new StudentDetailPanel(username,this, sceneManager);
        rightPanel.add(studentDetailPanel.getMainPanel());
        rightPanel.setBackground(Color.decode("#42577F"));
    }

    public String getUsername(){return this.username;}

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void listen(Event event) {
        if(event.getClass().equals(LogoutEvent.class)){
            videoPlayerArea.dismissVideo();
        }
        else if(event.getClass().equals(NewQuestionEvent.class)){
            studentDetailPanel.addQuestionToList(((NewQuestionEvent) event).getQuestion());
        }
        else if(event.getClass().equals(UpdateQuestionEvent.class)){
            studentDetailPanel.updateQuestionList();
        }
    }
}
