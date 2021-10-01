package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;
import uk.co.caprica.vlcj.player.media.Media;

import java.io.File;

public class AddVideoFormModel {
    Professor professor;

    public AddVideoFormModel(Professor professor) {
        this.professor = professor;
    }

    public Video createNewVideo(String title, String description, String previewImage, File file){
        VideoService service = new VideoService();
        Video video = service.createVideo(title, description, previewImage, file, professor);
        return video;
    }

}
