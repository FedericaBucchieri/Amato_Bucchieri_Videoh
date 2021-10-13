package StudentLogin;

import EventManagement.BackEvent;
import EventManagement.Listener;
import EventManagement.StudentLoginEvent;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentLoginPanel extends JComponent {
    // The model of the component
    private StudentLoginPanelModel model;
    // The view of the component
    private StudentLoginPanelView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of StudentLoginPanel adding a SceneManager instance to the listeners list, creating a new model and a view
     * @param sceneManager the SceneManager instance to add to the listeners list
     */
    public StudentLoginPanel (SceneManager sceneManager){
        this.listeners.add(sceneManager);

        model = new StudentLoginPanelModel(this);
        view = new StudentLoginPanelView(this);
        view.installUI();
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    public StudentLoginPanelModel getModel() {
        return model;
    }

    @Override
    public void paintComponent(Graphics pen) {
        add(view.getMainPanel(), BorderLayout.CENTER);
    }

    /**
     * This method dispatches a new StudentLoginEvent event
     * @param username the username to pass inside the Event
     */
    public void sendUsername(String username){
        for (Listener listener : listeners)
            listener.listen(new StudentLoginEvent(username));
    }

    /**
     * This method dispatches a new BackEvent event
     */
    public void goBackToGeneralLogin(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }
}

