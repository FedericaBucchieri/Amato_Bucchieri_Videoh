package StudentHomePage;

import entities.Question;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StudentDetailPanelUI {
    private JPanel mainPanel;
    private JLabel studentName;
    private JLabel studentLogo;
    private StudentDetailPanel controller;
    private JButton logoutButton;
    private JPanel questionPanel;

    public StudentDetailPanelUI() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setSize(getPreferredSize());
        mainPanel.setPreferredSize(getPreferredSize());
        mainPanel.setBackground(Color.decode("#42577F"));

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupStudentLogo();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupWelcome();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupQuestionPanel();

        setupButtons();

    }

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

    private void setupQuestionPanel(){
        JLabel listTitle = new JLabel("Your Questions:");
        listTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        listTitle.setForeground(Color.white);
        mainPanel.add(listTitle);

        questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(questionPanel);
        mainPanel.add(scrollPane);
    }

    public void repaintQuestionList(){
        List<Question> questionList = controller.getModel().getQuestionList();

        questionPanel.removeAll();

        for (Question question: questionList) {
            displayNewQuestion(question);
        }
    }

    public void displayNewQuestion(Question question){
        JPanel questionElement = new JPanel();
        questionElement.setLayout(new BoxLayout(questionElement, BoxLayout.X_AXIS));

        JLabel questionTime = new JLabel(String.valueOf(Utils.formatTime(question.getTimestamp())));
        questionTime.setForeground(Color.decode("#F0A037"));
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

    private void setupButtons(){
        Utils utils = new Utils();
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton = utils.styleButtonThree(logoutButton);
        mainPanel.add(logoutButton);
    }

    private void setupStudentLogo() {
        studentLogo = new JLabel();
        studentLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/student.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        studentLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(studentLogo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public void installUI(StudentDetailPanel controller){
        this.controller = controller;
        studentName.setText(controller.getStudentUsername());

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleLogout();
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
