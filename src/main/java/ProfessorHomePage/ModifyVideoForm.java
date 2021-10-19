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
    //The model of the ModifyVideoForm component
    private ModifyVideoFormModel model;
    //The view of the ModifyVideoForm component
    private ModifyVideoFormView view;
    //The list of listeners that will handle the event dispatched by this Component
    private List<Listener> listeners = new ArrayList<>();

    /**
     * Creates a new ModifyVideoForm. It's the scene that is shown in the
     * @param professorHomePageScene: the parent component that will handle the  event dispatched by this Component
     * @param video the video to modify
     */
    public ModifyVideoForm(ProfessorHomePageScene professorHomePageScene, Video video) {
        this.listeners.add(professorHomePageScene);

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

    /**
     * Handle the request to modify the selected video
     * @param title the new title of the video
     * @param description the new description of the video
     * @param previewImage the new preview image of the video
     * @param videoFile the new file of the video
     */
    public void handleModifyRequest(String title, String description, File previewImage, File videoFile){
        try {
            model.modifyVideo(title, description, previewImage, videoFile);
            dispatchBackEvent();
        } catch (UpdateVideoException e) {
            dispatchErrorEvent();
        }
    }

    /**
     * Handle a new request to cancel the changes to the video.
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
     * Dispatch a new BackEvent to the above listeners
     */
    private void dispatchBackEvent(){
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    /**
     * Dispatch a new ErrorEvent to the above listeners
     */
    private void dispatchErrorEvent(){
        for (Listener listener : listeners)
            listener.listen(new ErrorEvent(Utils.UPDATE_VIDEO_ERROR));
    }

}
