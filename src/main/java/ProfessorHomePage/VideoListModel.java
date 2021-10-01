package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;

import java.util.List;

public class VideoListModel {
    private Professor professor;

    public VideoListModel(Professor professor) {
        this.professor = professor;
    }


    public List<Video> getVideoList() {
        VideoService service = new VideoService();
        //return service.findVideosByProfessor(professor.getId());
        return service.findVideoListByProfessor(professor.getId());
    }

    public Professor getProfessor() {
        return professor;
    }

    public void removeVideo(Video video){
        int videoId = video.getId();
        List<Video> videoList = professor.getVideoList();

        videoList.removeIf(v -> v.getId() == videoId);

    }

}
