package StudentHomePage;

import entities.Video;

public class VideoBoxModel {
    private VideoBox controller;
    private int width = 600;
    private int height = 400;
    private Video video;

    public VideoBoxModel (Video video){
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
}
