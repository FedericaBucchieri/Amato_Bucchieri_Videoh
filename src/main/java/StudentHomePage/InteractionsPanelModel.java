package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import sceneManager.Utils;
import services.InteractionService;
import services.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class InteractionsPanelModel {
    private VideoBox videoBox;
    private List<GenericInteraction> interactionList;
    private int currentQuestionTimestamp;
    private String username;

    public InteractionsPanelModel(VideoBox videoBox) {
        this.videoBox = videoBox;
        this.username = videoBox.getModel().getUsername();
        interactionList = new ArrayList<>();
    }

    public int getSliderWidth(){
        return videoBox.getSlider().getWidth();
    }

    public int getSliderMaximum(){
        return videoBox.getSlider().getMaximum();
    }

    public int getSliderValue(){
        return videoBox.getSlider().getValue();
    }

    public Interaction insertPositiveInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.POSITIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    public Interaction insertNegativeInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.NEGATIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);

        return interaction;
    }

    public List<GenericInteraction> getInteractionList() {
        return interactionList;
    }

    public void setLastQuestionTimestamp(int timestamp){
        currentQuestionTimestamp = timestamp;
    }

    public Question insertNewQuestion(String text){
        QuestionService service = new QuestionService();
        Question question = service.createQuestion(text, username, videoBox.getVideoId(), currentQuestionTimestamp);
        interactionList.add(question);

        return question;
    }
}
