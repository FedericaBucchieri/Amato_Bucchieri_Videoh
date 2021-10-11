package StudentHomePage;

import EventManagement.*;
import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import services.InteractionService;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionPanel implements Listener {
    private InteractionsPanelModel model;
    private InteractionPanelUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public InteractionPanel(VideoBox videoBox) {
        this.listeners.add(videoBox);

        this.model = new InteractionsPanelModel(videoBox);
        this.ui = new InteractionPanelUI(this);
        this.ui.install();
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public InteractionsPanelModel getModel() {
        return model;
    }

    public void handlePositiveInteraction(int timestamp){
        Interaction interaction = model.insertPositiveInteraction(timestamp);
        ui.printNewInteraction(interaction);
    }

    public void handleNegativeInteraction(int timestamp){
        Interaction interaction = model.insertNegativeInteraction(timestamp);
        ui.printNewInteraction(interaction);
    }

    public void handleQuestionInteraction(int timestamp){
        dispatchFreezeEvent();
        ui.displayQuestionTextField();
        model.setLastQuestionTimestamp(timestamp);
    }

    private void dispatchFreezeEvent(){
        for (Listener listener : listeners)
            listener.listen(new FreezeEvent());
    }

    public void sendQuestion(String text){
        Question question = model.insertNewQuestion(text);
        ui.hideQuestionTextField();
        ui.printNewInteraction(question);
        dispatchNewQuestionEvent(question);
    }

    public void deleteQuestion(Question question){
        ui.deleteQuestionFromInteractionList(question);
    }

    public void cancelQuestionInsertion(){
        ui.hideQuestionTextField();
        ui.printInteractionList();
        dispatchRestartAfterFreezeEvent();
    }

    private void dispatchNewQuestionEvent(Question question){
        for(Listener listener: listeners)
            listener.listen(new NewQuestionEvent(question));
    }

    private void dispatchRestartAfterFreezeEvent(){
        for (Listener listener : listeners)
            listener.listen(new RestartAfterFreezeEvent());
    }

    private void dispatchUpdateQuestionEvent(UpdateQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    public void handleDeleteRequest(boolean active){
        if(active) {
            dispatchFreezeEvent();
            this.ui.getInteractionList().activateDeleteMode();
        }
        else {
            dispatchRestartAfterFreezeEvent();
            this.ui.getInteractionList().deactivateDeleteMode();
        }
    }


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
    public InteractionPanelUI getUi() {
        return ui;
    }

    public void populateInteractionListByVideo(int videoID) {
        System.out.println("[interaction panel] populateInteractionListByVideo: "+videoID);
        model.populateInteractionListPerVideo(videoID);
        List<GenericInteraction> allListPerVideo = model.getInteractionList();
        this.ui.setInteractionList(allListPerVideo);

    }

    public void repaint() {
        this.ui.repaint();
    }

    public void disableListeners() {
        this.ui.disableListeners();
    }
}
