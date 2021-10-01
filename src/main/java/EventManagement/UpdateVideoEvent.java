package EventManagement;

import entities.Video;

public class UpdateVideoEvent implements Event{
    private Video video;

    public UpdateVideoEvent(Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }
}
