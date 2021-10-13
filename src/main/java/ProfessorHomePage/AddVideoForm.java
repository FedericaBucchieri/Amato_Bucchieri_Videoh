package ProfessorHomePage;

import EventManagement.CancelEvent;
import EventManagement.ErrorEvent;
import EventManagement.Listener;
import EventManagement.NewVideoEvent;
import entities.Video;
import uk.co.caprica.vlcj.player.media.Media;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddVideoForm extends JComponent {
    private AddVideoFormModel model;
    private AddVideoFormUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public AddVideoForm(ProfessorHomePage professorHomePage) {
        this.listeners.add(professorHomePage);
        this.setLayout(new BorderLayout());

        this.model = new AddVideoFormModel(this, professorHomePage.getProfessor());
        this.ui = new AddVideoFormUI(this);
    }

    public JPanel getMainPanel(){
        return this.ui.getMainPanel();
    }

    public void handleNewVideoRequest(String title, String description, File previewImage, File file){
        Video newVideo = model.createNewVideo(title, description, previewImage, file);
        if(newVideo != null)
            dispatchNewVideoEvent(newVideo);
    }

    private void dispatchNewVideoEvent(Video video){
        for (Listener listener : listeners)
            listener.listen(new NewVideoEvent(video));
    }

    public void handleCancelRequest(){
        dispatchCancelEvent();
    }

    private void dispatchCancelEvent(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    public void reportError(String error){
        ui.displayError(error);
    }
}
