package EventManagement;

import entities.Video;

import java.io.File;

public class GoToVideoEvent implements Event {
    private Video video;
    private String username;

    public GoToVideoEvent (Video video, String username){
        this.video = video;
        this.username = username;
    }

    public Video getVideo() {
        return video;
    }


    public String getUsername() {
        return this.username;
    }
}
