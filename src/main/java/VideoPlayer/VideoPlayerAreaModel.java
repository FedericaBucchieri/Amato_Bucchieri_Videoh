package VideoPlayer;

import entities.Video;

public class VideoPlayerAreaModel {
    private VideoPlayerArea controller;
    private Video video;
    private String username;

    public VideoPlayerAreaModel(VideoPlayerArea controller, Video video, String username){
        this.controller = controller;
        this.video = video;
        this.username = username;
    }

    public Video getVideo() {
        return video;
    }

    public String getUsername() {
        return username;
    }
}
