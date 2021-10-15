package ProfessorHomePage;

import entities.Video;
import Utils.Utils;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoListElementUI {
    private JPanel mainPanel;
    private JLabel videoTitle;
    private JLabel videoPreview;
    private JTextArea videoDescription;
    private JTextPane videoCode;
    private JButton seeStatisticsButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private VideoListElement controller;

    public VideoListElementUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        setupTitle();
        previewDescriptionButtonsPanel();

    }

    private void setupTitle(){
        videoTitle = new JLabel();
        videoTitle.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        videoTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(videoTitle);
        mainPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));

    }

    private void previewDescriptionButtonsPanel(){//contains video preview, video description and all buttons
        JPanel previewDescriptionButtonsPanel = new JPanel();
        previewDescriptionButtonsPanel.setLayout(new BoxLayout(previewDescriptionButtonsPanel, BoxLayout.X_AXIS));
        previewDescriptionButtonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(previewDescriptionButtonsPanel);

        setupLeftPanel(previewDescriptionButtonsPanel);

        setupRightPanel(previewDescriptionButtonsPanel);

    }

    private void setupLeftPanel(JPanel previewDescriptionButtonsPanel) {
        JPanel leftPanel = new JPanel(); //contains video preview and video description.
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.setBackground(Color.WHITE);
        previewDescriptionButtonsPanel.add(leftPanel);

        videoPreview = new JLabel();
        videoPreview.setAlignmentY(Component.TOP_ALIGNMENT);
        videoPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
        leftPanel.add(videoPreview);
        leftPanel.add(Box.createRigidArea(new Dimension(Utils.HORIZONTAL_RIGID_AREA_DIM30)));

        videoDescription = new JTextArea();
        videoDescription.setEditable(false);
        videoDescription.setWrapStyleWord(true);
        videoDescription.setLineWrap(true);
        videoDescription.setPreferredSize(new Dimension(Utils.DESCRIPTION_WIDTH,Utils.DESCRIPTION_HEIGHT));
        videoDescription.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.add(videoDescription);
    }

    public void setupRightPanel(JPanel previewDescriptionButtonsPanel){//it contains all the buttons and the video code
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBackground(Color.white);
        rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        previewDescriptionButtonsPanel.add(rightPanel);

        Utils utils = new Utils();

        seeStatisticsButton = new JButton("See statistics");
        seeStatisticsButton = utils.styleButtonOne(seeStatisticsButton);
        seeStatisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(seeStatisticsButton);
        rightPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));

        modifyButton = new JButton("Modify");
        modifyButton = utils.styleButtonOne(modifyButton);
        modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(modifyButton);
        rightPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));

        deleteButton = new JButton("Delete");
        deleteButton = utils.styleButtonTwo(deleteButton);
        deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(deleteButton);
        rightPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));


        videoCode = new JTextPane();
        videoCode.setAlignmentX(Component.CENTER_ALIGNMENT);
        StyledDocument doc = videoCode.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        rightPanel.add(videoCode);
        rightPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));
    }

    public void installUI(VideoListElement controller) {
        this.controller = controller;
        setVideoInformation();

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.deleteVideo();
            }
        });

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleModifyRequest();
            }
        });

        seeStatisticsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleStatisticRequest();
            }
        });
    }

    private void setVideoInformation(){
        Video video = controller.getVideo();
        videoTitle.setText(video.getTitle());
        videoDescription.setText(video.getDescription());
        videoCode.setText("Code:" + String.valueOf(video.getCode()));
        videoPreview.setIcon(new ImageIcon(new ImageIcon(video.getPreviewImage().getPath()).getImage().getScaledInstance(200, 113, Image.SCALE_SMOOTH)));
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

}
