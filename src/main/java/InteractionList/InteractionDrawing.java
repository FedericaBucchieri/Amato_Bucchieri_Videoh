package InteractionList;

import entities.GenericInteraction;
import entities.Interaction;
import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// This class represent the visual drawing of an interaction
public class InteractionDrawing extends JComponent {
    // the interaction represented by this drawing
    private GenericInteraction interaction;
    // the point where to draw the interaction
    private int x;
    // a boolean to understand if the interaction is selected or not by the user
    private boolean selected;

    public InteractionDrawing(GenericInteraction interaction, int x) {
        this.interaction = interaction;
        this.x = x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = getRightInteractionIcon(interaction);
        if(!isSelected())
            g.drawImage(image, x, 0, Utils.TAG_SIZE, Utils.TAG_SIZE, this);
        else
            g.drawImage(image, x, 0, Utils.TAG_SIZE + 20, Utils.TAG_SIZE + 20, this);
    }

    /**
     * This method returns the correct image to print accordingly to the Class type of the interaction instance
     * @param interaction The interaction to evaluate
     * @return the image corresponding to the evaluated interaction
     */
    public BufferedImage getRightInteractionIcon(GenericInteraction interaction){
        BufferedImage tagImage = null;
        try {

            if(interaction instanceof Interaction){ // it is an Interaction
                Interaction inter = (Interaction) interaction;
                    if(inter.getType() == Utils.POSITIVE_INTERACTION)
                        tagImage = ImageIO.read(new File("src/main/images/positiveTag.png"));
                    else
                        tagImage = ImageIO.read(new File("src/main/images/negativeTag.png"));
            }
            else // it is a Question
                tagImage = ImageIO.read(new File("src/main/images/questionTag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tagImage;
    }

    /**
     * This method returns if a point is part of the drawing instance or not
     * @param point the point to check
     * @return true if the drawing contains the point, false otherwise
     */
    public boolean contain(Point point){
        return point.x >= x && point.x < x + Utils.TAG_SIZE && point.y >= 0 && point.y < Utils.TAG_SIZE;
    }

    public void setX(int x) {
        this.x = x;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public GenericInteraction getInteraction() {
        return interaction;
    }
}
