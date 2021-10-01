package EventManagement;

import uk.co.caprica.vlcj.player.media.Media;

import java.io.File;

public class GoToVideoEvent implements Event {
    private File file;
    private String username;

    public GoToVideoEvent (File file, String username){
        this.file = file;
        this.username = username;
    }

    public File getFile() {
        return file;
    }


    public String getUsername() {
        return this.username;
    }
}
