package EventManagement;

import entities.Video;

public class ModifyVideoEvent implements Event{
    private Video video;

    public ModifyVideoEvent(Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }
}
