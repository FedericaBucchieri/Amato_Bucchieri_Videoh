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
    //The model of the DetailPanel component
    private DetailPanelModel model;
    //The view of the DetailPanel component
    private DetailPanelView view;
    //The list of listeners that will handle the event dispatched by this component
    private List<Listener> listeners = new ArrayList<>();


    /**
     * Creates a new DetailPanel component. It is the right blue panel where all the info of the professor are displayed
     * and whete these can be managed
     * @param professor the logged in professor
     * @param professorHomePageScene the parnet component that creates the DetailPanel. it is a listener for this DetailPanel component
     */
    public DetailPanel(Professor professor, ProfessorHomePageScene professorHomePageScene) {
        this.listeners.add(professorHomePageScene);

        this.model = new DetailPanelModel(professor);
        this.view = new DetailPanelView();
        this.view.installUI(this);
        this.setLayout(new BorderLayout());
    }

    public String getProfessorUsername(){
       return model.getProfessor().getUsername();
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    /**
     * Handles the request to create a new video.
     */
    public void handleNewVideoRequest(){
        dispatchNewVideoRequestEvent();
    }

    /**
     * Dispatches a new NewVideoRequestEvent to the above listeners
     */
    private void dispatchNewVideoRequestEvent(){
        for (Listener listener : listeners)
            listener.listen(new NewVideoRequestEvent());
    }

    /**
     * Handle the request to Update the Profile
     */
    public void handleUpdateProfileRequest(){
        dispatchUpdateProfileRequestEvent();
    }

    /**
     * Dispatches a new UpdateProfileRequestEvent to the above listeners
     */
    private void dispatchUpdateProfileRequestEvent(){
        for (Listener listener : listeners)
            listener.listen(new UpdateProfileRequestEvent());
    }

    /**
     * Handles the request of log out dispatching a new LogoutEvent to the above listeners
     */
    public void handleLogout(){
        for (Listener listener : listeners)
            listener.listen(new LogoutEvent());
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
