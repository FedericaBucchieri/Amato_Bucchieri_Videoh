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

    }


    public void dismissVideo(){
        videoBox.dismissVideo();
    }


    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.blue); //to remove
    }

    private void setupVideoBox() {
        new NativeDiscovery().discover();
        videoBox = new VideoBox(controller, controller.getModel().getVideo(), controller.getModel().getUsername());
        mainPanel.add(videoBox.getUI().getMainPanel(), BorderLayout.CENTER);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
