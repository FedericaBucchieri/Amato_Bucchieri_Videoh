package ProfessorHomePage;

import entities.Video;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoListElementUI {
    private JPanel mainPanel;
    private JLabel videoTitle;
    private JLabel videoPreview;
    private JTextArea videoDescription;
    private JLabel videoCode;
    private JButton seeStatisticsButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private VideoListElement controller;

    public VideoListElementUI() {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.white);

        setupTitle();
        setupLeftPanel();
        setupRightPanel();

    }

    private void setupTitle(){
        videoTitle = new JLabel();
        videoTitle.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        mainPanel.add(videoTitle, BorderLayout.NORTH);
    }

    private void setupLeftPanel(){
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setBorder(new EmptyBorder(new Insets(Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER)));
        leftPanel.setBackground(Color.white);
        mainPanel.add(leftPanel, BorderLayout.CENTER);

        videoPreview = new JLabel();
        videoPreview.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.add(videoPreview);
        leftPanel.add(Box.createHorizontalStrut(Utils.STANDARD_BORDER));

        videoDescription = new JTextArea();
        videoDescription.setEditable(false);
        videoDescription.setWrapStyleWord(true);
        videoDescription.setLineWrap(true);
        videoDescription.setPreferredSize(new Dimension(Utils.DESCRIPTION_WIDTH,Utils.DESCRIPTION_HEIGHT));
        videoDescription.setAlignmentY(Component.TOP_ALIGNMENT);
        leftPanel.add(videoDescription);
    }

    public void setupRightPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        rightPanel.setBackground(Color.white);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        Utils utils = new Utils();

        seeStatisticsButton = new JButton("See statistics");
        seeStatisticsButton = utils.styleButtonOne(seeStatisticsButton);
        rightPanel.add(seeStatisticsButton);
        rightPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER/2));

        modifyButton = new JButton("Modify");
        modifyButton = utils.styleButtonOne(modifyButton);
        rightPanel.add(modifyButton);
        rightPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER/2));

        deleteButton = new JButton("Delete");
        deleteButton = utils.styleButtonTwo(deleteButton);
        rightPanel.add(deleteButton);
        rightPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER/2));

        rightPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        videoCode = new JLabel();
        rightPanel.add(videoCode);
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
