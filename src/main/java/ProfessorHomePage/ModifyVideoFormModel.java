package ProfessorHomePage;

import entities.Video;
import exceptions.UpdateVideoException;
import services.VideoService;

import java.io.File;

public class ModifyVideoFormModel {
    //The video to modify
    private Video video;

    /**
     * Creates a new model for the ModifyVideoForm component
     * @param video the video to modify
     */
    public ModifyVideoFormModel(Video video) {
        this.video = video;
    }

    /**
     * Updates the given parameters to the video entity stored within the model
     * @param title the new title of the video
     * @param description the new description of the video
     * @param previewImage the new preview image of the video
     * @param videoFile the new file of the video
     * @throws UpdateVideoException if the updateVideo method of the videoService failde to update the video.
     */
    public void modifyVideo(String title, String description, File previewImage, File videoFile) throws UpdateVideoException {
        VideoService service = new VideoService();
        service.updateVideo(this.video, title, description, previewImage, videoFile);
    }

    public Video getVideo() {
        return video;
    }
}
