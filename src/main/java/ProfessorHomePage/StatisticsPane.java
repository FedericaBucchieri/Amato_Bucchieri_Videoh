package ProfessorHomePage;

import EventManagement.CancelEvent;
import EventManagement.Listener;
import entities.Interaction;
import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPane extends JComponent {
    //The model of the StatisticsPane comopnent
    private StatisticsPaneModel model;
    //The view of the StatisticsPane comopnent
    private StatisticsPaneView view;
    //The list of listener that will handle the event dispatched by this component.
    private List<Listener> listeners = new ArrayList<>();

    /**
    * Creates a statistics pane for the selected video, containing a videoPlayer, a summmery of the interactions/questions posted by the students, a panel to see the interaction/question on the timeline and the list of all the quesiton.
     * It will show
     * @param video: the video to show the statistics for.
     * @param professorHomePage: the home page compomnent of the Professor, to add it to the list of listeners of events dispatched by StatisticsPane
     */
    public StatisticsPane (Video video, ProfessorHomePage professorHomePage){
        model = new StatisticsPaneModel(this, video);
        view = new StatisticsPaneView(this);
        this.listeners.add(professorHomePage);

    }

    public Video getVideo(){
        return model.getVideo();
    }

    public JPanel getMainPanel() {
        return this.view.getMainPanel();
    }

    public JScrollPane getScrollPane(){
        return this.view.getScrollPane();
    }

    public void dismissVideo() {
        view.dismissVideo();
    }

    /**
     * Go back to the professor home page
     */
    public void goToProfessorHomePage() {
        dismissVideo();
        dispatchCancelEvent();
    }

    /**
     * Dispatch a CancelEvent to the above listeners
     */
    private void dispatchCancelEvent(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    public void getInteractions() {
        model.getVideo().getInteractionList();
    }

    /**
     * Counts from the interaction list of the video the number of negative ones.
     * @return the number of negative interactions
     */
    public int getCountNegativeInteractions() {
        List<Interaction> interactions = getVideo().getInteractionList();
        int negativeCount = 0;
        for (Interaction interaction: interactions) {
            if (interaction.getType() == Utils.NEGATIVE_INTERACTION){
                negativeCount++;
            }
        }
        return negativeCount;
    }

    /**
     * Counts from the interaction list of the video the number of positive ones.
     * @return the number of positive interactions
     */
    public  int getCountPositiveInteractions(){
        List<Interaction> interactions = getVideo().getInteractionList();
        int positiveCount = 0;
        for (Interaction interaction: interactions) {
            if (interaction.getType() == Utils.POSITIVE_INTERACTION){
                positiveCount++;
            }
        }
        return positiveCount;
    }

    /**
     * Counts the total number of interaction from the interaction list of the video.
     * @return the number interactions
     */
    public int getTotalInteractions(){
        return getVideo().getInteractionList().size();

    }
}
