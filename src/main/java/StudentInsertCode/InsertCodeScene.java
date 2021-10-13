package StudentInsertCode;


import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;

public class InsertCodeScene implements Scene {
    // The main component of the scene
    private InsertCodeForm insertCodeForm;

    /**
     * This constructor creates an instance of InsertCodeScene passing the SceneManager to the instance of insertCodeForm attribute
     * @param sceneManager the general SceneManager instance
     * @param username the logged in student username String
     */
    public InsertCodeScene (SceneManager sceneManager, String username) {
        insertCodeForm = new InsertCodeForm(sceneManager, username);
    }

    /**
     * This method gets the main JPanel of the scene
     * @return the main JPanel of the InsertCodeForm component
     */
    public JPanel getMainPanel(){
        return insertCodeForm.getMainPanel();
    }
}
