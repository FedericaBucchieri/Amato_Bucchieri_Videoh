package Dialogues;

import entities.Video;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;

public class HelpDialogue extends JDialog {
    private JPanel mainPanel;

    public HelpDialogue() {
        setupMainPanel();

        add(mainPanel);
        setPreferredSize(new Dimension(Utils.INFO_DIALOG_WIDTH, Utils.INFO_DIALOG_HEIGHT));
        setMinimumSize(getPreferredSize());
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));

        setupTitle();
        setupGuide();
    }

    private void setupTitle() {
        JLabel title = new JLabel("VIDEOH - User guide");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
    }

    private void setupGuide() {
        JTextArea guide = new JTextArea(Utils.GUIDE_TEXT);
        guide.setWrapStyleWord(true);
        guide.setLineWrap(true);
        guide.setForeground(Color.white);
        guide.setBackground(Color.decode("#42577F"));
        mainPanel.add(guide);
    }
}
