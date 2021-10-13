package ProfessorHomePage;

import entities.Video;
import exceptions.UpdateVideoException;
import services.VideoService;

import java.io.File;

public class ModifyVideoFormModel {
    private Video video;

    public ModifyVideoFormModel(Video video) {
        this.video = video;
    }

    public void modifyVideo(String title, String description, File previewImage, File videoFile) throws UpdateVideoException {
        VideoService service = new VideoService();
        service.updateVideo(this.video, title, description, previewImage, videoFile);
    }

    public Video getVideo() {
        return video;
    }
}
