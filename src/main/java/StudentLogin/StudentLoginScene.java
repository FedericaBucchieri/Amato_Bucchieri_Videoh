package StudentLogin;

import GeneralLogin.MainLoginPanel;
import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;

public class StudentLoginScene implements Scene {
    private StudentLoginPanel studentLoginPanel;

    public StudentLoginScene (SceneManager sceneManager) {
        studentLoginPanel = new StudentLoginPanel(sceneManager);
    }

    public JPanel getMainPanel(){
        return studentLoginPanel.getMainPanel();
    }

}
