package StudentHomePage;

import EventManagement.BackEvent;
import EventManagement.Listener;
import sceneManager.SceneManager;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoPlayerArea extends JComponent { //controler
    private VideoPlayerAreaModel model;
    private VideoPlayerAreaUI UI;
    private List<Listener> listeners = new ArrayList<>();

    public VideoPlayerArea(SceneManager sceneManager, File file, String username) {
        this.listeners.add(sceneManager);
        model = new VideoPlayerAreaModel(this, file, username);
        UI = new VideoPlayerAreaUI(this);
    }


    public VideoPlayerAreaUI getView () {
            return UI;
    }

    public JPanel getMainPanel () {
            return UI.getMainPanel();
        }

    public void goToStudentInsertCode() {
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    public void dismissVideo(){
        UI.dismissVideo();
    }

    public VideoPlayerAreaModel getModel() {
        return model;
    }
}
