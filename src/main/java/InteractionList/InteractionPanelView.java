package InteractionList;

import entities.GenericInteraction;
import entities.Question;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class InteractionPanelView {
    //The controller of the InteractionPanel
    private InteractionPanel controller;
    //The button to add a positiveInteraction
    private JButton positiveInteractionButton;
    //the button to add a negativeInteraction
    private JButton negativeInteractionButton;
    //the button to add a question
    private JButton questionButton;
    //The button to activate the delete mode to delete interactions/questions from the
    private JButton deleteButton;
    //The interactionList element containing all the interactions
    private InteractionList interactionList;
    //The textField to write the question
    private JTextField questionField;
    //The main panel containing all the interactions/questions panel
    private JPanel mainPanel;
    //The panel that substitutes the whole interaction panel with a panel just for the question. it contains the text field and the confirm question/discard question buttons
    private JPanel questionPanel;
    //
    private JPanel interactionTimelinePanel;
    //The layout that manages the changes between the questionPanel and the interactionPanel
    private CardLayout cardLayout;
    //
    private JPanel generalInteractionsPanel;
    //instructionsLine1 & instructionsLine2 are the 2 info labels at the bottom of the window
    private JLabel instructionsLine1;
    private JLabel instructionsLine2;
    //The button to confirm a posted question
    private JButton sendButton;
    //The button to discard the posted question
    private JButton cancelButton;


    /**
     * This constructor creates an instance of InteractionPanelView, adding all the UI element required to the mainPanel
     * @param controller the InteractionPanel instance that is the component controller
     */
    public InteractionPanelView(InteractionPanel controller) {
        this.controller = controller;

        mainPanel = new JPanel();
        mainPanel.setLayout( new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        generalInteractionsPanel = new JPanel();
        cardLayout = new CardLayout();
        generalInteractionsPanel.setLayout(cardLayout);
        mainPanel.add(generalInteractionsPanel);

        setupAnnotationDisplay();
        setupAnnotationButtons();
    }

    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI
     */
    public void installUI(){
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
                    exitDeleteMode();
                }
                else {
                    enterDeleteMode();
                }
                model.setDeleteMode(!model.isDeleteMode());
            }
        });

    }

    /**
     * This method exits the delete mode performing all the UI actions needed
     */
    private void exitDeleteMode(){
        enableInteractionButtons();
        controller.handleDeleteRequest(false);
        deleteButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/trash-bin.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        instructionsLine1.setText(Utils.LINE_1_INTERACTION);
        instructionsLine2.setText(Utils.LINE_2_INTERACTION);
    }

    /**
     * This method enables the delete mode performing all the UI actions needed
     */
    private void enterDeleteMode(){
        disableInteractionButtons();
        controller.handleDeleteRequest(true);
        deleteButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/cancel.png").getImage().getScaledInstance(Utils.ANNOTATION_BUTTON_SIZE, Utils.ANNOTATION_BUTTON_SIZE, Image.SCALE_SMOOTH)));
        instructionsLine1.setText(Utils.LINE_1_DELETING);
        instructionsLine2.setText(Utils.LINE_2_DELETING);
    }

    public InteractionList getInteractionList() {
        return interactionList;
    }

    /**
     * This method sets up the annotation Display and the interaction List
     */
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

    /**
     * This method allows to print a new interaction
     * @param interaction the interaction to be printed
     */
    public void printNewInteraction(GenericInteraction interaction){
        interactionList.addInteractionToList(interaction);
        interactionList.repaint();
    }

    /**
     * This method deletes a question from the interaction list
     * @param question the question to be deleted
     */
    public void deleteQuestionFromInteractionList(Question question){
        interactionList.deleteQuestionFromList(question);
        printInteractionList();
    }

    public void printInteractionList(){
        interactionList.repaint();
        repaint();
    }

    /**
     * This method sets up all the annotation buttons, their labels, position etc
     */
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

        instructionsLine1 = new JLabel(Utils.LINE_1_INTERACTION);
        instructionsLine1.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionsLine2 = new JLabel(Utils.LINE_2_INTERACTION);
        instructionsLine2.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(instructionsLine1);
        mainPanel.add(instructionsLine2);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


    /**
     * This method allows to display the question textField to add a new question to the interaction list
     */
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

        sendButton = new JButton();
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

        cancelButton = new JButton();
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

    /**
     * This method hides the questionTextField
     */
    public void hideQuestionTextField(){
        generalInteractionsPanel.add(interactionTimelinePanel);
        cardLayout.next(generalInteractionsPanel);
        generalInteractionsPanel.repaint();
    }

    public void setInteractionList(List<GenericInteraction> allListPerVideo) {
        this.interactionList.getModel().setInteractionList(allListPerVideo);
    }

    /**
     * This method disables all the interaction buttons
     */
    private void disableInteractionButtons(){
        positiveInteractionButton.setEnabled(false);
        negativeInteractionButton.setEnabled(false);
        questionButton.setEnabled(false);
    }

    /**
     * This method enables all the interaction buttons
     */
    private void enableInteractionButtons(){
        positiveInteractionButton.setEnabled(true);
        negativeInteractionButton.setEnabled(true);
        questionButton.setEnabled(true);
    }

    public void repaint() {
        this.interactionList.repaint();
    }

    /**
     * Forces the interaction list to disable all the listeners for the interaction,
     */
    public void disableListeners() {
        this.interactionList.disableListeners();
    }


    public JPanel getGeneralInteractionsPanel() {
        return generalInteractionsPanel;
    }

}
