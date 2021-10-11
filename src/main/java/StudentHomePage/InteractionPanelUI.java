package StudentHomePage;

import entities.GenericInteraction;
import entities.Question;
import entities.Interaction;
import sceneManager.Utils;

import javax.swing.*;
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
    private JButton deleteButton;
    private InteractionList interactionList;
    private JTextField questionField;
    private JPanel mainPanel;
    private JPanel questionPanel;
    private JPanel interactionTimelinePanel;
    private CardLayout cardLayout;
    private JPanel generalInteractionsPanel;


    public JPanel getGeneralInteractionsPanel(int videoID) {
//TODO: devi cercare qui di restituire un interaction panel già popolato.
        controller.populateInteractionListByVideo(videoID);
        printInteractionList();
        return generalInteractionsPanel;
    }

    public JPanel getGeneralInteractionsPanel_due() {
//TODO: devi cercare qui di restituire un interaction panel già popolato.
        return generalInteractionsPanel;
    }



    public InteractionPanelUI(InteractionPanel interactionTimelinePanel) {
        this.controller = interactionTimelinePanel;

        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        generalInteractionsPanel = new JPanel();
        cardLayout = new CardLayout();
        generalInteractionsPanel.setLayout(cardLayout);
        mainPanel.add(generalInteractionsPanel);

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

        questionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleQuestionInteraction(model.getSliderValue());
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(model.isDeleteMode()) {
                    enableInteractionButtons();
                    controller.handleDeleteRequest(false);
                    deleteButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/trash-bin.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
                }
                else {
                    disableInteractionButtons();
                    controller.handleDeleteRequest(true);
                    deleteButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/cancel.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
                }
                model.setDeleteMode(!model.isDeleteMode());
            }
        });

    }

    public InteractionList getInteractionList() {
        return interactionList;
    }

    public void setupAnnotationDisplay() {
        interactionTimelinePanel = new JPanel(new BorderLayout());
        interactionList = new InteractionList(controller.getModel().getSliderMaximum(), controller);
        interactionList.setBorder(new LineBorder(Color.black));
        interactionTimelinePanel.add(Box.createHorizontalStrut(Utils.TIMELINE_BOXES), BorderLayout.WEST);
        interactionTimelinePanel.add(interactionList, BorderLayout.CENTER);
        interactionTimelinePanel.add(Box.createHorizontalStrut(Utils.TIMELINE_BOXES), BorderLayout.EAST);

        generalInteractionsPanel.add(interactionTimelinePanel);
        cardLayout.next(generalInteractionsPanel);
    }

    public void printNewInteraction(GenericInteraction interaction){
        //List<GenericInteraction> interactionList = controller.getModel().getInteractionList();
        //interactionListComponent.setInteractionList(interactionList);
        interactionList.addInteractionToList(interaction);
        interactionList.repaint();
    }

    public void deleteQuestionFromInteractionList(Question question){
        interactionList.deleteQuestionFromList(question);
        printInteractionList();
    }

    public void printInteractionList(){
        for (GenericInteraction interaction : interactionList.getModel().getInteractionList()){
            interactionList.repaint();
        }

    }

    public void setupAnnotationButtons(){
        JLabel title = new JLabel("Your annotation List");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

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

        deleteButton = new JButton();
        deleteButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/trash-bin.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteButton.setOpaque(true);
        deleteButton.setBorderPainted(false);
        buttonPanel.add(deleteButton);

        JLabel instructionsLine1 = new JLabel(Utils.LINE_1_INTERACTION);
        instructionsLine1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel instructionsLine2 = new JLabel(Utils.LINE_2_INTERACTION);
        instructionsLine2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(instructionsLine1);
        mainPanel.add(instructionsLine2);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    public void displayQuestionTextField(){
        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.X_AXIS));

        questionField = new JTextField(Utils.WRITE_QUESTION_TEXT);
        questionField.setVisible(true);
        questionPanel.add(questionField);

        questionField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                questionField.setText("");
            }
        });

        questionField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                    controller.sendQuestion(questionField.getText());
            }
        });

        JButton sendButton = new JButton();
        sendButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/check.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        sendButton.setOpaque(true);
        sendButton.setBorderPainted(false);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.sendQuestion(questionField.getText());
            }
        });
        questionPanel.add(sendButton);

        JButton cancelButton = new JButton();
        cancelButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/cancel.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        cancelButton.setOpaque(true);
        cancelButton.setBorderPainted(false);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.cancelQuestionInsertion();
            }
        });
        questionPanel.add(cancelButton);

        generalInteractionsPanel.add(questionPanel);
        cardLayout.next(generalInteractionsPanel);
        generalInteractionsPanel.repaint();
    }

    public void hideQuestionTextField(){
        generalInteractionsPanel.add(interactionTimelinePanel);
        cardLayout.next(generalInteractionsPanel);
        generalInteractionsPanel.repaint();
    }

    public void setInteractionList(List<GenericInteraction> allListPerVideo) {
        this.interactionList.getModel().setInteractionList(allListPerVideo);
    }

    private void disableInteractionButtons(){
        positiveInteractionButton.setEnabled(false);
        negativeInteractionButton.setEnabled(false);
        questionButton.setEnabled(false);
    }

    private void enableInteractionButtons(){
        positiveInteractionButton.setEnabled(true);
        negativeInteractionButton.setEnabled(true);
        questionButton.setEnabled(true);
    }
    public void repaint() {
        this.interactionList.repaint();
    }
}