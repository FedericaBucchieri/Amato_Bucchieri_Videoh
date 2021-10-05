package StudentHomePage;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoPlayerAreaUI {
    private VideoPlayerArea controller;
    private JPanel mainPanel;
    private VideoBox videoBox;
    private JPanel settingsPanel;
    private JPanel annotationsPanel;

    private JButton backButton;

    public VideoPlayerAreaUI(VideoPlayerArea controller){
        this.controller = controller;

        setupMainPanel();
        setupVideoBox();
        //setupSettingsPanel();
        //setupBackButton();

    }

    /*
    private void setupBackButton() {
        Icon icon = new ImageIcon("src/main/images/back-2.png");
        backButton = new JButton(icon);
        settingsPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoBox.dismissVideo();
                controller.goToStudentInsertCode();
            }
        });
    }

     */


    public void dismissVideo(){
        videoBox.dismissVideo();
    }

    private void setupSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.add(Box.createVerticalGlue());

        settingsPanel.setBackground(Color.GRAY); //to remove
        mainPanel.add(settingsPanel, BorderLayout.EAST);

    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.blue); //to remove
    }

    private void setupVideoBox() {
        new NativeDiscovery().discover();
        videoBox = new VideoBox(controller.getModel().getVideo());
        mainPanel.add(videoBox.getUI().getMainPanel(), BorderLayout.CENTER);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
