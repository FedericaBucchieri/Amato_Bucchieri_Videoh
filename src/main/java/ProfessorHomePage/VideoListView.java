package ProfessorHomePage;

import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoListView {
    //The panel that contains all the widgets, specially the entire scroll pane
    private JPanel mainPanel;
    //The panel that contains the scroll pane
    private JPanel listPanel;
    //The scroll pane that will handle the videos
    private JScrollPane scrollPane;
    //The controller of this VideoList
    private VideoList controller;
    //the list of video entities
    private List<Video> videoList;
    //The errorLabel to be shown when no video has been loaded
    private JLabel errorLabel;

    /**
     * Creates the View of the VideoList. this view contains all the videoElement and some fancy labels.
     */
    public VideoListView() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.white);

        JLabel title = new JLabel("Your video List");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Utils.CUSTOM_BLUE3);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);

        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
        listPanel.setBackground(Color.white);

        scrollPane = new JScrollPane(listPanel);
        scrollPane.setBackground(Color.white);
        mainPanel.add(scrollPane);

        errorLabel = new JLabel();
        mainPanel.add(errorLabel);

    }

    /**
     * Creates a videoListElement for each video in the VideoList
     * @param controller
     */
    public void installUI(VideoList controller) {
        this.controller = controller;
        this.videoList = controller.getVideoList();
        paint();
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void displayError(String error){
        errorLabel.setText(error);
    }

    /**
     * Removes all the video from the listPanel, creates a videoListelement for each video and then add thees to the listPanel. Then, this method forces the listPanel to repaint
     */
    public void paint(){
        listPanel.removeAll();

        if(videoList.isEmpty())
            displayError(Utils.ERROR_EMPTY_LIST);
        else {
            for (Video video: videoList) {
                VideoListElement videoListElement = new VideoListElement(video, controller.getParentComponent());
                listPanel.add(videoListElement);
            }
        }

        listPanel.repaint();
    }
}
