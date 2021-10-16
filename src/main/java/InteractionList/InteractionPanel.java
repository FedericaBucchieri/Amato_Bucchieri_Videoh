package InteractionList;

import EventManagement.*;
import VideoPlayer.VideoBox;
import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionPanel implements Listener {
    // The model of the component
    private InteractionsPanelModel model;
    // The view of the component
    private InteractionPanelView view;
    // The list of listeners for event handling
    private List<Listener> listeners = new ArrayList<>();

    /**
     * This constructor creates an instance of InteractionPanel adding a VideoBox instance to the listeners list, creating a new model and a view
     * @param videoBox the VideoBox instance to add to the listeners list
     */
    public InteractionPanel(VideoBox videoBox) {
        this.listeners.add(videoBox);

        this.model = new InteractionsPanelModel(videoBox);
        this.view = new InteractionPanelView(this);
        this.view.install();
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    public InteractionsPanelModel getModel() {
        return model;
    }

    /**
     * This method inserts a new positive Interaction in the database and tells the view to print it
     * @param timestamp the timestamp to create the interaction at
     */
    public void handlePositiveInteraction(int timestamp){
        Interaction interaction = model.insertPositiveInteraction(timestamp);
        view.printNewInteraction(interaction);
    }

    /**
     * This method inserts a new negative Interaction in the database and tells the view to print it
     * @param timestamp the timestamp to create the interaction at
     */
    public void handleNegativeInteraction(int timestamp){
        Interaction interaction = model.insertNegativeInteraction(timestamp);
        view.printNewInteraction(interaction);
    }

    /**
     * This method handles the request of making a new question at the time equals to the timestamp. So it freezes the video and displays the question Text field
     * @param timestamp the timestamp the question was required
     */
    public void handleQuestionInteraction(int timestamp){
        dispatchFreezeEvent();
        view.displayQuestionTextField();
        model.setLastQuestionTimestamp(timestamp);
    }

    /**
     * This method dispatches a new FreezeEvent
     */
    private void dispatchFreezeEvent(){
        for (Listener listener : listeners)
            listener.listen(new FreezeEvent());
    }

    /**
     * This method sends a New question, creating it to the database and dispatching it to the listeners to update other part of the ui
     * @param text the body of the question to be created
     */
    public void sendQuestion(String text){
        Question question = model.insertNewQuestion(text);
        view.hideQuestionTextField();
        view.printNewInteraction(question);
        dispatchNewQuestionEvent(question);
    }

    /**
     * This method deletes a question from the interactionList
     * @param question the question instance to be deleted
     */
    public void deleteQuestion(Question question){
        view.deleteQuestionFromInteractionList(question);
    }

    /**
     * This methods allows to stop the insertion of a new question, going back to the regular video watching
     */
    public void cancelQuestionInsertion(){
        view.hideQuestionTextField();
        view.printInteractionList();
        dispatchRestartAfterFreezeEvent();
    }

    /**
     * This method dispatches a new NewQuestionEvent
     * @param question the question to be dispatched in the event
     */
    private void dispatchNewQuestionEvent(Question question){
        for(Listener listener: listeners)
            listener.listen(new NewQuestionEvent(question));
    }

    /**
     * This method dispatches a new RestartAfterFreezeEvent
     */
    private void dispatchRestartAfterFreezeEvent(){
        for (Listener listener : listeners)
            listener.listen(new RestartAfterFreezeEvent());
    }

    /**
     * This method dispatches a new UpdateQuestionEvent
     * @param event the event to be dispatched
     */
    private void dispatchUpdateQuestionEvent(UpdateQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    /**
     * This method handles a delete request, toggling the deleteModel of the component
     * @param active a parameter to know whether is possible to delete interactions or not, currently
     */
    public void handleDeleteRequest(boolean active){
        if(active) {
            dispatchFreezeEvent();
        }
        else {
            dispatchRestartAfterFreezeEvent();
        }
        this.view.getInteractionList().toggleDeleteMode();
    }

    /**
     * This method dispatches a new DeleteQuestionEvent
     * @param event the event to be dispatched
     */
    private void dispatchDeleteQuestionEvent(DeleteQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    @Override
    public void listen(Event event) {
        if (event.getClass().equals(UpdateQuestionEvent.class)){
            dispatchUpdateQuestionEvent((UpdateQuestionEvent) event);
        }
        else if (event.getClass().equals(DeleteQuestionEvent.class)){
            dispatchDeleteQuestionEvent((DeleteQuestionEvent) event);
        }

    }



    public InteractionPanelView getView() {
        return view;
    }

    /**
     * This method populates the Interaction List getting all the interactions of a video from the db
     * @param videoID the video to take the interaction list of
     */
    public void populateInteractionListByVideo(int videoID) {
        model.populateInteractionListPerVideo(videoID);

        List<GenericInteraction> allListPerVideo = model.getInteractionList();
        this.view.setInteractionList(allListPerVideo);
        for (Listener listener: listeners){
            listener.listen(new InteractionListPopulatedEvent());
        }

    }

    public void repaint() {
        this.view.repaint();
    }

    public void disableListeners() {
        this.view.disableListeners();
    }
}
