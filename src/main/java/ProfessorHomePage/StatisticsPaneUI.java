package ProfessorHomePage;

import InteractionList.InteractionPanel;
import VideoPlayer.VideoBox;
import entities.GenericInteraction;
import entities.Question;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StatisticsPaneUI {
    private StatisticsPane controller;
    private JPanel viewPortView;
    private JScrollPane scrollPane;
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
    private InteractionPanel interactionPanel;
    private ArrayList<Question> displayedQuestions = new ArrayList<Question>();

    public StatisticsPaneUI (StatisticsPane controller){
        this.controller = controller;
        setupMainPanel();
        setupCenterPanel();
        setupSouthPanel();
        setupNorthPanel();
        setupScrollPane();

    }

    private void setupScrollPane() {
        viewPortView = new JPanel();
        viewPortView.setLayout(new BoxLayout(viewPortView, BoxLayout.Y_AXIS));
        viewPortView.add(mainPanel);
        scrollPane = new JScrollPane(viewPortView);
        scrollPane.getVerticalScrollBar().setUnitIncrement(Utils.UNIT_SCROLLING_INCREMENT);

    }

    private void setupNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
        northPanel.setBackground(Color.white);
        setupBackButton();
        JPanel videoDetailPanel = new JPanel();//contains Title, Date, Description
        videoDetailPanel.setLayout(new BoxLayout(videoDetailPanel, BoxLayout.Y_AXIS));

        JPanel videoDetailPanel_TitleDate = new JPanel(); //contains Title and Date
        videoDetailPanel_TitleDate.setLayout(new BoxLayout(videoDetailPanel_TitleDate, BoxLayout.X_AXIS));


        JLabel videoTitleLabel = new JLabel(videoBox.getModel().getVideo().getTitle());
        videoTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        videoTitleLabel.setForeground(Color.BLACK);
        videoTitleLabel.setBackground(Color.white);
        videoTitleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));

        JLabel dateLabel = new JLabel(videoBox.getModel().getVideo().getDate().toString());
        dateLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, Utils.SUBTITLE_WIDTH/2));

        videoDetailPanel_TitleDate.add(videoTitleLabel);
        videoDetailPanel_TitleDate.add(Box.createHorizontalGlue());
        videoDetailPanel_TitleDate.add(dateLabel);
        videoDetailPanel_TitleDate.setBackground(Color.white);
        videoDetailPanel_TitleDate.setAlignmentX(Component.LEFT_ALIGNMENT);

        northPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        JTextArea videoDescriptionTextArea = new JTextArea(videoBox.getModel().getVideo().getDescription());
        videoDescriptionTextArea.setWrapStyleWord(true);
        videoDescriptionTextArea.setLineWrap(true);
        videoDescriptionTextArea.setBackground(Color.white);
        videoDescriptionTextArea.setAlignmentX(Component.LEFT_ALIGNMENT);

        videoDetailPanel.add(videoDetailPanel_TitleDate);
        videoDetailPanel.add(videoDescriptionTextArea);

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
    }

    private void setupSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(videoBox.getUI().getControllButtonsPanel());
        interactionPanel = retrieveInteractionPanel();
        interactionPanel.disableListeners();
        southPanel.add(interactionPanel.getView().getGeneralInteractionsPanel_due());

