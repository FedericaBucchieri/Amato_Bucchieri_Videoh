package StudentHomePage;

import EventManagement.Listener;
import entities.Interaction;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionPanel {
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
        model.insertPositiveInteraction(timestamp);
        ui.printNewInteraction();
    }

    public void handleNegativeInteraction(int timestamp){
        model.insertNegativeInteraction(timestamp);
        ui.printNewInteraction();
    }

}
