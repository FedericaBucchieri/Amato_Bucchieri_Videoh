package EventManagement;

import entities.Video;

public class GoToStatisticsEvent implements Event {
    private Video video;

    public GoToStatisticsEvent(Video video) {
        this.video = video;
    }

    public Video getVideo() {
        return this.video;
    }
}
