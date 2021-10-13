package Dialogues;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;

// This class implement a dialog used to open the Application guide after a help request
public class HelpDialogue extends JDialog {
    // The main panel of the scene
    private JPanel mainPanel;

    /**
     * This constructor creates an instance of HelpDialogue, adding all the UI element required to the mainPanel and setting the dialog dimensions
     */
    public HelpDialogue() {
        setupMainPanel();

        add(mainPanel);
        setPreferredSize(new Dimension(Utils.INFO_DIALOG_WIDTH, Utils.INFO_DIALOG_HEIGHT));
        setMinimumSize(getPreferredSize());
    }

    /**
     * This method sets up the main panel and all the required element of the panel: a title and a guide body text
     */
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));

        setupTitle();
        setupGuide();
    }

    /**
     * This method adds a title to the mainPanel
     */
    private void setupTitle() {
        JLabel title = new JLabel("VIDEOH - User guide");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
    }

    /**
     * This method adds a textArea displaying the application guide text
     */
    private void setupGuide() {
        JTextArea guide = new JTextArea(Utils.GUIDE_TEXT);
        guide.setWrapStyleWord(true);
        guide.setLineWrap(true);
        guide.setForeground(Color.white);
        guide.setBackground(Color.decode("#42577F"));
        mainPanel.add(guide);
    }
}
