package GeneralLogin;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

public class MainLoginPanelModel {
    // the Component controller
    private MainLoginPanel controller;
    // An icon to represent the students
    private ImageIcon studIcon;
    // An icon to represent the professors
    private ImageIcon profIcon;


    /**
     * This constructor creates an instance of MainLoginPanelModel, creating two icons to display in the MainLoginPanel view.
     * @param controller the MainLoginPanel instance that represents the controller of the component
     */
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
