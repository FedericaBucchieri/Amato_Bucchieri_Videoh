package ProfessorHomePage;

import EventManagement.CancelEvent;
import EventManagement.Listener;
import entities.Video;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPane extends JComponent {
    private StatisticsPaneModel model;
    private StatisticsPaneUI UI;

    private List<Listener> listeners = new ArrayList<>();

    public StatisticsPane (Video video, ProfessorHomePage professorHomePage){
        model = new StatisticsPaneModel(this, video);
        UI = new StatisticsPaneUI(this);


        this.listeners.add(professorHomePage);

    }

    public Video getVideo(){
        return model.getVideo();
    }

    public JPanel getMainPanel() {
        return this.UI.getMainPanel();
    }

    public JScrollPane getScrollPane(){
        return this.UI.getScrollPane();
    }

    public void dismissVideo() {
        UI.dismissVideo();
    }

    public void goToProfessorHomePage() {
        dismissVideo();
        dispatchCancelEvent();
    }

    private void dispatchCancelEvent(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    public void getInteractions() {
        model.getVideo().getInteractionList();
    }
}
