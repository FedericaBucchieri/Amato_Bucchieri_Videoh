package StudentHomePage;

import entities.Video;

public class VideoBoxModel {
    private VideoBox controller;
    private int width = 600;
    private int height = 400;
    private Video video;
    private String username;

    public VideoBoxModel (Video video, String username){
        this.video = video;
        this.username = username;
    }
    public VideoBoxModel (Video video){ //this constructor is used when the videobox is loaded by statisticsPane
        this.video = video;
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
