package ProfessorHomePage;

import EventManagement.CancelEvent;
import EventManagement.Listener;
import entities.Interaction;
import entities.Question;
import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsPane extends JComponent {
    //The model of the StatisticsPane component
    private StatisticsPaneModel model;
    //The view of the StatisticsPane component
    private StatisticsPaneView view;
    //The list of listener that will handle the event dispatched by this component.
    private List<Listener> listeners = new ArrayList<>();

    /**
    * Creates a statistics pane for the selected video, containing a videoPlayer, a summary of the interactions/questions posted by the students, a panel to see the interaction/question on the timeline and the list of all the question.
     * It will show
     * @param videoId: the id of the video to show the statistics for.
     * @param professorHomePage: the home page component of the Professor, to add it to the list of listeners of events dispatched by StatisticsPane
     */
    public StatisticsPane (int videoId, ProfessorHomePage professorHomePage){
        model = new StatisticsPaneModel(this, videoId);
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

    /*
     * Counts from the interaction list of the video the number of negative ones.
     * @return the number of negative interactions

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
     */

    /**
     * This method returns the number of negative interactions associated to the video stored in the model of the component
     * @return the number of negative interactions
     */
    public int getCountNegativeInteractions(){
        return model.getNegativeInteractions().size();
    }

    /**
     * This method returns the number of positive interactions associated to the video stored in the model of the component
     * @return the number of positive interactions
     */
    public int getCountPositiveInteractions(){
        return model.getPositiveInteractions().size();
    }

    /*
     * Counts from the interaction list of the video the number of positive ones.
     * @return the number of positive interactions

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
    */

    /**
     * Counts the total number of interaction from the interaction list of the video.
     * @return the number interactions
     */
    public int getTotalInteractions(){
        return model.getTotalInteractions().size();
    }

    /**
     * Counts the total number of questions from the question list of the video.
     * @return the number questions
     */
    public int getTotalQuestions(){
        return model.getTotalQuestions().size();
    }

    /**
     * This method returns the list of questions associate to the video
     * @return the list of questions
     */
    public List<Question> getQuestions(){
        return model.getTotalQuestions();
    }
}
