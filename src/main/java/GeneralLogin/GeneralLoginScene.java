package GeneralLogin;

import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GeneralLoginScene implements Scene {
    private MainLoginPanel mainLoginPanel; //costituirà il main panel

    public GeneralLoginScene(SceneManager sceneManager) {
        mainLoginPanel = new MainLoginPanel(sceneManager);
    }

    public JPanel getMainPanel(){
        return mainLoginPanel.getMainPanel();
    }

}
