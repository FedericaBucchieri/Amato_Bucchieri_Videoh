package StudentInsertCode;


import sceneManager.Scene;
import sceneManager.SceneManager;

import javax.swing.*;

public class InsertCodeScene implements Scene {
    private InsertCode insertCode;

    public InsertCodeScene (SceneManager sceneManager, String username) {
        insertCode = new InsertCode(sceneManager, username);
    }

    public JPanel getMainPanel(){
        return insertCode.getMainPanel();
    }
}
