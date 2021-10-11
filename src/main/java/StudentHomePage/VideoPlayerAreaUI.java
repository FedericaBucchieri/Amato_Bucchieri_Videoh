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

    public VideoPlayerAreaUI(VideoPlayerArea controller){
        this.controller = controller;

        setupMainPanel();
        setupVideoBox();

    }


    public void dismissVideo(){
        videoBox.dismissVideo();
    }

    public void stopVideoPlaying(){
        videoBox.stopVideoPlaying();
    }

    public void unlockVideoPlaying(){
        videoBox.unlockVideoPlaying();
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.blue); //to remove
    }

    private void setupVideoBox() {
        videoBox = new VideoBox(controller, controller.getModel().getVideo(), controller.getModel().getUsername());
        mainPanel.add(videoBox.getUI().getMainPanel(), BorderLayout.CENTER);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public VideoBox getVideoBox() {
        return videoBox;
    }
}
