package VideoPlayer;

import javax.swing.*;
import java.awt.*;

public class VideoPlayerAreaView {
    //The VideoPlayerArea element
    private VideoPlayerArea controller;
    //The main panel in which the videoBox is set.
    private JPanel mainPanel;
    //The video box that will show the the video and its related controller
    private VideoBox videoBox;

    public VideoPlayerAreaView(VideoPlayerArea controller){
        this.controller = controller;

        setupMainPanel();
        setupVideoBox();

    }


    /**
     *This method will ask the videoBox of the video to dismiss the video.
     */
    public void dismissVideo(){
        videoBox.dismissVideo();
    }

    /**
     * This method will ask the videoBox to stop the video.
     */
    public void stopVideoPlaying(){
        videoBox.stopVideoPlaying();
    }

    public void unlockVideoPlaying(){
        videoBox.unlockVideoPlaying();
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
    }

    private void setupVideoBox() {
        videoBox = new VideoBox(controller, controller.getModel().getVideo(), controller.getModel().getUsername());
        mainPanel.add(videoBox.getView().getMainPanel(), BorderLayout.CENTER);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public VideoBox getVideoBox() {
        return videoBox;
    }
}
