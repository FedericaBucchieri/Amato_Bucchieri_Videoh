package StudentInsertCode;

import entities.Video;
import services.VideoService;

public class InsertCodeFormModel {
    // The component controller
    private InsertCodeForm controller;
    // The username of the logged in student
    private String studentUsername;

    public InsertCodeFormModel(InsertCodeForm controller, String studentUsername) {
        this.controller = controller;
        this.studentUsername = studentUsername;
    }

    /**
     * This method seaches in the database for a Video instance corresponding to the videoCode
     * @param code the code of the video to search for
     * @return the Video instance if found, null otherwise
     */
    public Video searchVideoByCode(int code){
        VideoService videoService = new VideoService();
        return videoService.findVideoByCode(code);
    }

    public String getStudentUsername() {
        return studentUsername;
    }
}
