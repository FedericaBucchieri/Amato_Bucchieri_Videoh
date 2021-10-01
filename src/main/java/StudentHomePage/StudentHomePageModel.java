package StudentHomePage;

import uk.co.caprica.vlcj.player.media.Media;

import java.io.File;

public class StudentHomePageModel {
    private StudentHomePage controller;
    private File file;
    private String username;

    public StudentHomePageModel (StudentHomePage controller, File file, String username){
        this.controller = controller;
        this.file = file;
        this.username = username;
    }


    public File getFile() {
        return file;
    }


}
