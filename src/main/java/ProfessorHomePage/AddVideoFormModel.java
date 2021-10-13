package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;
import Utils.Utils;

import java.io.File;

public class AddVideoFormModel {
    private AddVideoForm controller;
    private Professor professor;

    public AddVideoFormModel(AddVideoForm controller, Professor professor) {
        this.controller = controller;
        this.professor = professor;
    }

    public Video createNewVideo(String title, String description, File previewImage, File file){
        if(title.equals("")){
            controller.reportError(Utils.EMPTY_TITLE_ERROR);
            return null;
        }
        else if(description.equals("")){
            controller.reportError(Utils.EMPTY_DESCRIPTION_ERROR);
            return null;
        }
        else if(previewImage == null){
            controller.reportError(Utils.EMPTY_IMAGE_ERROR);
            return null;
        }
        else if(file == null){
            controller.reportError(Utils.EMPTY_VIDEO_ERROR);
            return null;
        }

        VideoService service = new VideoService();
        Video video = service.createVideo(title, description, previewImage, file, professor);
        return video;
    }



}
