package EventManagement;

import entities.Video;

import javax.swing.*;

public class DeleteVideoEvent implements Event{
    private Video video;

    public DeleteVideoEvent( Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }
}
