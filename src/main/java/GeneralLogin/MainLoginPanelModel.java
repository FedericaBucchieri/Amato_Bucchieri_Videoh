package GeneralLogin;

import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;

public class MainLoginPanelModel { //model
    MainLoginPanel controller;
    private ImageIcon studIcon;
    private ImageIcon profIcon;


    public MainLoginPanelModel(MainLoginPanel controller){
        this.controller = controller;

        studIcon = new ImageIcon(new ImageIcon("src/main/images/student.png").getImage().getScaledInstance(Utils.SMALL_LOGO_SIZE, Utils.SMALL_LOGO_SIZE, Image.SCALE_SMOOTH));
        profIcon = new ImageIcon(new ImageIcon("src/main/images/professor.png").getImage().getScaledInstance(Utils.SMALL_LOGO_SIZE, Utils.SMALL_LOGO_SIZE, Image.SCALE_SMOOTH));
    }

    public ImageIcon getStudIcon() {
        return studIcon;
    }

    public ImageIcon getProfIcon() {
        return profIcon;
    }

}
