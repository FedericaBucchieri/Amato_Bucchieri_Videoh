package Dialogues;

import entities.Video;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {
    private Video video;
    private JPanel mainPanel;

    public InfoDialog(Video video) {
        this.video = video;
        setupMainPanel();

        add(mainPanel);
        setPreferredSize(new Dimension(Utils.INFO_DIALOG_WIDTH, Utils.INFO_DIALOG_HEIGHT));
        setMinimumSize(getPreferredSize());
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));

        setupTitle();
        setupDescription();
        setupCode();
    }

    private void setupTitle() {
        JLabel title = new JLabel(video.getTitle());
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        title.setForeground(Color.white);
        mainPanel.add(title);
    }


    private void setupDescription() {
        JLabel description = new JLabel(video.getDescription());
        description.setForeground(Color.white);
        mainPanel.add(description);
    }

    private void setupCode() {
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        JLabel code = new JLabel("Video Code: " + video.getCode());
        code.setForeground(Color.white);
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
