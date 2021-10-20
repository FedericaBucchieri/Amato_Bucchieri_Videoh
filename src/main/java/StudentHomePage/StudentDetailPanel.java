package StudentHomePage;

import EventManagement.EndReviewEvent;
import EventManagement.Listener;
import EventManagement.LogoutEvent;
import EventManagement.ReviewRequestEvent;
import entities.Question;
import sceneManager.SceneManager;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDetailPanel extends JComponent{
    // The model of the component
    private StudentDetailPanelModel model;
    // The view of the component
    private StudentDetailPanelView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * Creates an instance of StudentDetailPanel adding a SceneManager instance to the listeners list, creating a new model and a view
     * @param sceneManager the SceneManager instance to add to the listeners list
     */
    public StudentDetailPanel(String username, StudentHomePageScene studentHomePageScene, SceneManager sceneManager) {
        this.listeners.add(studentHomePageScene);
        this.listeners.add(sceneManager);

        this.model = new StudentDetailPanelModel(username);
        this.view = new StudentDetailPanelView();
        this.view.installUI(this);
        this.setLayout(new BorderLayout());
    }

    public String getStudentUsername(){
        return model.getUsername();
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    /**
     * This method dispatches a LogoutEvent event
     */
    public void handleLogout(){
        for (Listener listener : listeners)
            listener.listen(new LogoutEvent());
    }

    /**
     * This method adds a question to the list of question in the model and then updates the QuestionList
     * @param question the question instance to add to the list
     */
    public void addQuestionToList(Question question){
        model.addQuestionToList(question);
        updateQuestionList();
    }

    public StudentDetailPanelModel getModel() {
        return model;
    }

    /**
     * This method updates the question list sorting it by descent date and asking the view to repaint it
     */
    public void updateQuestionList(){
        model.sortQuestionList();
        view.repaintQuestionList();
        repaint();
    }

    /**
     * This method deletes a question from the question view, asking the view to repaint the list
     * @param question the question instance to be deleted
     */
    public void deleteQuestion(Question question){
        model.removeQuestion(question);
        model.sortQuestionList();
        view.repaintQuestionList();
        repaint();
    }

    /**
     * This method dispatches a new ReviewRequestEvent
     */
    public void handleReviewRequest(){
        for (Listener listener : listeners)
            listener.listen(new ReviewRequestEvent(model.getQuestionList()));
    }

    /**
     * This method dispatches a new EndReviewEvent
     */
    public void handleEndVisionRequest(){
        for (Listener listener : listeners)
            listener.listen(new EndReviewEvent());
    }

    /**
     * This method asks the view to hide the questionList
     */
    public void hideQuestionList(){
        this.view.hideQuestionList();
    }

    /**
     * This method asks the view to show the questionList
     * @param questionList the list of question to be showed in the questionList
     */
    public void showQuestionList(List<Question> questionList){
        this.view.showQuestionList(questionList);
    }

    @Override
    protected void paintComponent(Graphics g) {
        add(this.view.getMainPanel());
    }

    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.DETAILPANEL_WIDTH, Utils.DETAILPANEL_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }

}
