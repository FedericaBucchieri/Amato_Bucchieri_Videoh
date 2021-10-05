package StudentHomePage;

import entities.GenericInteraction;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InteractionDisplay extends JComponent {
    private int width;
    private int generalLenght;
    private List<GenericInteraction> interactionList = new ArrayList<>();

    public InteractionDisplay(int generalLenght) {
        this.generalLenght = generalLenght;
    }

    private int getCorrectPosition(int timestamp){
        int correctValue = (timestamp * getWidth())/generalLenght;
        //TODO nel resize delle finestre questo Utils.TAG_SIZE non basta più
        int correction = correctValue / Utils.TAG_SIZE;
        correction = correction % Utils.TAG_SIZE;
        return correctValue - correction;
    }

    public void setInteractionList(List<GenericInteraction> interactionList) {
        this.interactionList = interactionList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        InteractionDrawing drawing = null;

        for(GenericInteraction interaction: interactionList) {
            drawing = new InteractionDrawing(interaction, getCorrectPosition(interaction.getTimestamp()));
            drawing.paintComponent(g);
        }

        System.out.println(getWidth());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), 50);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return getPreferredSize();
    }
}
