package StudentHomePage;

import entities.Question;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentDetailPanelView {
    // the Component controller
    private StudentDetailPanel controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the label to display the student username
    private JLabel studentName;
    // the main logo of the panel
    private JLabel studentLogo;
    // the button to handle logout
    private JButton logoutButton;
    // the button to go to the review panel
    private JButton reviewButton;
    // the button to go to the endVisionScene
    private JButton endVisionButton;
    // a JPanel to display the list of question
    private JPanel questionPanel;
    // a scrollPane to contain the questionPanel
    private JScrollPane scrollPane;
    // a label for the question list title
    private JLabel listTitle;

    /**
     * This constructor creates an instance of StudentLoginPanelView, adding all the UI element required to the mainPanel
     */
    public StudentDetailPanelView() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setSize(getPreferredSize());
        mainPanel.setPreferredSize(getPreferredSize());
        mainPanel.setBackground(Utils.CUSTOM_BLUE3);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupStudentLogo();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupWelcome();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupQuestionPanel();

        setupButtons();

    }

    /**
     * This method creates a JLabel for the welcome title of the scene, adding the student username to the studentName label
     */
    private void setupWelcome(){
        JLabel welcome = new JLabel("Welcome");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setForeground(Color.white);
        welcome.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        mainPanel.add(welcome);

        studentName = new JLabel();
        studentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        studentName.setForeground(Color.white);
        studentName.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        mainPanel.add(studentName);
    }

    /**
     * This method sets up the question Panel to display the list of questions
     */
    private void setupQuestionPanel(){
        listTitle = new JLabel("Your Questions:");
        listTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        listTitle.setForeground(Color.white);
        mainPanel.add(listTitle);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(questionPanel);
        mainPanel.add(scrollPane);
    }

    /**
     * This method deletes the current questionList and repaints it
     */
    public void repaintQuestionList(){
        List<Question> questionList = controller.getModel().getQuestionList();

        questionPanel.removeAll();

        for (Question question: questionList) {
            displayNewQuestion(question);
        }
    }

    /**
     * This method hides the questionList from the Panel
     */
    public void hideQuestionList(){
        listTitle.setVisible(false);
        scrollPane.setVisible(false);
        reviewButton.setVisible(false);
    }

    /**
     * This method shows the question list in the panel
     * @param questionList the list of question to be displayed
     */
    public void showQuestionList(List<Question> questionList){
        controller.getModel().setQuestionList(questionList);
        listTitle.setVisible(true);
        scrollPane.setVisible(true);
        reviewButton.setVisible(true);
        repaintQuestionList();
    }

    /**
     * This method setup the UI elements to display a new question
     * @param question the question entities to be displayed
     */
    public void displayNewQuestion(Question question){
        JPanel questionElement = new JPanel();
        questionElement.setLayout(new BoxLayout(questionElement, BoxLayout.X_AXIS));

        JLabel questionTime = new JLabel(String.valueOf(Utils.formatTime(question.getTimestamp())));
        questionTime.setForeground(Utils.CUSTOM_ORANGE);
        questionTime.setFont((new Font(Font.SANS_SERIF,  Font.ITALIC, Utils.DATE_FONT_WIDTH)));
        questionTime.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionTime.setAlignmentY(Component.TOP_ALIGNMENT);
        questionElement.add(questionTime);

        questionElement.add(Box.createHorizontalStrut(Utils.STANDARD_BORDER));

        JLabel questionBody = new JLabel(question.getText());
        questionBody.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionBody.setAlignmentY(Component.TOP_ALIGNMENT);
        questionElement.add(questionBody);

        questionElement.setAlignmentY(Component.TOP_ALIGNMENT);
        questionElement.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionPanel.add(questionElement);
    }

    /**
     * This method setup the button of the Panel, the reviewButton and the logoutButton
     */
    private void setupButtons(){
        Utils utils = new Utils();
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        reviewButton = new JButton("Review Questions");
        reviewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reviewButton = utils.styleButtonTwo(reviewButton);
        mainPanel.add(reviewButton);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        endVisionButton = new JButton("End Vision");
        endVisionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        endVisionButton = utils.styleButtonTwo(endVisionButton);
        mainPanel.add(endVisionButton);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton = utils.styleButtonThree(logoutButton);
        mainPanel.add(logoutButton);
    }

    /**
     * This method creates a JLabel for the logo of the scene
     */
    private void setupStudentLogo() {
        studentLogo = new JLabel();
        studentLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/student.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        studentLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(studentLogo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI
     */
    public void installUI(StudentDetailPanel controller){
        this.controller = controller;
        studentName.setText(controller.getStudentUsername());

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleLogout();
            }
        });

        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleReviewRequest();
            }
        });

        endVisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleEndVisionRequest();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Dimension getPreferredSize() {
        return new Dimension(Utils.DETAILPANEL_WIDTH, Utils.DETAILPANEL_HEIGHT);
    }
}
