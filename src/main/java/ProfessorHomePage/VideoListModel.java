package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import services.VideoService;

import java.util.List;

public class VideoListModel {
    //The professor logged in
    private Professor professor;
    //the list of video of the logged-in professor
    private List<Video> videoList;

    /**
     * Creates the model for the VideoList
     * @param professor the professor for whiich the videoList has been created
     */
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

    /**
     * Removes the given video from the video list of the professor.
     * @param video
     */
    public void removeVideo(Video video){
        videoList.removeIf(v -> v.getId() == video.getId());
        professor.setVideoList(videoList);
    }

}
