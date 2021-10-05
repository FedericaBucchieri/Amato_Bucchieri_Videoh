package EventManagement;

public class PositiveInteractionEvent  implements Event{
    private int timestamp;
    private int videoId;

    public PositiveInteractionEvent(int timestamp, int videoId) {
        this.timestamp = timestamp;
        this.videoId = videoId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getVideoId() {
        return videoId;
    }
}
