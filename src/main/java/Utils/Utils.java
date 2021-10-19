package Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Utils {
    public static final int JFRAME_WIDTH = 1200;
    public static final int JFRAME_HEIGHT = 850;
    public final static int JFRAME_LOCATION_X = 300;
    public final static int JFRAME_LOCATION_Y = 100;
    public static final int VIDEOLIST_WIDTH = 900;
    public static final int VIDEOLIST_HEIGHT = 750;
    public static final int DETAILPANEL_WIDTH = 250;
    public static final int DETAILPANEL_HEIGHT = 700;
    public static final int QUESTIONPANEL_WIDTH = 250;
    public static final int QUESTIONPANEL_HEIGHT = 50;
    public static final int VIDEO_CODE_BOUND = 999999;
    public static final int TITLE_WIDTH = 36;
    public static final int DATE_FONT_WIDTH = 10;
    public static final int TITLE_MARGIN= 80;
    public static final int SUBTITLE_WIDTH = 24;
    public static final int BUTTON_LABEL_WIDTH = 16;
    public static final int SUBBUTTON_LABEL_WIDTH = 14;
    public static final int LOGOUT_BUTTON_WIDTH = 12;
    public static final int STANDARD_BORDER = 20;
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
    public final static int INFO_DIALOG_WIDTH = 600;
    public final static int INFO_DIALOG_HEIGHT = 600;
    public final static int STUDENT_VIDEO_FRAME_WIDTH = 960;
    public final static int STUDENT_VIDEO_FRAME_HEIGHT = 540;
    public final static int BACK_BUTTON_DIMENSIONS = 64;
    public final static int VIDEO_PREVIEW_WIDTH = 200;
    public final static int VIDEO_PREVIEW_HEIGHT = 115;
    public final static int QUESTIONBODY_WIDTH = 700;
    public final static int QUESTIONBODY_HEIGHT = 100;
    public final static String INSERT_USERNAME = "Insert an username";
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
    public static final String UPDATE_VIDEO_ERROR = "Sorry, an error occurred while updating the video";
    public static final String INVALID_USERNAME ="Please insert a valid username";
    public static final String INVALID_PASSWORD ="Please insert a valid password";
    public static final String GUIDE_TEXT = "VIDEOH is a video player application with educational purposes. \n\nVIDEOH allows you to enter the application both as a professor or as a student. Professors have a personal account and need to log in with username and password. Students instead can enter the application with a nickname in an anonymous way. \n\n" +
            "If you are a student, don't forget to ask your professor to give you the video code to access the required lesson, otherwise it would not be possibile to show you any video. Once you select the desired video, you can then begin to use VIDEOH. You'll have three buttons to leave interactions and pose questions to the professor. You don't need to save anything, VIDEOH is going to do that for you automatically." +
            "\n\nIf you are a professor, you need your username and password to access your homepage. From there you can manage all your videos, upload new ones and see all the statistics of your videos.";

    public static final int STATS_LABEL_DIM = 25;
    public static final Dimension VERTICAL_RIGID_AREA_DIM10 = new Dimension(0, 10);
    public static final Dimension VERTICAL_RIGID_AREA_DIM15 = new Dimension(0, 15);
    public static final Dimension VERTICAL_RIGID_AREA_DIM30 = new Dimension(0, 30);
    public static final Dimension VERTICAL_RIGID_AREA_DIM100 = new Dimension(0, 100);
    public static final Dimension VERTICAL_RIGID_AREA_DIM80 = new Dimension(0, 80);

    public static final Dimension HORIZONTAL_RIGID_AREA_DIM30 = new Dimension(30,0);
    public static final Dimension HORIZONTAL_RIGID_AREA_DIM15 = new Dimension(15,0);
    public static final Dimension HORIZONTAL_RIGID_AREA_DIM10 = new Dimension(10,0);

    public static final Color CUSTOM_GREEN = Color.decode("#32a852");
    public static final Color CUSTOM_BLUE = Color.decode("#314668");
    public static final Color CUSTOM_BLUE2 = Color.decode("#223047");
    public static final Color CUSTOM_BLUE3 = Color.decode("#42577F");
    public static final Color CUSTOM_VIOLET = Color.decode("#b8234b");
    public static final Color CUSTOM_VIOLET2 = Color.decode("#DB2A58");
    public static final Color CUSTOM_GREY = Color.decode("#999999");
    public static final Color CUSTOM_GREY2 = Color.decode("#807e7e");
    public static final Color CUSTOM_ORANGE = Color.decode("#F0A037");

    public static final int UNIT_SCROLLING_INCREMENT = 3;


    /**
     * This method gives a style to a button: style one that is the blue button style
     * @param button the button to be styled
     * @return the button after styling
     */
    public JButton styleButtonOne(JButton button){
        button.setBackground(CUSTOM_BLUE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, BUTTON_LABEL_WIDTH)));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(CUSTOM_BLUE2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(CUSTOM_BLUE2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CUSTOM_BLUE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(CUSTOM_BLUE);
            }
        });

        return button;
    }

    /**
     * This method gives a style to a button: style two that is the violet button style
     * @param button the button to be styled
     * @return the button after styling
     */
    public JButton styleButtonTwo(JButton button){
        button.setBackground(CUSTOM_VIOLET2);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, SUBBUTTON_LABEL_WIDTH)));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(CUSTOM_VIOLET);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(CUSTOM_VIOLET);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CUSTOM_VIOLET2);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(CUSTOM_VIOLET2);
            }
        });

        return button;
    }

    /**
     * This method gives a style to a button: style three that is the grey button style
     * @param button the button to be styled
     * @return the button after styling
     */
    public JButton styleButtonThree(JButton button){
        button.setBackground(CUSTOM_GREY);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setForeground(Color.white);
        button.setFont((new Font(Font.SANS_SERIF,  Font.BOLD, LOGOUT_BUTTON_WIDTH)));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(CUSTOM_GREY2);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(CUSTOM_GREY2);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(CUSTOM_GREY);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(CUSTOM_GREY);
            }
        });

        return button;
    }

    /**
     * This button sets up a backButton style
     * @return the button after styling
     */
    public static JButton setUPBackButton(){
        ImageIcon imageIcon = new ImageIcon("src/main/images/back-2.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(BACK_BUTTON_DIMENSIONS, BACK_BUTTON_DIMENSIONS,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);

        JButton backButton = new JButton(imageIcon);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorderPainted(false);

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon("src/main/images/back.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(BACK_BUTTON_DIMENSIONS, BACK_BUTTON_DIMENSIONS,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);

                backButton.setIcon(imageIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon("src/main/images/back.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(BACK_BUTTON_DIMENSIONS, BACK_BUTTON_DIMENSIONS,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);

                backButton.setIcon(imageIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon("src/main/images/back-2.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(BACK_BUTTON_DIMENSIONS, BACK_BUTTON_DIMENSIONS,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);

                backButton.setIcon(imageIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon imageIcon = new ImageIcon("src/main/images/back-2.png"); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newimg = image.getScaledInstance(BACK_BUTTON_DIMENSIONS, BACK_BUTTON_DIMENSIONS,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newimg);

                backButton.setIcon(imageIcon);
            }
        });

        return backButton;
    }

    /**
     * This method translates a long value into a printable String
     * @param value the value to be formatted
     * @return the String containing the value
     */
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
