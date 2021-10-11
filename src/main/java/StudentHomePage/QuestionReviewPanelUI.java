package StudentHomePage;

import entities.Question;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionReviewPanelUI {
    private QuestionReviewPanel controller;
    private JPanel mainPanel;
    private JPanel northPanel;
    private JButton backButton;
    private JPanel questionListPanel;
    private JButton endReviewButton;
    private JScrollPane scrollPane;

    public QuestionReviewPanelUI(QuestionReviewPanel controller) {
        this.controller = controller;
        setupMainPanel();
    }

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

    private void setupMainPanel(){
        mainPanel = new JPanel(new BorderLayout());

        setupNorthPanel();
        setupQuestionList();
        setupEndReviewButton();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

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

    private void setupQuestionList(){
        questionListPanel = new JPanel();
        questionListPanel.setLayout( new BoxLayout(questionListPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(questionListPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
    }

    private void setupQuestions(){
        List<Question> questionList = controller.getModel().getQuestionList();

        if(questionList.size() > 0) {
            for (Question question : questionList) {
                JPanel singleQuestionElement = new JPanel();
                singleQuestionElement.setLayout(new BoxLayout(singleQuestionElement, BoxLayout.Y_AXIS));

                JPanel singleQuestionInfo = new JPanel();
                singleQuestionInfo.setLayout(new BoxLayout(singleQuestionInfo, BoxLayout.X_AXIS));

                setQuestionDate(singleQuestionInfo, question);

                singleQuestionInfo.add(Box.createHorizontalStrut(Utils.STANDARD_BORDER));

                JTextArea questionBody = setQuestionBody(singleQuestionInfo, question);
                singleQuestionElement.add(singleQuestionInfo);

                setQuestionCommandButtons(singleQuestionElement, question, questionBody);

                questionListPanel.add(singleQuestionElement);
                questionListPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
            }
        }
        else {
            JLabel noQuestionLabel = new JLabel("You don't have question to review");
            questionListPanel.add(noQuestionLabel);
        }

        questionListPanel.repaint();
    }

    private void setQuestionDate(JPanel singleQuestionPanel, Question question){
        JLabel dateLabel = new JLabel(Utils.formatTime(question.getTimestamp()));
        dateLabel.setAlignmentY(Component.TOP_ALIGNMENT);
        singleQuestionPanel.add(dateLabel);
    }

    private JTextArea setQuestionBody(JPanel singleQuestionPanel, Question question){
        JTextArea questionBody = new JTextArea(question.getText());
        questionBody.setWrapStyleWord(true);
        questionBody.setLineWrap(true);
        questionBody.setEditable(true);
        questionBody.setMaximumSize(new Dimension(700, 100));
        questionBody.setMinimumSize(new Dimension(700, 100));
        singleQuestionPanel.add(questionBody);

        return questionBody;
    }

    private void setQuestionCommandButtons(JPanel singleQuestionPanel, Question question, JTextArea questionBody){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        Utils utils = new Utils();
        JButton modifyButton = new JButton("Modify");
        modifyButton = utils.styleButtonTwo(modifyButton);
        buttonPanel.add(modifyButton);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleModifyRequest(question, questionBody.getText());
            }
        });

        buttonPanel.add(Box.createHorizontalStrut(Utils.STANDARD_BORDER));

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

    private void setupEndReviewButton(){
        Utils utils = new Utils();
        endReviewButton = new JButton("End Review and Conclude vision");
        endReviewButton = utils.styleButtonOne(endReviewButton);

        JPanel endReviewButtonPanel = new JPanel();
        endReviewButtonPanel.add(endReviewButton);

        mainPanel.add(endReviewButtonPanel, BorderLayout.SOUTH);
    }

    public void repaintQuestionList(){
        questionListPanel.removeAll();
        setupQuestions();
    }
}
