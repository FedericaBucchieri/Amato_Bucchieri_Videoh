package EventManagement;

public class NegativeInteractionEvent implements Event{
    private int timestamp;
    private int videoId;

    public NegativeInteractionEvent(int timestamp, int videoId) {
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
