package StudentHomePage;

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
    private StudentDetailPanelModel model;
    private StudentDetailPanelUI ui;
    private List<Listener> listeners = new ArrayList<>();


    public StudentDetailPanel(String username, StudentHomePageScene studentHomePageScene, SceneManager sceneManager) {
        this.listeners.add(studentHomePageScene);
        this.listeners.add(sceneManager);

        this.model = new StudentDetailPanelModel(username);
        this.ui = new StudentDetailPanelUI();
        this.ui.installUI(this);
        this.setLayout(new BorderLayout());
    }

    public String getStudentUsername(){
        return model.getUsername();
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public void handleLogout(){
        for (Listener listener : listeners)
            listener.listen(new LogoutEvent());
    }

    public void addQuestionToList(Question question){
        model.addQuestionToList(question);
        updateQuestionList();
    }

    public StudentDetailPanelModel getModel() {
        return model;
    }

    public void updateQuestionList(){
        model.sortQuestionList();
        ui.repaintQuestionList();
        repaint();
    }

    public void deleteQuestion(Question question){
        model.removeQuestion(question);
        ui.repaintQuestionList();
        repaint();
    }

    public void handleReviewRequest(){
        for (Listener listener : listeners)
            listener.listen(new ReviewRequestEvent(model.getQuestionList()));
    }

    public void hideQuestionList(){
        this.ui.hideQuestionList();
    }


    public void showQuestionList(List<Question> questionList){
        this.ui.showQuestionList(questionList);
    }

    @Override
    protected void paintComponent(Graphics g) {
        add(this.ui.getMainPanel());
    }

    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.DETAILPANEL_WIDTH, Utils.DETAILPANEL_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }

}
