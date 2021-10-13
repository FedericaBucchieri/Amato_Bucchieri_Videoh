package ProfessorHomePage;

import EventManagement.Listener;
import EventManagement.LogoutEvent;
import EventManagement.NewVideoRequestEvent;
import EventManagement.UpdateProfileRequestEvent;
import entities.Professor;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DetailPanel extends JComponent {
    private DetailPanelModel model;
    private DetailUI ui;
    private List<Listener> listeners = new ArrayList<>();


    public DetailPanel(Professor professor, ProfessorHomePage professorHomePage) {
        this.listeners.add(professorHomePage);

        this.model = new DetailPanelModel(professor);
        this.ui = new DetailUI();
        this.ui.installUI(this);
        this.setLayout(new BorderLayout());
    }

    public String getProfessorUsername(){
       return model.getProfessor().getUsername();
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public void handleNewVideoRequest(){
        dispatchNewVideoRequestEvent();
    }

    private void dispatchNewVideoRequestEvent(){
        for (Listener listener : listeners)
            listener.listen(new NewVideoRequestEvent());
    }


    public void handleUpdateProfileRequest(){
        dispatchUpdateProfileRequestEvent();
    }

    private void dispatchUpdateProfileRequestEvent(){
        for (Listener listener : listeners)
            listener.listen(new UpdateProfileRequestEvent());
    }

    public void handleLogout(){
        for (Listener listener : listeners)
            listener.listen(new LogoutEvent());
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
