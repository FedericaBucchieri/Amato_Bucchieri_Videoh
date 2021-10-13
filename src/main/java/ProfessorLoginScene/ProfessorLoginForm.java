package ProfessorLoginScene;

import EventManagement.*;
import EventManagement.Event;
import entities.Professor;
import sceneManager.SceneManager;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorLoginForm extends JComponent implements Listener {
    // The model of the component
    private ProfessorLoginFormModel model;
    // The view of the component
    private ProfessorLoginFormView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of ProfessorLoginForm adding a SceneManager instance to the listeners list, creating a new model and a view
     * @param sceneManager the SceneManager instance to add to the listeners list
     */
    public ProfessorLoginForm(SceneManager sceneManager) {
        this.listeners.add(sceneManager);

        this.model = new ProfessorLoginFormModel(this);
        this.model.addListeners(sceneManager);
        this.model.addListeners(this);

        this.view = new ProfessorLoginFormView();
        this.view.installUI(this);
    }

    public ProfessorLoginFormModel getModel() {
        return model;
    }

    /**
     * This method handles professor login, asking the model to check the user credentials
     * @param usr the String containing the username of the professor
     * @param pwd the String containing the password of the professor
     */
    public void checkCredential(String usr, String pwd){
        model.checkCredential(usr, pwd);
    }



    /**
     * This method dispatches an errorEvent event
     * @param error the error String to pass inside the event
     */
    public void dispatchErrorEvent(String error){
        for (Listener listener : listeners)
            listener.listen(new ErrorEvent(error));
    }

    /**
     * This method dispatches a LoginEvent event
     * @param professor the Professor instance to pass inside the event
     */
    public void dispatchLoginEvent(Professor professor){
        for (Listener listener : listeners)
            listener.listen(new ProfessorLoginEvent(professor));
    }

    @Override
    public void listen(Event event) {
        if(event.getClass().equals(ErrorEvent.class)) {
            this.view.displayError(((ErrorEvent) event).getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        add(this.view.getMainPanel());
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    /**
     * This method dispatches a BackEvent event
     */
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
