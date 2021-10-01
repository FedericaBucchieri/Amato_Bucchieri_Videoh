package EventManagement;

import entities.Video;

public class NewVideoEvent implements Event{
    private Video video;

    public NewVideoEvent(Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }
}
