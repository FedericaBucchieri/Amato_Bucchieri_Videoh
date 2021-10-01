package ProfessorHomePage;

import EventManagement.*;
import entities.Video;
import services.VideoService;

import java.util.ArrayList;
import java.util.List;

public class VideoListElementModel {
    private Video video;

    public VideoListElementModel(Video video) {
        this.video = video;
    }

    public void deleteVideo(){
        VideoService videoService = new VideoService();
        videoService.deleteVideo(video.getId());
    }

    /*
    TODO Non va qui
    public void updateVideo(String title, String description, String preview){
        VideoService videoService = new VideoService();

        try {
            videoService.updateVideo(video, title, description, preview);
            dispatchUpdateVideoEvent(video);
        } catch (UpdateVideoException e) {
            dispatchErrorEvent(e.getMessage());
        }

    }

     */


}
