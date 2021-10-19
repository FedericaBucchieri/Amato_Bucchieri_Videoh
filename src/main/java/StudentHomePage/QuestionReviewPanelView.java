package StudentHomePage;

import entities.Question;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionReviewPanelView {
    // the Component controller
    private QuestionReviewPanel controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the panel that contains the northPart of the view
    private JPanel northPanel;
    // the button to go back to the previous scene
    private JButton backButton;
    // the panel that contains the list of question to be displayed
    private JPanel questionListPanel;
    // the button to end the questions review
    private JButton endReviewButton;
    // the scroll pain that contains the questionListPanel
    private JScrollPane scrollPane;


    /**
     * This constructor creates an instance of QuestionReviewPanelView, adding all the UI element required to the mainPanel
     * @param controller the QuestionReviewPanel instance that is the component controller
     */
    public QuestionReviewPanelView(QuestionReviewPanel controller) {
        this.controller = controller;
        setupMainPanel();
    }


    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI and creating the questionList
     */
    public void installUI(){
        setupQuestions();

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.backToVideo();
            }
        });

        endReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.endReview();
            }
        });
    }

    /**
     * This method instantiate a new JPanel as mainPanel populating it with the required elements
     */
    private void setupMainPanel(){
        mainPanel = new JPanel(new BorderLayout());

        setupNorthPanel();
        setupQuestionList();
        setupEndReviewButton();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * This method instantiate a new JPanel as northPanel populating it with the required elements
     */
    private void setupNorthPanel(){
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));

        Utils utils = new Utils();
        backButton = utils.setUPBackButton();
        northPanel.add(backButton);

        JLabel title = new JLabel("Review your questions");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.TITLE_WIDTH));
        northPanel.add(title);

        mainPanel.add(northPanel, BorderLayout.NORTH);
    }

    /**
     * This method instantiate a new JPanel as questionListPanel, placing it in a new JScrollPane
     */
    private void setupQuestionList(){
        questionListPanel = new JPanel();
        questionListPanel.setLayout( new BoxLayout(questionListPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(questionListPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * This method displays each question to be review in the QuestionListPanel
     */
    private void setupQuestions(){
        List<Question> questionList = controller.getModel().getQuestionList();

        if(questionList.size() > 0) {
            for (Question question : questionList) {
                JPanel singleQuestionElement = new JPanel();//A panel containing the info timeline label, the textArea of the question, and two buttons: Modify button and Delete button.
                singleQuestionElement.setLayout(new BoxLayout(singleQuestionElement, BoxLayout.Y_AXIS));

                JPanel singleQuestionInfo = new JPanel(); //Panel containing just the info timeline label, the textArea of the question
                singleQuestionInfo.setLayout(new BoxLayout(singleQuestionInfo, BoxLayout.X_AXIS));

                setQuestionDate(singleQuestionInfo, question);

                singleQuestionInfo.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

                JTextArea questionBody = setQuestionBody(singleQuestionInfo, question);
                questionBody.setAlignmentY(Component.CENTER_ALIGNMENT);
                singleQuestionElement.add(singleQuestionInfo);
                singleQuestionElement.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));
                setQuestionCommandButtons(singleQuestionElement, question, questionBody);

                questionListPanel.add(singleQuestionElement);
                singleQuestionElement.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));
            }
        }
        else {
            JLabel noQuestionLabel = new JLabel("You don't have question to review");
            questionListPanel.add(noQuestionLabel);
        }

        questionListPanel.repaint();
    }

    /**
     * This method sets up the Question date label
     * @param singleQuestionPanel The panel to attach the label to
     * @param question the question to display the date of
     */
    private void setQuestionDate(JPanel singleQuestionPanel, Question question){
        JLabel dateLabel = new JLabel(Utils.formatTime(question.getTimestamp())); //the label containing just the date of the quesiton
        dateLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        singleQuestionPanel.add(dateLabel);
        singleQuestionPanel.add(Box.createRigidArea(Utils.HORIZONTAL_RIGID_AREA_DIM10));
    }

    /**
     * This method sets up the Question body text area
     * @param singleQuestionPanel The panel to attach the text area to
     * @param question the question to display the body of
     */
    private JTextArea setQuestionBody(JPanel singleQuestionPanel, Question question){
        JTextArea questionBody = new JTextArea(question.getText());
        questionBody.setWrapStyleWord(true);
        questionBody.setLineWrap(true);
        questionBody.setEditable(true);
        questionBody.setMaximumSize(new Dimension(Utils.QUESTIONBODY_WIDTH, Utils.QUESTIONBODY_HEIGHT));
        questionBody.setMinimumSize(new Dimension(Utils.QUESTIONBODY_WIDTH, Utils.QUESTIONBODY_HEIGHT));
        singleQuestionPanel.add(questionBody);

        return questionBody;
    }

    /**
     * This method sets up the button to modify or delete a question from the review list
     * @param singleQuestionPanel The panel to attach the button to
     * @param question the question to display the button for
     * @param questionBody the question body
     */
    private void setQuestionCommandButtons(JPanel singleQuestionPanel, Question question, JTextArea questionBody){
        JPanel buttonPanel = new JPanel(); //The panel with the 2 buttons: Modify and Delete Button
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));


        JLabel confirmationMessage = new JLabel();
        confirmationMessage.setForeground(Utils.CUSTOM_GREEN);
        confirmationMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        singleQuestionPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));
        singleQuestionPanel.add(confirmationMessage);
        singleQuestionPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        Utils utils = new Utils();
        JButton modifyButton = new JButton("Modify");
        modifyButton = utils.styleButtonTwo(modifyButton);
        buttonPanel.add(modifyButton);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(controller.handleModifyRequest(question, questionBody.getText())) {
                    confirmationMessage.setText("Question modified with success");
                }
                else {
                    confirmationMessage.setText("An error occurred while modifying the question");
                    confirmationMessage.setForeground(Color.red);
                }
            }
        });

        buttonPanel.add(Box.createRigidArea(Utils.HORIZONTAL_RIGID_AREA_DIM15));

        JButton deleteButton = new JButton("Delete");
        deleteButton = utils.styleButtonTwo(deleteButton);
        buttonPanel.add(deleteButton);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleDeleteRequest(question);
            }
        });

        singleQuestionPanel.add(buttonPanel);
    }

    /**
     * This method sets up the endReviewButton
     */
    private void setupEndReviewButton(){
        Utils utils = new Utils();
        endReviewButton = new JButton("End Review and Conclude vision");
        endReviewButton = utils.styleButtonOne(endReviewButton);

        JPanel endReviewButtonPanel = new JPanel();
        endReviewButtonPanel.add(endReviewButton);

        mainPanel.add(endReviewButtonPanel, BorderLayout.SOUTH);
    }

    /**
     * This method repaints the QuestionList from scratch
     */
    public void repaintQuestionList(){
        questionListPanel.removeAll();
        setupQuestions();
    }
}
