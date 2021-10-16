package VideoPlayer;

import entities.Video;

public class VideoPlayerAreaModel {
    private VideoPlayerArea controller;
    private Video video;
    private String username;

    /**
     * The model of the VideoPlayerArea.
     * @param controller: a VideoPlayerArea object
     * @param video: the video entity instance of the video to be played
     * @param username: the student who will watch the video and post some question/interactions
     */
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
