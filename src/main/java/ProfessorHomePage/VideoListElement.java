package ProfessorHomePage;

import EventManagement.DeleteVideoEvent;
import EventManagement.Listener;
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
        dispatchDeleteVideoEvent(this.ui.getMainPanel(), this.getVideo());
        model.deleteVideo();
    }

    private void dispatchDeleteVideoEvent(JPanel videoPanel, Video video){
        for (Listener listener : listeners)
            listener.listen(new DeleteVideoEvent(videoPanel, video));
    }


    public void handleModifyRequest(){
        //@TODO
        System.out.println("Modify Request");
    }

    public void handleStatisticRequest(){
        //@TODO
        System.out.println("Statistic request");
    }

    public void paintComponent(Graphics pen) {
        add(this.ui.getMainPanel());
    }

}
