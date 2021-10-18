package ProfessorHomePage;

import EventManagement.CancelEvent;
import EventManagement.Listener;
import EventManagement.NewVideoEvent;
import entities.Video;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddVideoForm extends JComponent {
    //The model of the AddVideoForm component
    private AddVideoFormModel model;
    //The view of the AddVideoForm component
    private AddVideoFormView view;
    //The list of listeners that will react to event dispatched by the AddVideoForm component
    private List<Listener> listeners = new ArrayList<>();

    /**
     * Creaates an AddVideoForm component. IT is the scene shown on top of the ProfessorHomePage when the AddVideo button is pressed
     * @param professorHomePageScene: the listener of the event dispatched by the AddVideoForm component
     */
    public AddVideoForm(ProfessorHomePageScene professorHomePageScene) {
        this.listeners.add(professorHomePageScene);
        this.setLayout(new BorderLayout());

        this.model = new AddVideoFormModel(this, professorHomePageScene.getProfessor());
        this.view = new AddVideoFormView(this);
    }

    public JPanel getMainPanel(){
        return this.view.getMainPanel();
    }


    /**
     * Handles the requests of adding new video. This func is called whenever the addvideo button is pressed. after the
     * creation of the video, it call the method dispatchNewVideoEvent
     * @param title the title of the video to add
     * @param description the description of the video to add
     * @param previewImage the thumbnail of the video to add
     * @param file the actual file of the video
     */
    public void handleNewVideoRequest(String title, String description, File previewImage, File file){
        Video newVideo = model.createNewVideo(title, description, previewImage, file);
        if(newVideo != null)
            dispatchNewVideoEvent(newVideo);
    }

    /**
     * Dispatch a NewVideoEvent event to the above listeners
     * @param video the video to pass via the dispatched NewVideoEvent
     */
    private void dispatchNewVideoEvent(Video video){
        for (Listener listener : listeners)
            listener.listen(new NewVideoEvent(video));
    }

    /**
     * Handle a new request of cancelling a the video add process or a simple request to go back to the professor home page
     */
    public void handleCancelRequest(){
        dispatchCancelEvent();
    }

    /**
     * Dispatch a new CancelEvent to the above listeners
     */
    private void dispatchCancelEvent(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    /**
     * Asks the view to display the reportError
     * @param error: the error the view is asked to display
     */
    public void reportError(String error){
        view.displayError(error);
    }
}
