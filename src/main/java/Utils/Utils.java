package Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Utils {
    public static final int JFRAME_WIDTH = 1200;
    public static final int JFRAME_HEIGHT = 850;
    public static final int VIDEOLIST_WIDTH = 900;
    public static final int VIDEOLIST_HEIGHT = 750;
    public static final int DETAILPANEL_WIDTH = 250;
    public static final int DETAILPANEL_HEIGHT = 700;
    public static final int VIDEO_CODE_BOUND = 999999;
    public static final int TITLE_WIDTH = 36;
    public static final int DATE_FONT_WIDTH = 10;
    public static final int TITLE_MARGIN= 80;
    public static final int SUBTITLE_WIDTH = 24;
    public static final int BUTTON_LABEL_WIDTH = 16;
    public static final int SUBBUTTON_LABEL_WIDTH = 14;
    public static final int LOGOUT_BUTTON_WIDTH = 12;
    public static final int STANDARD_BORDER = 20;
    public static final int STANDARD_TEXT_FIELD_WIDTH = 300;
    public static final int STANDARD_SMALL_TEXT_FIELD_WIDTH = 300;
    public static final int STANDARD_TEXTAREA_FIELD_HEIGHT = 400;
    public static final int STANDARD_TEXT_FIELD_HEIGHT = 40;
    public static final int BIG_LOGO_SIZE = 170;
    public static final int SMALL_LOGO_SIZE = 100;
    public static final int DIVIDER = 200;
    public static final int DESCRIPTION_WIDTH = 300;
    public static final int DESCRIPTION_HEIGHT = 150;
    public static final int PLAY_PAUSE_SIZE = 30;
    public static final int ANNOTATION_PANEL_HEIGHT = 50;
    public static final int ANNOTATION_BUTTON_SIZE = 50;
    public final static int NEGATIVE_INTERACTION = 0;
    public final static int POSITIVE_INTERACTION = 1;
    public final static int TIMELINE_BOXES = 82;
    public final static int TAG_SIZE = 30;
    public final static int QUESTION_LIST_HEIGHT = 250;
    public final static int INFO_DIALOG_WIDTH = 600;
    public final static int INFO_DIALOG_HEIGHT = 400;
    public final static int STUDENT_VIDEO_FRAME_WIDTH = 960;
    public final static int STUDENT_VIDEO_FRAME_HEIGHT = 540;
    public final static String LINE_1_INTERACTION = "Click on the buttons to interact with the video";
    public final static String LINE_2_INTERACTION = "You can drag & drop every interaction to move it in time";
    public final static String LINE_1_DELETING = "Click on the interactions to delete them";
    public final static String LINE_2_DELETING = "When you finish deleting all the desired interaction, click on the red button to go back to view mode";
    public final static String WRITE_QUESTION_TEXT = "Write your question here";
    public static final String ERROR_EMPTY_LIST = "Your list is empty! Please add a new video to start your VIDEOH experience";
    public static final String ERROR_UPDATE_VIDEO = "Sorry, an error occurred while updating your video details";
    public static final String USERNAME_ERROR = "Please, type a valid username";
    public static final String CODE_ERROR = "Please, type a valid numeric code";
    public static final String EMPTY_TITLE_ERROR = "Please, insert a valid title";
    public static final String EMPTY_DESCRIPTION_ERROR = "Please, insert a valid description";
    public static final String EMPTY_IMAGE_ERROR = "Please, type a valid image file for the preview image";
    public static final String EMPTY_VIDEO_ERROR = "Please, type a valid video file";
    public static final String UPDATE_VIDEO_ERROR = "Sorry, an error occured while updating the video";
    public static final String GUIDE_TEXT = "Prova di guida";

    public static final int STATS_LABEL_DIM = 25;
    public static final Dimension VERTICAL_RIGID_AREA_DIM10 = new Dimension(0, 10);
    public static final Dimension VERTICAL_RIGID_AREA_DIM15 = new Dimension(0, 15);
    public static final Dimension VERTICAL_RIGID_AREA_DIM30 = new Dimension(0, 30);
    public static final Dimension VERTICAL_RIGID_AREA_DIM100 = new Dimension(0, 100);

    public static final Dimension HORIZONTAL_RIGID_AREA_DIM30 = new Dimension(30,0);
    public static final int UNIT_SCROLLING_INCREMENT = 3;


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

    public static JButton setUPBackButton(){
        Icon icon = new ImageIcon("src/main/images/back-2.png");
        JButton backButton = new JButton(icon);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorderPainted(false);

        return backButton;
    }

    public static String formatTime(long value) {
        value /= 1000;
        int hours = (int) value / 3600;
        int remainder = (int) value - hours * 3600;
        int minutes = remainder / 60;
        remainder = remainder - minutes * 60;
        int seconds = remainder;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
