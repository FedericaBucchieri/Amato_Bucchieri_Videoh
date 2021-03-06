package InteractionList;

import EventManagement.DeleteQuestionEvent;
import EventManagement.Listener;
import EventManagement.UpdateQuestionEvent;
import entities.GenericInteraction;
import entities.Question;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

// this class represent the list of interaction to be displayed inside the InteractionPanel
public class InteractionList extends JComponent {
    // The model of the component
    private InteractionListModel model;
    // the view of the component
    private InteractionListView view;
    // the list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of InteractionList assigning a InteractionPanel to its listeners
     * @param generalLength the lenght of the list Panel to be displayed
     * @param interactionPanel the interaction panel that contains the list
     */
    public InteractionList(int generalLength, InteractionPanel interactionPanel) {
        this.listeners.add(interactionPanel);

        this.model = new InteractionListModel(generalLength, this);
        this.view = new InteractionListView(this);
        this.view.installUI();

    }


    public InteractionListModel getModel() {
        return model;
    }

    public void addInteractionToList(GenericInteraction interaction){
        model.addInteractionToList(interaction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        view.paint(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), Utils.ANNOTATION_PANEL_HEIGHT);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return getPreferredSize();
    }

    /**
     * This method updates an instance of generic interaction updating its position
     * @param interaction the interaction to update
     * @param newPosition the new position value
     */
    public void updateInteraction(GenericInteraction interaction, int newPosition){
        model.updateInteractionTimestamp(interaction, newPosition);

        if(interaction instanceof Question) {
            dispatchUpdateQuestionEvent();
        }
    }

    /**
     * This method dispatches a new UpdateQuestionEvent
     */
    public void dispatchUpdateQuestionEvent(){
        for (Listener listener : listeners)
            listener.listen(new UpdateQuestionEvent());
    }

    /**
     * This method allow to delete a question from the db
     * @param question the question instance to delete
     */
    public void deleteQuestionFromList(Question question){
        model.deleteQuestion(question);
        //repaint();
    }

    /**
     * This method dispatches a new DeleteQuestionEvent
     * @param question the question to be dispatched in the event
     */
    public void dispatchDeleteQuestionEvent(Question question){
        for (Listener listener : listeners)
            listener.listen(new DeleteQuestionEvent(question));
    }

    /**
     * This method sets the listener to unactive. A boolean variable in the model will hold this status.
     */
    public void disableListeners() {
        model.setListenersActive(false);
    }

    /**
     * This method toggles the state of the deleteMode boolean of the component state
     */
    public void toggleDeleteMode(){
        model.setDeleteMode(!model.isDeleteMode());
    }
}
