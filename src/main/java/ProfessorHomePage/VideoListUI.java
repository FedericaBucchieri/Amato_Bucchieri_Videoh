package ProfessorHomePage;

import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoListUI {
    private JPanel mainPanel;
    private JPanel listPanel;
    private JScrollPane scrollPane;
    private VideoList controller;
    private List<Video> videoList;
    private JLabel errorLabel;

    public VideoListUI() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.white);

        JLabel title = new JLabel("Your video List");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Color.decode("#42577F"));
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

    public void installUI(VideoList controller) {
        this.controller = controller;
        this.videoList = controller.getVideoList();
        paint();
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public JPanel getListPanel() {
        return listPanel;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void displayError(String error){
        errorLabel.setText(error);
    }

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
