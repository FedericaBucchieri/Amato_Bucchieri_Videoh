package ProfessorHomePage;

import EventManagement.Event;
import EventManagement.InteractionListPopulatedEvent;
import EventManagement.InteractionPanelCreatedEvent;
import EventManagement.Listener;
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

public class StatisticsPaneView implements Listener {
    //The StatisticsPane component
    private StatisticsPane controller;
    //The panel that contains all the element ot the scrollPane
    private JPanel viewPortView;
    //The scroll pane to host all the widgets of the statisticsPane
    private JScrollPane scrollPane;
    //The panel that contains the Video player, the summary panel, the controlls panel for the video and the interaction panel.
    private JPanel mainPanel;
    //The panel that contains the video player and the summary panel
    private JPanel centerPanel;
    //The panel that contains the control buttons of the video, the slider and the interactionPanel.
    private JPanel southPanel;
    //The panel that contains the summary of the interactions and question attached to the video.
    private JPanel summaryPanel;
    //The videoPLayer that plays the video.
    private VideoBox videoBox;
    //The button that will show all the interactions and question in the interaction panel.
    private JButton showInteratcionButton;
    //The button to go back to the ProfessorHomePage
    private JButton backButton;
    //The panel that contains the backButton and Title, Date and Description of the video
    private JPanel northPanel;
    //The label that displays the total number of  interactions attached to the video
    private JLabel totalInteraction;
    //The label that displays the  number of  negative interactions attached to the video
    private JLabel totalNegativeInteraction;
    //The label that displays the  number of  positive interactions attached to the video
    private JLabel totalPositiveInteraction;
    //The label that displays the total number of  questions attached to the video
    private JLabel totalQuestion;
    //The panel that shows the interactions as pins under the timeline
    private InteractionPanel interactionPanel;
    //Thelist of all question displayed under the interaction panel
    private ArrayList<Question> displayedQuestions = new ArrayList<Question>();

    /**
     * Creates the view for the given StatisticsPaneComponent.
     * @param controller: the statisticsPaneController
     */
    public StatisticsPaneView(StatisticsPane controller){
        this.controller = controller;
        setupMainPanel();
        setupCenterPanel();
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

    /**
     * Creates the northPanel, that is the panel that contains the backButton and Title, Date and Description of the video
     */
    private void setupNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.X_AXIS));
        northPanel.setBackground(Color.white);
        setupBackButton();
        JPanel videoDetailPanel = new JPanel();//contains Title, Date, Description
        videoDetailPanel.setLayout(new BoxLayout(videoDetailPanel, BoxLayout.Y_AXIS));

        JPanel videoDetailPanel_TitleDate = new JPanel(); //contains Title and Date
        videoDetailPanel_TitleDate.setLayout(new BoxLayout(videoDetailPanel_TitleDate, BoxLayout.X_AXIS));


        /*
        videoTitleLabel, dateLabel and videoDescriptionLabel have been created locally in this method since no other
        objects will interact or modify them.
        * */
        JLabel videoTitleLabel = new JLabel(videoBox.getModel().getVideo().getTitle());//Shows on screen the title of the video
        videoTitleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        videoTitleLabel.setForeground(Color.BLACK);
        videoTitleLabel.setBackground(Color.white);
        videoTitleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));

        JLabel dateLabel = new JLabel(videoBox.getModel().getVideo().getDate().toString());//Shows on screen the date of creation of the video
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


    /**
     * Creates the backButton (taking the aspects from the Utils module) and sets it up with the actionListener.
     */
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

    /**
     * Creates the south panel, that is the panel that contains the control buttons of the video, the slider and the interactionPanel.
     */
    private void setupSouthPanel() {
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(videoBox.getView().getControllButtonsPanel());
        interactionPanel = retrieveInteractionPanel();
        interactionPanel.disableListeners();
        southPanel.add(interactionPanel.getView().getGeneralInteractionsPanel());
        mainPanel.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Asks the VideoBox to return, from its view, the interaction Panel
     * @return the interaction panel of the videoBox
     */
    private InteractionPanel retrieveInteractionPanel() {
        return videoBox.getView().getInteractionPanel();

    }


    /**
     * Sets up the Center Panel, that is the panel that contains the video player and the summary panel
     */
    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        setupVideoBox();

    }

    /**
     * Creates the summary panel, that is the panel that contains the summary of the interactions and question attached to the video.
     */
    private void setupSummaryPanel() {
        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));

        /*
        * totalInteractionLabel, totalNegativeInteractionLabel, totalPositiveInteractionLabel and totalQuestionLabel
        * have been created locally in this method since no other widget will interact and modify them.
        * */
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

    /**
     * Sets up the showInteratcionButton, that is the button that will show all the interactions and question in the interaction panel.
     */
    private void setupShowInteractionButton() {
        showInteratcionButton = new JButton("Show interactions and Questions");
        summaryPanel.add(showInteratcionButton);
        showInteratcionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInteratcionButton.setEnabled(false);
                displayInteractionPanel();
            }
        });
    }

    /**
     * This method forces the interactionPanel to populate its interactionList for the video that this StatisticsPane is handling.
     * It then displays all the interactions in the interaction panel and creates the questionPanel that shows the questions that have
     * been posted by students for the current video.
     */
    private void displayInteractionPanel() {
        interactionPanel.populateInteractionListByVideo(videoBox.getVideoId());
        createQuestionsPanels();
        southPanel.repaint();
    }

    /**
     * Creates a questionPanel for each question attached to The Video. Each question panel is then added to the viewPortView
     * in order to be displayed in the scroll view
     */
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


    /**
     * Creates a questionPanel for the givenQuestion
     * @param question the question to create a panel for.
     * @return the questionPanel to be displayed in the scrollView
     */
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


        questionPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.setBackground(Color.WHITE);
        questionPanel.setBorder(BorderFactory.createLineBorder(Color.orange));
        questionPanel.setMinimumSize(new Dimension(250, 50));
        questionPanel.setMaximumSize(new Dimension(this.videoBox.getModel().getWidth(), this.videoBox.getModel().getHeight()));

        return questionPanel;
    }


    /**
     * Creates a new instance of videoBox and adds it to the center Panel
     */
    private void setupVideoBox() {
        videoBox = new VideoBox(controller.getVideo(), this);
        centerPanel.add(videoBox.getView().getVideoSurface());


    }

    /**
     * Setup the main panel, that is the panel that contains the Video player, the summary panel, the controls panel for the video and the interaction panel.
     */
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

    /**
     * Ask the videoBox to dismiss the video.
     */
    public void dismissVideo() {
        videoBox.dismissVideo();
    }

    @Override
    public void listen(Event event) {
        if (event.getClass().equals(InteractionPanelCreatedEvent.class)){
            setupSouthPanel();
            setupSummaryPanel();
        }
        else if (event.getClass().equals(InteractionListPopulatedEvent.class)){
            System.out.println("Statistics pane ui ha ricevuto l'evento InteractionListPopulatedEvent ");
//            createQuestionsPanels();
//            interactionPanel.repaint();
            //TODO: ho fatto questo con l'obiettivo di disegnare le interaction dopo che sono state raccolte. ma evidentemente ci mette tempo a disegnare le interaction drawings.
//            non riesco bene a capire dove lanciare l'evento InteractionListPopulatedEvent per essere sicuro che le drawings sono pronte
            //per ora il bottone resta necessario ma se non altro è stato eliminto il while per instanziare l'interaction panel vuoto.
        }
    }

}

