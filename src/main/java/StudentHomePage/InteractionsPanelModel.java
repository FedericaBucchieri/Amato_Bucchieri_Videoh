package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import sceneManager.Utils;
import services.InteractionService;

import java.util.ArrayList;
import java.util.List;

public class InteractionsPanelModel {
    private VideoBox videoBox;
    private List<GenericInteraction> interactionList;

    public InteractionsPanelModel(VideoBox videoBox) {
        this.videoBox = videoBox;
        interactionList = new ArrayList<>();
    }

    public int getSliderWidth(){
        return videoBox.getSlider().getWidth();
    }

    public int getSliderValue(){
        return videoBox.getSlider().getValue();
    }

    public void insertPositiveInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.POSITIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);
    }

    public void insertNegativeInteraction(int timestamp){
        InteractionService service = new InteractionService();
        Interaction interaction = service.createInteraction(Utils.NEGATIVE_INTERACTION, videoBox.getVideoId(), timestamp);
        interactionList.add(interaction);
    }

    public List<GenericInteraction> getInteractionList() {
        return interactionList;
    }
}
