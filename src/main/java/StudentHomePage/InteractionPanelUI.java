package StudentHomePage;

import entities.GenericInteraction;
import entities.Interaction;
import sceneManager.Utils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InteractionPanelUI {
    private InteractionPanel controller;
    private JButton positiveInteractionButton;
    private JButton negativeInteractionButton;
    private JButton questionButton;
    //private JPanel interactionsDisplay;
    private InteractionDisplay interactionDisplay;
    private JPanel mainPanel;

    public InteractionPanelUI(InteractionPanel interactionPanel) {
        this.controller = interactionPanel;

        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        setupAnnotationDisplay();
        setupAnnotationButtons();
    }

    public void install(){
        InteractionsPanelModel model = controller.getModel();

        positiveInteractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handlePositiveInteraction(model.getSliderValue());
            }
        });

        negativeInteractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleNegativeInteraction(model.getSliderValue());
            }
        });


    }

    public void setupAnnotationDisplay() {
        JPanel interactionPanel = new JPanel(new BorderLayout());
        interactionDisplay = new InteractionDisplay( 55681);
        interactionDisplay.setBorder(new LineBorder(Color.black));
        interactionPanel.add(Box.createHorizontalStrut(Utils.TIMELINE_BOXES), BorderLayout.WEST);
        interactionPanel.add(interactionDisplay, BorderLayout.CENTER);
        interactionPanel.add(Box.createHorizontalStrut(Utils.TIMELINE_BOXES), BorderLayout.EAST);

        mainPanel.add(interactionPanel);
    }

    public void printNewInteraction(){
        List<GenericInteraction> interactionList = controller.getModel().getInteractionList();
        interactionDisplay.setInteractionList(interactionList);
        interactionDisplay.repaint();
    }

    public void setupAnnotationButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        mainPanel.add(buttonPanel);

        positiveInteractionButton = new JButton();
        positiveInteractionButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/like.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        positiveInteractionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        positiveInteractionButton.setOpaque(true);
        positiveInteractionButton.setBorderPainted(false);
        buttonPanel.add(positiveInteractionButton);

        negativeInteractionButton = new JButton();
        negativeInteractionButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/notlike.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        negativeInteractionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        negativeInteractionButton.setOpaque(true);
        negativeInteractionButton.setBorderPainted(false);
        buttonPanel.add(negativeInteractionButton);

        questionButton = new JButton();
        questionButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/faq.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        questionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionButton.setOpaque(true);
        questionButton.setBorderPainted(false);
        buttonPanel.add(questionButton);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
