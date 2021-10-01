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

public class MainLoginPanel extends JPanel { //controller


    private MainLoginPanelModel model;
    private MainLoginPanelView view;
    private List<Listener> listeners = new ArrayList<>();


    public MainLoginPanel (SceneManager sceneManager){
        model = new MainLoginPanelModel(this);
        view = new MainLoginPanelView(this);
        this.listeners.add(sceneManager);

        setPreferredSize(new Dimension(600, 800));
        setMinimumSize(getPreferredSize());
    }

    public MainLoginPanelModel getModel() {
        return model;
    }

    @Override
    public void paintComponent(Graphics pen) {
        //aggiungi al pannello che chiama il main panel
//        System.out.println("paintComponent del Main Login Panel");
        add(view.getMainPanel(), BorderLayout.CENTER);
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }


    public void dispatchStudentLoginPressed() {
        for (Listener listener : listeners)
            listener.listen(new LoginStudEvent());
    }

    public void dispatchProfLoginPressed() {
        for (Listener listener : listeners)
            listener.listen(new LoginProfEvent());

    }
}
