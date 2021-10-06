package StudentHomePage;

import EventManagement.Event;
import EventManagement.Listener;
import EventManagement.LogoutEvent;
import entities.Question;
import sceneManager.SceneManager;
import sceneManager.Utils;

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
        ui.displayNewQuestion(question);
    }

    public StudentDetailPanelModel getModel() {
        return model;
    }

    public void updateQuestionList(){
        ui.repaintQuestionList();
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
