package StudentInsertCode;

import entities.Video;
import services.VideoService;

public class InsertCodeModel {
    private InsertCode controller;
    private String studentUsername;

    public InsertCodeModel(InsertCode controller, String studentUsername) {
        this.controller = controller;
        this.studentUsername = studentUsername;
    }

    public Video searchVideoByCode(int code){
        VideoService videoService = new VideoService();
        return videoService.findVideoByCode(code);


    }

    public String getStudentUsername() {
        return studentUsername;
    }
}
