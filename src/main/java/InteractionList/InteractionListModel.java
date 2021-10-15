package InteractionList;

import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import Utils.Utils;
import services.InteractionService;
import services.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class InteractionListModel {
    // a boolean to indicate whether listeners are active or not
    public boolean listenersActive = true;
    // the component Controller
    private InteractionList controller;
    // the lenght of the all panel, proportioned to the video timeline
    private int generalLenght;
    // The list of interactions to be displayed
    private List<GenericInteraction> interactionList = new ArrayList<>();
    // the list of interactions drawing to be painted
    private List<InteractionDrawing> interactionDrawings = new ArrayList<>();
    // the selected interaction drawing
    private InteractionDrawing selectedInteractionDrawing;
    // a boolean to detect if the mouse is pressed
    private boolean mousePressed;
    // a boolean for the state of the deleteMode
    private boolean deleteMode;


    public InteractionListModel(int generalLenght, InteractionList controller) {
        this.generalLenght = generalLenght;
        this.controller = controller;
    }

    /**
     * This method sets the Interaction list attribute and for each interaction of the list, creates the corresponding InteractionDrawing to be painted
     * @param interactionList the list of interaction to set
     */
    public void setInteractionList(List<GenericInteraction> interactionList) {
        this.interactionList = interactionList;
        for (GenericInteraction interaction: interactionList){
                InteractionDrawing drawing = new InteractionDrawing(interaction, getCorrectPosition(interaction.getTimestamp()));
                interactionDrawings.add(drawing);
        }
    }

    /**
     * This method adds an interaction to the interactionList, creating the correspondent InteractionDrawing instance and adding it to the interactionDrawingList
     * @param interaction the interaction to be added
     */
    public void addInteractionToList(GenericInteraction interaction){
        this.interactionList.add(interaction);
        InteractionDrawing drawing = new InteractionDrawing(interaction, getCorrectPosition(interaction.getTimestamp()));
        interactionDrawings.add(drawing);
    }

    /**
     * This method gets the correct drawing position of the interaction tag, accordingly to the interaction timestamp
     * @param timestamp the timestamp to be converted in pixels
     * @return the pixel distance to draw the interactionDrawing in
     */
    private int getCorrectPosition(int timestamp){
        int correctValue = (timestamp * controller.getWidth())/generalLenght;
        int correction = correctValue / Utils.TAG_SIZE;
        correction = correction % Utils.TAG_SIZE;
        return correctValue - correction;
    }

    /**
     * This method convers a position in the InteractionPanel into a timestamp
     * @param position The position to be converted
     * @return the value correspondent to the position of the object, in terms of milliseconds
     */
    private int getCorrectTimestamp(int position){
        return (position * generalLenght) / controller.getWidth();
    }

    /**
     * This method updates the interaction timestamp after a dragging movement, accordingly to the new position of its representative drawing
     * @param interaction the interaction to update
     * @param newPosition the new position of the interaction drawing
     */
    public void updateInteractionTimestamp(GenericInteraction interaction, int newPosition){
        int newTimestamp = getCorrectTimestamp(newPosition);

        if(interaction instanceof Interaction){
            InteractionService service = new InteractionService();
            service.updateInteraction((Interaction) interaction, newTimestamp);
        }
        else if(interaction instanceof Question) {
            QuestionService service = new QuestionService();
            service.updateQuestionTimestamp((Question) interaction, newTimestamp);
        }
    }

    /**
     * this method deletes the selectedInteraction updating the db and the visual element of the UI
     */
    public void deleteSelectedInteraction(){
        GenericInteraction toBeDeleted = selectedInteractionDrawing.getInteraction();

        interactionDrawings.remove(selectedInteractionDrawing);
        interactionList.remove(toBeDeleted);

        if(toBeDeleted instanceof Interaction){
            InteractionService service = new InteractionService();
            service.deleteInteraction(((Interaction) toBeDeleted).getId());
        }
        else if (toBeDeleted instanceof Question){
            Question toDelete = (Question) toBeDeleted;
            QuestionService service = new QuestionService();
            service.deleteQuestion(toDelete.getId());
            controller.dispatchDeleteQuestionEvent(toDelete);
        }
    }

    /**
     * This method deletes a question from the interactionList and the corresponding InteractionDrawing from the interactionDrawings list
     * @param question the question to be deleted
     */
    public void deleteQuestion(Question question){
        interactionDrawings.removeIf(draw -> draw.getInteraction() == question);
        interactionList.removeIf(toDelete -> toDelete.getId() == question.getId());
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public List<GenericInteraction> getInteractionList() {
        return interactionList;
    }

    public List<InteractionDrawing> getInteractionDrawings() {
        return interactionDrawings;
    }

    public InteractionDrawing getSelectedInteractionDrawing() {
        return selectedInteractionDrawing;
    }

    public void setSelectedInteractionDrawing(InteractionDrawing selectedInteractionDrawing) {
        this.selectedInteractionDrawing = selectedInteractionDrawing;
    }
}
