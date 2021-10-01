package StudentHomePage;

import uk.co.caprica.vlcj.player.media.Media;

import java.io.File;

public class VideoBoxModel {
    private VideoBox controller;
    private int width = 1200;
    private int height = 800;
    private File file;

    public VideoBoxModel (File file){
        this.file = file;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public File getFile() {
        return file;
    }
}
