package ProfessorHomePage;

import EventManagement.BackEvent;
import EventManagement.CancelEvent;
import EventManagement.ErrorEvent;
import EventManagement.Listener;
import entities.Video;
import exceptions.UpdateVideoException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Utils.Utils;

import javax.swing.*;

public class ModifyVideoForm {
    private ModifyVideoFormModel model;
    private ModifyVideoFormView view;
    private List<Listener> listeners = new ArrayList<>();

    public ModifyVideoForm(ProfessorHomePage professorHomePage, Video video) {
        this.listeners.add(professorHomePage);

        this.model = new ModifyVideoFormModel(video);
        this.view = new ModifyVideoFormView(this);
        this.view.installUI();
    }

    public JPanel getMainPanel(){
        return this.view.getMainPanel();
    }

    public ModifyVideoFormModel getModel() {
        return model;
    }

    public void handleModifyRequest(String title, String description, File previewImage, File videoFile){
        try {
            model.modifyVideo(title, description, previewImage, videoFile);
            dispatchBackEvent();
        } catch (UpdateVideoException e) {
            dispatchErrorEvent();
        }
    }

    public void handleCancelRequest(){
        dispatchCancelEvent();
    }

    private void dispatchCancelEvent(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    private void dispatchBackEvent(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    private void dispatchErrorEvent(){
        for (Listener listener : listeners)
            listener.listen(new ErrorEvent(Utils.UPDATE_VIDEO_ERROR));
    }

}
