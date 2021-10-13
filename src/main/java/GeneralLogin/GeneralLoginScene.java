package GeneralLogin;

import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GeneralLoginScene implements Scene {
    // Main panel of the Scene
    private MainLoginPanel mainLoginPanel;

    /**
     * This constructor creates the class passing the SceneManager to the instance of mainLoginPanel attribute
     * @param sceneManager the general SceneManager instance
     */
    public GeneralLoginScene(SceneManager sceneManager) {
        mainLoginPanel = new MainLoginPanel(sceneManager);
    }

    /**
     * This method gets the main JPanel of the scene
     * @return the main JPanel of the MainLoginPanel component
     */
    public JPanel getMainPanel(){
        return mainLoginPanel.getMainPanel();
    }

}
