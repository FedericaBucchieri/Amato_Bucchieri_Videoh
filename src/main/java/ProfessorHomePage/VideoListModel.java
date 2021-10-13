package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;

import java.util.List;

public class VideoListModel {
    private Professor professor;
    private List<Video> videoList;

    public VideoListModel(Professor professor) {
        this.professor = professor;
    }

    public List<Video> getVideoList() {
        VideoService service = new VideoService();
        videoList = service.findVideoListByProfessor(professor.getId());
        return videoList;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void removeVideo(Video video){
        videoList.removeIf(v -> v.getId() == video.getId());
        professor.setVideoList(videoList);
    }

}
