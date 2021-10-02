package StudentHomePage;

import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;

public class AnnotationPanelUI {
    private AnnotationPanel controller;
    private JButton insertPositiveAnnotation;
    private JButton insertNegativeAnnotation;
    private JButton insertQuestionAnnotation;
    private JPanel annotationDisplay;
    private JPanel mainPanel;

    public AnnotationPanelUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    }

    public void install(AnnotationPanel annotationPanel){
        this.controller = annotationPanel;
        setupAnnotationDisplay();
        setupAnnotationButtons();
    }

    public void setupAnnotationDisplay(){
        annotationDisplay = new JPanel();
        annotationDisplay.setBackground(Color.blue);
        annotationDisplay.setSize(new Dimension(controller.getModel().getSliderWidth(), Utils.ANNOTATION_PANEL_HEIGHT));
        annotationDisplay.setPreferredSize(new Dimension(controller.getModel().getSliderWidth(), Utils.ANNOTATION_PANEL_HEIGHT));
        mainPanel.add(annotationDisplay);
    }

    public void setupAnnotationButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        mainPanel.add(buttonPanel);

        insertPositiveAnnotation = new JButton();
        insertPositiveAnnotation.setIcon(new ImageIcon(new ImageIcon("src/main/images/like.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        insertPositiveAnnotation.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertPositiveAnnotation.setOpaque(true);
        insertPositiveAnnotation.setBorderPainted(false);
        buttonPanel.add(insertPositiveAnnotation);

        insertNegativeAnnotation = new JButton();
        insertNegativeAnnotation.setIcon(new ImageIcon(new ImageIcon("src/main/images/notlike.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        insertNegativeAnnotation.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertNegativeAnnotation.setOpaque(true);
        insertNegativeAnnotation.setBorderPainted(false);
        buttonPanel.add(insertNegativeAnnotation);

        insertQuestionAnnotation = new JButton();
        insertQuestionAnnotation.setIcon(new ImageIcon(new ImageIcon("src/main/images/faq.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        insertQuestionAnnotation.setAlignmentX(Component.CENTER_ALIGNMENT);
        insertQuestionAnnotation.setOpaque(true);
        insertQuestionAnnotation.setBorderPainted(false);
        buttonPanel.add(insertQuestionAnnotation);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
