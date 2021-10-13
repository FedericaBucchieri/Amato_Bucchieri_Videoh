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
    private VideoListElementModel model;
    private VideoListElementUI ui;
    private Video video;
    private List<Listener> listeners = new ArrayList<>();

    public VideoListElement(Video video, ProfessorHomePage professorHomePage) {
        this.video = video;
        setBackground(Color.white);

        this.model = new VideoListElementModel(video);
        this.listeners.add(professorHomePage);

        this.ui = new VideoListElementUI();
        this.ui.installUI(this);
    }


    public Video getVideo() {
        return video;
    }

    public void deleteVideo(){
        model.deleteVideo();
        dispatchDeleteVideoEvent(this.getVideo());
    }

    private void dispatchDeleteVideoEvent(Video video){
        for (Listener listener : listeners)
            listener.listen(new DeleteVideoEvent(video));
    }


    public void handleModifyRequest(){
        for (Listener listener : listeners){
            listener.listen(new ModifyVideoEvent(video));
        }
    }

    public void handleStatisticRequest(){
        //@TODO
        for (Listener listener : listeners){
            listener.listen(new GoToStatisticsEvent(video));
        }
    }

    public void paintComponent(Graphics pen) {
        add(this.ui.getMainPanel());
    }

}
