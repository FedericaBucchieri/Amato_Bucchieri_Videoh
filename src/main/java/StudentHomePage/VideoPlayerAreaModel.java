package StudentHomePage;

import java.io.File;

public class VideoPlayerAreaModel {
    private VideoPlayerArea controller;
    private File file;
    private String username;

    public VideoPlayerAreaModel(VideoPlayerArea controller, File file, String username){
        this.controller = controller;
        this.file = file;
        this.username = username;
    }


    public File getFile() {
        return file;
    }


}
