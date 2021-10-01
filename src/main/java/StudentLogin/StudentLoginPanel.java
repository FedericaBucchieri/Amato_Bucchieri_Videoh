package StudentLogin;

import EventManagement.BackEvent;
import EventManagement.Listener;
import EventManagement.StudentLoginEvent;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StudentLoginPanel extends JComponent { //controller
    private StudentLoginPanelModel model;
    private StudentLoginPanelView view;
    private List<Listener> listeners = new ArrayList<>();

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

    public void sendUsername(String username){
        for (Listener listener : listeners)
            listener.listen(new StudentLoginEvent(username));
    }

    public void goBackToGeneralLogin(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }
}

