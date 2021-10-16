package StudentHomePage;

import EventManagement.*;
import EventManagement.Event;
import VideoPlayer.VideoPlayerArea;
import entities.Question;
import entities.Video;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentHomePageScene implements Listener, Scene{
    // A component of the Scene: a videoPlayerArea to display the Video
    private VideoPlayerArea videoPlayerArea;
    // A component of the Scene: the student detail panel to be displayed on the right
    private StudentDetailPanel studentDetailPanel;
    // A componet of the Scene: a panel to review all student questions
    private QuestionReviewPanel questionReviewPanel;
    // The logged student username
    private String username;
    // the main panel of the scene
    private JPanel mainPanel;
    // The center panel of the scene, on the left part
    private JPanel centerPanel;
    // the right panel of the scene, on the right part
    private JPanel rightPanel;
    // A card layout to switch panel in the centerPanel
    private CardLayout cardLayout;
    // A list of Listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of StudentHomePageScene, adding the main sceneManager as listener, creating the mainPanels of the scene
     * @param sceneManager The main SceneManager to add to the listeners list
     * @param video The video to be shown to the student
     * @param username The string containing the logged in student
     */
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

    /**
     * This method sets up the CenterPanel creating a cardLayout to display panels when needed
     * @param sceneManager the sceneManager instance to pass to the VideoPlayerArea component
     * @param video the Video instance do be displayed to the student
     */
    public void setupCentralPanel(SceneManager sceneManager, Video video){
        cardLayout = new CardLayout();
        this.centerPanel = new JPanel();
        centerPanel.setLayout(cardLayout);

        videoPlayerArea = new VideoPlayerArea(sceneManager, this, video, username);
        centerPanel.add(videoPlayerArea.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * This method sets up the RightPanel
     * @param sceneManager the sceneManager instance to pass to the StudentDetailPanel component
     */
    public void setupRightPanel(SceneManager sceneManager){
        this.rightPanel = new JPanel();
        studentDetailPanel = new StudentDetailPanel(username,this, sceneManager);
        rightPanel.add(studentDetailPanel.getMainPanel());
        rightPanel.setBackground(Color.decode("#42577F"));
    }

    /**
     * This method allows to display the questionReviewPanel in the centerPanel
     * @param questionList the list of questions to be displayed for review
     */
    private void switchToReviewMode(List<Question> questionList){
        videoPlayerArea.stopVideoPlaying();
        studentDetailPanel.hideQuestionList();

        questionReviewPanel = new QuestionReviewPanel(this, questionList);
        centerPanel.add(questionReviewPanel.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * This method repaint the question list in the questionReviewPanel
     */
    private void repaintReview(){
        centerPanel.add(questionReviewPanel.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * This method allows to display the VideoPlayerArea in the centerPanel
     */
    private  void switchToVideoPanel(){
        videoPlayerArea.unlockVideoPlaying();
        studentDetailPanel.showQuestionList(questionReviewPanel.getQuestionList());

        centerPanel.add(videoPlayerArea.getMainPanel());
        cardLayout.next(centerPanel);
    }

    /**
     * This method dispatches an EndReviewEvent event
     * @param event the event to be dispatched
     */
    private void dispatchEndReviewEvent(EndReviewEvent event){
        videoPlayerArea.dismissVideo();
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
            if(questionReviewPanel != null)
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
