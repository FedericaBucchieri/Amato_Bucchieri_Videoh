package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import sceneManager.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InteractionDrawing extends JComponent {
    private GenericInteraction interaction;
    private int x;

    public InteractionDrawing(GenericInteraction interaction, int x) {
        this.interaction = interaction;
        this.x = x;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image image = getRightInteractionIcon(interaction);
        g.drawImage(image, x, 0, Utils.TAG_SIZE, Utils.TAG_SIZE, this);
    }

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
}
