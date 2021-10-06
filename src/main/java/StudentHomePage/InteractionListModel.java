package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import sceneManager.Utils;
import services.InteractionService;
import services.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class InteractionListModel {
    private InteractionList controller;
    private int generalLenght;
    private List<GenericInteraction> interactionList = new ArrayList<>();
    private List<InteractionDrawing> interactionDrawings = new ArrayList<>();
    private InteractionDrawing selectedInteractionDrawing;
    private boolean mousePressed;

    public InteractionListModel(int generalLenght, InteractionList controller) {
        this.generalLenght = generalLenght;
        this.controller = controller;
    }


    public void addInteractionToList(GenericInteraction interaction){
        this.interactionList.add(interaction);
        InteractionDrawing drawing = new InteractionDrawing(interaction, getCorrectPosition(interaction.getTimestamp()));
        interactionDrawings.add(drawing);
    }

    private int getCorrectPosition(int timestamp){
        int correctValue = (timestamp * controller.getWidth())/generalLenght;
        //TODO nel resize delle finestre questo Utils.TAG_SIZE non basta più
        int correction = correctValue / Utils.TAG_SIZE;
        correction = correction % Utils.TAG_SIZE;
        return correctValue - correction;
    }

    private int getCorrectTimestamp(int position){
        return (position * generalLenght) / controller.getWidth();
    }

    public void updateInteractionTimestamp(GenericInteraction interaction, int newPosition){
        int newTimestamp = getCorrectTimestamp(newPosition);

        if(interaction instanceof Interaction){
            InteractionService service = new InteractionService();
            service.updateInteraction((Interaction) interaction, newTimestamp);
        }
        else if(interaction instanceof Question) {
            QuestionService service = new QuestionService();
            service.updateQuestion((Question) interaction, newTimestamp);
        }
    }

    public boolean isMousePressed() {
        return mousePressed;
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
