package EventManagement;

import entities.Video;

import javax.swing.*;

public class DeleteVideoEvent implements Event{
    private JPanel videoPanel;
    private Video video;

    public DeleteVideoEvent(JPanel videoPanel, Video video) {
        this.videoPanel = videoPanel;
        this.video = video;
    }

    public JPanel getVideoPanel() {
        return videoPanel;
    }

    public Video getVideo() {
        return video;
    }
}
