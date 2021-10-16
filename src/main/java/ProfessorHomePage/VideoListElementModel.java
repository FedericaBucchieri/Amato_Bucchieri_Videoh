package ProfessorHomePage;

import entities.Video;
import services.VideoService;


public class VideoListElementModel {

    private Video video;

    /**
     * Creates the model for the VideoListElement
     * @param video the video of the VideoListElement
     */
    public VideoListElementModel(Video video) {
        this.video = video;
    }


    public Video getVideo() {
        return video;
    }

    /**
     * Delete the video from the database
     */
    public void deleteVideo(){
        VideoService videoService = new VideoService();
        videoService.deleteVideo(video.getId());
        System.out.println("delete");
    }
}
