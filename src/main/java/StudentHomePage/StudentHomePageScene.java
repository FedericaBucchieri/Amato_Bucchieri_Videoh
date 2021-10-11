package StudentHomePage;

import EventManagement.*;
import EventManagement.Event;
import entities.Question;
import entities.Video;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHomePageScene implements Listener, Scene{
    private VideoPlayerArea videoPlayerArea;
    private StudentDetailPanel studentDetailPanel;
    private QuestionReviewPanel questionReviewPanel;
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
        setupRightPanel(sceneManager);

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

    public void setupRightPanel(SceneManager sceneManager){
        this.rightPanel = new JPanel();
        studentDetailPanel = new StudentDetailPanel(username,this, sceneManager);
        rightPanel.add(studentDetailPanel.getMainPanel());
        rightPanel.setBackground(Color.decode("#42577F"));
    }

    private void switchToReviewMode(List<Question> questionList){
        videoPlayerArea.stopVideoPlaying();
        studentDetailPanel.hideQuestionList();

        questionReviewPanel = new QuestionReviewPanel(this, questionList);
        centerPanel.add(questionReviewPanel.getMainPanel());
        cardLayout.next(centerPanel);
    }

    private void repaintReview(){
        centerPanel.add(questionReviewPanel.getMainPanel());
        cardLayout.next(centerPanel);
    }

    private  void switchToVideoPanel(){
        videoPlayerArea.unlockVideoPlaying();
        studentDetailPanel.showQuestionList(questionReviewPanel.getQuestionList());

        centerPanel.add(videoPlayerArea.getMainPanel());
        cardLayout.next(centerPanel);
    }

    private void dispatchEndReviewEvent(EndReviewEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
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
        else if(event.getClass().equals(DeleteQuestionEvent.class)){
            studentDetailPanel.deleteQuestion(((DeleteQuestionEvent) event).getQuestion());
            videoPlayerArea.deleteQuestion(((DeleteQuestionEvent) event).getQuestion());
            repaintReview();
        }
        else if(event.getClass().equals(ReviewRequestEvent.class)){
            switchToReviewMode(((ReviewRequestEvent) event).getQuestionList());
        }
        else if(event.getClass().equals(EndReviewEvent.class)){
            dispatchEndReviewEvent((EndReviewEvent) event);
        }
        else if(event.getClass().equals(BackEvent.class)){
            switchToVideoPanel();
        }
    }
}
