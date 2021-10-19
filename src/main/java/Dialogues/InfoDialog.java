package Dialogues;

import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;

// This class implement a dialog used to display the video information and details
public class InfoDialog extends JDialog {
    // The video to display the info about
    private Video video;
    // the main panel of the scene
    private JPanel mainPanel;

    /**
     * This constructor creates an instance of InfoDialog adding the relevant element to the UI and setting the dialog dimensions
     * @param video The video to display the info about
     */
    public InfoDialog(Video video) {
        this.video = video;
        setupMainPanel();

        add(mainPanel);
        setPreferredSize(new Dimension(Utils.INFO_DIALOG_WIDTH, Utils.INFO_DIALOG_HEIGHT));
        setMinimumSize(getPreferredSize());
    }

    /**
     * This method sets up the main panel and all the required element of the panel
     */
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));
        mainPanel.setPreferredSize(new Dimension(Utils.INFO_DIALOG_WIDTH - Utils.STANDARD_BORDER, Utils.INFO_DIALOG_HEIGHT - Utils.STANDARD_BORDER));

        setupTitle();
        setupDescription();
        setupCode();
    }

    /**
     * This method adds the video title to the mainPanel
     */
    private void setupTitle() {
        JLabel title = new JLabel(video.getTitle());
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setForeground(Color.white);

        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(Utils.VERTICAL_RIGID_AREA_DIM15));
    }


    /**
     * This method adds the video description to the mainPanel
     */
    private void setupDescription() {
        JTextArea description = new JTextArea(video.getDescription());
        description.setWrapStyleWord(true);
        description.setLineWrap(true);
        description.setForeground(Color.white);
        description.setBackground(Color.decode("#42577F"));
        description.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(description);
    }

    /**
     * This method adds the video code to the main panel
     */
    private void setupCode() {
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        JLabel code = new JLabel("Video Code: " + video.getCode());
        code.setForeground(Color.white);
        code.setAlignmentX(Component.LEFT_ALIGNMENT);
        mainPanel.add(code);
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Utils.INFO_DIALOG_WIDTH, Utils.INFO_DIALOG_HEIGHT);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return getPreferredSize();
    }
}
