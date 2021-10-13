package GeneralLogin;

import EventManagement.ErrorEvent;
import EventManagement.Listener;
import EventManagement.LoginProfEvent;
import EventManagement.LoginStudEvent;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainLoginPanel extends JPanel {
    // The model of the component
    private MainLoginPanelModel model;
    // The view of the component
    private MainLoginPanelView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of MainLoginPanel adding a SceneManager instance to the listeners list, creating a new model and a view
     * @param sceneManager the SceneManager instance to add to the listeners list
     */
    public MainLoginPanel (SceneManager sceneManager){
        model = new MainLoginPanelModel(this);
        view = new MainLoginPanelView(this);
        this.listeners.add(sceneManager);
    }

    public MainLoginPanelModel getModel() {
        return model;
    }

    @Override
    public void paintComponent(Graphics pen) {
        add(view.getMainPanel(), BorderLayout.CENTER);
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    /**
     * This method dispatches a new LoginStudEvent event
     */
    public void dispatchStudentLoginPressed() {
        for (Listener listener : listeners)
            listener.listen(new LoginStudEvent());
    }

    /**
     * This method dispatches a new LoginProfEvent event
     */
    public void dispatchProfLoginPressed() {
        for (Listener listener : listeners)
            listener.listen(new LoginProfEvent());

    }
}
