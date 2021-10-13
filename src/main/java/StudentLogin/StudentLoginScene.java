package StudentLogin;

import GeneralLogin.MainLoginPanel;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;

public class StudentLoginScene implements Scene {
    // The main component of the scene
    private StudentLoginPanel studentLoginPanel;

    /**
     * This constructor creates an instance of StudentLoginScene passing the SceneManager to the instance of studentLoginPanel attribute
     * @param sceneManager the general SceneManager instance
     */
    public StudentLoginScene (SceneManager sceneManager) {
        studentLoginPanel = new StudentLoginPanel(sceneManager);
    }

    /**
     * This method gets the main JPanel of the scene
     * @return the main JPanel of the StudentLoginPanel component
     */
    public JPanel getMainPanel(){
        return studentLoginPanel.getMainPanel();
    }

}
