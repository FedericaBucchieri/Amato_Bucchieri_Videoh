package ProfessorHomePage;

import StudentHomePage.VideoBox;
import StudentHomePage.VideoPlayerArea;

import javax.swing.*;
import java.awt.*;

public class StatisticsPaneUI {
    private StatisticsPane controller;
    private JPanel mainPanel;
    private JPanel centerPanel;
    private VideoBox videoBox;

    public StatisticsPaneUI (StatisticsPane controller){
        this.controller = controller;
        setupMainPanel();
        setupCenterPanel();
        setupSouthPanel();

    }

    private void setupSouthPanel() {
        mainPanel.add(videoBox.getUI().getSouthPanel(), BorderLayout.SOUTH);
    }

    private void setupCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.X_AXIS));
        centerPanel.setBackground(Color.lightGray);//TODO: remove
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        setupVideoBox();

    }

    private void setupVideoBox() {
        videoBox = new VideoBox(controller.getVideo());
        centerPanel.add(videoBox.getUI().getVideoSurface());
        JPanel leftcenterpanel = new JPanel();
        leftcenterpanel.setBackground(Color.RED);
        centerPanel.add(leftcenterpanel);

    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.GRAY);



    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
