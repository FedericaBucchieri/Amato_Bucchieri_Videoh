package StudentInsertCode;

import EventManagement.BackEvent;
import EventManagement.GoToVideoEvent;
import EventManagement.Listener;
import entities.Video;
import sceneManager.SceneManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InsertCodeForm extends JComponent {
    // The model of the component
    private InsertCodeFormModel model;
    // The view of the component
    private InsertCodeFormView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of InsertCodeForm adding the SceneManager to the listeners list
     * @param sceneManager the general SceneManager instance
     * @param studentUsername the logged in student username String
     */
    public InsertCodeForm(SceneManager sceneManager, String studentUsername) {
        this.listeners.add(sceneManager);

        this.model = new InsertCodeFormModel(this, studentUsername);
        this.view = new InsertCodeFormView(this);
        this.view.installUI();
    }

    public JPanel getMainPanel() {
        return view.getMainPanel();
    }

    /**
     * This method dispatches a BackEvent event
     */
    public void dispatchBackEvent() {
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    public String getStudentUsername() {
        return model.getStudentUsername();
    }

    /**
     * This method handles a videoRequest, asking the model to retrieve the video correspondent to the videoCode and dispatching a GoToVideoEvent fi the video exists, an ErrorEvent otherwise
     * @param videoCode a string containing the videoCode to search for
     * @param username the student username to pass to the next scene
     */
    public void goToStudentHomePage(String videoCode, String username) {
        Video video = model.searchVideoByCode(Integer.parseInt(videoCode));

        if(video == null)
            view.displayError();
        else {
            for (Listener listener : listeners) {
                listener.listen(new GoToVideoEvent(video, username));
            }
        }

    }
}
