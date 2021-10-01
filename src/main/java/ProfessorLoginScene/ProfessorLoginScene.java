package ProfessorLoginScene;

import sceneManager.Scene;
import sceneManager.SceneManager;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;

public class ProfessorLoginScene implements Scene {
    private ProfessorLoginForm professorLoginForm;


    public ProfessorLoginScene(SceneManager sceneManager) {
        professorLoginForm = new ProfessorLoginForm(sceneManager);
    }

    public JPanel getMainPanel(){
        return professorLoginForm.getMainPanel();
    }


}
