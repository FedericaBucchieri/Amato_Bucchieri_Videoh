package StudentHomePage;

import EventManagement.Listener;
import EventManagement.UpdateQuestionEvent;
import entities.GenericInteraction;
import entities.Interaction;
import entities.Question;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionList extends JComponent {
    private InteractionListModel model;
    private InteractionListUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public InteractionList(int generalLength, InteractionPanel interactionPanel) {
        this.listeners.add(interactionPanel);

        this.model = new InteractionListModel(generalLength, this);
        this.ui = new InteractionListUI(this);
        this.ui.installUI();
    }

    public InteractionListModel getModel() {
        return model;
    }

    public void addInteractionToList(GenericInteraction interaction){
        model.addInteractionToList(interaction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ui.paint(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), Utils.ANNOTATION_PANEL_HEIGHT);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return getPreferredSize();
    }

    public void updateInteraction(GenericInteraction interaction, int newPosition){
        model.updateInteractionTimestamp(interaction, newPosition);

        if(interaction instanceof Question) {
            //dispatch UpdateQuestionEvent
            for (Listener listener : listeners)
                listener.listen(new UpdateQuestionEvent());
        }
    }
}
