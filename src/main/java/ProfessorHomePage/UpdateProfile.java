package ProfessorHomePage;

import EventManagement.*;
import entities.Professor;
import entities.Video;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UpdateProfile extends JComponent {
    private UpdateProfileModel model;
    private UpdateProfileUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public UpdateProfile(ProfessorHomePage professorHomePage, Professor professor) {
        this.listeners.add(professorHomePage);
        this.model = new UpdateProfileModel(this, professor);
        this.ui = new UpdateProfileUI(this);
        this.ui.installUI();
    }

    public void handleUpdateProfileRequest(String username, String password){
        Professor professor = model.updateProfile(username, password);
        dispatchUpdateProfileEvent(professor);
    }

    private void dispatchUpdateProfileEvent(Professor professor){
        for (Listener listener : listeners)
            listener.listen(new UpdateProfileEvent(professor));
    }

    public String getUsername(){
        return model.getUsername();
    }

    public String getPassword(){
        return model.getPassword();
    }

    public void goBackToVideoList(){
        for (Listener listener : listeners)
            listener.listen(new CancelEvent());
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    @Override
    protected void paintComponent(Graphics g) {
        add(this.ui.getMainPanel());
    }


}
