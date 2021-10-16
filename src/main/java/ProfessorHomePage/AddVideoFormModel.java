package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;
import Utils.Utils;

import java.io.File;

public class AddVideoFormModel {
    //The AddVideoForm component
    private AddVideoForm controller;
    //The logged professor
    private Professor professor;

    /**
     * Creates the model of the AddVideoForm component
     * @param controller the AddVideoForm component
     * @param professor the logged professor
     */
    public AddVideoFormModel(AddVideoForm controller, Professor professor) {
        this.controller = controller;
        this.professor = professor;
    }

    /**
     * Creates a new video in the database, checking that the inserted elements are not null
     * @param title the title of the video to add
     * @param description the description of the video to add
     * @param previewImage the thumbnail of the video to add
     * @param file the actual file of the video to add
     * @return null if the video has some empty parameter (in this case it shows some error labels), the new video
     * if all the parameters are set up correctly.
     */
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
