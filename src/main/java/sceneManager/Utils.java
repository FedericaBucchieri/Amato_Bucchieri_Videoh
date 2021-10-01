package sceneManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Utils {
    public static final int JFRAME_WIDTH = 1150;
    public static final int JFRAME_HEIGHT = 750;
    public static final int VIDEOLIST_WIDTH = 900;
    public static final int VIDEOLIST_HEIGHT = 750;
    public static final int DETAILPANEL_WIDTH = 250;
    public static final int DETAILPANEL_HEIGHT = 750;
    public static final int VIDEO_CODE_BOUND = 999999;
    public static final int TITLE_WIDTH = 36;
    public static final int TITLE_MARGIN= 80;
    public static final int SUBTITLE_WIDTH = 24;
    public static final int BUTTON_LABEL_WIDTH = 16;
    public static final int SUBBUTTON_LABEL_WIDTH = 14;
    public static final int LOGOUT_BUTTON_WIDTH = 12;
    public static final int STANDARD_BORDER = 20;
    public static final int STANDARD_TEXT_FIELD_WIDTH = 300;
    public static final int STANDARD_SMALL_TEXT_FIELD_WIDTH = 300;
    public static final int STANDARD_TEXT_FIELD_HEIGHT = 40;
    public static final int BIG_LOGO_SIZE = 170;
    public static final int SMALL_LOGO_SIZE = 100;
    public static final int DIVIDER = 200;
    public static final int DESCRIPTION_WIDTH = 300;
    public static final int DESCRIPTION_HEIGHT = 150;
    public static final String ERROR_EMPTY_LIST = "Your list is empty! Please add a new video to start your VIDEOH experience";
    public static final String ERROR_UPDATE_VIDEO = "Sorry, an error occured while updating your video details";


    public JButton styleButtonOne(JButton button){
        button.setBackground(Color.decode("#314668"));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, BUTTON_LABEL_WIDTH)));
        return button;
    }

    public JButton styleButtonTwo(JButton button){
        button.setBackground(Color.decode("#DB2A58"));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, SUBBUTTON_LABEL_WIDTH)));
        return button;
    }

    public JButton styleButtonThree(JButton button){
        button.setBackground(Color.decode("#999999"));
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, LOGOUT_BUTTON_WIDTH)));
        return button;
    }

    public JButton setUPBackButton(){
        Icon icon = new ImageIcon("src/main/images/back-2.png");
        JButton backButton = new JButton(icon);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorderPainted(false);

        return backButton;
    }
}
