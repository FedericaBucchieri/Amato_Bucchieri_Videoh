package ProfessorHomePage;

import entities.Professor;
import sceneManager.Scene;

import javax.swing.*;

public class UpdateProfileScene implements Scene {
    private UpdateProfile updateProfile;

    public UpdateProfileScene (ProfessorHomePage professorHomePage, Professor professor) {
        updateProfile = new UpdateProfile(professorHomePage, professor);
    }

    public JPanel getMainPanel(){
        return updateProfile.getMainPanel();
    }
}