//        interactionPanel.repaint();
//        interactionPanel.populateInteractionListByVideo(controller.getVideo().getId());
//        southPanel.repaint();
        /*TODO: remove this comment
         * in questo punto provavo add aggiungere al southpanel un pannello delle interazioni già
         * popolato ma purtroppo le interactions per qualche motivo non le carica subito.
         * quindi l'unica soluzione è questa attuale. mi istanzio da subito un pannello di interazioni
         * vuoto, poi col tasto show le recupero e le printo nel pannello delle interazioni
         * */
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    private InteractionPanel retrieveInteractionPanel() {
        while (videoBox.getUI().getInteractionPanel() == null){
            System.out.println("waiting for interaction panel to be created");
        }
        return videoBox.getUI().getInteractionPanel();

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


        totalInteraction = new JLabel(String.valueOf(controller.getTotalInteractions()));
        totalInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));

        JLabel totalNegativeInteractionLabel = new JLabel("Total negative Interactions:");
        summaryPanel.add(totalNegativeInteractionLabel);
        totalNegativeInteractionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalNegativeInteraction = new JLabel(String.valueOf(controller.getCountNegativeInteractions()));
        totalNegativeInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalNegativeInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));


        JLabel totalPositiveInteractionLabel = new JLabel("Total positive Interaction:");
        summaryPanel.add(totalPositiveInteractionLabel);
        totalPositiveInteractionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalPositiveInteraction = new JLabel(String.valueOf(controller.getCountPositiveInteractions()));
        totalPositiveInteraction.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.STATS_LABEL_DIM));
        summaryPanel.add(totalPositiveInteraction);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));


        JLabel totalQuestionLabel = new JLabel("Total question:");
        totalQuestionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        summaryPanel.add(totalQuestionLabel);
        summaryPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));

        totalQuestion = new JLabel(String.valueOf(controller.getVideo().getQuestionList().size()));
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
        interactionPanel.populateInteractionListByVideo(videoBox.getVideoId());
        southPanel.repaint();
        createQuestionsPanels();
    }

    private void createQuestionsPanels() {
        System.out.println("********[createQuestionsPanels] DIMENSIONE INTERACTION LIST: "+interactionPanel.getModel().getInteractionList().size());
        for (GenericInteraction genericInteraction : interactionPanel.getModel().getInteractionList()) {
            if (genericInteraction.getClass().equals(Question.class)){
                Question question = (Question) genericInteraction;
                if (!hasBeenDisplayed(question)){
                    JPanel toAdd = createQuestionPanel(question);
                    displayedQuestions.add(question);
                    viewPortView.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));
                    viewPortView.add(toAdd);
                }

            }
        }
    }

    private boolean hasBeenDisplayed(Question question) {
        if (displayedQuestions.isEmpty())
            return false;
        for (Question displayedQuestion :
                this.displayedQuestions) {
            if (displayedQuestion.getId() == question.getId())
                return true;
        }
        return false;
    }



    public JPanel createQuestionPanel(Question question){
        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));

        JLabel questionTime = new JLabel(String.valueOf(Utils.formatTime(question.getTimestamp())));
        questionTime.setForeground(Color.decode("#F0A037"));
        questionTime.setFont((new Font(Font.SANS_SERIF,  Font.ITALIC, Utils.DATE_FONT_WIDTH)));
        questionTime.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionTime.setAlignmentY(Component.TOP_ALIGNMENT);
        questionPanel.add(questionTime);

        questionPanel.add(Box.createRigidArea(new Dimension(Utils.VERTICAL_RIGID_AREA_DIM10)));

        JTextArea questionBody = new JTextArea(question.getText());
        questionBody.setAlignmentX(Component.LEFT_ALIGNMENT);
        questionBody.setAlignmentY(Component.TOP_ALIGNMENT);
        questionBody.setEditable(false);
        questionBody.setLineWrap(true);
        questionPanel.add(questionBody);

        questionPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        questionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

//        viewPortView.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM10));
//        viewPortView.add(questionPanel);

        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.setBackground(Color.WHITE);
//        questionPanel.setPreferredSize(new Dimension(200, 100));
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        questionPanel.setMinimumSize(new Dimension(250, 50));
//        questionPanel.setPreferredSize(questionPanel.getMinimumSize());
        questionPanel.setMaximumSize(new Dimension(this.videoBox.getModel().getWidth(), this.videoBox.getModel().getHeight()));

        return questionPanel;
    }



    private void setupVideoBox() {
        videoBox = new VideoBox(controller.getVideo());
        centerPanel.add(videoBox.getUI().getVideoSurface());


    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setAlignmentX(Component.CENTER_ALIGNMENT);



    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JScrollPane getScrollPane(){
        return scrollPane;
    }

    public void dismissVideo() {
        videoBox.dismissVideo();
    }
}

