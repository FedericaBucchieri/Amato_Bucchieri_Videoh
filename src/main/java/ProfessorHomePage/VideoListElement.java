package ProfessorHomePage;

import EventManagement.DeleteVideoEvent;
import EventManagement.GoToStatisticsEvent;
import EventManagement.Listener;
import EventManagement.ModifyVideoEvent;
import entities.Video;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VideoListElement extends JPanel{
    //The model of the videoListElement
    private VideoListElementModel model;
    //The view of the videoListElement
    private VideoListElementView view;
    //The video of the videoListElement
    //TODO: come mai sta sia qui che nel model?
    private Video video;
    private List<Listener> listeners = new ArrayList<>();

    /**
     * Creates a new videoListElement, that is the single row of the videoList to be displayed. This videoListElement will show
     * the preview of the video, the title, the description and all the buttons to modify it.
     * @param video: the video for which the VideoListElement has to be created
     * @param professorHomePageScene the parent component to be added to the listeners of the VideoListElement
     */
    public VideoListElement(Video video, ProfessorHomePageScene professorHomePageScene) {
        this.video = video;
        setBackground(Color.white);

        this.model = new VideoListElementModel(video);
        this.listeners.add(professorHomePageScene);

        this.view = new VideoListElementView();
        this.view.installUI(this);
    }


    public Video getVideo() {
        return video;
    }

    /**
     * Delete the video of this VideoListElement and dispatch DeleteVideoEvent to above listeners
     */
    public void deleteVideo(){
        model.deleteVideo();
        dispatchDeleteVideoEvent(this.getVideo());
    }

    /**
     * Dispatch a new DeleteVideoEvent to above listeners
     * @param video the video to pass to above listeners via the DeleteVideoEvent
     */
    private void dispatchDeleteVideoEvent(Video video){
        for (Listener listener : listeners)
            listener.listen(new DeleteVideoEvent(video));
    }


    /**
     * Dispatch a new ModifyVideoEvent to the above listeners
     */
    public void dispatchModifyVideoEvent(){
        for (Listener listener : listeners){
            listener.listen(new ModifyVideoEvent(video));
        }
    }

    /**
     * Dispatch a new GoToStatisticsEvent to the above listeners
     */
    public void dispatchGoToStatisticsEvent(){
        for (Listener listener : listeners){
            listener.listen(new GoToStatisticsEvent(video));
        }
    }

    /**
     * Add the main panel of the VideoListElementView to this VideoListElement
     * @param pen the unused Graphics
     */
    public void paintComponent(Graphics pen) {
        add(this.view.getMainPanel());
    }

}
