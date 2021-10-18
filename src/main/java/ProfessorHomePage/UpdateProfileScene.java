package ProfessorHomePage;

import entities.Professor;
import sceneManager.Scene;

import javax.swing.*;

public class UpdateProfileScene implements Scene {
    //The UpdateProfile component
    private UpdateProfile updateProfile;

    //TODO: ma lo usiamo mai?
    public UpdateProfileScene(ProfessorHomePageScene professorHomePageScene, Professor professor) {
        updateProfile = new UpdateProfile(professorHomePageScene, professor);
    }

    public JPanel getMainPanel(){
        return updateProfile.getMainPanel();
    }
}
