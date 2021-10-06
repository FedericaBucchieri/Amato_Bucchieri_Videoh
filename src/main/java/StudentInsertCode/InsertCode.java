package StudentInsertCode;

import EventManagement.BackEvent;
import EventManagement.GoToVideoEvent;
import EventManagement.Listener;
import entities.Video;
import sceneManager.SceneManager;
import uk.co.caprica.vlcj.player.media.Media;
import uk.co.caprica.vlcj.player.media.callback.seekable.RandomAccessFileMedia;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InsertCode extends JComponent {
    private InsertCodeModel model;
    private InsertCodeUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public InsertCode(SceneManager sceneManager, String studentUsername) {
        this.listeners.add(sceneManager);

        this.model = new InsertCodeModel(this, studentUsername);
        this.ui = new InsertCodeUI(this);
        this.ui.installUI();
    }

    public JPanel getMainPanel() {
        return ui.getMainPanel();
    }

    public void goBackToGeneralLogin() {
        for (Listener listener : listeners)
            listener.listen(new BackEvent());
    }

    public String getStudentUsername() {
        return model.getStudentUsername();
    }


    public void goToStudentHomePage(String videoCode, String username) {
        Video video = model.searchVideoByCode(Integer.parseInt(videoCode));

        if(video == null)
            ui.displayError();
        else {
            for (Listener listener : listeners) {
                listener.listen(new GoToVideoEvent(video, username));
            }
        }

    }
}
