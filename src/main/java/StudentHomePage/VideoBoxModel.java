package StudentHomePage;

import entities.Video;

public class VideoBoxModel {
    private VideoBox controller;
    private int width = 1200;
    private int height = 800;
    private Video video;
    private String username;

    public VideoBoxModel (Video video, String username){
        this.video = video;
        this.username = username;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Video getVideo() {
        return video;
    }

    public String getUsername() {
        return username;
    }
}
