package ProfessorLoginScene;

import EventManagement.BackEvent;
import EventManagement.ErrorEvent;
import EventManagement.Event;
import EventManagement.Listener;
import sceneManager.SceneManager;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorLoginForm extends JComponent implements Listener {
    private ProfessorLoginFormModel model;
    private ProfessorLoginFormUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public ProfessorLoginForm(SceneManager sceneManager) {
        this.listeners.add(sceneManager);

        this.model = new ProfessorLoginFormModel();
        this.model.addlisteners(sceneManager);
        this.model.addlisteners(this);

        this.ui = new ProfessorLoginFormUI();
        this.ui.installUI(this);
    }

    public ProfessorLoginFormModel getModel() {
        return model;
    }

    public void checkCredential(String usr, String pwd){
        model.checkCredential(usr, pwd);
    }

    @Override
    public void listen(Event event) {
        if(event.getClass().equals(ErrorEvent.class)) {
            this.ui.displayError(((ErrorEvent) event).getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        add(this.ui.getMainPanel());
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public void goBackToGeneralLogin(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.JFRAME_WIDTH, Utils.JFRAME_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }
}
