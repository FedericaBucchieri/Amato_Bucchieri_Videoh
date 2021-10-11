package ProfessorHomePage;

import StudentHomePage.VideoBox;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsPaneUI {
    private StatisticsPane controller;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel summaryPanel;
    private VideoBox videoBox;
    private JButton showInteratcion;
    private JButton backButton;
    private JPanel northPanel;



    private JLabel totalInteraction;
    private JLabel totalNegativeInteraction;
    private JLabel totalPositiveInteraction;
    private JLabel totalQuestion;

    public StatisticsPaneUI (StatisticsPane controller){
        this.controller = controller;
        setupMainPanel();
        setupCenterPanel();
        setupSouthPanel();
        setupNorthPanel();


    }

    private void setupNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
        setupBackButton();
        JPanel videoDetailPanel = new JPanel();//contains Title, Date, Description
        videoDetailPanel.setLayout(new BoxLayout(videoDetailPanel, BoxLayout.Y_AXIS));

        JPanel videoDetailPanel_TitleDate = new JPanel(); //contains Title and Date
        videoDetailPanel_TitleDate.setLayout(new BoxLayout(videoDetailPanel_TitleDate, BoxLayout.X_AXIS));


        JLabel videoTitleLabel = new JLabel(videoBox.getModel().getVideo().getTitle());
        videoTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        videoTitleLabel.setForeground(Color.BLACK);
        videoTitleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));

        JLabel dateLabel = new JLabel(videoBox.getModel().getVideo().getDate().toString());
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Utils.SUBTITLE_WIDTH/2));

        videoDetailPanel_TitleDate.add(videoTitleLabel);
        videoDetailPanel_TitleDate.add(Box.createHorizontalGlue());
        videoDetailPanel_TitleDate.add(dateLabel);
        videoDetailPanel_TitleDate.setAlignmentX(Component.LEFT_ALIGNMENT);


        JLabel videoDescriptionLabel = new JLabel(videoBox.getModel().getVideo().getDescription());
        videoDescriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        videoDetailPanel.add(videoDetailPanel_TitleDate);
        videoDetailPanel.add(videoDescriptionLabel);

        northPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM100));
        northPanel.add(videoDetailPanel);

        mainPanel.add(northPanel, BorderLayout.NORTH);
    }


    private void setupBackButton() {
        backButton = Utils.setUPBackButton();
        northPanel.add(backButton);
        northPanel.add(Box.createRigidArea(Utils.HORIZONTAL_RIGID_AREA_DIM30));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goToProfessorHomePage();
            }
        });
        //TODO: add action listener, add icon
    }

    private void setupSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(videoBox.getUI().getControllButtonsPanel());
//        installUI();

//        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    public void installUI() {
        southPanel.add(videoBox.getUI().getInteractionPanel());
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setupVideoBox();
        setupSummaryPanel();

    }

    private void setupSummaryPanel() {
        summaryPanel = new JPanel();
//        summaryPanel.setBackground(Color.RED);
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        JLabel totalInteractionLabel = new JLabel("Total Interaction:");
        totalInteractionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(totalInteractionLabel);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));


        totalInteraction = new JLabel(String.valueOf(controller.getVideo().getInteractionList().size()));//TODO: remove number
        totalInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));

        JLabel totalNegativeInteractionLabel = new JLabel("Total negative Interaction:");
        summaryPanel.add(totalNegativeInteractionLabel);
        totalNegativeInteractionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalNegativeInteraction = new JLabel("120");//TODO: remove number
        totalNegativeInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalNegativeInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));


        JLabel totalPositiveInteractionLabel = new JLabel("Total positive Interaction:");
        summaryPanel.add(totalPositiveInteractionLabel);
        totalPositiveInteractionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalPositiveInteraction = new JLabel("114");//TODO: remove number
        totalPositiveInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalPositiveInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));


        JLabel totalQuestionLabel = new JLabel("Total question:");
        totalQuestionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(totalQuestionLabel);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalQuestion = new JLabel(String.valueOf(controller.getVideo().getQuestionList().size()));//TODO: remove number
        totalQuestion.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalQuestion);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));



        centerPanel.add(Box.createRigidArea(Utils.HORIZONTAL_RIGID_AREA_DIM30));
        centerPanel.add(summaryPanel);
        setupShowInteractionButton();

    }

    private void setupShowInteractionButton() {
        showInteratcion = new JButton("show");
        summaryPanel.add(showInteratcion);
        showInteratcion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayInteractionPanel();


            }
        });
    }

    private void displayInteractionPanel() {

        southPanel.add(videoBox.getUI().getInteractionPanel());
        mainPanel.repaint();
    }


    private void setupVideoBox() {
        videoBox = new VideoBox(controller.getVideo());
        centerPanel.add(videoBox.getUI().getVideoSurface());


    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);



    }
    public void setTotalInteraction(JLabel totalInteraction) {
        this.totalInteraction = totalInteraction;
    }

    public void setTotalNegativeInteraction(JLabel totalNegativeInteraction) {
        this.totalNegativeInteraction = totalNegativeInteraction;
    }

    public void setTotalPositiveInteraction(JLabel totalPositiveInteraction) {
        this.totalPositiveInteraction = totalPositiveInteraction;
    }

    public void setTotalQuestion(JLabel totalQuestion) {
        this.totalQuestion = totalQuestion;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void dismissVideo() {
        videoBox.dismissVideo();
    }
}
