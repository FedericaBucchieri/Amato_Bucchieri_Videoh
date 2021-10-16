package VideoPlayer;

import entities.Video;

public class VideoBoxModel {
    // The controller of the component
    private VideoBox controller;
    //The default width of the image of the video panel.
    private int width = 600;
    //The default height of the image of the video panel.
    private int height = 400;
    //The video entity retrieved from the DB.
    private Video video;
    //The username of the student that is currently watchg the video
    private String username;

    /**
     * This constructor creates the model for the videoBox element
     * @param video The video entity retrieved from the DB.
     * @param username The username of the student that is currently watchg the video
     * @param width The width of the image of the video panel.
     * @param height The height of the image of the video panel.
     */
    public VideoBoxModel (Video video, String username, int width, int height){
        this.video = video;
        this.username = username;
        this.width = width;
        this.height = height;
    }

//    public VideoBoxModel(Video video, String username) {
//        this.video = video;
//        this.username = username;
//    }

    /**
     * this constructor is meant to be used when the videobox controller is created by statisticsPaneView.
     * It won't use the username of the student that is currently watching the video and it will use default
     * width and height to create the video panel.
     * @param video
     */
    public VideoBoxModel (Video video){ //
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

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
