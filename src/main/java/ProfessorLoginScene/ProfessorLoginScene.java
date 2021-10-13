package ProfessorLoginScene;

import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;

public class ProfessorLoginScene implements Scene {
    // The main component of the scene
    private ProfessorLoginForm professorLoginForm;

    /**
     * This constructor creates the class passing the SceneManager to the instance of professorLoginForm attribute
     * @param sceneManager the general SceneManager instance
     */
    public ProfessorLoginScene(SceneManager sceneManager) {
        professorLoginForm = new ProfessorLoginForm(sceneManager);
    }

    /**
     * This method gets the main JPanel of the scene
     * @return the main JPanel of the ProfessorLoginForm component
     */
    public JPanel getMainPanel(){
        return professorLoginForm.getMainPanel();
    }

}
